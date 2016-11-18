package org.optaplanner.testgen.drools1174;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Drools1174Test {

    @Test
    public void test() {
        KieContainer kieContainer;
        KieSession kieSession;
        String doctor = "D";
        String politician = "P";
        Long table1 = 0L;
        Long table2 = 1L;
        Seat seat0 = new Seat(0, politician);
        Seat seat1 = new Seat(1, politician);
        Seat seat2 = new Seat(2, politician);
        Seat seat3 = new Seat(3, doctor);
        Seat seat4 = new Seat(4, doctor);

        String rule = "package pkg;\n"
                + "    dialect \"java\"\n"
                + "\n"
                + "import " + Drools1174Test.Seat.class.getCanonicalName() + ";\n"
                + "\n"
                + "rule \"twoSameJobTypePerTable\"\n"
                + "    when\n"
                + "        $jobType : String()\n"
                + "        $table : Long()\n"
                + "        not (\n"
                + "            Seat(guestJob == $jobType, table == $table, $leftId : id)\n"
                + "            and Seat(guestJob == $jobType, table == $table, id > $leftId)\n"
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

        seat0.setTable(table2);
        seat2.setTable(table2);
        seat3.setTable(table1);
        seat4.setTable(table1);

        kieSession.insert(seat0);
        kieSession.insert(seat1);
        kieSession.insert(seat2);
        kieSession.insert(seat3);
        kieSession.insert(seat4);
        kieSession.insert(politician);
        kieSession.insert(doctor);
        kieSession.insert(table1);
        kieSession.insert(table2);

        Assert.assertEquals(2, kieSession.fireAllRules());
        // demonstrate that no additional rules fire when there are no fact changes
        Assert.assertEquals(0, kieSession.fireAllRules());
        // no change but the update is necessary
        kieSession.update(kieSession.getFactHandle(seat3), seat3);
        seat2.setTable(null);
        kieSession.update(kieSession.getFactHandle(seat2), seat2);
        seat1.setTable(table2);
        kieSession.update(kieSession.getFactHandle(seat1), seat1);
        // This is the corrupted score, just to make sure the bug is reproducible
        // expected: 0 because the conditions haven't changed
        Assert.assertEquals(1, kieSession.fireAllRules());
        Assert.assertEquals(0, kieSession.fireAllRules());

        // Insert everything into a fresh session to see the uncorrupted score
        kieSession = kieContainer.newKieSession();
        kieSession.insert(seat0);
        kieSession.insert(seat1);
        kieSession.insert(seat2);
        kieSession.insert(seat3);
        kieSession.insert(seat4);
        kieSession.insert(politician);
        kieSession.insert(doctor);
        kieSession.insert(table1);
        kieSession.insert(table2);
        Assert.assertEquals(2, kieSession.fireAllRules());
        Assert.assertEquals(0, kieSession.fireAllRules());
    }

    public static class Seat {

        private final int id;
        private final String guestJob;
        private Long table;

        public Seat(int id, String guestJob) {
            this.id = id;
            this.guestJob = guestJob;
        }

        public String getGuestJob() {
            return guestJob;
        }

        public int getId() {
            return id;
        }

        public Long getTable() {
            return table;
        }

        public void setTable(Long table) {
            this.table = table;
        }
    }
}
