package org.optaplanner.testgen.drools1174;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.utils.KieHelper;

public class Drools1174Test {

    @Test
    public void test() {
        // DROOLS-1174
        String rule = "import " + Drools1174Test.Seat.class.getCanonicalName() + ";\n"
                + "\n"
                + "rule twoSameJobTypePerTable when\n"
                + "    $job: String()\n"
                + "    $table : Long()\n"
                + "    not (\n"
                + "        Seat( guestJob == $job, table == $table, $leftId : id )\n"
                + "        and Seat( guestJob == $job, table == $table, id > $leftId )\n"
                + "    )\n"
                + "then\n"
                + "        System.out.println(\"Table \" + $table + \", \" + $job);\n"
                + "end\n"
                + "";

        KieSession kieSession = new KieHelper().addContent(rule, ResourceType.DRL).build().newKieSession();

        String doctor = "D";
        String politician = "P";
        Long table1 = 1L;
        Long table2 = 2L;
        Seat seat0 = new Seat(0, politician, table2);
        Seat seat1 = new Seat(1, politician, null);
        Seat seat2 = new Seat(2, politician, table2);
        Seat seat3 = new Seat(3, doctor, table1);
        Seat seat4 = new Seat(4, doctor, table1);

        kieSession.insert(seat0);
        FactHandle fh1 = kieSession.insert(seat1);
        FactHandle fh2 = kieSession.insert(seat2);
        FactHandle fh3 = kieSession.insert(seat3);
        kieSession.insert(seat4);
        kieSession.insert(politician);
        kieSession.insert(doctor);
        kieSession.insert(table1);
        kieSession.insert(table2);

        System.out.println("FIRE");
        assertEquals(2, kieSession.fireAllRules());
        kieSession.update(fh3, seat3); // no change but the update is necessary to reproduce the bug
        kieSession.update(fh2, seat2.setTable(null));
        kieSession.update(fh1, seat1.setTable(table2));
        // This is the corrupted score, just to make sure the bug is reproducible
        // expected: 0 because the conditions haven't changed
        System.out.println("FIRE");
        assertEquals(1, kieSession.fireAllRules());
    }

    public static class Seat {

        private final int id;
        private final String guestJob;
        private Long table;

        public Seat(int id, String guestJob, Long table) {
            this.id = id;
            this.guestJob = guestJob;
            this.table = table;
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

        public Seat setTable(Long table) {
            this.table = table;
            return this;
        }
    }
}
