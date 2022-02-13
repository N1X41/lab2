package LAB5;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class CasheTestingApp {
    private static final String PATH_TO_LOG_FILE = "/home/nick/gitwatch/lab2/logs/log5.log";
    public final static Logger LOGGER = Logger.getLogger("MyLog");

    public static void main(String[] args) throws Exception {
        FileHandler fh = new FileHandler(PATH_TO_LOG_FILE);
        LOGGER.addHandler(fh);

        LOGGER.info("start!");
        ActorSystem system = ActorSystem.create("routes");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
    }
}
