package LAB4;

import akka.actor.AbstractActor;
import akka.http.javadsl.marshallers.jackson.Jackson;

import static akka.http.javadsl.server.Directives.*;

public class RouteActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        route (
                path ( "run", () ->
                        route (
                                post(() ->
                                        entity(Jackson.unmarshaller(JsonFile.class), file ->{
                                        })
                                )
                        )
                )
        );
    }
}
