
package com.example.daa.benchmark;

import com.example.daa.metrics.Metrics;
import com.example.daa.structures.DynamicArray;
import com.example.daa.structures.MyLinkedList;
import com.example.daa.structures.MinHeap;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class BenchmarkRunner {

    private static final int[] SIZES = {100, 1000, 10000, 100000};

    private static final int GET_OPERATIONS = 10000;
    private static final int SEARCH_OPERATIONS = 1000;
    private static final int UPDATE_OPERATIONS = 1000;

    private static final int TOTAL_RUNS = 5;
    private static final int WARMUP_RUNS = 1;

    private static final String CSV_PATH = "results/results.csv";

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Path.of("results"));

        try (FileWriter writer = new FileWriter(CSV_PATH)) {
            writer.write("workload,description,variant,structure,n,time_ms,steps,moves,comparisons\n");

            printHeader();

            for (int n : SIZES) {
                System.out.println();
                System.out.println("============================================================");
                System.out.println("                    DATA SIZE: " + n);
                System.out.println("============================================================");

                runW1(writer, n);
                runW2(writer, n);
                runW3(writer, n, "head");
                runW3(writer, n, "middle");
                runW4(writer, n);
            }
        }

        System.out.println();
        System.out.println("============================================================");
        System.out.println("Benchmark completed successfully.");
        System.out.println("CSV file: " + CSV_PATH);
        System.out.println("============================================================");
    }

    private static void printHeader() {
        System.out.println("DAA ASSIGNMENT 2 - BENCHMARK RESULTS");
        System.out.println("------------------------------------");
        System.out.println("W1 - Random Access: get(index)");
        System.out.println("W2 - Search: 500 present + 500 absent values");
        System.out.println("W3 - Insert/Remove at Head and Middle");
        System.out.println("W4 - MinHeap: insert all + extract all minimums");
        System.out.println();
        System.out.printf(
                "%-5s %-18s %-9s %12s %12s %12s %12s%n",
                "Work", "Structure", "N", "Time(ms)",
                "Steps", "Moves", "Comparisons"
        );
        System.out.println("--------------------------------------------------------------------------");
    }

    private static int[] generateData(int n) {
        Random random = new Random(42);
        int[] data = new int[n];

        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt(n * 10);
        }

        return data;
    }

    private static void runW1(FileWriter writer, int n) throws IOException {
        int[] data = generateData(n);
        Random random = new Random(42);
        int[] indexes = new int[GET_OPERATIONS];

        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = random.nextInt(n);
        }

        benchmark(writer, "W1", "Random Access get(index)", "-", "DynamicArray",
                n, () -> {
                    DynamicArray array = new DynamicArray();
                    for (int value : data) array.add(value);

                    Metrics metrics = array.getMetrics();
                    metrics.reset();

                    long checksum = 0;
                    for (int index : indexes) checksum += array.get(index);

                    if (checksum == Long.MIN_VALUE) System.out.print("");
                    return metrics;
                });

        benchmark(writer, "W1", "Random Access get(index)", "-", "MyLinkedList",
                n, () -> {
                    MyLinkedList list = new MyLinkedList();
                    for (int value : data) list.add(value);

                    Metrics metrics = list.getMetrics();
                    metrics.reset();

                    long checksum = 0;
                    for (int index : indexes) checksum += list.get(index);

                    if (checksum == Long.MIN_VALUE) System.out.print("");
                    return metrics;
                });
    }

    private static void runW2(FileWriter writer, int n) throws IOException {
        int[] data = generateData(n);
        int[] queries = new int[SEARCH_OPERATIONS];

        Random random = new Random(42);

        for (int i = 0; i < SEARCH_OPERATIONS / 2; i++) {
            queries[i] = data[random.nextInt(n)];
        }

        for (int i = SEARCH_OPERATIONS / 2; i < SEARCH_OPERATIONS; i++) {
            queries[i] = n * 10 + i + 1;
        }

        benchmark(writer, "W2", "Search: 500 present, 500 absent", "-", "DynamicArray",
                n, () -> {
                    DynamicArray array = new DynamicArray();
                    for (int value : data) array.add(value);

                    Metrics metrics = array.getMetrics();
                    metrics.reset();

                    int found = 0;
                    for (int query : queries) {
                        if (array.contains(query)) found++;
                    }

                    if (found < 0) System.out.print("");
                    return metrics;
                });

        benchmark(writer, "W2", "Search: 500 present, 500 absent", "-", "MyLinkedList",
                n, () -> {
                    MyLinkedList list = new MyLinkedList();
                    for (int value : data) list.add(value);

                    Metrics metrics = list.getMetrics();
                    metrics.reset();

                    int found = 0;
                    for (int query : queries) {
                        if (list.contains(query)) found++;
                    }

                    if (found < 0) System.out.print("");
                    return metrics;
                });
    }

    private static void runW3(FileWriter writer, int n, String variant)
            throws IOException {
        int[] data = generateData(n);
        String description = variant.equals("head")
                ? "Insert/Remove at Head"
                : "Insert/Remove at Middle";

        benchmark(writer, "W3", description, variant, "DynamicArray",
                n, () -> {
                    DynamicArray array = new DynamicArray();
                    for (int value : data) array.add(value);

                    Metrics metrics = array.getMetrics();
                    metrics.reset();

                    int index = variant.equals("head") ? 0 : n / 2;

                    for (int i = 0; i < UPDATE_OPERATIONS; i++) {
                        array.add(index, i);
                    }

                    for (int i = 0; i < UPDATE_OPERATIONS; i++) {
                        array.remove(index);
                    }

                    return metrics;
                });

        benchmark(writer, "W3", description, variant, "MyLinkedList",
                n, () -> {
                    MyLinkedList list = new MyLinkedList();
                    for (int value : data) list.add(value);

                    Metrics metrics = list.getMetrics();
                    metrics.reset();

                    int index = variant.equals("head") ? 0 : n / 2;

                    for (int i = 0; i < UPDATE_OPERATIONS; i++) {
                        list.add(index, i);
                    }

                    for (int i = 0; i < UPDATE_OPERATIONS; i++) {
                        list.remove(index);
                    }

                    return metrics;
                });
    }

    private static void runW4(FileWriter writer, int n) throws IOException {
        int[] data = generateData(n);

        benchmark(writer, "W4", "MinHeap insert + extractMin", "-", "MinHeap",
                n, () -> {
                    MinHeap heap = new MinHeap();

                    Metrics metrics = heap.getMetrics();
                    metrics.reset();

                    for (int value : data) {
                        heap.insert(value);
                    }

                    int previous = Integer.MIN_VALUE;

                    for (int i = 0; i < n; i++) {
                        int current = heap.extractMin();

                        if (current < previous) {
                            throw new IllegalStateException(
                                    "Heap extraction order is incorrect"
                            );
                        }

                        previous = current;
                    }

                    return metrics;
                });
    }

    private static void benchmark(
            FileWriter writer,
            String workload,
            String description,
            String variant,
            String structure,
            int n,
            BenchmarkTask task
    ) throws IOException {

        double[] times = new double[TOTAL_RUNS - WARMUP_RUNS];

        long steps = 0;
        long moves = 0;
        long comparisons = 0;

        for (int run = 0; run < TOTAL_RUNS; run++) {
            long start = System.nanoTime();
            Metrics metrics = task.run();
            long end = System.nanoTime();

            if (run >= WARMUP_RUNS) {
                times[run - WARMUP_RUNS] = (end - start) / 1_000_000.0;

                steps = metrics.getSteps();
                moves = metrics.getMoves();
                comparisons = metrics.getComparisons();
            }
        }

        Arrays.sort(times);
        double median = (times[1] + times[2]) / 2.0;

        System.out.printf(
                Locale.US,
                "%-5s %-18s %-9d %12.3f %12d %12d %12d%n",
                workload, structure, n, median, steps, moves, comparisons
        );

        writer.write(String.format(
                Locale.US,
                "%s,%s,%s,%s,%d,%.3f,%d,%d,%d%n",
                workload, description, variant, structure, n,
                median, steps, moves, comparisons
        ));
    }

    @FunctionalInterface
    private interface BenchmarkTask {
        Metrics run();
    }
}