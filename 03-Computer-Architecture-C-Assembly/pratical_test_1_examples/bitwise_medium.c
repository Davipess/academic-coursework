#include <stdint.h>

/* ==========================================================================
 * BITWISE MEDIUM (M)
 * ========================================================================== */

/* --- Category: COUNTING & PROPERTIES -------------------------------------- */

// M-7 - MEDIUM - BITWISE
int get_highest_bit_set(uint32_t val){
int i = 31;

while(i >= 0){
if(val & (1 << i) != 0){
return i;
}
i--;
}
}
/* val:     A 32-bit value
   returns: The index (0-31) of the most significant bit that is 1.
            Returns -1 if val is 0.
   Example 1: get_highest_bit_set(0x12) returns 4 (binary 10010)
   Example 2: get_highest_bit_set(0x01) returns 0
*/


// E-12 - EASY - BITWISE
uint32_t get_lowest_bit_set(uint32_t val){
int i = 0;

while (i < 32) {
if(val & (1 << i)){
return i;
}
i++;
}
return -1;
}
/* val:     A 32-bit value
   returns: A value where only the lowest bit that was set in val remains set
   Example 1: get_lowest_bit_set(0x12) returns 0x02 (binary 10010 -> 00010)
   Example 2: isolate_lowest_set_bit(0x08) returns 0x08
*/


// 9. EASY
int parity_check(uint8_t data)[
int i = 0;
int count = 0;

while(i < 8){
if((data & (1 << i)) != 0){
count++;
}

}
if(count % 2 == 0){
return 0;
}
else{
return 1;
}
]
/* data: An 8-bit value
   returns: 1 if the number of bits in data set to 1 is odd, 0 if even
   Example 1: parity_check(0x01) returns 1
   Example 2: parity_check(0x03) returns 0
*/


// 15. EASY
uint8_t create_mask(int start, int end){
int i = 0;

while (i < 8){

if(start & (1 << i) != 0){
end = start ^ 1;
}

}
return end;

}
/* start: Starting bit position LSBit (e.g., 0)
   end: Ending bit position MSbit (e.g., 3)
   returns: A byte with bits from start to end set to 1
   Example 1: create_mask(0, 3) returns 0x0F
   Example 2: create_mask(4, 7) returns 0xF0
*/



// 16. EASY
int check_alignment_4(void *ptr){
unsigned long address = (unsigned long)ptr;
    if ((address & 3) == 0) {
        return 1; 
    } else {
        return 0;
    }
}
/* ptr: A memory address
   returns: 1 if the address is a multiple of 4, 0 otherwise
   Notes:    Must use bitmaks to detect alignment
   Example 1: check_alignment_4((void*)0x1000) returns 1
   Example 2: check_alignment_4((void*)0x1001) returns 0
*/

// M-7 - MEDIUM - BITWISE
int get_highest_bit_set(uint32_t val);
/* val:     A 32-bit value
   returns: The index (0-31) of the most significant bit that is 1.
            Returns -1 if val is 0.
   Example 1: get_highest_bit_set(0x12) returns 4 (binary 10010)
   Example 2: get_highest_bit_set(0x01) returns 0
*/

// TESTE 4 - ARRAYS
int is_palindrome_array(int *arr, int length){

   for(int i = 0; i < length - 1; i++; length--){
         if(arr[i] != arr[j]){
            return 0;
         }   
   }
      return 1;

}
/* arr:     Pointer to an integer array
   length:  Number of elements
   returns: 1 if the array reads the same forwards and backwards, 0 otherwise.
   Example 1: {1, 2, 3, 2, 1}, length 5 -> returns 1
   Example 2: {1, 2, 3, 4, 5}, length 5 -> returns 0
*/

// TESTE 5 - BITWISE (MODIFIER)
uint8_t invert_upper_nibble(uint8_t data){
   data = data ^ 0xF0;

   return data; 
}
/* data:    An 8-bit value
   returns: The same value, but with the 4 most significant bits inverted.
   Example 1: invert_upper_nibble(0xF0) returns 0x00 (binary 11110000 -> 00000000)
   Example 2: invert_upper_nibble(0xAA) returns 0x5A (binary 10101010 -> 01011010)
*/

