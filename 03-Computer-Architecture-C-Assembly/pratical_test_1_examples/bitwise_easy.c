#include <stdint.h>

/* ==========================================================================
 * BITWISE EASY (E)
 * ========================================================================== */

/* --- Category: NOT OPERATOR ----------------------------------------------- */

// E-5 — EASY — BITWISE
int complement(int val){
return ~val + 1;
}
/* val:     A signed integer value
   returns: The arithmetic complement (negation) of the value
   Note:    You must use the bitwise negation operator (NOT the arithmetic negation operator)
   Example 1: complement(5) returns -5
   Example 2: complement(-2) returns 2
*/

/* --- Category: FIELD EXTRACTION & MASKING --------------------------------- */

// E-10 — EASY — BITWISE
uint8_t get_byte(uint32_t val, int n){
return (val >> (n * 8)) & 0xFF;
}
/* val:     A 32-bit value
   n:       The byte position to extract (0-3)
   returns: The value of the nth byte (0 being the least significant)
   Example 1: get_byte(0x12345678, 0) returns 0x78
   Example 2: get_byte(0x12345678, 2) returns 0x34
*/

// E-11 — EASY — BITWISE
uint32_t clear_lower_n_bits(uint32_t val, int n){
return (val >> n) << n;
}
/* val:     A 32-bit value
   n:       The number of bits to clear (0-31)
   returns: The value with the lowest n bits set to 0
   Example 1: clear_lower_n_bits(0xFF, 4) returns 0xF0
   Example 2: clear_lower_n_bits(0x1234, 8) returns 0x1200
*/

/* --- Category: COUNTING & PROPERTIES -------------------------------------- */

// E-6 — EASY — BITWISE
int count_set_bits(uint32_t val){
int i = 0;
int counter = 0;
while(i < 32){
if((val & (1 << i) != 0){
counter++
}
i++;
}
return counter;
}
/* val:     A 32-bit value
   returns: The total number of bits set to 1 (the Hamming weight)
   Example 1: count_set_bits(0x07) returns 3
   Example 2: count_set_bits(0x80000001) returns 2
*/

// E-7 — EASY — BITWISE
int is_power_of_two(uint32_t val){
int i = 0;
int counter = 0;
while(i < 32){
if((val & (1 << i) != 0){
counter++
}
i++;
}
if (counter == 1){
return 1;
}
return 0;
}
}
/* val:     A 32-bit value
   returns: 1 if val is a power of 2 (e.g., 1, 2, 4, 8...), 0 otherwise
   Example 1: is_power_of_two(16) returns 1
   Example 2: is_power_of_two(15) returns 0
*/

/* --- Category: COMPARISON & LOGIC ----------------------------------------- */

// E-13 — EASY — BITWISE
int has_same_sign(int a, int b){
if(a & (1 << 31) == b & (1 << 31)){
return 1;
}
return 0;
}
/* a, b:    Two signed integer values
   returns: 1 if both numbers have the same sign bit, 0 otherwise
   Note:    DO NOT compare the numbers. You must use masks and bitwise operators.
   Example 1: has_same_sign(5, 10) returns 1
   Example 2: has_same_sign(-5, 2) returns 0
*/

// E-14 — EASY — BITWISE
int is_nth_bit_different(uint32_t a, uint32_t b, int n){
if(a & (1 << n) != b & (1 << n)){
return 1;
}
return 0;
}
}
/* a, b:    Two 32-bit values
   n:       The bit position to check (0-31)
   returns: 1 if bit n is different between a and b, 0 if they are the same
   Example 1: is_nth_bit_different(0x01, 0x00, 0) returns 1
   Example 2: is_nth_bit_different(0x08, 0x0F, 3) returns 0
*/

/* --- Category: BIT ROTATION ----------------------------------------------- */

// E-15 — EASY — BITWISE
uint32_t rotate_left_one(uint32_t val){
int saver = 0;

saver = val & (1 << 31)
if(saver == 0){
return val << 1;
}
return (val << 1) ^ 1;
}
/* val:     A 32-bit value
   returns: The value rotated left by 1 bit (the bit shifted out at 31 enters at 0)
   Example 1: rotate_left_1(0x80000000) returns 0x00000001
   Example 2: rotate_left_1(0x00000001) returns 0x00000002
*/

// E-15 — EASY — BITWISE
uint32_t rotate_right_one(uint32_t val){
int saver = 0;

saver = val & 1;
if(saver == 0){
return val >> 1;
}
return (val >> 1) ^ (1 << 31);
}
}
/* val:     A 32-bit value
   returns: The value rotated right by 1 bit (the bit shifted out at 0 enters at 31)
   Example 1: rotate_right_1(0x80000000) returns 0x40000000
   Example 2: rotate_right_1(0x00000001) returns 0x80000000
*/



// E-7 — EASY — BITWISE OPERATIONS
int count_zero_bits(uint8_t byte){
int i = 0;
int counter = 0;

while (i < 8){
if((byte & (1 << i)) == 0){
counter++;
}
i++;
}
return counter;
}
/* byte: An 8-bit value
   returns: The number of bits currently set to 0
   Example 1: count_zero_bits(0xFE) returns 1
   Example 2: count_zero_bits(0x00) returns 8
*/


/* --- Category: DATA PACKING ----------------------------------------------- */

// E-8 — EASY — BITWISE
uint8_t combine_nibbles(uint8_t high, uint8_t low){
uint8_t result = 0;
result = ((high << 4) | (low & 0x0F));
return result;
}
/* high:    A 4-bit value (0-15)
   low:     A 4-bit value (0-15)
   returns: An 8-bit value where 'high' is in bits 7-4 and 'low' is in bits 3-0
   Example 1: combine_nibbles(0xA, 0x5) returns 0xA5
   Example 2: combine_nibbles(0x1, 0x2) returns 0x12
*/


// E-8 — EASY — BITWISE
uint16_t combine_bytes(uint8_t high, uint8_t low){
uint16_t result = 0;

result = ((high << 8) | (low)]
return result;
}
/* high: The byte intended for the upper 8 bits [cite: 5]
   low: The byte intended for the lower 8 bits [cite: 5]
   returns: A 16-bit unsigned integer combining both [cite: 5]
   Example 1: combine_bytes(0x12, 0x34) returns 0x1234
   Example 2: combine_bytes(0xFF, 0x00) returns 0xFF00
*/


// 5a. EASY
void clear_lower_nibble(uint8_t *val){
(*val >> 4) << 4;
}
/* val: A pointer to an 8-bit value [cite: 8]
   returns: nothing [cite: 8]
   Notes:    Sets the lower 4 bits to 0 without changing the upper 4. [cite: 8]
   Example 1: if *val = 0xFF, clear_lower_nibble(val) results in *val = 0xF0
   Example 2: if *val = 0x12, clear_lower_nibble(val) results in *val = 0x10
*/

// 5a. EASY
void clear_upper_nibble(uint8_t *val){
*val = *val & 0x0F;
}
/* val: A pointer to an 8-bit value [cite: 8]
   returns: nothing [cite: 8]
   Notes:    Sets the upper 4 bits to 0 without changing the lower 4. [cite: 8]
   Example 1: if *val = 0xFF, clear_upper_nibble(val) results in *val = 0x0F
   Example 2: if *val = 0x12, clear_upper_nibble(val) results in *val = 0x02
*/


// E-9 — EASY — BITWISE
uint16_t swap_endianness_16(uint16_t val){
uint16_t upper = (val & 0xFF00) >> 8;
uint16_t lower = (val & 0x00FF) << 8;

result = upper | lower;

return result;

}
/* val:     A 16-bit value
   returns: The value with the upper and lower bytes swapped
   Example 1: swap_endianness_16(0x1234) returns 0x3412
   Example 2: swap_endianness_16(0xAB00) returns 0x00AB
*/

// E-16 — EASY — BITWISE
uint32_t pack_four_bytes(uint8_t b3, uint8_t b2, uint8_t b1, uint8_t b0){
uint32_t result = (b3 << 24) | (b2 << 16) | (b1 << 8) | b0;

return result;
}
/* b3-b0:   Four 8-bit values
   returns: A 32-bit value with b3 in the most significant byte and b0 in the least
   Example 1: pack_four_bytes(0x12, 0x34, 0x56, 0x78) returns 0x12345678
*/

// 3. EASY
uint8_t get_byte_le(int32_t val, int n){
return (val >> (n * 8)) & 0xFF;

}
/* val: The original 32-bit integer [cite: 6]
   n: The byte to extract (0-3) [cite: 6]
   returns: the value of the n-th byte in the number (LSByte=0, MSByte=3) [cite: 6]
   Example 1: get_byte_le(0x12345678, 0) returns 0x78
   Example 2: get_byte_le(0x12345678, 3) returns 0x12
*/

// 5b. EASY
uint16_t swap_nibbles(uint8_t byte){
uint8_t upper = (byte & 0xF0) >> 4;
uint8_t lower = (byte & 0x0F) << 4;

return upper | lower;
}
/* byte: An 8-bit input [citeS: 9]
   returns: An 8-bit value where the high 4 bits and low 4 bits are swapped [cite: 9]
   Example 1: swap_nibbles(0xF0) returns 0x0F
   Example 2: swap_nibbles(0xA2) returns 0x2A
*/


