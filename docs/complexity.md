# Time and Space Complexity Analysis

Let n be the number of elements in the data structure.

## DynamicArray

| Operation         | Time Complexity                 | Explanation                                 |
| ----------------- | ------------------------------- | ------------------------------------------- |
| add(value)        | O(1) amortized, O(n) worst case | Resizing may require copying elements       |
| add(index, value) | O(n)                            | Elements may need to shift                  |
| remove(index)     | O(n)                            | Elements after the removed index shift left |
| get(index)        | O(1)                            | Direct array access                         |
| contains(value)   | O(n)                            | Linear search                               |

Space complexity: O(n).

## MyLinkedList

| Operation         | Time Complexity | Explanation                                 |
| ----------------- | --------------- | ------------------------------------------- |
| add(value)        | O(n)            | Traversal to the end without a tail pointer |
| add(index, value) | O(n)            | Traversal to the insertion position         |
| remove(index)     | O(n)            | Traversal to the target node                |
| get(index)        | O(n)            | Sequential traversal                        |
| contains(value)   | O(n)            | Linear search                               |

Space complexity: O(n), including node references.

## MinHeap

| Operation     | Time Complexity | Explanation                       |
| ------------- | --------------- | --------------------------------- |
| insert(value) | O(log n)        | Sift-up                           |
| peekMin()     | O(1)            | Minimum is stored at the root     |
| extractMin()  | O(log n)        | Sift-down after removing the root |

Space complexity: O(n).

## Benchmark Workloads

| Workload | Operation                           | Expected Complexity                          |
| -------- | ----------------------------------- | -------------------------------------------- |
| W1       | Random access                       | DynamicArray O(1), MyLinkedList O(n)         |
| W2       | contains                            | O(n) per search                              |
| W3       | Insert/remove at head               | DynamicArray O(n), MyLinkedList O(1) at head |
| W3       | Insert/remove at middle             | O(n)                                         |
| W4       | n heap insertions and n extractions | O(n log n)                                   |

The benchmark results are used to compare theoretical complexity with measured execution time and operation counters.
