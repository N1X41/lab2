package LAB4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class RouteActor extends AbstractActor {
    @Override
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(RunMessage.class, msg -> )
    }
}
