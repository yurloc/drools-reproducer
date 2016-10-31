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

public class Drools1174Test {

    KieContainer kieContainer;
    KieSession kieSession;
    ScoreHolder scoreHolder = new SimpleScoreDefinition().buildScoreHolder(true);
    private final SeatDesignation seatDesignation0 = new SeatDesignation();
    private final SeatDesignation seatDesignation1 = new SeatDesignation();
    private final SeatDesignation seatDesignation2 = new SeatDesignation();
    private final SeatDesignation seatDesignation3 = new SeatDesignation();
    private final SeatDesignation seatDesignation4 = new SeatDesignation();
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

        seatDesignation0.setId(0);
        seatDesignation0.setSeatTable(table2);
        seatDesignation0.setGuestJob("P21");
        seatDesignation0.setGuestJobType(JobType.POLITICIAN);

        // seat designation 1
        seatDesignation1.setId(1);
        seatDesignation1.setGuestJob("P11");
        seatDesignation1.setGuestJobType(JobType.POLITICIAN);
        // seat designation 2
        seatDesignation2.setId(2);
        seatDesignation2.setSeatTable(table2);
        seatDesignation2.setGuestJob("P12");
        seatDesignation2.setGuestJobType(JobType.POLITICIAN);
        // seat designation 3
        seatDesignation3.setId(3);
        seatDesignation3.setSeatTable(table1);
        seatDesignation3.setGuestJob("D11");
        seatDesignation3.setGuestJobType(JobType.DOCTOR);
        // seat designation 4
        seatDesignation4.setId(4);
        seatDesignation4.setSeatTable(table1);
        seatDesignation4.setGuestJob("D21");
        seatDesignation4.setGuestJobType(JobType.DOCTOR);

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
        seatDesignation3.setSeatTable(table1);
        kieSession.update(kieSession.getFactHandle(seatDesignation3), seatDesignation3);
        seatDesignation2.setSeatTable(null);
        kieSession.update(kieSession.getFactHandle(seatDesignation2), seatDesignation2);
        seatDesignation1.setSeatTable(table2);
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

    public static enum JobType {
        DOCTOR, POLITICIAN
    }

    public static class SeatDesignation {

        private int id;
        private Table seatTable;
        private String guestJob;
        private JobType guestJobType;

        public boolean differentKindIfNeeded(String otherGuestJob) {
            return !guestJob.equals(otherGuestJob);
        }

        public String getGuestJob() {
            return guestJob;
        }

        public void setGuestJob(String guestJob) {
            this.guestJob = guestJob;
        }

        public JobType getGuestJobType() {
            return guestJobType;
        }

        public void setGuestJobType(JobType guestJobType) {
            this.guestJobType = guestJobType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Table getSeatTable() {
            return seatTable;
        }

        public void setSeatTable(Table seatTable) {
            this.seatTable = seatTable;
        }
    }

    public static class Table {
    }
}
