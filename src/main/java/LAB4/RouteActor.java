package LAB4;

import akka.actor.AbstractActor;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.japi.pf.ReceiveBuilder;

import static akka.http.javadsl.server.Directives.*;

public class RouteActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
    }
}
