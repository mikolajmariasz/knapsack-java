package org.example.knapsack;

public record Item(int id, int weight, int value) {
    public double ratio() {
        return value / (double) weight;
    }

    @Override
    public String toString() {
        return String.format("%-3d %-6d %-6d%n", id, weight, value);
    }
}
