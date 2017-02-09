package org.optaplanner.drools.reproducer;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.optaplanner.core.api.score.holder.ScoreHolder;
import org.optaplanner.core.impl.score.buildin.hardsoftlong.HardSoftLongScoreDefinition;
import org.optaplanner.examples.coachshuttlegathering.domain.BusStop;
import org.optaplanner.examples.coachshuttlegathering.domain.Coach;
import org.optaplanner.examples.coachshuttlegathering.domain.Shuttle;

public class DroolsReproducerTest {

    KieContainer kieContainer;
    KieSession kieSession;
    ScoreHolder scoreHolder = new HardSoftLongScoreDefinition().buildScoreHolder(true);
    private final Coach coach_2 = new Coach();
    private final Shuttle shuttle_8 = new Shuttle();
    private final BusStop busStop_17 = new BusStop();

    @Before
    public void setUp() {
        String drl = "package org.optaplanner.examples.coachshuttlegathering.solver;\n"
                + "    dialect \"java\"\n"
                + "\n"
                + "import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScoreHolder;\n"
                + "\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.Bus;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.BusHub;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.BusOrStop;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.BusStop;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.Coach;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.CoachShuttleGatheringSolution;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.Shuttle;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.StopOrHub;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.location.RoadLocation;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.location.RoadLocationArc;\n"
                + "\n"
                + "global HardSoftLongScoreHolder scoreHolder;\n"
                + "\n"
                + "rule \"shuttleDestinationIsCoachOrHub\"\n"
                + "    when\n"
                + "        $destination : StopOrHub(visitedByCoach == false)\n"
                + "        Shuttle(destination == $destination)\n"
                + "    then\n"
                + "        scoreHolder.addHardConstraintMatch(kcontext, - 1000000000L);\n"
                + "end\n"
                + "\n"
                + "rule \"distanceFromPrevious\"\n"
                + "    when\n"
                + "        BusStop(previousBusOrStop != null, $cost : distanceFromPreviousCost)\n"
                + "    then\n"
                + "        scoreHolder.addSoftConstraintMatch(kcontext, - $cost);\n"
                + "end\n"
                + "";
        kieSession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();
        kieSession.setGlobal("scoreHolder", scoreHolder);

        //S5
        shuttle_8.setName("S8");
        //B7
        busStop_17.setBus(coach_2);

        //operation I #8
        kieSession.insert(shuttle_8);
        //operation I #17
        kieSession.insert(busStop_17);
    }

    @Test
    public void test() {
        //operation U #1005
        shuttle_8.setDestination(busStop_17);
        kieSession.update(kieSession.getFactHandle(shuttle_8), shuttle_8, "destination");
        //operation F #1007
        kieSession.fireAllRules();
        Assert.assertEquals("0hard/0soft", scoreHolder.extractScore(0).toString());
        //operation U #1298
        busStop_17.setBus(null);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "bus");
        //operation F #1303
        kieSession.fireAllRules();
        Assert.assertEquals("0hard/0soft", scoreHolder.extractScore(0).toString());
        //operation U #1338
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transferShuttleList");
        //operation F #1340
        kieSession.fireAllRules();
        // This is the corrupted score, just to make sure the bug is reproducible
        Assert.assertEquals("-1000000000hard/0soft", scoreHolder.extractScore(0).toString());
    }
}
