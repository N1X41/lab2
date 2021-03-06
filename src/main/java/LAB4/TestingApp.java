package LAB4;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.CompletionStage;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class TestingApp {
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 1969;
    private static final String START_MSG_FORMAT = "Listening on %s:%d\n";
    public final static Logger LOGGER = Logger.getLogger("MyLog");
    private static final String PATH_TO_LOG_FILE = "/home/nick/gitwatch/lab2/logs/log4.log";

    public static void main(String[] args) throws Exception{
        BasicConfigurator.configure();
        FileHandler fh = new FileHandler(PATH_TO_LOG_FILE);
        LOGGER.addHandler(fh);

        ActorSystem system = ActorSystem.create("Testing");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        HttpServer server = new HttpServer(system);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = server.getRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow, ConnectHttp.toHost(IP_ADDRESS, PORT), materializer);
        LOGGER.info(String.format(START_MSG_FORMAT, IP_ADDRESS, PORT));
        System.in.read();
        binding.thenCompose(ServerBinding::unbind).
                thenAccept(unbound -> system.terminate());
    }
}
