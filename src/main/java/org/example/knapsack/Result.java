package org.example.knapsack;

import java.util.*;

public final class Result {
    private final Map<Integer,Integer> copies;
    private final Map<Integer,Item> itemsById;
    private final int weight;
    private final int value;

    Result(Map<Integer,Integer> copies,
           Map<Integer,Item>   itemsById,
           int weight,
           int value) {
        Objects.requireNonNull(copies);
        Objects.requireNonNull(itemsById);
        this.copies    = Collections.unmodifiableMap(new LinkedHashMap<>(copies));
        this.itemsById = itemsById;
        this.weight    = weight;
        this.value     = value;
    }

    public Map<Integer,Integer> copies() { return copies; }
    public int weight()                  { return weight; }
    public int value()                   { return value;  }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String header = String.format("%-3s %-6s %-6s %-6s",
                "id", "weight", "value", "copies");
        sb.append(header).append(System.lineSeparator());
        sb.append("─".repeat(header.length())).append(System.lineSeparator());

        copies.forEach((id, qty) -> {
            Item it = itemsById.get(id);
            sb.append(String.format("%-3d %-6d %-6d %-6d%n",
                    id, it.weight(), it.value(), qty));
        });

        sb.append("─".repeat(header.length())).append(System.lineSeparator());
        sb.append(String.format("%-3s %-6d %-6d%n", "TOTAL", weight, value));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Result r)) return false;
        return weight == r.weight &&
                value == r.value &&
                copies.equals(r.copies);
    }
    @Override
    public int hashCode() { return Objects.hash(copies, weight, value); }
}
