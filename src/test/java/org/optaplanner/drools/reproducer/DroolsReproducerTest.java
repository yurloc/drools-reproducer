package org.optaplanner.drools.reproducer;

import java.util.HashMap;

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
import org.optaplanner.examples.coachshuttlegathering.domain.location.RoadLocation;
import org.optaplanner.examples.coachshuttlegathering.domain.location.RoadLocationArc;

public class DroolsReproducerTest {

    KieContainer kieContainer;
    KieSession kieSession;
    ScoreHolder scoreHolder = new HardSoftLongScoreDefinition().buildScoreHolder(true);
    private final Coach coach_2 = new Coach();
    private final Shuttle shuttle_8 = new Shuttle();
    private final BusStop busStop_17 = new BusStop();
    private final RoadLocation roadLocation_22 = new RoadLocation();
    private final RoadLocation roadLocation_23 = new RoadLocation();
    private final RoadLocation roadLocation_24 = new RoadLocation();
    private final RoadLocation roadLocation_25 = new RoadLocation();
    private final RoadLocation roadLocation_26 = new RoadLocation();
    private final RoadLocation roadLocation_27 = new RoadLocation();
    private final RoadLocation roadLocation_28 = new RoadLocation();
    private final RoadLocation roadLocation_29 = new RoadLocation();
    private final RoadLocation roadLocation_30 = new RoadLocation();
    private final RoadLocation roadLocation_31 = new RoadLocation();
    private final RoadLocation roadLocation_32 = new RoadLocation();
    private final RoadLocation roadLocation_33 = new RoadLocation();
    private final RoadLocation roadLocation_34 = new RoadLocation();
    private final RoadLocation roadLocation_35 = new RoadLocation();
    private final RoadLocation roadLocation_36 = new RoadLocation();
    private final RoadLocation roadLocation_37 = new RoadLocation();
    private final RoadLocation roadLocation_38 = new RoadLocation();
    private final RoadLocation roadLocation_39 = new RoadLocation();
    private final RoadLocation roadLocation_40 = new RoadLocation();
    private final RoadLocation roadLocation_41 = new RoadLocation();

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
                + "\n"
                + "rule \"distanceCoachDirectlyToDestination\"\n"
                + "    when\n"
                + "        Coach(destination != null, nextStop == null, $cost : distanceToDestinationCost)\n"
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
        busStop_17.setLocation(roadLocation_28);
        busStop_17.setName("B17");
        //RoadLocation-0
        HashMap<RoadLocation, RoadLocationArc> roadLocation_22_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_22_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_22_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_22_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_22_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_22_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_22_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_22_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_22_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_22_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_22_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_22_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 10000m/900s, shuttle: 10000m/600s]
        roadLocation_22_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_22_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_22_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_22_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_22_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_22_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 12000m/1080s, shuttle: 12000m/720s]
        roadLocation_22_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 11000m/990s, shuttle: 11000m/660s]
        roadLocation_22_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 10000m/900s, shuttle: 10000m/600s]
        roadLocation_22_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_22.setTravelDistanceMap(roadLocation_22_travelDistanceMap);
        roadLocation_22.setId(22L);
        //RoadLocation-1
        //RoadLocation-2
        //RoadLocation-3
        //RoadLocation-4
        HashMap<RoadLocation, RoadLocationArc> roadLocation_26_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_26_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_26_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_26_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_26_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_26_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_26_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_26_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_26_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_26_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_26_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 11000m/990s, shuttle: 11000m/660s]
        roadLocation_26_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_26_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_26_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_26_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_26_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 11000m/990s, shuttle: 11000m/660s]
        roadLocation_26_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_26_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_26_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_26_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_26_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_26.setTravelDistanceMap(roadLocation_26_travelDistanceMap);
        roadLocation_26.setId(26L);
        //RoadLocation-5
        HashMap<RoadLocation, RoadLocationArc> roadLocation_27_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_27_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_27_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_27_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_27_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_27_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_27_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_27_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_27_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 1000m/90s, shuttle: 1000m/60s]
        roadLocation_27_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_27_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_27_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_27_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_27_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_27_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_27_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_27_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_27_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_27_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_27_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_27_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_27.setTravelDistanceMap(roadLocation_27_travelDistanceMap);
        roadLocation_27.setId(27L);
        //RoadLocation-6
        HashMap<RoadLocation, RoadLocationArc> roadLocation_28_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_28_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_28_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_28_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_28_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_28_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_28_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_28_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_28_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_28_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_28_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_28_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_28_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_28_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_28_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_28_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_28_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_28_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_28_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_28_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_28_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_28.setTravelDistanceMap(roadLocation_28_travelDistanceMap);
        roadLocation_28.setId(28L);
        //RoadLocation-7
        HashMap<RoadLocation, RoadLocationArc> roadLocation_29_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_29_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_29_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_29_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_29_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_29_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_29_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_29_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_29_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_29_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_29_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_29_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_29_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_29_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_29_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_29_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_29_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_29_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 10000m/900s, shuttle: 10000m/600s]
        roadLocation_29_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_29_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_29_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_29.setTravelDistanceMap(roadLocation_29_travelDistanceMap);
        roadLocation_29.setId(29L);
        //RoadLocation-8
        //RoadLocation-9
        HashMap<RoadLocation, RoadLocationArc> roadLocation_31_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_31_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_31_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_31_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_31_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_31_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_31_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_31_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_31_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_31_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_31_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_31_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_31_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_31_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_31_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_31_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_31_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_31_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_31_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_31_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_31_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_31.setTravelDistanceMap(roadLocation_31_travelDistanceMap);
        roadLocation_31.setId(31L);
        //RoadLocation-10
        //RoadLocation-11
        //RoadLocation-12
        HashMap<RoadLocation, RoadLocationArc> roadLocation_34_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_34_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_34_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_34_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_34_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_34_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_34_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_34_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_34_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_34_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_34_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_34_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 1000m/90s, shuttle: 1000m/60s]
        roadLocation_34_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_34_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 1000m/90s, shuttle: 1000m/60s]
        roadLocation_34_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_34_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_34_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_34_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_34_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_34_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_34_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_34.setTravelDistanceMap(roadLocation_34_travelDistanceMap);
        roadLocation_34.setId(34L);
        //RoadLocation-13
        HashMap<RoadLocation, RoadLocationArc> roadLocation_35_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_35_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_35_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_35_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_35_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_35_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_35_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_35_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_35_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_35_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_35_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_35_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_35_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 1000m/90s, shuttle: 1000m/60s]
        roadLocation_35_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_35_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_35_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_35_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_35_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_35_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_35_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_35_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_35.setTravelDistanceMap(roadLocation_35_travelDistanceMap);
        roadLocation_35.setId(35L);
        //RoadLocation-14
        //RoadLocation-15
        HashMap<RoadLocation, RoadLocationArc> roadLocation_37_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_37_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_37_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_37_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_37_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 11000m/990s, shuttle: 11000m/660s]
        roadLocation_37_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_37_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_37_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_37_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_37_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_37_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_37_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_37_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_37_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_37_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_37_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_37_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_37_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_37_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_37_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_37_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_37.setTravelDistanceMap(roadLocation_37_travelDistanceMap);
        roadLocation_37.setId(37L);
        //RoadLocation-16
        HashMap<RoadLocation, RoadLocationArc> roadLocation_38_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_38_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_38_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_38_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_38_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_38_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_38_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_38_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_38_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_38_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_38_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_38_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_38_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_38_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_38_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_38_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_38_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_38_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_38_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_38_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_38_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_38.setTravelDistanceMap(roadLocation_38_travelDistanceMap);
        roadLocation_38.setId(38L);
        //RoadLocation-17
        HashMap<RoadLocation, RoadLocationArc> roadLocation_39_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 12000m/1080s, shuttle: 12000m/720s]
        roadLocation_39_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_39_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_39_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 10000m/900s, shuttle: 10000m/600s]
        roadLocation_39_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_39_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 5000m/450s, shuttle: 5000m/300s]
        roadLocation_39_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_39_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 10000m/900s, shuttle: 10000m/600s]
        roadLocation_39_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_39_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_39_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 10000m/900s, shuttle: 10000m/600s]
        roadLocation_39_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_39_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_39_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_39_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_39_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_39_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_39_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_39_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 1000m/90s, shuttle: 1000m/60s]
        roadLocation_39_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_39_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_39.setTravelDistanceMap(roadLocation_39_travelDistanceMap);
        roadLocation_39.setId(39L);
        //RoadLocation-18
        //RoadLocation-19
        HashMap<RoadLocation, RoadLocationArc> roadLocation_41_travelDistanceMap = new HashMap<RoadLocation, RoadLocationArc>();
        //RoadLocation-null => Road arc [coach: 10000m/900s, shuttle: 10000m/600s]
        roadLocation_41_travelDistanceMap.put(roadLocation_22, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_41_travelDistanceMap.put(roadLocation_23, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_41_travelDistanceMap.put(roadLocation_24, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_41_travelDistanceMap.put(roadLocation_25, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 9000m/810s, shuttle: 9000m/540s]
        roadLocation_41_travelDistanceMap.put(roadLocation_26, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 7000m/630s, shuttle: 7000m/420s]
        roadLocation_41_travelDistanceMap.put(roadLocation_27, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_41_travelDistanceMap.put(roadLocation_28, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_41_travelDistanceMap.put(roadLocation_29, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_41_travelDistanceMap.put(roadLocation_30, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_41_travelDistanceMap.put(roadLocation_31, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 8000m/720s, shuttle: 8000m/480s]
        roadLocation_41_travelDistanceMap.put(roadLocation_32, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_41_travelDistanceMap.put(roadLocation_33, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 3000m/270s, shuttle: 3000m/180s]
        roadLocation_41_travelDistanceMap.put(roadLocation_34, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_41_travelDistanceMap.put(roadLocation_35, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 4000m/360s, shuttle: 4000m/240s]
        roadLocation_41_travelDistanceMap.put(roadLocation_36, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 6000m/540s, shuttle: 6000m/360s]
        roadLocation_41_travelDistanceMap.put(roadLocation_37, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_41_travelDistanceMap.put(roadLocation_38, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 2000m/180s, shuttle: 2000m/120s]
        roadLocation_41_travelDistanceMap.put(roadLocation_39, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 1000m/90s, shuttle: 1000m/60s]
        roadLocation_41_travelDistanceMap.put(roadLocation_40, new RoadLocationArc(0, 0, 0, 0));
        //RoadLocation-null => Road arc [coach: 0m/0s, shuttle: 0m/0s]
        roadLocation_41_travelDistanceMap.put(roadLocation_41, new RoadLocationArc(0, 0, 0, 0));
        roadLocation_41.setTravelDistanceMap(roadLocation_41_travelDistanceMap);
        roadLocation_41.setId(41L);

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
