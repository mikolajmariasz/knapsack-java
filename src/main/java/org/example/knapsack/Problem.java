package org.example.knapsack;

import java.util.*;

public final class Problem {

    private final int n;
    private final long seed;
    private final int lower;
    private final int upper;
    private final List<Item> items;
    private final Map<Integer, Item> itemsById;

    public Problem(int n, long seed, int lower, int upper) {
        if (n <= 0) throw new IllegalArgumentException("n must be >0");
        if (upper < lower) throw new IllegalArgumentException("upper<lower");

        this.n = n; this.seed = seed; this.lower = lower; this.upper = upper;

        Random rnd = new Random(seed);
        List<Item> tmp = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int w = rnd.nextInt(upper - lower + 1) + lower;
            int v = rnd.nextInt(upper - lower + 1) + lower;
            tmp.add(new Item(i, w, v));
        }
        this.items = List.copyOf(tmp);

        Map<Integer, Item> map = new LinkedHashMap<>();
        items.forEach(it -> map.put(it.id(), it));
        this.itemsById = Collections.unmodifiableMap(map);
    }

    public Result solve(int capacity) {
        if (capacity <= 0) return new Result(Map.of(), itemsById, 0, 0);

        List<Item> sorted = items.stream()
                .sorted(Comparator.comparingDouble(Item::ratio).reversed())
                .toList();

        Map<Integer, Integer> copies = new LinkedHashMap<>();
        int totalW = 0, totalV = 0;

        for (Item it : sorted) {
            int canCopy = (capacity - totalW) / it.weight();
            if (canCopy > 0) {
                copies.put(it.id(), canCopy);
                totalW += canCopy * it.weight();
                totalV += canCopy * it.value();
            }
            if (totalW == capacity) break;
        }
        return new Result(copies, itemsById, totalW, totalV);
    }

    public List<Item> items()            { return items;     }
    public Map<Integer, Item> itemsById(){ return itemsById; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format(
                "Problem (n=%d, seed=%d, range=[%d,%d])%n",
                n, seed, lower, upper));

        String header = String.format("%-3s %-6s %-6s", "id", "weight", "value");
        sb.append(header).append(System.lineSeparator());
        sb.append("─".repeat(header.length())).append(System.lineSeparator());
        items.forEach(sb::append);
        return sb.toString();
    }
}
