package LAB4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class RunActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(RunMessage.class, m -> {
                    TestResults results = new TestResults();
                    results.runTests(m.getTests(), m.getJsScript(), m.getFunctionName(), m.getPackageId());
                })
                .build();
    }
}
