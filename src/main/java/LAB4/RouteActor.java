package LAB4;

import akka.actor.AbstractActor;

import static akka.http.javadsl.server.Directives.route;

public class RouteActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return null;
    }

    public void createRoute() {
        route (
                "execute", () -> {

                }
        );
    }
}