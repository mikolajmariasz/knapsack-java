package org.example.knapsack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemTest {

    @Test
    void generatorProducesCorrectSize() {
        Problem p = new Problem(7, 1L, 1, 10);
        assertEquals(7, p.items().size());
    }

    @Test
    void alwaysFitsWeight() {
        Problem p = new Problem(5, 1L, 1, 5);
        int capacity = 9;
        Result r = p.solve(capacity);
        assertTrue(r.weight() <= capacity);
    }

    @Test
    void noItemFitsReturnsEmpty() {
        Problem p = new Problem(3, 1L, 6, 10);
        Result r = p.solve(5);
        assertTrue(r.copies().isEmpty());
    }

    @Test
    void resultWeightAndValueMatchCopies() {
        Problem p = new Problem(6, 7L, 1, 5);
        int capacity = 12;

        Result r = p.solve(capacity);

        int expectedWeight = 0;
        int expectedValue  = 0;
        for (var e : r.copies().entrySet()) {
            Item it = p.itemsById().get(e.getKey());
            expectedWeight += it.weight() * e.getValue();
            expectedValue  += it.value()  * e.getValue();
        }

        assertEquals(expectedWeight, r.weight(), "Weight mismatch");
        assertEquals(expectedValue,  r.value(),  "Value mismatch");
    }

}
