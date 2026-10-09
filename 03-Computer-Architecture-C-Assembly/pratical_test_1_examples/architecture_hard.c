#include <stdint.h>
#include <stddef.h>

/* ==========================================================================
 * ARCHITECTURE & MEMORY EASY (E)
 * ========================================================================== */

/* --- Category: ENDIANNESS (BYTE ORDER) ------------------------------------ */

// E-1 — EASY — ARCHITECTURE
int is_little_endian(void) {
    uint16_t valor = 0x0001;
    unsigned char *p = (unsigned char *)&valor;

    if (*p == 0x01) {
        return 1; // É Little Endian
    } else {
        return 0;
    }
}
/* returns: 1 if the architecture is Little Endian, 0 if Big Endian
   Notes:   Create a multi-byte variable (like uint16_t x = 0x0001) and check 
            the first byte in memory using a char pointer.
   Example 1: On x86/Intel architecture, returns 1
   Example 2: On older PowerPC or SPARC architectures, returns 0
*/

// E-2 — EASY — ARCHITECTURE
uint32_t swap_bytes_32(uint32_t val){
uint32_t up1 = val & 0xFF000000;
uint32_t up2 = val & 0x00FF0000;
uint32_t low1 = val & 0x0000FF00;
uint32_t low2 = val & 0xF00000FF;

val = (low2 << 24) | (low1 << 8) | (up2 >> 8) | (up1 >> 24);

}
/* val:     A 32-bit unsigned integer
   returns: The value with all 4 bytes reversed (Big to Little or vice-versa)
   Example 1: swap_bytes_32(0x12345678) returns 0x78563412
   Example 2: swap_bytes_32(0xAABBCCDD) returns 0xDDCCBBAA
*/

/* --- Category: ALIGNMENT & PADDING ---------------------------------------- */

// E-3 — EASY — ARCHITECTURE
unsigned int get_padding_size(void);
/* returns: The amount of padding bytes in a specific structure
   Notes:   Define a struct { char a; int b; }. Return the difference 
            between sizeof(the_struct) and the sum of the sizes of its members.
   Example 1: If char is 1 byte, int is 4, and struct aligns to 4 bytes, returns 3
   Example 2: If the structure is strictly packed (__attribute__((packed))), returns 0
*/

// E-4 — EASY — ARCHITECTURE
int is_aligned(void *ptr, unsigned int alignment);
/* ptr:       A memory address
   alignment: A power of two (2, 4, 8, 16...)
   returns:   1 if the address is a multiple of alignment, 0 otherwise
   Notes:     Cast the pointer to an integer type (like uintptr_t) and use modulo.
   Example 1: is_aligned((void*)0x1004, 4) returns 1
   Example 2: is_aligned((void*)0x1004, 8) returns 0
*/

/* --- Category: POINTER ARITHMETIC & TYPES --------------------------------- */

// E-5 — EASY — ARCHITECTURE
int pointer_distance_bytes(void *p1, void *p2);
/* p1, p2:  Two memory addresses
   returns: The number of bytes between p2 and p1 (p2 - p1)
   Notes:   Cast pointers to (char *) before subtracting to ensure byte-level counting.
   Example 1: p1=(void*)0x1000, p2=(void*)0x1008 returns 8
   Example 2: p1=(void*)0x1008, p2=(void*)0x1000 returns -8
*/

// E-6 — EASY — ARCHITECTURE
void write_at_offset(void *base, int offset, uint8_t value);
/* base:    Base memory address
   offset:  Number of bytes to move from base
   value:   The byte to write
   Effect:  Writes 'value' at memory address (base + offset)
   Example 1: base=0x1000, offset=2 results in byte at 0x1002 being updated
   Example 2: base=0x2000, offset=0 results in byte at 0x2000 being updated
*/

/* --- Category: STRUCTURES & OFFSETS --------------------------------------- */

// E-7 — EASY — ARCHITECTURE
unsigned int offset_of_member(void);
/* returns: The byte offset of member 'c' within a structure
   Notes:   Define struct { char a; char b; int c; }. 
            Calculate manually by casting the struct to bytes.
   Example 1: On a system with standard 4-byte alignment, returns 4
   Example 2: On a system where structures are strictly packed (no padding), returns 2
*/

/* --- Category: MEMORY LAYOUT & PUNNING ------------------------------------ */

// E-9 — EASY — ARCHITECTURE
unsigned int get_pointer_size(void);
/* returns: The size of a memory address pointer in bytes
   Notes:   Use sizeof on a void pointer. This helps determine if the compiled 
            target is a 32-bit or 64-bit architecture.
   Example 1: On a 32-bit architecture, returns 4
   Example 2: On a 64-bit architecture, returns 8
*/

// E-10 — EASY — ARCHITECTURE
uint32_t float_to_raw_bits(float f);
/* f:       A floating-point value
   returns: The exact IEEE-754 bit representation of the float as an integer
   Notes:   Use a union to perform type punning without breaking 
            strict aliasing rules. Do NOT simply cast (uint32_t)f.
   Example 1: float_to_raw_bits(0.0f) returns 0x00000000
   Example 2: float_to_raw_bits(1.0f) returns 0x3F800000
*/

// E-11 — EASY — ARCHITECTURE
uint8_t get_memory_byte(void *ptr, int index);
/* ptr:     A pointer to any data type
   index:   The byte offset to read
   returns: The byte value at exactly (ptr + index)
   Notes:   Cast the pointer to uint8_t* to read raw memory bytes.
   Example 1: If ptr points to uint32_t 0xAABBCCDD (Little Endian), index=0 returns 0xDD
   Example 2: If ptr points to uint32_t 0xAABBCCDD (Little Endian), index=3 returns 0xAA
*/

/* --- Category: ADVANCED ALIGNMENT ----------------------------------------- */

// E-12 — EASY — ARCHITECTURE
unsigned int align_up(unsigned int size, unsigned int alignment);
/* size:      The original size in bytes
   alignment: The required alignment boundary (a power of 2, like 4 or 8)
   returns:   The size rounded up to the nearest multiple of the alignment
   Example 1: align_up(5, 4) returns 8
   Example 2: align_up(16, 8) returns 16
*/

// E-13 — EASY — ARCHITECTURE
void* get_array_element_address(void *base, unsigned int elem_size, int index);
/* base:      The starting address of an array
   elem_size: The size of a single element in bytes
   index:     The array index to access
   returns:   The raw memory address of the element at the given index
   Notes:     Perform pointer arithmetic using raw byte sizes.
   Example 1: base=(void*)0x1000, elem_size=4, index=2 returns (void*)0x1008
   Example 2: base=(void*)0x1000, elem_size=1, index=5 returns (void*)0x1005
*/

// E-14 — EASY — ARCHITECTURE
ptrdiff_t struct_member_distance(void *member1, void *member2);
/* member1: A pointer to the first member in a structure
   member2: A pointer to the second member in the same structure
   returns: The distance in bytes from member1 to member2 (member2 - member1)
   Example 1: If member1 is at 0x2000 and member2 is at 0x2004, returns 4
   Example 2: If member1 is at 0x2008 and member2 is at 0x2000, returns -8
*/

/* --- Category: BUFFER MANIPULATION ---------------------------------------- */

// E-15 — EASY — ARCHITECTURE
uint32_t read_unaligned_32_le(const uint8_t *buffer);
/* buffer:  A pointer to an array of bytes (might not be 4-byte aligned)
   returns: A 32-bit integer reconstructed from the bytes assuming Little Endian order
   Notes:   Reconstruct the value using bitwise shifts instead of direct casting 
            to avoid unaligned access memory faults on strict architectures.
   Example 1: buffer={0x12, 0x34, 0x56, 0x78} returns 0x78563412
   Example 2: buffer={0xFF, 0x00, 0x00, 0x00} returns 0x000000FF
*/

// E-16 — EASY — ARCHITECTURE
int memory_regions_overlap(void *start1, unsigned int len1, void *start2, unsigned int len2);
/* start1, len1: Base address and length of the first memory block
   start2, len2: Base address and length of the second memory block
   returns:      1 if the two memory blocks overlap, 0 otherwise
   Notes:        Convert pointers to uintptr_t to compare ranges safely.
   Example 1: start1=0x1000, len1=10, start2=0x1005, len2=10 returns 1
   Example 2: start1=0x1000, len1=10, start2=0x1010, len2=5 returns 0
*/

// E-17 — EASY — ARCHITECTURE
uint32_t swap_halves_32(uint32_t val);
/* val:     A 32-bit unsigned integer
   returns: The value with its upper 16 bits swapped with its lower 16 bits
   Example 1: swap_halves_32(0x12345678) returns 0x56781234
   Example 2: swap_halves_32(0x0000FFFF) returns 0xFFFF0000
*/

// E-18 — EASY — ARCHITECTURE
void swap_pointers(void **ptr1, void **ptr2);
/* ptr1:    Pointer to the first pointer variable
   ptr2:    Pointer to the second pointer variable
   Effect:  Swaps the actual memory addresses held by the two pointer variables
   Example 1: If *ptr1=0x1000 and *ptr2=0x2000, after call *ptr1=0x2000 and *ptr2=0x1000
   Example 2: If *ptr1=0x0000 and *ptr2=0xAAAA, after call *ptr1=0xAAAA and *ptr2=0x0000
*/







// TESTE 1 — ARRAYS / LOOPS
int count_target_pairs(int *arr, int length, int target){

for(int i = 0; i < length; i++){
int count = 0;

    for(int j = i + 1; i < length; i++){
    if(arr[i] + arr[j] == target){
    count++
    }

    }
}
return coutn;
}
/* arr:     Pointer to an integer array
   length:  Number of elements
   target:  The sum we are looking for
   returns: The number of pairs (two different positions) that add up to 'target'.
   Example: arr {1, 5, 3, 4, 2}, target 6 -> returns 2 (pairs: 1+5 and 4+2)
*/




// TESTE 2 — BITWISE (SCANNER)
int count_zero_bits(uint8_t data){
int i = 0;
int count = 0;

while (i < 8){
if(data & (1 << i) == 0){
count++;
}
return count;
}

}
/* data:    An 8-bit value
   returns: The number of bits that are exactly 0.
   Example 1: count_zero_bits(0xFF) returns 0 (binary 11111111)
   Example 2: count_zero_bits(0x02) returns 7 (binary 00000010)
*/

// TESTE 3 — BITWISE (PUZZLE)
uint16_t extract_middle_16(uint32_t val){
uint16_t result = 0;

uint32_t val1 = val & 0x00FFFF00;

return (val1 >> 8)
}
/* val:     A 32-bit unsigned integer
   returns: A 16-bit number made of the two middle bytes of 'val'.
   Example: extract_middle_16(0x12345678) returns 0x3456
   Note:    You will need a correct Hex mask and a shift!
*/