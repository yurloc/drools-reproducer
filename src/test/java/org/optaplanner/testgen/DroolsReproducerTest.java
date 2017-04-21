package org.optaplanner.testgen;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.optaplanner.examples.coachshuttlegathering.domain.BusStop;
import org.optaplanner.examples.coachshuttlegathering.domain.Coach;
import org.optaplanner.examples.coachshuttlegathering.domain.Shuttle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsReproducerTest {

    private static final Logger logger = LoggerFactory.getLogger(DroolsReproducerTest.class);

    @Test
    public void test() {
        String drl = "import org.optaplanner.examples.coachshuttlegathering.domain.BusStop;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.Coach;\n"
                + "import org.optaplanner.examples.coachshuttlegathering.domain.Shuttle;\n"
                + "\n"
                + "global java.util.List result;\n"
                + "\n"
                + "rule coachCapacity\n"
                + "    when\n"
                + "        $coach : Coach()\n"
                + "        accumulate(\n"
                + "            BusStop(bus == $coach);\n"
                + "            count()\n"
                + "        )\n"
                + "\n"
                + "        $shuttle : Shuttle()\n"
                + "        accumulate(\n"
                + "            BusStop(bus == $coach)\n"
                + "            and BusStop(bus == $shuttle);\n"
                + "            $result : count()\n"
                + "        )\n"
                + "    then\n"
                + "        result.add($result);\n"
                + "end";
        KieSession kieSession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();

        ArrayList<Integer> result = new ArrayList<Integer>();
        kieSession.setGlobal("result", result);

        Coach coach1 = new Coach();
        Coach coach2 = new Coach();
        Shuttle shuttle = new Shuttle();
        BusStop stop1 = new BusStop();
        BusStop stop2 = new BusStop();

        stop2.setBus(coach2);

        kieSession.insert(coach1);
        kieSession.insert(coach2);
        kieSession.insert(shuttle);
        kieSession.insert(stop1);
        kieSession.insert(stop2);

        Assert.assertEquals(2, kieSession.fireAllRules());
        logger.info("F1: {}", result);
        result.clear();

        kieSession.update(kieSession.getFactHandle(shuttle), shuttle);
        kieSession.update(kieSession.getFactHandle(stop2), stop2);

        Assert.assertEquals(2, kieSession.fireAllRules());
        logger.info("F2: {}", result);
        result.clear();

        kieSession.update(kieSession.getFactHandle(shuttle), shuttle);
        stop1.setBus(shuttle);
        kieSession.update(kieSession.getFactHandle(stop1), stop1);

        Assert.assertEquals(2, kieSession.fireAllRules());
        logger.info("F3: {}", result);
        ArrayList<Integer> actual = new ArrayList<Integer>(result);
        Collections.sort(actual);
        result.clear();

        kieSession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();
        kieSession.setGlobal("result", result);

        // Insert everything into a fresh session to see the uncorrupted score
        kieSession.insert(coach1);
        kieSession.insert(coach2);
        kieSession.insert(shuttle);
        kieSession.insert(stop1);
        kieSession.insert(stop2);

        kieSession.fireAllRules();
        logger.info("F1’: {}", result);
        ArrayList<Integer> expected = new ArrayList<Integer>(result);
        Collections.sort(expected);
        Assert.assertEquals(expected, actual);
    }
}
