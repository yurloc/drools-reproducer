package org.optaplanner.testgen;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Match;
import org.kie.internal.event.rule.RuleEventListener;
import org.kie.internal.event.rule.RuleEventManager;
import org.kie.internal.utils.KieHelper;
import org.optaplanner.core.api.score.holder.ScoreHolder;
import org.optaplanner.core.impl.score.buildin.simplelong.SimpleLongScoreDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsReproducerTest {

    private static final Logger logger = LoggerFactory.getLogger(DroolsReproducerTest.class);

    @Test
    public void test() {
        String drl = "import org.optaplanner.core.api.score.buildin.simplelong.SimpleLongScoreHolder;\n"
                + "import " + DroolsReproducerTest.BusStop.class.getCanonicalName() + ";\n"
                + "import " + DroolsReproducerTest.Coach.class.getCanonicalName() + ";\n"
                + "global SimpleLongScoreHolder scoreHolder;\n"
                + "global java.util.List result;\n"
                + "rule coachStopLimit\n"
                + "    when\n"
                + "        $coach : Coach($stopLimit : stopLimit)\n"
                + "        accumulate(\n"
                + "            $stop : BusStop(bus == $coach);\n"
                + "            $stopTotal : count($stop);\n"
                + "            $stopTotal > $stopLimit\n"
                + "        )\n"
                + "    then\n"
                + "        scoreHolder.addConstraintMatch(kcontext, $stopTotal);\n"
                + "        result.add($stopTotal);\n"
                + "end";
        KieSession kieSession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();

        ScoreHolder scoreHolder = new SimpleLongScoreDefinition().buildScoreHolder(true);
        List<Long> result = new ArrayList<Long>();
        kieSession.setGlobal("result", result);
        kieSession.setGlobal("scoreHolder", scoreHolder);

        final List<String> list = new ArrayList<String>();

        ((RuleEventManager) kieSession).addEventListener(new RuleEventListener() {
            @Override
            public void onDeleteMatch(Match match) {
                list.add("onDeleteMatch: " + match);
                logger.info("{}", list);
            }

            @Override
            public void onUpdateMatch(Match match) {
                list.add("onUpdateMatch: " + match);
                logger.info("{}", list);
            }
        });

        Coach coach = new Coach();
        BusStop busStop1 = new BusStop();
        BusStop busStop2 = new BusStop();

        busStop1.setBus(coach);

        kieSession.insert(coach);
        kieSession.insert(busStop1);
        kieSession.insert(busStop2);

        kieSession.fireAllRules();
        Assert.assertEquals("1", scoreHolder.extractScore(0).toString());
        Assert.assertEquals((Long) 1L, result.get(result.size() - 1));

        busStop2.setBus(coach);
        kieSession.update(kieSession.getFactHandle(busStop2), busStop2, "bus");

        kieSession.fireAllRules();
        Assert.assertEquals((Long) 2L, result.get(result.size() - 1));
        // Corrupted score is 3 but should be 2 because there are 2 bus stops with the same coach in total.
        // Looks like the count() for busStop1 from the previous match becomes duplicated.
        Assert.assertEquals("2", scoreHolder.extractScore(0).toString());
    }

    public static class Coach {

        public long getStopLimit() {
            return 0;
        }

    }

    public static class BusStop {

        private Coach bus;

        public Coach getBus() {
            return bus;
        }

        public void setBus(Coach bus) {
            this.bus = bus;
        }

    }

}
