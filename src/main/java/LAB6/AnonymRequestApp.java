package LAB6;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.stream.ActorMaterializer;

import java.util.concurrent.CompletionStage;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class AnonymRequestApp {
    private final static String PATH_TO_LOG_FILE = "/home/nick/gitwatch/lab2/logs/lab6.log";
    public final static Logger LOGGER = Logger.getLogger("MyLog");

    public static void main(String[] args) throws Exception{
        FileHandler fh = new FileHandler(PATH_TO_LOG_FILE);
        LOGGER.addHandler(fh);

        LOGGER.info("start!");
        ActorSystem system = ActorSystem.create("routes");
        ActorRef actor = system.actorOf(Props.create(RouteActor.class));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(HOST, PORT),
                materializer
        );
    }
}
