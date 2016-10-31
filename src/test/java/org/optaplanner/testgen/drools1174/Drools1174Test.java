package org.optaplanner.testgen.drools1174;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.optaplanner.core.api.score.holder.ScoreHolder;
import org.optaplanner.core.impl.score.buildin.simple.SimpleScoreDefinition;
import org.optaplanner.examples.dinnerparty.domain.Guest;
import org.optaplanner.examples.dinnerparty.domain.Job;
import org.optaplanner.examples.dinnerparty.domain.JobType;
import org.optaplanner.examples.dinnerparty.domain.Seat;
import org.optaplanner.examples.dinnerparty.domain.SeatDesignation;
import org.optaplanner.examples.dinnerparty.domain.Table;

public class Drools1174Test {

    KieContainer kieContainer;
    KieSession kieSession;
    ScoreHolder scoreHolder = new SimpleScoreDefinition().buildScoreHolder(true);
    private final SeatDesignation seatDesignation0 = new SeatDesignation();
    private final SeatDesignation seatDesignation1 = new SeatDesignation();
    private final SeatDesignation seatDesignation2 = new SeatDesignation();
    private final SeatDesignation seatDesignation3 = new SeatDesignation();
    private final SeatDesignation seatDesignation4 = new SeatDesignation();
    private final Guest guest_P1 = new Guest();
    private final Guest guest_P2 = new Guest();
    private final Guest guest_P3 = new Guest();
    private final Guest guest_D1 = new Guest();
    private final Guest guest_D2 = new Guest();
    private final Job job_Doc1 = new Job();
    private final Job job_Doc2 = new Job();
    private final Job job_Pol1 = new Job();
    private final Job job_Pol2 = new Job();
    private final Seat seat1A = new Seat();
    private final Seat seat1B = new Seat();
    private final Seat seat2A = new Seat();
    private final Seat seat2B = new Seat();
    private final Table table1 = new Table();
    private final Table table2 = new Table();

    @Before
    public void setUp() {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write(kieServices.getResources()
                .newClassPathResource("org/optaplanner/testgen/drools1179/dinnerPartyScoreRules.drl"));
        kieServices.newKieBuilder(kfs).buildAll();
        kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("scoreHolder", scoreHolder);

        seatDesignation0.setId(0L);
        seatDesignation0.setSeat(seat2A);
        seatDesignation0.setGuest(guest_P1);
        guest_P1.setJob(job_Pol2);
        job_Pol2.setJobType(JobType.POLITICIAN);
        seat2A.setTable(table2);

        // seat designation 1
        seatDesignation1.setId(1L);
        seatDesignation1.setGuest(guest_P2);
        guest_P2.setJob(job_Pol1);
        job_Pol1.setJobType(JobType.POLITICIAN);
        // seat designation 2
        seatDesignation2.setId(2L);
        seatDesignation2.setSeat(seat2B);
        seatDesignation2.setGuest(guest_P3);
        guest_P3.setJob(job_Pol1);
        seat2B.setTable(table2);
        // seat designation 3
        seatDesignation3.setId(3L);
        seatDesignation3.setSeat(seat1A);
        seatDesignation3.setGuest(guest_D1);
        guest_D1.setJob(job_Doc1);
        job_Doc1.setJobType(JobType.DOCTOR);
        seat1A.setTable(table1);
        // seat designation 4
        seatDesignation4.setId(4L);
        seatDesignation4.setSeat(seat1B);
        seatDesignation4.setGuest(guest_D2);
        guest_D2.setJob(job_Doc2);
        job_Doc2.setJobType(JobType.DOCTOR);
        seat1B.setTable(table1);

        kieSession.insert(seatDesignation0);
        kieSession.insert(seatDesignation1);
        kieSession.insert(seatDesignation2);
        kieSession.insert(seatDesignation3);
        kieSession.insert(seatDesignation4);
        kieSession.insert(JobType.POLITICIAN);
        kieSession.insert(JobType.DOCTOR);
        kieSession.insert(table1);
        kieSession.insert(table2);
    }

    @Test
    public void test() {
        kieSession.fireAllRules();
        seatDesignation3.setSeat(seat1A);
        kieSession.update(kieSession.getFactHandle(seatDesignation3), seatDesignation3);
        seatDesignation2.setSeat(null);
        kieSession.update(kieSession.getFactHandle(seatDesignation2), seatDesignation2);
        seatDesignation1.setSeat(seat2B);
        kieSession.update(kieSession.getFactHandle(seatDesignation1), seatDesignation1);
        kieSession.fireAllRules();
        // This is the corrupted score, just to make sure the bug is reproducible
        Assert.assertEquals("-300", scoreHolder.extractScore(0).toString());
        kieSession = kieContainer.newKieSession();
        scoreHolder = new SimpleScoreDefinition().buildScoreHolder(true);
        kieSession.setGlobal("scoreHolder", scoreHolder);

        // Insert everything into a fresh session to see the uncorrupted score
        kieSession.insert(seatDesignation0);
        kieSession.insert(seatDesignation1);
        kieSession.insert(seatDesignation2);
        kieSession.insert(seatDesignation3);
        kieSession.insert(seatDesignation4);
        kieSession.insert(JobType.POLITICIAN);
        kieSession.insert(JobType.DOCTOR);
        kieSession.insert(table1);
        kieSession.insert(table2);
        kieSession.fireAllRules();
        Assert.assertEquals("-200", scoreHolder.extractScore(0).toString());
    }
}
