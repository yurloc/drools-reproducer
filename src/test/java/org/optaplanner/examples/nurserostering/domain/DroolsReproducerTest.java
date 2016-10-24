package org.optaplanner.examples.nurserostering.domain;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.optaplanner.core.api.score.Score;
import org.optaplanner.core.api.score.holder.ScoreHolder;
import org.optaplanner.core.impl.score.buildin.hardsoft.HardSoftScoreDefinition;
import org.optaplanner.examples.nurserostering.domain.contract.Contract;
import org.optaplanner.examples.nurserostering.domain.contract.ContractLineType;
import org.optaplanner.examples.nurserostering.domain.contract.MinMaxContractLine;

import static org.junit.Assert.*;

public class DroolsReproducerTest {

    private KieContainer kieContainer;
    private KieSession kieSession;
    private final ShiftDate shiftDate_2Sun = new ShiftDate();
    private final ShiftDate shiftDate_7Fri = new ShiftDate();
    private final Shift shift_2Sun = new Shift();
    private final Shift shift_7Fri = new Shift();
    private final ShiftAssignment shiftAssignment_2Sun = new ShiftAssignment();
    private final ShiftAssignment shiftAssignment_7Fri1 = new ShiftAssignment();
    private final ShiftAssignment shiftAssignment_7Fri2 = new ShiftAssignment();
    private final Employee employeeB = new Employee();
    private final Contract contractB = new Contract();
    private final MinMaxContractLine maxWeekendsContractLine = new MinMaxContractLine();

    @Before
    public void setUp() {
        KieServices kieServices = KieServices.Factory.get();
        KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.writeKModuleXML(kieModuleModel.toXML());
        kfs.write(kieServices.getResources()
                .newClassPathResource("org/optaplanner/examples/nurserostering/solver/nurseRosteringScoreRules.drl")
                .setResourceType(ResourceType.DRL));
        kieServices.newKieBuilder(kfs).buildAll();
        kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());

        // 1 shift on Sunday 2nd
        shiftAssignment_2Sun.setShift(shift_2Sun);
        shift_2Sun.setShiftDate(shiftDate_2Sun);
        shiftDate_2Sun.setDayIndex(2);
        shiftDate_2Sun.setDayOfWeek(DayOfWeek.SUNDAY);

        // 2 shifts on Friday 7th
        shiftAssignment_7Fri1.setShift(shift_7Fri);
        shiftAssignment_7Fri2.setShift(shift_7Fri);
        shift_7Fri.setShiftDate(shiftDate_7Fri);
        shiftDate_7Fri.setDayIndex(7);
        shiftDate_7Fri.setDayOfWeek(DayOfWeek.FRIDAY);

        // employee B (going to work 2 weekends in sequence, Friday is part of weekend for this employee)
        employeeB.setName("B");
        employeeB.setContract(contractB);
        contractB.setWeekendDefinition(WeekendDefinition.FRIDAY_SATURDAY_SUNDAY);
        maxWeekendsContractLine.setContract(contractB);
        maxWeekendsContractLine.setContractLineType(ContractLineType.CONSECUTIVE_WORKING_WEEKENDS);
        maxWeekendsContractLine.setMaximumEnabled(true);
        maxWeekendsContractLine.setMaximumWeight(1);
        maxWeekendsContractLine.setMaximumValue(1);
    }

    @Test
    public void test() {
        kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("scoreHolder", new HardSoftScoreDefinition().buildScoreHolder(true));

        kieSession.insert(shiftAssignment_2Sun);
        kieSession.insert(shiftAssignment_7Fri1);
        kieSession.insert(shiftAssignment_7Fri2);
        kieSession.insert(maxWeekendsContractLine);
        kieSession.insert(employeeB);

        // B works two weekends in a row, rules fire correctly
        shiftAssignment_2Sun.setEmployee(employeeB);
        kieSession.update(kieSession.getFactHandle(shiftAssignment_2Sun), shiftAssignment_2Sun);
        shiftAssignment_7Fri1.setEmployee(employeeB);
        kieSession.update(kieSession.getFactHandle(shiftAssignment_7Fri1), shiftAssignment_7Fri1);

        System.out.println("FIRE");
        assertEquals(4, kieSession.fireAllRules());
        dumpSession(kieSession);

        // B moves to a different assignment of the same shift (and the same day) so he still works two weekends...
        shiftAssignment_7Fri1.setEmployee(null);
        kieSession.update(kieSession.getFactHandle(shiftAssignment_7Fri1), shiftAssignment_7Fri1);
        shiftAssignment_7Fri2.setEmployee(employeeB);
        kieSession.update(kieSession.getFactHandle(shiftAssignment_7Fri2), shiftAssignment_7Fri2);

        // ... however rules now fire unexpectedly (there are two weekend assignment starts and ends, one for each weekend,
        // and so there are two separate weekend assignment sequences of lenght 1 so the "2 consecutive weekends" rule
        // doesn't fire)
        System.out.println("FIRE");
        assertEquals(5, kieSession.fireAllRules()); // this assert is incorrect but it makes sure the bug reproduces

        dumpSession(kieSession);
        Score<?> score = ((ScoreHolder) kieSession.getGlobal("scoreHolder")).extractScore(0);
        assertEquals("0hard/0soft", score.toString()); // this assert is incorrect but it makes sure the bug reproduces

        // And this is how the corrupted score was detected - we make no additional changes to the facts,
        // we just insert them to a fresh session => same rules should fire.
        // Surprisingly, the following session will fire correctly, while the previous didn't.
        kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("scoreHolder", new HardSoftScoreDefinition().buildScoreHolder(true));

        kieSession.insert(shiftAssignment_2Sun);
        kieSession.insert(shiftAssignment_7Fri1);
        kieSession.insert(shiftAssignment_7Fri2);
        kieSession.insert(maxWeekendsContractLine);
        kieSession.insert(employeeB);

        System.out.println("FIRE");
        assertEquals(4, kieSession.fireAllRules());
        dumpSession(kieSession);
        assertEquals("0hard/-1soft", ((ScoreHolder) kieSession.getGlobal("scoreHolder")).extractScore(0).toString());
    }

    private void dumpSession(KieSession ks) {
        for (Object o : ks.getObjects()) {
            System.out.printf("%41s: %s%n", o.getClass().getSimpleName(), o);
        }
    }
}
