package org.optaplanner.drools.reproducer;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.optaplanner.core.api.score.holder.ScoreHolder;
import org.optaplanner.core.impl.score.buildin.hardsoftlong.HardSoftLongScoreDefinition;
import org.optaplanner.examples.coachshuttlegathering.domain.BusHub;
import org.optaplanner.examples.coachshuttlegathering.domain.BusStop;
import org.optaplanner.examples.coachshuttlegathering.domain.Coach;
import org.optaplanner.examples.coachshuttlegathering.domain.Shuttle;
import org.optaplanner.examples.coachshuttlegathering.domain.location.RoadLocation;
import org.optaplanner.examples.coachshuttlegathering.domain.location.RoadLocationArc;

public class DroolsReproducerTest {

    KieContainer kieContainer;
    KieSession kieSession;
    ScoreHolder scoreHolder = new HardSoftLongScoreDefinition().buildScoreHolder(true);
    private final BusHub busHub_0 = new BusHub();
    private final Coach coach_1 = new Coach();
    private final Coach coach_2 = new Coach();
    private final Coach coach_3 = new Coach();
    private final Shuttle shuttle_4 = new Shuttle();
    private final Shuttle shuttle_5 = new Shuttle();
    private final Shuttle shuttle_6 = new Shuttle();
    private final Shuttle shuttle_7 = new Shuttle();
    private final Shuttle shuttle_8 = new Shuttle();
    private final Shuttle shuttle_9 = new Shuttle();
    private final BusStop busStop_10 = new BusStop();
    private final BusStop busStop_11 = new BusStop();
    private final BusStop busStop_12 = new BusStop();
    private final BusStop busStop_13 = new BusStop();
    private final BusStop busStop_14 = new BusStop();
    private final BusStop busStop_15 = new BusStop();
    private final BusStop busStop_16 = new BusStop();
    private final BusStop busStop_17 = new BusStop();
    private final BusStop busStop_18 = new BusStop();
    private final BusStop busStop_19 = new BusStop();
    private final BusStop busStop_20 = new BusStop();
    private final BusStop busStop_21 = new BusStop();
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

        //H1
        busHub_0.setLocation(roadLocation_32);
        busHub_0.setName("H0");
        //C1
        coach_1.setDepartureLocation(roadLocation_26);
        //C2
        coach_2.setDepartureLocation(roadLocation_26);
        //C3
        coach_3.setDepartureLocation(roadLocation_33);
        //S1
        shuttle_4.setName("S4");
        //S2
        shuttle_5.setDepartureLocation(roadLocation_22);
        shuttle_5.setName("S5");
        //S3
        shuttle_6.setDepartureLocation(roadLocation_41);
        shuttle_6.setName("S6");
        //S4
        shuttle_7.setDepartureLocation(roadLocation_40);
        shuttle_7.setName("S7");
        //S5
        shuttle_8.setName("S8");
        //S6
        shuttle_9.setDepartureLocation(roadLocation_39);
        shuttle_9.setName("S9");
        //B0
        busStop_10.setBus(coach_2);
        busStop_10.setLocation(roadLocation_29);
        busStop_10.setName("B10");
        //B1
        busStop_11.setBus(coach_1);
        busStop_11.setLocation(roadLocation_27);
        busStop_11.setName("B11");
        //B2
        busStop_12.setBus(coach_3);
        busStop_12.setLocation(roadLocation_30);
        busStop_12.setName("B12");
        //B3
        busStop_13.setBus(shuttle_7);
        busStop_13.setLocation(roadLocation_34);
        busStop_13.setName("B13");
        //B4
        busStop_14.setBus(coach_1);
        busStop_14.setLocation(roadLocation_24);
        busStop_14.setName("B14");
        //B5
        busStop_15.setBus(coach_3);
        busStop_15.setLocation(roadLocation_31);
        busStop_15.setName("B15");
        //B6
        busStop_16.setBus(shuttle_7);
        busStop_16.setLocation(roadLocation_35);
        busStop_16.setName("B16");
        //B7
        busStop_17.setBus(coach_2);
        busStop_17.setLocation(roadLocation_28);
        busStop_17.setName("B17");
        //B8
        busStop_18.setBus(shuttle_6);
        busStop_18.setLocation(roadLocation_38);
        busStop_18.setName("B18");
        //B9
        busStop_19.setBus(coach_1);
        busStop_19.setLocation(roadLocation_25);
        busStop_19.setName("B19");
        //B10
        busStop_20.setBus(shuttle_7);
        busStop_20.setLocation(roadLocation_36);
        busStop_20.setName("B20");
        //B11
        busStop_21.setBus(shuttle_7);
        busStop_21.setLocation(roadLocation_37);
        busStop_21.setName("B21");
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

        //operation I #0
        kieSession.insert(busHub_0);
        //operation I #1
        kieSession.insert(coach_1);
        //operation I #2
        kieSession.insert(coach_2);
        //operation I #3
        kieSession.insert(coach_3);
        //operation I #5
        kieSession.insert(shuttle_5);
        //operation I #6
        kieSession.insert(shuttle_6);
        //operation I #7
        kieSession.insert(shuttle_7);
        //operation I #8
        kieSession.insert(shuttle_8);
        //operation I #9
        kieSession.insert(shuttle_9);
        //operation I #10
        kieSession.insert(busStop_10);
        //operation I #11
        kieSession.insert(busStop_11);
        //operation I #12
        kieSession.insert(busStop_12);
        //operation I #13
        kieSession.insert(busStop_13);
        //operation I #14
        kieSession.insert(busStop_14);
        //operation I #15
        kieSession.insert(busStop_15);
        //operation I #16
        kieSession.insert(busStop_16);
        //operation I #17
        kieSession.insert(busStop_17);
        //operation I #18
        kieSession.insert(busStop_18);
        //operation I #19
        kieSession.insert(busStop_19);
        //operation I #20
        kieSession.insert(busStop_20);
        //operation I #21
        kieSession.insert(busStop_21);
    }

    @Test
    public void test() {
        //operation U #1005
        shuttle_8.setDestination(busStop_17);
        kieSession.update(kieSession.getFactHandle(shuttle_8), shuttle_8, "destination");
        //operation U #1006
        busStop_17.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_8));
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transferShuttleList");
        //operation F #1007
        kieSession.fireAllRules();
        Assert.assertEquals("0hard/0soft", scoreHolder.extractScore(0).toString());
        //operation U #1008
        busHub_0.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_5));
        kieSession.update(kieSession.getFactHandle(busHub_0), busHub_0, "transferShuttleList");
        //operation U #1009
        busHub_0.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busHub_0), busHub_0, "transferShuttleList");
        //operation U #1010
        busStop_11.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transferShuttleList");
        //operation U #1011
        busStop_19.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transferShuttleList");
        //operation U #1012
        busStop_17.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transferShuttleList");
        //operation U #1013
        busStop_12.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busStop_12), busStop_12, "transferShuttleList");
        //operation U #1014
        busStop_18.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1015
        coach_1.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_1), coach_1, "nextStop");
        //operation U #1016
        coach_3.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_3), coach_3, "nextStop");
        //operation U #1017
        shuttle_7.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_7), shuttle_7, "nextStop");
        //operation U #1018
        busStop_11.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "nextStop");
        //operation U #1019
        busStop_17.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "nextStop");
        //operation U #1020
        busStop_13.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1021
        coach_2.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1022
        shuttle_6.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1023
        busStop_14.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "nextStop");
        //operation U #1024
        busStop_16.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "nextStop");
        //operation U #1025
        busStop_10.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "nextStop");
        //operation U #1026
        busStop_18.setNextStop(busStop_10);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1027
        coach_1.setNextStop(busStop_11);
        kieSession.update(kieSession.getFactHandle(coach_1), coach_1, "nextStop");
        //operation U #1028
        coach_3.setNextStop(busStop_12);
        kieSession.update(kieSession.getFactHandle(coach_3), coach_3, "nextStop");
        //operation U #1029
        shuttle_7.setNextStop(busStop_13);
        kieSession.update(kieSession.getFactHandle(shuttle_7), shuttle_7, "nextStop");
        //operation U #1030
        busStop_11.setNextStop(busStop_14);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "nextStop");
        //operation U #1031
        busStop_17.setNextStop(busStop_15);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "nextStop");
        //operation U #1032
        busStop_13.setNextStop(busStop_16);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1033
        coach_2.setNextStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1034
        shuttle_6.setNextStop(busStop_18);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1035
        busStop_14.setNextStop(busStop_19);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "nextStop");
        //operation U #1036
        busStop_16.setNextStop(busStop_20);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "nextStop");
        //operation U #1037
        busStop_10.setNextStop(busStop_21);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "nextStop");
        //operation U #1038
        busHub_0.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_4));
        kieSession.update(kieSession.getFactHandle(busHub_0), busHub_0, "transferShuttleList");
        //operation U #1039
        busHub_0.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_4, shuttle_5));
        kieSession.update(kieSession.getFactHandle(busHub_0), busHub_0, "transferShuttleList");
        //operation U #1040
        busStop_11.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_6));
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transferShuttleList");
        //operation U #1041
        busStop_19.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_7));
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transferShuttleList");
        //operation U #1042
        busStop_17.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_8));
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transferShuttleList");
        //operation U #1043
        busStop_12.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_9));
        kieSession.update(kieSession.getFactHandle(busStop_12), busStop_12, "transferShuttleList");
        //operation F #1044
        kieSession.fireAllRules();
        //operation U #1045
        coach_1.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_1), coach_1, "nextStop");
        //operation U #1046
        busStop_11.setPreviousBusOrStop(shuttle_9);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "previousBusOrStop");
        //operation U #1047
        shuttle_9.setNextStop(busStop_11);
        kieSession.update(kieSession.getFactHandle(shuttle_9), shuttle_9, "nextStop");
        //operation U #1048
        busStop_11.setBus(shuttle_9);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "bus");
        //operation U #1049
        busStop_14.setBus(shuttle_9);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "bus");
        //operation U #1050
        busStop_19.setBus(shuttle_9);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "bus");
        //operation U #1051
        busStop_19.setTransportTimeToHub(1080);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transportTimeToHub");
        //operation U #1052
        busStop_20.setTransportTimeToHub(null);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "transportTimeToHub");
        //operation U #1053
        busStop_16.setTransportTimeToHub(null);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1054
        busStop_13.setTransportTimeToHub(null);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1055
        busStop_14.setTransportTimeToHub(1200);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation U #1056
        busStop_11.setTransportTimeToHub(1380);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1057
        busStop_21.setTransportTimeToHub(null);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1058
        busStop_10.setTransportTimeToHub(null);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1059
        busStop_18.setTransportTimeToHub(null);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation F #1060
        kieSession.fireAllRules();
        //operation U #1061
        shuttle_9.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_9), shuttle_9, "nextStop");
        //operation U #1062
        busStop_11.setPreviousBusOrStop(coach_1);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "previousBusOrStop");
        //operation U #1063
        coach_1.setNextStop(busStop_11);
        kieSession.update(kieSession.getFactHandle(coach_1), coach_1, "nextStop");
        //operation U #1064
        busStop_11.setBus(coach_1);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "bus");
        //operation U #1065
        busStop_14.setBus(coach_1);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "bus");
        //operation U #1066
        busStop_19.setBus(coach_1);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "bus");
        //operation U #1067
        busStop_11.setTransportTimeToHub(1470);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1068
        busStop_21.setTransportTimeToHub(2010);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1069
        busStop_10.setTransportTimeToHub(2250);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1070
        busStop_18.setTransportTimeToHub(2610);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1071
        busStop_14.setTransportTimeToHub(1260);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation U #1072
        busStop_11.setTransportTimeToHub(1530);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1073
        busStop_21.setTransportTimeToHub(2070);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1074
        busStop_10.setTransportTimeToHub(2310);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1075
        busStop_18.setTransportTimeToHub(2670);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1076
        busStop_19.setTransportTimeToHub(540);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transportTimeToHub");
        //operation U #1077
        busStop_20.setTransportTimeToHub(780);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "transportTimeToHub");
        //operation U #1078
        busStop_16.setTransportTimeToHub(900);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1079
        busStop_13.setTransportTimeToHub(960);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1080
        busStop_14.setTransportTimeToHub(720);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation U #1081
        busStop_11.setTransportTimeToHub(990);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1082
        busStop_21.setTransportTimeToHub(1530);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1083
        busStop_10.setTransportTimeToHub(1770);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1084
        busStop_18.setTransportTimeToHub(2130);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation F #1085
        kieSession.fireAllRules();
        //operation U #1086
        busStop_18.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1087
        busStop_10.setPreviousBusOrStop(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "previousBusOrStop");
        //operation U #1088
        coach_2.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1089
        busStop_17.setPreviousBusOrStop(busStop_18);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "previousBusOrStop");
        //operation U #1090
        busStop_17.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "nextStop");
        //operation U #1091
        busStop_15.setPreviousBusOrStop(busStop_10);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "previousBusOrStop");
        //operation U #1092
        busStop_10.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "nextStop");
        //operation U #1093
        busStop_21.setPreviousBusOrStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "previousBusOrStop");
        //operation U #1094
        coach_2.setNextStop(busStop_10);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1095
        busStop_18.setNextStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1096
        busStop_10.setNextStop(busStop_15);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "nextStop");
        //operation U #1097
        busStop_17.setNextStop(busStop_21);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "nextStop");
        //operation U #1098
        busStop_10.setBus(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "bus");
        //operation U #1099
        busStop_17.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "bus");
        //operation U #1100
        busStop_18.setTransportTimeToHub(960);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1101
        busStop_17.setTransportTimeToHub(1890);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation U #1102
        busStop_18.setTransportTimeToHub(2130);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1103
        busStop_10.setTransportTimeToHub(900);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation F #1104
        kieSession.fireAllRules();
        //operation U #1105
        busStop_18.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1106
        busStop_17.setPreviousBusOrStop(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "previousBusOrStop");
        //operation U #1107
        coach_2.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1108
        busStop_10.setPreviousBusOrStop(busStop_18);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "previousBusOrStop");
        //operation U #1109
        busStop_10.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "nextStop");
        //operation U #1110
        busStop_15.setPreviousBusOrStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "previousBusOrStop");
        //operation U #1111
        busStop_17.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "nextStop");
        //operation U #1112
        busStop_21.setPreviousBusOrStop(busStop_10);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "previousBusOrStop");
        //operation U #1113
        coach_2.setNextStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1114
        busStop_18.setNextStop(busStop_10);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1115
        busStop_17.setNextStop(busStop_15);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "nextStop");
        //operation U #1116
        busStop_10.setNextStop(busStop_21);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "nextStop");
        //operation U #1117
        busStop_17.setBus(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "bus");
        //operation U #1118
        busStop_10.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "bus");
        //operation U #1119
        busStop_18.setTransportTimeToHub(1260);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1120
        busStop_10.setTransportTimeToHub(1770);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1121
        busStop_18.setTransportTimeToHub(2130);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1122
        busStop_17.setTransportTimeToHub(720);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation F #1123
        kieSession.fireAllRules();
        //operation U #1124
        busStop_13.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1125
        busStop_16.setPreviousBusOrStop(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "previousBusOrStop");
        //operation U #1126
        coach_2.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1127
        busStop_17.setPreviousBusOrStop(busStop_13);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "previousBusOrStop");
        //operation U #1128
        coach_2.setNextStop(busStop_16);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1129
        busStop_13.setNextStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1130
        busStop_16.setBus(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "bus");
        //operation U #1131
        busStop_20.setBus(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "bus");
        //operation U #1132
        busStop_17.setBus(shuttle_7);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "bus");
        //operation U #1133
        busStop_15.setBus(shuttle_7);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "bus");
        //operation U #1134
        busStop_13.setTransportTimeToHub(1020);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1135
        busStop_16.setTransportTimeToHub(960);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1136
        busStop_20.setTransportTimeToHub(360);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "transportTimeToHub");
        //operation U #1137
        busStop_16.setTransportTimeToHub(540);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1138
        busStop_17.setTransportTimeToHub(660);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation U #1139
        busStop_13.setTransportTimeToHub(960);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1140
        busStop_15.setTransportTimeToHub(780);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "transportTimeToHub");
        //operation U #1141
        busStop_17.setTransportTimeToHub(900);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation U #1142
        busStop_13.setTransportTimeToHub(1200);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation F #1143
        kieSession.fireAllRules();
        //operation U #1144
        coach_2.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1145
        busStop_16.setPreviousBusOrStop(busStop_13);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "previousBusOrStop");
        //operation U #1146
        busStop_13.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1147
        busStop_17.setPreviousBusOrStop(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "previousBusOrStop");
        //operation U #1148
        busStop_13.setNextStop(busStop_16);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1149
        coach_2.setNextStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1150
        busStop_16.setBus(shuttle_7);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "bus");
        //operation U #1151
        busStop_20.setBus(shuttle_7);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "bus");
        //operation U #1152
        busStop_17.setBus(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "bus");
        //operation U #1153
        busStop_15.setBus(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "bus");
        //operation U #1154
        busStop_13.setTransportTimeToHub(600);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1155
        busStop_16.setTransportTimeToHub(480);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1156
        busStop_13.setTransportTimeToHub(540);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1157
        busStop_20.setTransportTimeToHub(780);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "transportTimeToHub");
        //operation U #1158
        busStop_16.setTransportTimeToHub(900);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1159
        busStop_13.setTransportTimeToHub(960);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1160
        busStop_17.setTransportTimeToHub(960);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation U #1161
        busStop_15.setTransportTimeToHub(540);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "transportTimeToHub");
        //operation U #1162
        busStop_17.setTransportTimeToHub(720);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation F #1163
        kieSession.fireAllRules();
        //operation U #1164
        busStop_18.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1165
        busStop_10.setPreviousBusOrStop(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "previousBusOrStop");
        //operation U #1166
        shuttle_6.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1167
        busStop_18.setPreviousBusOrStop(shuttle_5);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "previousBusOrStop");
        //operation U #1168
        shuttle_6.setNextStop(busStop_10);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1169
        shuttle_5.setNextStop(busStop_18);
        kieSession.update(kieSession.getFactHandle(shuttle_5), shuttle_5, "nextStop");
        //operation U #1170
        busStop_18.setBus(shuttle_5);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "bus");
        //operation U #1171
        busStop_18.setTransportTimeToHub(360);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation F #1172
        kieSession.fireAllRules();
        //operation U #1173
        shuttle_5.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_5), shuttle_5, "nextStop");
        //operation U #1174
        busStop_18.setPreviousBusOrStop(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "previousBusOrStop");
        //operation U #1175
        shuttle_6.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1176
        busStop_10.setPreviousBusOrStop(busStop_18);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "previousBusOrStop");
        //operation U #1177
        shuttle_6.setNextStop(busStop_18);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1178
        busStop_18.setNextStop(busStop_10);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1179
        busStop_18.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "bus");
        //operation U #1180
        busStop_18.setTransportTimeToHub(2130);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation F #1181
        kieSession.fireAllRules();
        //operation U #1182
        shuttle_6.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1183
        busStop_18.setPreviousBusOrStop(busStop_11);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "previousBusOrStop");
        //operation U #1184
        busStop_11.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "nextStop");
        //operation U #1185
        busStop_14.setPreviousBusOrStop(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "previousBusOrStop");
        //operation U #1186
        busStop_11.setNextStop(busStop_18);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "nextStop");
        //operation U #1187
        shuttle_6.setNextStop(busStop_14);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1188
        busStop_18.setBus(coach_1);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "bus");
        //operation U #1189
        busStop_10.setBus(coach_1);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "bus");
        //operation U #1190
        busStop_21.setBus(coach_1);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "bus");
        //operation U #1191
        busStop_14.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "bus");
        //operation U #1192
        busStop_19.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "bus");
        //operation U #1193
        busStop_19.setTransportTimeToHub(1290);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transportTimeToHub");
        //operation U #1194
        busStop_20.setTransportTimeToHub(null);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "transportTimeToHub");
        //operation U #1195
        busStop_16.setTransportTimeToHub(null);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1196
        busStop_13.setTransportTimeToHub(null);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1197
        busStop_14.setTransportTimeToHub(1410);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation U #1198
        busStop_11.setTransportTimeToHub(2760);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1199
        busStop_19.setTransportTimeToHub(3060);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transportTimeToHub");
        //operation U #1200
        busStop_14.setTransportTimeToHub(3180);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation U #1201
        busStop_18.setTransportTimeToHub(2310);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1202
        busStop_11.setTransportTimeToHub(2940);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1203
        busStop_19.setTransportTimeToHub(3240);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transportTimeToHub");
        //operation U #1204
        busStop_14.setTransportTimeToHub(3360);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation U #1205
        busStop_10.setTransportTimeToHub(1890);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1206
        busStop_18.setTransportTimeToHub(2430);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1207
        busStop_11.setTransportTimeToHub(3060);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1208
        busStop_19.setTransportTimeToHub(3360);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transportTimeToHub");
        //operation U #1209
        busStop_14.setTransportTimeToHub(3480);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation U #1210
        busStop_21.setTransportTimeToHub(180);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1211
        busStop_10.setTransportTimeToHub(540);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1212
        busStop_18.setTransportTimeToHub(1080);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1213
        busStop_11.setTransportTimeToHub(1710);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1214
        busStop_19.setTransportTimeToHub(2010);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transportTimeToHub");
        //operation U #1215
        busStop_14.setTransportTimeToHub(2130);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation F #1216
        kieSession.fireAllRules();
        //operation U #1217
        busStop_11.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "nextStop");
        //operation U #1218
        busStop_18.setPreviousBusOrStop(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "previousBusOrStop");
        //operation U #1219
        shuttle_6.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1220
        busStop_14.setPreviousBusOrStop(busStop_11);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "previousBusOrStop");
        //operation U #1221
        shuttle_6.setNextStop(busStop_18);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1222
        busStop_11.setNextStop(busStop_14);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "nextStop");
        //operation U #1223
        busStop_18.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "bus");
        //operation U #1224
        busStop_10.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "bus");
        //operation U #1225
        busStop_21.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "bus");
        //operation U #1226
        busStop_14.setBus(coach_1);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "bus");
        //operation U #1227
        busStop_19.setBus(coach_1);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "bus");
        //operation U #1228
        busStop_11.setTransportTimeToHub(2400);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1229
        busStop_21.setTransportTimeToHub(2940);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1230
        busStop_10.setTransportTimeToHub(3180);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1231
        busStop_18.setTransportTimeToHub(3540);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1232
        busStop_14.setTransportTimeToHub(2190);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation U #1233
        busStop_11.setTransportTimeToHub(2460);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1234
        busStop_21.setTransportTimeToHub(3000);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1235
        busStop_10.setTransportTimeToHub(3240);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1236
        busStop_18.setTransportTimeToHub(3600);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation U #1237
        busStop_19.setTransportTimeToHub(540);
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transportTimeToHub");
        //operation U #1238
        busStop_20.setTransportTimeToHub(780);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "transportTimeToHub");
        //operation U #1239
        busStop_16.setTransportTimeToHub(900);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1240
        busStop_13.setTransportTimeToHub(960);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1241
        busStop_14.setTransportTimeToHub(720);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "transportTimeToHub");
        //operation U #1242
        busStop_11.setTransportTimeToHub(990);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transportTimeToHub");
        //operation U #1243
        busStop_21.setTransportTimeToHub(1530);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1244
        busStop_10.setTransportTimeToHub(1770);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1245
        busStop_18.setTransportTimeToHub(2130);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation F #1246
        kieSession.fireAllRules();
        //operation U #1247
        busStop_16.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "nextStop");
        //operation U #1248
        busStop_20.setPreviousBusOrStop(busStop_13);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "previousBusOrStop");
        //operation U #1249
        busStop_13.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1250
        busStop_16.setPreviousBusOrStop(busStop_15);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "previousBusOrStop");
        //operation U #1251
        busStop_13.setNextStop(busStop_20);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1252
        busStop_15.setNextStop(busStop_16);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "nextStop");
        //operation U #1253
        busStop_16.setBus(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "bus");
        //operation U #1254
        busStop_16.setTransportTimeToHub(540);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1255
        busStop_15.setTransportTimeToHub(720);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "transportTimeToHub");
        //operation U #1256
        busStop_17.setTransportTimeToHub(900);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation F #1257
        kieSession.fireAllRules();
        //operation U #1258
        busStop_15.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "nextStop");
        //operation U #1259
        busStop_16.setPreviousBusOrStop(busStop_13);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "previousBusOrStop");
        //operation U #1260
        busStop_13.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1261
        busStop_20.setPreviousBusOrStop(busStop_16);
        kieSession.update(kieSession.getFactHandle(busStop_20), busStop_20, "previousBusOrStop");
        //operation U #1262
        busStop_13.setNextStop(busStop_16);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1263
        busStop_16.setNextStop(busStop_20);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "nextStop");
        //operation U #1264
        busStop_16.setBus(shuttle_7);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "bus");
        //operation U #1265
        busStop_15.setTransportTimeToHub(540);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "transportTimeToHub");
        //operation U #1266
        busStop_17.setTransportTimeToHub(720);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation U #1267
        busStop_13.setTransportTimeToHub(600);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation U #1268
        busStop_16.setTransportTimeToHub(900);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "transportTimeToHub");
        //operation U #1269
        busStop_13.setTransportTimeToHub(960);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "transportTimeToHub");
        //operation F #1270
        kieSession.fireAllRules();
        //operation U #1271
        busStop_17.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "nextStop");
        //operation U #1272
        busStop_15.setPreviousBusOrStop(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "previousBusOrStop");
        //operation U #1273
        coach_2.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1274
        busStop_17.setPreviousBusOrStop(busStop_21);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "previousBusOrStop");
        //operation U #1275
        coach_2.setNextStop(busStop_15);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1276
        busStop_21.setNextStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "nextStop");
        //operation U #1277
        busStop_17.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "bus");
        //operation U #1278
        busStop_17.setTransportTimeToHub(1170);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation U #1279
        busStop_21.setTransportTimeToHub(1530);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1280
        busStop_10.setTransportTimeToHub(1770);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1281
        busStop_18.setTransportTimeToHub(2130);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation F #1282
        kieSession.fireAllRules();
        //operation U #1283
        busStop_21.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "nextStop");
        //operation U #1284
        busStop_17.setPreviousBusOrStop(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "previousBusOrStop");
        //operation U #1285
        coach_2.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1286
        busStop_15.setPreviousBusOrStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "previousBusOrStop");
        //operation U #1287
        coach_2.setNextStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1288
        busStop_17.setNextStop(busStop_15);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "nextStop");
        //operation U #1289
        busStop_17.setBus(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "bus");
        //operation U #1290
        busStop_17.setTransportTimeToHub(720);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation F #1291
        kieSession.fireAllRules();
        //operation U #1292
        busStop_17.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "nextStop");
        //operation U #1293
        busStop_15.setPreviousBusOrStop(coach_2);
        kieSession.update(kieSession.getFactHandle(busStop_15), busStop_15, "previousBusOrStop");
        //operation U #1294
        coach_2.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1295
        busStop_17.setPreviousBusOrStop(busStop_21);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "previousBusOrStop");
        //operation U #1296
        coach_2.setNextStop(busStop_15);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1297
        busStop_21.setNextStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "nextStop");
        //operation U #1298
        busStop_17.setBus(shuttle_6);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "bus");
        //operation U #1299
        busStop_17.setTransportTimeToHub(1170);
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transportTimeToHub");
        //operation U #1300
        busStop_21.setTransportTimeToHub(1530);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "transportTimeToHub");
        //operation U #1301
        busStop_10.setTransportTimeToHub(1770);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "transportTimeToHub");
        //operation U #1302
        busStop_18.setTransportTimeToHub(2130);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "transportTimeToHub");
        //operation F #1303
        kieSession.fireAllRules();
        Assert.assertEquals("0hard/0soft", scoreHolder.extractScore(0).toString());
        //operation U #1304
        busHub_0.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_5));
        kieSession.update(kieSession.getFactHandle(busHub_0), busHub_0, "transferShuttleList");
        //operation U #1305
        busHub_0.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busHub_0), busHub_0, "transferShuttleList");
        //operation U #1306
        busStop_11.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transferShuttleList");
        //operation U #1307
        busStop_19.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transferShuttleList");
        //operation U #1308
        busStop_17.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transferShuttleList");
        //operation U #1309
        busStop_12.setTransferShuttleList(Arrays.<Shuttle>asList());
        kieSession.update(kieSession.getFactHandle(busStop_12), busStop_12, "transferShuttleList");
        //operation U #1310
        busStop_18.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1311
        coach_1.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_1), coach_1, "nextStop");
        //operation U #1312
        coach_3.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_3), coach_3, "nextStop");
        //operation U #1313
        shuttle_7.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_7), shuttle_7, "nextStop");
        //operation U #1314
        busStop_11.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "nextStop");
        //operation U #1315
        coach_2.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1316
        busStop_13.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1317
        busStop_21.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "nextStop");
        //operation U #1318
        shuttle_6.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1319
        busStop_14.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "nextStop");
        //operation U #1320
        busStop_16.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "nextStop");
        //operation U #1321
        busStop_10.setNextStop(null);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "nextStop");
        //operation U #1322
        busStop_18.setNextStop(busStop_10);
        kieSession.update(kieSession.getFactHandle(busStop_18), busStop_18, "nextStop");
        //operation U #1323
        coach_1.setNextStop(busStop_11);
        kieSession.update(kieSession.getFactHandle(coach_1), coach_1, "nextStop");
        //operation U #1324
        coach_3.setNextStop(busStop_12);
        kieSession.update(kieSession.getFactHandle(coach_3), coach_3, "nextStop");
        //operation U #1325
        shuttle_7.setNextStop(busStop_13);
        kieSession.update(kieSession.getFactHandle(shuttle_7), shuttle_7, "nextStop");
        //operation U #1326
        busStop_11.setNextStop(busStop_14);
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "nextStop");
        //operation U #1327
        coach_2.setNextStop(busStop_15);
        kieSession.update(kieSession.getFactHandle(coach_2), coach_2, "nextStop");
        //operation U #1328
        busStop_13.setNextStop(busStop_16);
        kieSession.update(kieSession.getFactHandle(busStop_13), busStop_13, "nextStop");
        //operation U #1329
        busStop_21.setNextStop(busStop_17);
        kieSession.update(kieSession.getFactHandle(busStop_21), busStop_21, "nextStop");
        //operation U #1330
        shuttle_6.setNextStop(busStop_18);
        kieSession.update(kieSession.getFactHandle(shuttle_6), shuttle_6, "nextStop");
        //operation U #1331
        busStop_14.setNextStop(busStop_19);
        kieSession.update(kieSession.getFactHandle(busStop_14), busStop_14, "nextStop");
        //operation U #1332
        busStop_16.setNextStop(busStop_20);
        kieSession.update(kieSession.getFactHandle(busStop_16), busStop_16, "nextStop");
        //operation U #1333
        busStop_10.setNextStop(busStop_21);
        kieSession.update(kieSession.getFactHandle(busStop_10), busStop_10, "nextStop");
        //operation U #1334
        busHub_0.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_4));
        kieSession.update(kieSession.getFactHandle(busHub_0), busHub_0, "transferShuttleList");
        //operation U #1335
        busHub_0.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_4, shuttle_5));
        kieSession.update(kieSession.getFactHandle(busHub_0), busHub_0, "transferShuttleList");
        //operation U #1336
        busStop_11.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_6));
        kieSession.update(kieSession.getFactHandle(busStop_11), busStop_11, "transferShuttleList");
        //operation U #1337
        busStop_19.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_7));
        kieSession.update(kieSession.getFactHandle(busStop_19), busStop_19, "transferShuttleList");
        //operation U #1338
        busStop_17.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_8));
        kieSession.update(kieSession.getFactHandle(busStop_17), busStop_17, "transferShuttleList");
        //operation U #1339
        busStop_12.setTransferShuttleList(Arrays.<Shuttle>asList(shuttle_9));
        kieSession.update(kieSession.getFactHandle(busStop_12), busStop_12, "transferShuttleList");
        //operation F #1340
        kieSession.fireAllRules();
        // This is the corrupted score, just to make sure the bug is reproducible
        Assert.assertEquals("-1000000000hard/0soft", scoreHolder.extractScore(0).toString());
    }
}
