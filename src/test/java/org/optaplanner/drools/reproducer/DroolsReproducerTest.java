package org.optaplanner.drools.reproducer;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.optaplanner.examples.coachshuttlegathering.domain.BusStop;
import org.optaplanner.examples.coachshuttlegathering.domain.Coach;
import org.optaplanner.examples.coachshuttlegathering.domain.Shuttle;

public class DroolsReproducerTest {

    @Test
    public void test() {
        String drl = "package org.optaplanner.examples.coachshuttlegathering.solver;\n"
                + "    dialect \"java\"\n"
                + "\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.BusStop;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.Shuttle;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.StopOrHub;\n"
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
        Coach coach = new Coach();
        BusStop busStop = new BusStop();
        Shuttle shuttle = new Shuttle();

        busStop.setBus(coach);

        kieSession.insert(shuttle);
        kieSession.insert(busStop);
        shuttle.setDestination(busStop);
        kieSession.update(kieSession.getFactHandle(shuttle), shuttle, "destination");
        Assert.assertEquals(0, kieSession.fireAllRules());
        busStop.setBus(null);
        // LHS is satisfied, rule should fire but it doesn't
        Assert.assertEquals(busStop, shuttle.getDestination());
        Assert.assertFalse(busStop.isVisitedByCoach());
        kieSession.update(kieSession.getFactHandle(busStop), busStop, "bus");
        Assert.assertEquals(0, kieSession.fireAllRules());
        kieSession.update(kieSession.getFactHandle(busStop), busStop, "transferShuttleList");
        // This is the corrupted score, just to make sure the bug is reproducible
        Assert.assertEquals(1, kieSession.fireAllRules());
    }
}
