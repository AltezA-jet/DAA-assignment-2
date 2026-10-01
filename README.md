# DAA Assignment 2 — Data Structures

## Project Overview

This project implements three fundamental data structures in Java 17:

* DynamicArray
* MyLinkedList
* MinHeap

All structures are implemented from scratch without using Java Collection Framework implementations for their internal storage.

## Technologies

* Java 17
* Maven
* JUnit 5
* Python 3
* Matplotlib

## Implemented Operations

### DynamicArray

* add(value)
* add(index, value)
* remove(index)
* get(index)
* contains(value)

### MyLinkedList

* add(value)
* add(index, value)
* remove(index)
* get(index)
* contains(value)

### MinHeap

* insert(value)
* peekMin()
* extractMin()

## Testing

Run unit tests:

```bash
mvn clean test
```

All 30 unit tests passed successfully.

## Benchmark Workloads

* W1 — Random Access
* W2 — Search
* W3 — Insert and Remove at Head and Middle
* W4 — MinHeap Insert and Extract Minimum

Input sizes:

100, 1000, 10000, 100000.

Each benchmark uses one warm-up run and four measured runs. The reported execution time is the median of the measured runs.

## Results

Benchmark data is stored in:

`results/results.csv`

Performance plots are stored in:

`results/plots/`

## Running the Benchmark

```bash
mvn exec:java -Dexec.mainClass=com.example.daa.benchmark.BenchmarkRunner -Dexec.classpathScope=compile
```

## Generating Plots

```bash
py -m pip install matplotlib
py scripts/plot_results.py
```

