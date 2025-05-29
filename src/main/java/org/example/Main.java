package org.example;

import org.example.knapsack.Problem;
import org.example.knapsack.Result;

public class Main {
    public static void main(String[] args) {
        Problem p = new Problem(10, 81L, 2, 10);
        System.out.println(p);
        int capacity = 1999;
        Result r = p.solve(capacity);
        System.out.printf("%n capacity = %d %n", capacity);
        System.out.println(r);
    }
}