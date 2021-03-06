package LAB5;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.Query;
import akka.japi.Pair;
import akka.pattern.Patterns;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static LAB5.CacheTestingApp.LOGGER;
import static org.asynchttpclient.Dsl.asyncHttpClient;

public class HttpServer {
    private static final String URL_ARG = "testUrl";
    private static final String COUNT_ARG = "count";
    private static final String INFO_MSG_PTR = "%s - %d\n";
    private static final String TIME_RESPONSE_PTR = "Response's Time = %d\n";
    private static final String AVG_RESPONSE_TIME_PTR = "----Average resonse time = %d!----\n";
    private static final int MAP_ASYNC = 1;
    private static final Duration TIMEOUT = Duration.ofSeconds(5);

    public HttpServer() {}

    public static Flow<HttpRequest, HttpResponse, NotUsed> createFlow(Http http, ActorSystem system,
                                                                      ActorMaterializer materializer, ActorRef actor) {
        return Flow.of(HttpRequest.class)
                .map(r -> {
                    Query query = r.getUri().query();
                    String url = query.get(URL_ARG).get();
                    int count = Integer.parseInt(query.get(COUNT_ARG).get());
                    LOGGER.info(String.format(INFO_MSG_PTR, url, count));
                    return new Pair<String, Integer>(url, count);
                })
                .mapAsync(MAP_ASYNC, r -> {
                    CompletionStage<Object> stage = Patterns.ask(actor, new GetMessage(r.first()), TIMEOUT);
                    return stage.thenCompose(res -> {
                        if ((int) res != -1) {
                            return CompletableFuture.completedFuture(new Pair<>(r.first(), ((int) res)));
                        }

                        Flow<Pair<String, Integer>, Integer, NotUsed> flow = Flow.<Pair<String, Integer>>create()
                                .mapConcat(p -> new ArrayList<>(Collections.nCopies(p.second(), p.first())))
                                .mapAsync(r.second(), url -> {
                                    long start = System.currentTimeMillis();
                                    asyncHttpClient().prepareGet(url).execute();
                                    long finish = System.currentTimeMillis();
                                    int time = (int)(finish - start);
                                    LOGGER.info(String.format(TIME_RESPONSE_PTR, time));
                                    return CompletableFuture.completedFuture(time);
                                });
                        return Source.single(r)
                                .via(flow)
                                .toMat(Sink.fold(0, Integer::sum), Keep.right())
                                .run(materializer)
                                .thenApply(sum -> new Pair<>(r.first(), (sum / r.second())));
                    });
                })
                .map(r -> {
                    actor.tell(new StoreMessage(r.first(), r.second()), ActorRef.noSender());
                    LOGGER.info(String.format(AVG_RESPONSE_TIME_PTR, r.second()));
                    return HttpResponse.create().withEntity(r.second().toString() + '\n');
                });
    }
}