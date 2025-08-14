# Deque

## Overview

This is a manually implemented array-based double-ended queue that avoids the typical *circular buffer* approach. Instead of modulo arithmetic, it keeps the data block **centered in the array** and explicitly shifts/reallocates during resizes. This design is tuned for **predictable memory layout** and **balanced access** from both ends.

---

## Key Design Points

### 1. **Center-Aligned Storage**

* Items are always stored contiguously, centered in the underlying array.
* On resize, elements are copied to the middle of the new array, minimizing head/tail imbalance.

This ensures the data points are centred on resize, minimizing risk of wasting memory in heavy `addFirst()` or `addLast()` situations.

### 2. **No Modulo Arithmetic**

* Typical array deques wrap indices with `(index % capacity)`; this implementation avoids it entirely.
* This reduces index calculation overhead and can be more cache-friendly for sequential access.

### 3. **Symmetrical Growth & Shrink**

* Resize is triggered when either:

  * **Adding** to a full head or tail.
  * **Removing** when the array is ≥ 4× larger than the element count.
* Resize size is always `size * 2 + 1` to maintain an odd length (ensures perfect centering).

### 4. **Constant-Time End Operations**

* Insertion/removal at **both ends** is O(1) amortized.
* Memory reallocation during resize is O(n) but infrequent.

### 5. **Balanced Memory Efficiency**

* Grows and shrinks proportionally to active data, preventing unbounded memory bloat.
* Shrink threshold ensures you don’t constantly reallocate during minor oscillations in size.

---

## API Reference

| Method                   | Description                                             |
| ------------------------ | ------------------------------------------------------- |
| `boolean isEmpty()`      | Returns true if deque has no elements.                  |
| `int size()`             | Returns number of elements in deque.                    |
| `void addFirst(T input)` | Inserts element at front.                               |
| `void addLast(T input)`  | Inserts element at back.                                |
| `T removeFirst()`        | Removes and returns front element. Throws if empty.     |
| `T removeLast()`         | Removes and returns back element. Throws if empty.      |
| `void printDeque()`      | Debug print showing array contents + head/tail markers. |

---

## Performance Characteristics

| Operation     | Time Complexity | Notes       |
| ------------- | --------------- | ----------- |
| `addFirst`    | O(1) amortized  | Resize O(n) |
| `addLast`     | O(1) amortized  | Resize O(n) |
| `removeFirst` | O(1) amortized  | Shrink O(n) |
| `removeLast`  | O(1) amortized  | Shrink O(n) |
| `isEmpty`     | O(1)            | —           |
| `size`        | O(1)            | —           |

---

## Debug Visualization

`printDeque()` prints the internal array with:

* **H** = head index
* **T** = tail index
* **null** = empty slot
* Both markers can appear together if head and tail overlap.

Example:

```
Deque = [H, 1, 2, 3, T, null, null]
```

---

Included `DequeTest.java` file for minimal testing of the deque
