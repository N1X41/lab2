package LAB4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class RouteActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match()
    }
}
