package LAB6;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class AnonymRequestApp {
    private final static String PATH_TO_LOG_FILE = "/home/nick/gitwatch/lab2/logs/lab6.log";
    public final static Logger LOGGER = Logger.getLogger("MyLog");

    public static void main(String[] args) throws Exception{
        FileHandler fh = new FileHandler(PATH_TO_LOG_FILE);
        LOGGER.addHandler(fh);

    }
}
