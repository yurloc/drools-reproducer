# DROOLS-1326

```
Running org.optaplanner.examples.nurserostering.domain.DroolsReproducerTest
2016-10-24 11:33:38,901 [main] INFO  Discovered kie.conf url=jar:file:/home/jlocker/.m2/repository/org/optaplanner/optaplanner-core/7.0.0-SNAPSHOT/optaplanner-core-7.0.0-SNAPSHOT.jar!/META-INF/kie.conf 
2016-10-24 11:33:38,952 [main] INFO  Adding Assembler org.optaplanner.core.impl.solver.kie.KieSolverAssemblerService 
2016-10-24 11:33:39,611 [main] INFO  KieModule was added: MemoryKieModule[releaseId=org.default:artifact:1.0.0-SNAPSHOT]
2016-10-24 11:33:39,612 [main] DEBUG Cannot load a KieRepositoryScanner, using the DummyKieScanner
2016-10-24 11:33:39,643 [main] DEBUG Starting Engine in PHREAK mode
FIRE
                          ShiftAssignment: null/null
                          ShiftAssignment: null/null
                          ShiftAssignment: null/null
                       MinMaxContractLine: null-CONSECUTIVE_WORKING_WEEKENDS
                                 Employee: B
EmployeeConsecutiveWeekendAssignmentStart: B weekend 2 - ...
  EmployeeConsecutiveWeekendAssignmentEnd: B weekend ... - 9
                  EmployeeWeekendSequence: B is working the weekend of 2 - 9
FIRE
                          ShiftAssignment: null/null
                          ShiftAssignment: null/null
                          ShiftAssignment: null/null
                       MinMaxContractLine: null-CONSECUTIVE_WORKING_WEEKENDS
                                 Employee: B
EmployeeConsecutiveWeekendAssignmentStart: B weekend 2 - ...
EmployeeConsecutiveWeekendAssignmentStart: B weekend 9 - ...
  EmployeeConsecutiveWeekendAssignmentEnd: B weekend ... - 2
  EmployeeConsecutiveWeekendAssignmentEnd: B weekend ... - 9
                  EmployeeWeekendSequence: B is working the weekend of 2 - 2
                  EmployeeWeekendSequence: B is working the weekend of 9 - 9
FIRE
                          ShiftAssignment: null/null
                          ShiftAssignment: null/null
                          ShiftAssignment: null/null
                       MinMaxContractLine: null-CONSECUTIVE_WORKING_WEEKENDS
                                 Employee: B
EmployeeConsecutiveWeekendAssignmentStart: B weekend 2 - ...
  EmployeeConsecutiveWeekendAssignmentEnd: B weekend ... - 9
                  EmployeeWeekendSequence: B is working the weekend of 2 - 9
```
