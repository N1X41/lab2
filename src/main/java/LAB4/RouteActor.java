package LAB4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class RouteActor extends AbstractActor {
    private final static int POOL_SIZE = 2;
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(RunMessage.class, msg -> )
    }
}
