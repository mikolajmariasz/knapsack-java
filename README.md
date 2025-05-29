# knapsack-java

A compact Java 17 project that demonstrates the knapsack problem:

* **Generator** – builds random item sets (`Problem`) from a seed and value/weight range.  
* **Greedy solver** – Dantzig ratio-based (`Problem.solve`) that fills the pack up to a capacity.  
* **Result** – summary, printed as a table.  
* **JUnit tests** – four unit tests proving the pieces work together.

## What the tests cover
| Test                  | Purpose                                                          |
|-----------------------|------------------------------------------------------------------|
| `generatorProducesCorrectSize` | Confirms the generator returns exactly *n* items. |
| `alwaysFitsWeight`      | Asserts the solver never exceeds the given capacity. |
| `noItemFitsReturnsEmpty`      | Ensures an empty map is returned when nothing can fit. |
| `resultWeightAndValueMatchCopies` | Recomputes weight/value from the copies map to verify `Result` consistency. |
