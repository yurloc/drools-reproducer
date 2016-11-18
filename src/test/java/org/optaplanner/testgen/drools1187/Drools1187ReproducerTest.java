package org.optaplanner.testgen.drools1187;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;

import static org.junit.Assert.*;

import org.kie.api.runtime.rule.FactHandle;

public class Drools1187ReproducerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void test() {
        String drl = "import " + Drools1187ReproducerTest.ShiftAssignment.class.getCanonicalName() + ";\n"
                + "\n"
                + "rule insertEmployeeConsecutiveWeekendAssignmentStart when\n"
                + "    ShiftAssignment(\n"
                + "        weekend == true,\n"
                + "        $employee : employee, employee != null,\n"
                + "        $week : week\n"
                + "    )\n"
                + "    // The first working weekend has no working weekend before it\n"
                + "    not ShiftAssignment(\n"
                + "        weekend == true,\n"
                + "        employee == $employee,\n"
                + "        week == ($week - 1)\n"
                + "    )\n"
                + "then\n"
                + "end";

        KieSession kieSession = new KieHelper().addContent(drl, ResourceType.DRL).build().newKieSession();

        ShiftAssignment shiftAssignment1 = new ShiftAssignment(0, 4);
        ShiftAssignment shiftAssignment2 = new ShiftAssignment(1, 5);
        ShiftAssignment shiftAssignment3 = new ShiftAssignment(2, 6);
        ShiftAssignment shiftAssignment4 = new ShiftAssignment(2, 6);
        ShiftAssignment shiftAssignment5 = new ShiftAssignment(2, 0);
        ShiftAssignment shiftAssignment6 = new ShiftAssignment(3, 0);

        String employeeA = "Stan";
        String employeeB = "Susan";
        String employeeC = "Fred";

        shiftAssignment4.setEmployee(employeeB);
        shiftAssignment5.setEmployee(employeeA);

        FactHandle fh1 = kieSession.insert(shiftAssignment1);
        FactHandle fh2 = kieSession.insert(shiftAssignment2);
        FactHandle fh3 = kieSession.insert(shiftAssignment3);
        FactHandle fh4 = kieSession.insert(shiftAssignment4);
        FactHandle fh5 = kieSession.insert(shiftAssignment5);
        FactHandle fh6 = kieSession.insert(shiftAssignment6);

        assertEquals(2, kieSession.fireAllRules());

        kieSession.update(fh6, shiftAssignment6.setEmployee(employeeA));
        kieSession.update(fh1, shiftAssignment1.setEmployee(employeeA));
        kieSession.update(fh2, shiftAssignment2.setEmployee(employeeC));

        assertEquals(1, kieSession.fireAllRules());

        kieSession.update(fh4, shiftAssignment4.setEmployee(employeeB));
        kieSession.update(fh3, shiftAssignment3.setEmployee(employeeB));
        kieSession.update(fh5, shiftAssignment5.setEmployee(employeeB));
        kieSession.update(fh2, shiftAssignment2.setEmployee(employeeA));
        kieSession.update(fh4, shiftAssignment4.setEmployee(employeeA));

        thrown.expect(NullPointerException.class);
        kieSession.fireAllRules();
    }

    public class ShiftAssignment {

        private final int week;
        private final int dayOfWeek;
        private String employee;

        public ShiftAssignment(int week, int dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            this.week = week;
        }

        public String getEmployee() {
            return employee;
        }

        public ShiftAssignment setEmployee(String employee) {
            this.employee = employee;
            return this;
        }

        public int getWeek() {
            return week;
        }

        public boolean isWeekend() {
            if (employee == null) {
                return false;
            }
            return dayOfWeek == 6 || dayOfWeek == 0 || dayOfWeek == 5 && hasWeekendOnFriday(employee);
        }

        private boolean hasWeekendOnFriday(String employee) {
            return employee.startsWith("F");
        }

    }
}
