/**
* C module that models a CPU general-purpose register, a fast access memory used to store data to be supplied
 * to instruction execution units, such as the ALU.
 */

#ifndef CPU_GP_REGISTER_H
#define CPU_GP_REGISTER_H

#include <stdbool.h>

/**
 * Size of the general-purpose registers.
 */
#define GP_REGISTER_SIZE 8

/**
 * The register as an array of booleans
 */
typedef bool gp_register[GP_REGISTER_SIZE];

/**
 *  Sets the bit at position index fo register reg to value.
 */
void gp_register_set_bit(gp_register reg, int index, bool value);

/**
 * Returns the value of the bit of register reg at position index.
 */
int gp_register_get_bit(const gp_register reg, int index);

/**
 * Stores an integer in register reg.
 */
void gp_register_set(gp_register reg, int value);

/**
 * Interprets the contents of register reg as an unsigned integer.
 */
unsigned int gp_register_get_unsigned(const gp_register reg);

/**
 * Interprets the contents of register reg as an signed integer.
 */
int gp_register_get_int(const gp_register reg);

/**
 * Prints the contents of the register to the standard output.
 * The contents of the register will be presented inn groups of 4 bits separated by spaces (e.g., 1111 1110)
 */
void print_register(const gp_register reg);

#endif //CPU_GP_REGISTER_H