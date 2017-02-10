package org.optaplanner.drools.reproducer;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.utils.KieHelper;

public class DroolsReproducerTest {

    public static interface StopOrHub {

        boolean isVisitedByCoach();

        Object getUnrelatedProperty();

        void setUnrelatedProperty(Object unrelatedProperty);
    }

    public static class BusStop implements StopOrHub {

        private boolean visitedByCoach;

        @Override
        public Object getUnrelatedProperty() {
            throw new UnsupportedOperationException("The property is never read.");
        }

        @Override
        public void setUnrelatedProperty(Object transferShuttleList) {
            throw new UnsupportedOperationException("The property is never modified.");
        }

        @Override
        public boolean isVisitedByCoach() {
            return visitedByCoach;
        }

        public BusStop setVisitedByCoach(boolean visitedByCoach) {
            this.visitedByCoach = visitedByCoach;
            return this;
        }

    }

    public static class Shuttle {

        private StopOrHub destination;

        public StopOrHub getDestination() {
            return destination;
        }

        public Shuttle setDestination(StopOrHub destination) {
            this.destination = destination;
            return this;
        }

    }

    @Test
    public void test() {
        String drl = "package org.test;\n"
                + "import " + DroolsReproducerTest.BusStop.class.getCanonicalName() + ";\n"
                + "import " + DroolsReproducerTest.Shuttle.class.getCanonicalName() + ";\n"
                + "import " + DroolsReproducerTest.StopOrHub.class.getCanonicalName() + ";\n"
                + "rule shuttleDestinationIsCoachOrHub\n"
                + "    when\n"
                + "        $destination : StopOrHub(visitedByCoach == false)\n"
                + "        Shuttle(destination == $destination)\n"
                + "    then\n"
                + "end";
        KieSession kieSession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();

        BusStop busStop = new BusStop();
        Shuttle shuttle = new Shuttle();
        busStop.setVisitedByCoach(true);

        FactHandle fhShuttle = kieSession.insert(shuttle);
        FactHandle fhBusStop = kieSession.insert(busStop);

        kieSession.update(fhShuttle, shuttle.setDestination(busStop), "destination");
        Assert.assertEquals(0, kieSession.fireAllRules());

        kieSession.update(fhBusStop, busStop.setVisitedByCoach(false), "visitedByCoach");
        // LHS is now satisfied, the rule should fire but it doesn't
        Assert.assertEquals(busStop, shuttle.getDestination());
        Assert.assertFalse(busStop.isVisitedByCoach());
        Assert.assertEquals(0, kieSession.fireAllRules());

        // after this update (without any real fact modification) the rule will fire as expected
        kieSession.update(fhBusStop, busStop, "unrelatedProperty");
        Assert.assertEquals(1, kieSession.fireAllRules());
    }
}
