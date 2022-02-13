package LAB6;

import org.apache.zookeeper.ZooKeeper;

import java.time.Duration;

public class ZooKeeperConn {
    private static final String HOST = "localhost:2181";
    private ZooKeeper keeper;

    public ZooKeeperConn() {
    }
}