#include <stdint.h>

/* ==========================================================================
 * BITWISE VERY EASY (VE)
 * ========================================================================== */

/* --- Category: BASIC LOGICAL OPERATIONS ----------------------------------- */

// VE-1 - VERY EASY - BITWISE
uint32_t bitwise_and(uint32_t a, uint32_t b){
return a & b;
}
/* a, b:    Two 32-bit values
   returns: The result of (a AND b)
   Example 1: bitwise_and(0x0F, 0x03) returns 0x03
*/

// VE-2 - VERY EASY - BITWISE
uint32_t bitwise_or(uint32_t a, uint32_t b){
return a | b;
}
/* a, b:    Two 32-bit values
   returns: The result of (a OR b)
   Example 1: bitwise_or(0x01, 0x02) returns 0x03
*/

// VE-3 - VERY EASY - BITWISE
uint32_t bitwise_not(uint32_t val){
return ~val;
}
/* val:     A 32-bit value
   returns: The bitwise inverse (NOT) of the value
   Example 1: bitwise_not(0x00000000) returns 0xFFFFFFFF
*/


/* --- Category: SHIFTING --------------------------------------------------- */

// VE-4 - VERY EASY - BITWISE
uint32_t shift_left_by_one(uint32_t val){
return val << 1;
}
/* val:     A 32-bit value
   returns: The value shifted left by 1 bit (effectively val * 2)
   Example 1: shift_left_by_one(0x05) returns 0x0A (10)
*/

// VE-5 - VERY EASY - BITWISE
uint32_t shift_right_by_n(uint32_t val, int n){
return val >> n;
}
/* val:     A 32-bit value
   n:       Number of positions to shift
   returns: The value shifted right by n bits
   Example 1: shift_right_by_n(0x10, 2) returns 0x04
*/

/* --- Category: ELEMENTARY MASKING ----------------------------------------- */

// VE-1 - VERY EASY - BITWISE
int get_bit(uint32_t val, int n){
return (val >> n) & 1;
}
/* val:     A 32-bit value
   n:       The position of the bit to get (0-31)
   returns: The value of bit n (0 or 1)
   Example 1: get_bit(0x08, 3) returns 1
   Example 2: get_bit(0x08, 2) returns 0
*/

// VE-2 - VERY EASY - BITWISE
uint32_t set_bit(uint32_t val, int n){
return val | (1 << n);
}
/* val:     A 32-bit value
   n:       The bit position to set (0-31)
   returns: The value with bit n forced to 1, others unchanged
   Example 1: set_bit(0x00, 4) returns 0x10 (16)
   Example 2: set_bit(0x01, 1) returns 0x03 (3)
*/

// VE-3 - VERY EASY - BITWISE
uint32_t clear_bit(uint32_t val, int n){
return val & ~(1 << n);
}
/* val:     A 32-bit value
   n:       The bit position to clear (0-31)
   returns: The value with bit n forced to 0, others unchanged
   Example 1: clear_bit(0x0F, 0) returns 0x0E (14)
   Example 2: clear_bit(0xFF, 7) returns 0x7F (127)
*/

// VE-4 - VERY EASY - BITWISE
uint32_t toggle_bit(uint32_t val, int n){
return val ^ (1 << n);
}
/* val:     A 32-bit value
   n:       The bit position to flip (0-31)
   returns: The value with bit n inverted (0->1 or 1->0)
   Example 1: toggle_bit(0x01, 0) returns 0x00
   Example 2: toggle_bit(0x00, 2) returns 0x04
*/


// VE-6 - VERY EASY - BITWISE
uint32_t keep_lower_8_bits(uint32_t val){
return val & 0xFF;
}
/* val:     A 32-bit value
   returns: The value with only the lowest 8 bits preserved (bits 0-7), 
            all other bits (8-31) forced to 0.
   Example 1: keep_lower_8_bits(0xABCD1234) returns 0x00000034
*/

// VE-7 - VERY EASY - BITWISE
int is_even_bitwise(int val){
if(val & 1 == 0){
return 1;
}
return 0;
}
/* val:     An integer value
   returns: 1 if the value is even, 0 if odd.
   Note:    Do NOT use the modulo (%) operator. Check the 0th bit instead.
   Example 1: is_even_bitwise(4) returns 1
   Example 2: is_even_bitwise(7) returns 0
*/

// VE-8 - VERY EASY - BITWISE
uint32_t get_low_nibble(uint8_t val){
return val & 0xF;
}
/* val:     An 8-bit value
   returns: The value of the lower 4 bits (the "low nibble")
   Example 1: get_low_nibble(0xAB) returns 0x0B
*/
