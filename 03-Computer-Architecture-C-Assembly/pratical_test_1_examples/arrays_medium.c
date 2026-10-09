#include <stdint.h>
#include <stddef.h>

/* ==========================================================================
 * ARRAYS MEDIUM (M)
 * ========================================================================== */

// M-1 — MEDIUM — ARRAYS
int count_unique(int *arr, int length)
int unique_total = 0;
    for (int i = 0; i < length; i++) {
        int appearances = 0;
        for (int j = 0; j < length; j++) {
            if (arr[i] == arr[j]) {
                appearances++;
            }
        }
        if (appearances == 1) {
            unique_total++;
        }
    }
    return unique_total;
}
/* arr:     Pointer to the start of an integer array
   length:  Number of elements
   returns: The number of elements that appear EXACTLY once in the array
   Example 1: count_unique({1, 2, 2, 3, 4, 1}, 6) returns 2 (3 and 4)
   Example 2: count_unique({1, 1, 1}, 3) returns 0
*/

// M-2 — MEDIUM — ARRAYS
int longest_consecutive_run(int *arr, int length)
if (length == 0) return 0;
    int max_run = 1;
    int current_run = 1;

    for (int i = 1; i < length; i++) {
        if (arr[i] == arr[i-1]) {
            current_run++;
        }
        else {
            current_run = 1;
        }
        if (current_run > max_run) {
            max_run = current_run;
        }
    }

    return max_run;
}
/* arr:     Pointer to the start of an integer array
   length:  Number of elements
   returns: The length of the longest sequence of identical consecutive values
   Example 1: longest_consecutive_run({1, 2, 2, 2, 3, 3}, 6) returns 3
   Example 2: longest_consecutive_run({1, 2, 3, 4}, 4) returns 1
*/

// M-3 — MEDIUM — ARRAYS
int find_first_duplicate(int *arr, int length)
// Ciclo i: Escolhe o número que vamos testar
    for (int i = 0; i < length; i++) {
        for (int j = i + 1; j < length; j++) {

            if (arr[i] == arr[j]) {

                return arr[i];
            }
        }
    }
    return -1
/* arr:     Pointer to the start of an integer array
   length:  Number of elements
   returns: The first value in the array that appears again later, 
            or -1 if all elements are unique.
   Example 1: find_first_duplicate({3, 1, 4, 1, 3}, 5) returns 3
   Example 2: find_first_duplicate({1, 2, 3}, 3) returns -1
*/

// M-4 — MEDIUM — ARRAYS
int count_peaks(int *arr, int length){
int peak = 0;
for(int i = 1; i < length - 1; i++){
if(arr[i] > arr[i+1] && arr[i] > arr[i-1]){
peak++
}
}
return peak;
}
/* arr:     Pointer to the start of an integer array
   length:  Number of elements
   returns: The number of "peaks". A peak is an element strictly greater than 
            its immediate neighbors (left and right).
   Note:    Ignore the first and last elements as they don't have two neighbors.
   Example 1: count_peaks({1, 3, 2, 5, 4}, 5) returns 2 (the values 3 and 5)
*/

// M-5 — MEDIUM — ARRAYS
int is_subrange_sum_possible(int *arr, int length, int target){
int sum = 0;
for(int i = 0; i < length; i++){
sum += arr[i];

if(sum == target){
return 1;
}
}
return  0;
}
/* arr:     Pointer to the start of an integer array
   length:  Number of elements
   target:  A sum to look for
   returns: 1 if any contiguous sub-segment of the array sums to target, 0 otherwise
   Example 1: is_subrange_sum_possible({1, 2, 3, 4}, 4, 5) returns 1 (because 2 + 3 = 5)
   Example 2: is_subrange_sum_possible({1, 2, 3, 4}, 4, 8) returns 0
*/

// M-6 — MEDIUM — ARRAYS
int find_majority_element(int *arr, int length){
int element = 0;
int biggest = 0;

for(int i = 0; i < length i++){
    int times = 0;
    for(int j = 0; j < length; j++){
    if(arr[i] == arr[j]){
    times++;
    }
    if(biggest < times){
    biggest = times;
    element = arr[i];
    }
    }
}
return element;
}
/* arr:     Pointer to the start of an integer array
   length:  Number of elements
   returns: The element that appears more than length/2 times. 
            If no such element exists, return -1.
   Example 1: find_majority_element({2, 2, 1, 2, 3}, 5) returns 2 (appears 3 times)
   Example 2: find_majority_element({1, 2, 3}, 3) returns -1
*/

// M-7 — MEDIUM — ARRAYS
void array_diff_neighbors(int *arr, int length, int *result){
result[0] = arr[0]

for(int i = 1; i < length; i++){
result[i] = arr[i] - arr[i-1]
}
}
/* arr:     Pointer to the start of an integer array
   length:  Number of elements (at least 1)
   result:  Pointer to an array where the differences will be stored
   Effect:  For every index i > 0, result[i] = arr[i] - arr[i-1]. 
            For index 0, result[0] should remain arr[0].
   Example 1: arr {5, 8, 10, 7}, length 4 -> result {5, 3, 2, -3}
*/

// M-8 — MEDIUM — ARRAYS
int count_strictly_increasing_pairs(int *arr, int length){
count = 0;

for(int i = 0; i < length; i++){
    for(int j = 0; j < length; j++){

    if(i < j && arr[i] < arr[j]){
    count++;
    }

    }
}
return count;
}
/* arr:     Pointer to the start of an integer array
   length:  Number of elements
   returns: The number of pairs (i, j) such that i < j and arr[i] < arr[j]
   Example 1: count_strictly_increasing_pairs({3, 1, 4, 2}, 4) returns 3 
              (pairs: (3,4), (1,4), (1,2))
*/



// 18. MEDIUM
void compare_arrays(char *a, char *b, int *c, int size){

for(int i = 0; i < length; i++){
if(a[i] == b[i]){
c[i] == true;
}
c[i] = false
}

}
/* a, b: Two char arrays to compare
   c: array to be filled with boolean values, true if the values in corresponding index in 'a' and 'b' are the same, false otherwise
   size: Number of elements in all the arrays
   returns: nothing
   Notes:    All arrays have the same 'size'
   Example 1: a={'x','y'}, b={'x', 'z'}, size=2 results in c={true, false}
   Example 2: a={'1','2','3'}, b={'1','2','3'}, size=3 results in c={true, true, true}
*/



