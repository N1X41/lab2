package LAB5;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
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

    }
}
