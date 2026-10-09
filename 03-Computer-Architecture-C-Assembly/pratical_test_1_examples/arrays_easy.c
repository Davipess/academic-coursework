#include <stdint.h>
#include <stddef.h>

/* ==========================================================================
 * ARRAYS EASY (E)
 * ========================================================================== */

/* --- Category: MAX / MIN -------------------------------------------------- */

// E-01 - EASY - ARRAYS
int array_max(int *arr, int length){
int i = 1;
int max = arr[0];

while (i < length){
if(arr[i] > max){
max = arr[i];
}
i++;
}
return max;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements (at least 1)
   returns:   The maximum value in the array
   Example 1: array_max({3, 1, 4, 1, 7}, 5) returns 7
   Example 2: array_max({-10, -3, -7}, 3) returns -3
*/

// E-02 - EASY - ARRAYS
int array_min(int *arr, int length){
int i = 1;
int min = arr[0];

while (i < length){
if(arr[i] < min){
min = arr[i];
}
i++;
}
return min;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements (at least 1)
   returns:   The minimum value in the array
   Example 1: array_min({3, 1, 4, 1, 7}, 5) returns 1
   Example 2: array_min({-10, -3, -7}, 3) returns -10
*/

// E-03 - EASY - ARRAYS
int absolute_max(int *arr, int length){
    int max = arr[0];
    if (max < 0) {
        max = -max;
    }
    int i = 1;
    while (i < length) {
        int current = arr[i];

        if (current < 0) {
            current = -current;
        }
        if (current > max) {
            max = current;
        }
        i++;
    }
    return max;
}

/* arr:       Pointer to the start of an integer array
   length:    Number of elements (at least 1)
   returns:   The maximum absolute value found in the array
   Example 1: absolute_max({1, -5, 3}, 3) returns 5
   Example 2: absolute_max({-10, 2}, 2) returns 10
*/

// E-04 - EASY - ARRAYS
int find_min_index(int *arr, int length){
int i = 1;
int min = arr[0];
int index = 0;

while (i < length){
if(arr[i] < min){
min = arr[i];
index = i;
}
i++;
}
return index;
}
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements (at least 1)
   returns:   The index of the first occurrence of the minimum value
   Example 1: find_min_index({3, 1, 4, 1, 5}, 5) returns 1
   Example 2: find_min_index({10, 20, 30}, 3) returns 0
   Example 3: find_min_index({0, -5, -2, -5}, 4) returns 1
*/

// E-05 - EASY - ARRAYS
int find_max_index(int *arr, int length){
// E-01 - EASY - ARRAYS
int max = arr[0];
index = i;

for(int i = 1; i < length; i++){
if(arr[i] > max){
max = arr[i];
index = i;
}
}
return index;
}

/* arr:       Pointer to the start of an integer array
   length:    Number of elements (at least 1)
   returns:   The index of the first occurrence of the maximum value
   Example 1: find_max_index({3, 1, 4, 1, 4}, 5) returns 2
   Example 2: find_max_index({10, 20, 30}, 3) returns 2
*/

/* --- Category: SUM / MEAN / PRODUCT --------------------------------------- */

// E-06 - EASY - ARRAYS
int array_sum(int *arr, int length){
int sum = 0;

for(int i = 0; i < length; i++){
sum += arr[i];
}
return sum;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   The sum of all the elements in the array
   Example 1: array_sum({1, 2, 3, 4}, 4) returns 10
   Example 2: array_sum({-1, 3, -5}, 3) returns -3
*/

// E-07 - EASY - ARRAYS
double array_mean(int *arr, int length){
int sum = 0;

for(int i = 0; i < length; i++){
sum += arr[i];
}
return (double) sum / length;
}
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements (at least 1)
   returns:   The average as a double (exact, not floor division)
   Notes:     Cast at least one operand to double before dividing.
   Example 1: array_mean({1, 2, 3, 4}, 4) returns 2.5
   Example 2: array_mean({0, -1, -2, 3}, 4) returns 0.0
*/

// E-08 - EASY - ARRAYS
int array_product(int *arr, int length)}

int sum = 1;

for(int i = 0; i < length; i++){
sum *= arr[i];
}
return sum;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   The product of all elements in the array
   Example 1: array_product({1, 2, 3, 4}, 4) returns 24
   Example 2: array_product({5, 0, 10}, 3) returns 0
*/

// E-09 - EASY - ARRAYS
int sum_even_indexed(int *arr, int length){
int sum = 0;

for(int i = 0; i < length; i += 2){
sum += arr[i];
}
return sum;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   The sum of elements at indices 0, 2, 4, etc.
   Example 1: sum_even_indexed({10, 1, 20, 2, 30}, 5) returns 60 (10+20+30)
   Example 2: sum_even_indexed({1, 5, 2, 6}, 4) returns 3 (1+2)
*/

// E-10 - EASY - ARRAYS
int sum_odd_indexed(int *arr, int length){
int sum = 0;

for(int i = 1; i < length; i += 2){
sum += arr[i];
}
return sum;
}
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   The sum of elements at indices 1, 3, 5, etc.
   Example 1: sum_odd_indexed({10, 1, 20, 2, 30}, 5) returns 3 (1+2)
   Example 2: sum_odd_indexed({1, 5, 2, 6}, 4) returns 11 (5+6)
*/

/* --- Category: COUNTING --------------------------------------------------- */

// E-11 - EASY - ARRAYS
int count_occurrences(int *arr, int length, int val){
int counter = 0;
for(int i = 0; i < length; i++){
if(arr[i] == val){
counter++;
}
}
return counter;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   val:       The value to count
   returns:   The number of times val appears in the array
   Example 1: count_occurrences({1, 2, 2, 3}, 4, 2) returns 2
   Example 2: count_occurrences({1, 2, 3}, 3, 5) returns 0
*/

// E-12 - EASY - ARRAYS
int count_even(int *arr, int length){
    int counter = 0;

    for (int i = 0; i < length; i++) {
        if (arr[i] % 2 == 0) {
            counter++;
        }
    }
    return counter;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   The number of even values in the array
   Example 1: count_even({1, 2, 3, 4}, 4) returns 2
   Example 2: count_even({1, 3, 5}, 3) returns 0
*/

// E-13 - EASY - ARRAYS
int count_odd(int *arr, int length){
    int counter = 0;

    for (int i = 0; i < length; i++) {
        if (arr[i] % 2 != 0) {
            counter++;
        }
    }
    return counter;
}
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   The number of odd values in the array
   Example 1: count_odd({1, 2, 3, 4}, 4) returns 2
   Example 2: count_odd({1, 3, 5}, 3) returns 3
*/

// E-14 - EASY - ARRAYS
int count_negatives(int *arr, int length){
  int counter = 0;

    for (int i = 0; i < length; i++) {
        if (arr[i] < 0) {
            counter++;
        }
    }
    return counter;
}
}

}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   The number of elements < 0
   Example 1: count_negatives({1, -2, -3, 4}, 4) returns 2
   Example 2: count_negatives({1, 2, 3}, 3) returns 0
*/

// E-15 - EASY - ARRAYS
int count_positives(int *arr, int length);
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   The number of elements > 0
   Example 1: count_negatives({1, -2, -3, 4}, 4) returns 2
   Example 2: count_negatives({1, 2, 3}, 3) returns 3
*/

// E-16 - EASY - ARRAYS
int count_non_zero(int *arr, int length);
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   The number of elements not equal to 0
   Example 1: count_non_zero({0, 5, 0, 3}, 4) returns 2
   Example 2: count_non_zero({0, 0, 0}, 3) returns 0
*/

// E-17 - EASY - ARRAYS
int count_above_val(int *arr, int length, int val);
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   val:       The comparison value
   returns:   The number of elements strictly greater than val
   Example 1: count_above_val({1, 5, 10, 2}, 4, 2) returns 2
   Example 2: count_above_val({1, 2, 3}, 3, 0) returns 3
*/

// E-18 - EASY - ARRAYS
int count_below_val(int *arr, int length, int val);
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   val:       The comparison value
   returns:   The number of elements strictly smaller than val
   Example 1: count_above_val({1, 5, 10, 2}, 4, 2) returns 1
   Example 2: count_above_val({1, 2, 3}, 3, 0) returns 2
*/

/* --- Category: SEARCH ----------------------------------------------------- */

// E-19 - EASY - ARRAYS
int find_index(int *arr, int length, int val){

for(int i = 0; i < length; i++){
if(arr[i] = val){
return i;
}
}
return -1;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   val:       The value to search for
   returns:   The index of the first occurrence of val, or -1 if not found
   Example 1: find_index({10, 20, 30}, 3, 20) returns 1
   Example 2: find_index({1, 2, 3}, 3, 5) returns -1
*/

// E-20 - EASY - ARRAYS
int find_last_index(int *arr, int length, int val);
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   val:       The value to search for
   returns:   The index of the LAST occurrence of val, or -1 if not found
   Notes:     Iterate the array backwards
   Example 1: find_last_index({1, 2, 3, 2, 1}, 5, 2) returns 3
   Example 2: find_last_index({1, 2, 3}, 3, 5) returns -1
*/

// E-21 - EASY - ARRAYS
int array_contains(int *arr, int length, int val);
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   val:       The value to look for
   returns:   1 if val appears at least once in the array, 0 otherwise
   Example 1: array_contains({1, 2, 3}, 3, 2) returns 1
   Example 2: array_contains({1, 2, 3}, 3, 5) returns 0
*/

/* --- Category: PROPERTIES / SORTEDNESS ------------------------------------ */

// E-22 - EASY - ARRAYS
int is_sorted(int *arr, int length) {
    if (length < 2) return 1;

    for (int i = 1; i < length; i++) {
        if (arr[i-1] > arr[i]) {
            return 0;
        }
    }

    return 1;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   1 if the array is sorted in non-decreasing order, 0 otherwise
   Example 1: is_sorted({1, 2, 2, 5}, 4) returns 1
   Example 2: is_sorted({3, 1, 4}, 3) returns 0
*/

// E-23 - EASY - ARRAYS
int is_strictly_increasing(int *arr, int length){
    if (length < 2) return 1;

    for (int i = 1; i < length; i++) {
        if (arr[i-1] >= arr[i]) {
            return 0;
        }
    }

    return 1;
}

/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   1 if arr[i] < arr[i+1] for all i, 0 otherwise
   Example 1: is_strictly_increasing({1, 2, 5}, 3) returns 1
   Example 2: is_strictly_increasing({1, 2, 2, 3}, 4) returns 0
*/

// E-24 - EASY - ARRAYS
int is_strictly_decreasing(int *arr, int length){
    if (length < 2) return 1;

    for (int i = 1; i < length; i++) {
        if (arr[i-1] <= arr[i]) {
            return 0;
        }
    }

    return 1;
}

/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   1 if arr[i] > arr[i+1] for all i, 0 otherwise
   Example 1: is_strictly_decreasing({5, 2, 1}, 3) returns 1
   Example 2: is_strictly_decreasing({5, 2, 2, 1}, 4) returns 0
*/

// E-25 - EASY - ARRAYS
int all_positive(int *arr, int length){

if(length == 0){
return 1;
}

for(int i = 0; i < length; i++){
if(arr[i] <= 0){
return 0
}
}
return 1;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   1 if all elements are > 0, 0 otherwise (returns 1 if length is 0)
   Example 1: all_positive({1, 5, 3}, 3) returns 1
   Example 2: all_positive({1, -2, 3}, 3) returns 0
*/

// E-26 - EASY - ARRAYS
int any_negative(int *arr, int length);
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   1 if at least one element is < 0, 0 otherwise
   Example 1: any_negative({1, 2, -3, 4}, 4) returns 1
   Example 2: any_negative({1, 2, 3}, 3) returns 0
*/

// E-27 - EASY - ARRAYS
int is_constant(int *arr, int length){

if(length == 0)
return 1;

for(int i = 1; i < length; i++){
if(arr[i-1] != arr[i]) {
return 0;
}
}
return 1;
}
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   1 if all elements are equal to each other, 0 otherwise
   Example 1: is_constant({7, 7, 7}, 3) returns 1
   Example 2: is_constant({7, 8, 7}, 3) returns 0
*/

// E-28 - EASY - ARRAYS
int array_all_zero(int *arr, int length);
/* arr:       Pointer to the start of an integer array
   length:    Number of elements
   returns:   1 if all elements are 0, 0 otherwise
   Example 1: array_all_zero({0, 0, 0}, 3) returns 1
   Example 2: array_all_zero({0, 1, 0}, 3) returns 0
*/


// 13. EASY
void fill_array(int *arr, int size, int val){
int i = 0;

while(i < size){
arr[i] = val;
i++;
}
}
/* arr: Pointer to the start of an array [cite: 17]
   size: Number of elements [cite: 17]
   val: The value to store in each cell of the array [cite: 17]
   returns: nothing [cite: 17]
   Notes:    Initialize all the cells in the array with the given value. [cite: 17]
   Example 1: fill_array(arr, 3, 10) results in arr = {10, 10, 10}
   Example 2: fill_array(arr, 5, 0) results in arr = {0, 0, 0, 0, 0}
*/


// 18. MEDIUM
int compare_arrays(char *a, char *b, int size){

for(int i = 0; i < size; i++){
if(a[i] != b[i]){
return 0
}
}
return 1;
}
/* a, b: Two char arrays (same size) to compare
   size: Number of elements in all the arrays
   returns: 1 is arrays are equal, zero if different
   Notes:    Cannot use any pre-existing string comparison functions.
   Example 1: a={'x','y'}, b={'x', 'z'}, returns false
   Example 2: a={'1','2','3'}, b={'1','2','3'}, returns true
*/
