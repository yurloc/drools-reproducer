package org.optaplanner.testgen.drools1174;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Drools1174Test {

    KieContainer kieContainer;
    KieSession kieSession;
    private final String doctor = "D";
    private final String politician = "P";
    private final Long table1 = 0L;
    private final Long table2 = 1L;
    private final SeatDesignation seatDesignation0 = new SeatDesignation(0, politician);
    private final SeatDesignation seatDesignation1 = new SeatDesignation(1, politician);
    private final SeatDesignation seatDesignation2 = new SeatDesignation(2, politician);
    private final SeatDesignation seatDesignation3 = new SeatDesignation(3, doctor);
    private final SeatDesignation seatDesignation4 = new SeatDesignation(4, doctor);

    @Before
    public void setUp() {
        String rule = "package pkg;\n"
                + "    dialect \"java\"\n"
                + "\n"
                + "import " + Drools1174Test.SeatDesignation.class.getCanonicalName() + ";\n"
                + "\n"
                + "rule \"twoSameJobTypePerTable\"\n"
                + "    when\n"
                + "        $jobType : String()\n"
                + "        $table : Long()\n"
                + "        not (\n"
                + "            SeatDesignation(guestJobType == $jobType, seatTable == $table, $leftId : id)\n"
                + "            and SeatDesignation(guestJobType == $jobType, seatTable == $table, id > $leftId)\n"
                + "        )\n"
                + "    then\n"
                + "end\n"
                + "";
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/dinnerPartyScoreRules.drl", rule);
        kieServices.newKieBuilder(kfs).buildAll();
        kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        kieSession = kieContainer.newKieSession();

        seatDesignation0.setSeatTable(table2);
        seatDesignation2.setSeatTable(table2);
        seatDesignation3.setSeatTable(table1);
        seatDesignation4.setSeatTable(table1);

        kieSession.insert(seatDesignation0);
        kieSession.insert(seatDesignation1);
        kieSession.insert(seatDesignation2);
        kieSession.insert(seatDesignation3);
        kieSession.insert(seatDesignation4);
        kieSession.insert(politician);
        kieSession.insert(doctor);
        kieSession.insert(table1);
        kieSession.insert(table2);
    }

    @Test
    public void test() {
        Assert.assertEquals(2, kieSession.fireAllRules());
        // no change but the update is necessary
        kieSession.update(kieSession.getFactHandle(seatDesignation3), seatDesignation3);
        seatDesignation2.setSeatTable(null);
        kieSession.update(kieSession.getFactHandle(seatDesignation2), seatDesignation2);
        seatDesignation1.setSeatTable(table2);
        kieSession.update(kieSession.getFactHandle(seatDesignation1), seatDesignation1);
        // This is the corrupted score, just to make sure the bug is reproducible
        // expected: 0
        Assert.assertEquals(1, kieSession.fireAllRules());

        // Insert everything into a fresh session to see the uncorrupted score
        kieSession = kieContainer.newKieSession();
        kieSession.insert(seatDesignation0);
        kieSession.insert(seatDesignation1);
        kieSession.insert(seatDesignation2);
        kieSession.insert(seatDesignation3);
        kieSession.insert(seatDesignation4);
        kieSession.insert(politician);
        kieSession.insert(doctor);
        kieSession.insert(table1);
        kieSession.insert(table2);
        Assert.assertEquals(2, kieSession.fireAllRules());
    }

    public static class SeatDesignation {

        private final int id;
        private final String guestJobType;
        private Long seatTable;

        public SeatDesignation(int id, String guestJobType) {
            this.id = id;
            this.guestJobType = guestJobType;
        }

        public String getGuestJobType() {
            return guestJobType;
        }

        public int getId() {
            return id;
        }

        public Long getSeatTable() {
            return seatTable;
        }

        public void setSeatTable(Long seatTable) {
            this.seatTable = seatTable;
        }
    }
}
