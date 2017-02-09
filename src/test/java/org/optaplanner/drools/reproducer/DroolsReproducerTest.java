package org.optaplanner.drools.reproducer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.optaplanner.examples.coachshuttlegathering.domain.BusStop;
import org.optaplanner.examples.coachshuttlegathering.domain.Coach;
import org.optaplanner.examples.coachshuttlegathering.domain.Shuttle;

public class DroolsReproducerTest {

    KieSession kieSession;
    private final Coach coach_2 = new Coach();
    private final Shuttle shuttle_8 = new Shuttle();
    private final BusStop busStop_17 = new BusStop();

    @Before
    public void setUp() {
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
        kieSession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();

        busStop_17.setBus(coach_2);

        kieSession.insert(shuttle_8);
        kieSession.insert(busStop_17);
    }

    @Test
    public void test() {
        shuttle_8.setDestination(busStop_17);
        kieSession.update(kieSession.getFactHandle(shuttle_8), shuttle_8, "destination");
        Assert.assertEquals(0, kieSession.fireAllRules());
        busStop_17.setBus(null);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "bus");
        Assert.assertEquals(0, kieSession.fireAllRules());
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transferShuttleList");
        // This is the corrupted score, just to make sure the bug is reproducible
        Assert.assertEquals(1, kieSession.fireAllRules());
    }
}
