package org.optaplanner.drools.reproducer;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

public class DroolsReproducerTest {

    public static interface StopOrHub {

        boolean isVisitedByCoach();

        List<Shuttle> getTransferShuttleList();

        void setTransferShuttleList(List<Shuttle> transferShuttleList);
    }

    public static class BusStop implements StopOrHub {

        private boolean visitedByCoach;

        @Override
        public List<Shuttle> getTransferShuttleList() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setTransferShuttleList(List<Shuttle> transferShuttleList) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isVisitedByCoach() {
            return visitedByCoach;
        }

        public void setVisitedByCoach(boolean visitedByCoach) {
            this.visitedByCoach = visitedByCoach;
        }

    }

    public static class Shuttle {

        private StopOrHub destination;

        public StopOrHub getDestination() {
            return destination;
        }

        public void setDestination(StopOrHub destination) {
            this.destination = destination;
        }

    }

    @Test
    public void test() {
        String drl = "package org.optaplanner.examples.coachshuttlegathering.solver;\n"
                + "    dialect \"java\"\n"
                + "\n"
                + "import " + DroolsReproducerTest.BusStop.class.getCanonicalName() + ";\n"
                + "import " + DroolsReproducerTest.Shuttle.class.getCanonicalName() + ";\n"
                + "import " + DroolsReproducerTest.StopOrHub.class.getCanonicalName() + ";\n"
                + "\n"
                + "rule \"shuttleDestinationIsCoachOrHub\"\n"
                + "    when\n"
                + "        $destination : StopOrHub(visitedByCoach == false)\n"
                + "        Shuttle(destination == $destination)\n"
                + "    then\n"
                + "end\n"
                + "\n"
                + "rule \"distanceFromPrevious\"\n"
                + "    enabled false"
                + "    when\n"
                + "        BusStop()\n"
                + "    then\n"
                + "end\n"
                + "";
        KieSession kieSession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();
        BusStop busStop = new BusStop();
        Shuttle shuttle = new Shuttle();

        busStop.setVisitedByCoach(true);

        kieSession.insert(shuttle);
        kieSession.insert(busStop);
        shuttle.setDestination(busStop);
        kieSession.update(kieSession.getFactHandle(shuttle), shuttle, "destination");
        Assert.assertEquals(0, kieSession.fireAllRules());
        busStop.setVisitedByCoach(false);
        // LHS is satisfied, rule should fire but it doesn't
        Assert.assertEquals(busStop, shuttle.getDestination());
        Assert.assertFalse(busStop.isVisitedByCoach());
        kieSession.update(kieSession.getFactHandle(busStop), busStop, "visitedByCoach");
        Assert.assertEquals(0, kieSession.fireAllRules());
        kieSession.update(kieSession.getFactHandle(busStop), busStop, "transferShuttleList");
        // This is the corrupted score, just to make sure the bug is reproducible
        Assert.assertEquals(1, kieSession.fireAllRules());
    }
}
