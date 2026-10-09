#include <caoss/alu.h>
#include <caoss/register.h>


extern caoss_word flags;


/**
 * Get the value of flag f
 * @param f
 */
bool get_flag(const flag f) {
    // TODO
}

void add(const op_size size, const byte register_in1, const byte register_in2, const byte register_out) {
	caoss_word in1 = read_register(register_in1);
	caoss_word in2 = read_register(register_in2);
	
	write_register(register_out, in1 + in2);
}

void sub(const op_size size, const byte register_in1, const byte register_in2, const byte register_out) {
    caoss_word in1 = read_register(register_in1);
	caoss_word in2 = read_register(register_in2);
	
	write_register(register_out, in1 - in2);
}

void and(const op_size size, const byte register_in1, const byte register_in2, const byte register_out) {
    caoss_word in1 = read_register(register_in1);
	caoss_word in2 = read_register(register_in2);
	
	write_register(register_out, in1 & in2);
}

void or(const op_size size, const byte register_in1, const byte register_in2, const byte register_out) {
    caoss_word in1 = read_register(register_in1);
	caoss_word in2 = read_register(register_in2);
	
	write_register(register_out, in1 | in2);
}

void sll(op_size size, byte register_in1, byte register_in2, byte register_out) {
	caoss_word in1 = read_register(register_in1);
	caoss_word in2 = read_register(register_in2);
	
	write_register(register_out, in1 << in2);
}

void sra(op_size size, byte register_in1, byte register_in2, byte register_out) {
    caoss_word in1 = read_register(register_in1);
	caoss_word in2 = read_register(register_in2);
	
	write_register(register_out, in1 >> in2);
}

void neg(op_size size, byte register_in, byte register_out) {
    caoss_word in = read_register(register_in);
	
	write_register(register_out, ~in);
}
void not(op_size size, byte register_in, byte register_out) {
    caoss_word in = read_register(register_in);
	
	write_register(register_out, !in);
}