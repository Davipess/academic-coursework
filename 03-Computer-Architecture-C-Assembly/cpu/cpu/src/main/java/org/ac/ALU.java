package org.ac;

import static org.ac.Register.GP_REGISTER_SIZE;

/**
 * The class the implements the Arithmetic and Login Unit (ALU)
 */
public class ALU {

    /**
     * Flags register.
     * Bit 0 (Negative): Set if the result of the last operation executed by the ALU is negative, that is,
     * that MSB of the register holding the result is 1.
     * Bit 1 (Zero): Set if the result of the last operation was exactly zero.
     * Bit 2 (Carry): Set if a carry occurred in the last operation executed by the ALU.
     * Bit 3 (Overflow): et if an arithmetic overflow occurred in the last operation executed by the ALU.
     */
    final Register NZCV = new Register(GP_REGISTER_SIZE);

    private void updateFlags(Register result, boolean carry, boolean overflow) {
        boolean isZero = true;
        for (int i = 0; i < GP_REGISTER_SIZE; i++) {
            if (result.getBit(i)) {
                isZero = false;
                break;
            }
        }
        NZCV.setBit(1, isZero);

        boolean isNegative = result.getBit(GP_REGISTER_SIZE - 1);
        NZCV.setBit(0, isNegative);

        NZCV.setBit(2, carry);
        NZCV.setBit(3, overflow);
    }

    /**
     * Stores in register out the result of the bitwise inversion of register in (one’s complement).
     * Assume that both registers have the same size.
     */
    public void not(Register out, Register in) {
        for (int i = 0; i < GP_REGISTER_SIZE; i++) {
            out.setBit(i, !in.getBit(i));
        }
        updateFlags(out, false, false);
    }

    /**
     * Stores in register out the result of the bitwise logical AND between rin1 and rin2.
     * Assume that all registers have the same size.
     */
    public void and(Register out, Register in1, Register in2) {
        for (int i = 0; i < GP_REGISTER_SIZE; i++) {
            out.setBit(i, in1.getBit(i) && in2.getBit(i));
        }
        updateFlags(out, false, false);
    }

    /**
     * Stores in register out the result of the bitwise logical OR between rin1 and rin2.
     * Assume that all registers have the same size.
     */
    public void or(Register out, Register in1, Register in2) {
        for (int i = 0; i < GP_REGISTER_SIZE; i++) {
            out.setBit(i, in1.getBit(i) || in2.getBit(i));
        }
        updateFlags(out, false, false);
    }

    /**
     * Stores in register out the result of the bitwise logical XOR between rin1 and rin2.
     * Assume that all registers have the same size.
     */
    public void xor(Register out, Register in1, Register in2) {
        for (int i = 0; i < GP_REGISTER_SIZE; i++) {
            out.setBit(i, in1.getBit(i) != in2.getBit(i));
        }
        updateFlags(out, false, false);
    }

    /**
     * Stores in register out the result of a logical shift left operation on register in.
     * Assume that both registers have the same size.
     */
    public void lsl(Register out, Register in, int n) {
        boolean carry = false;
        if (n > 0 && n <= GP_REGISTER_SIZE) {
            carry = in.getBit(GP_REGISTER_SIZE - n);
        }

        for (int i = 0; i < GP_REGISTER_SIZE; i++) {
            if (i - n >= 0) {
                out.setBit(i, in.getBit(i - n));
            } else {
                out.setBit(i, false);
            }
        }

        boolean signBefore = in.getBit(GP_REGISTER_SIZE - 1);
        boolean signAfter = out.getBit(GP_REGISTER_SIZE - 1);
        boolean overflow = (signBefore != signAfter);

        updateFlags(out, carry, overflow);
    }

    /**
     * Stores in register out the result of the arithmetic negation of in (two’s complement).
     * Assume that both registers have the same size.
     */
    public void neg(Register out, Register in) {
        Register tempNot = new Register(GP_REGISTER_SIZE);
        Register numberOne = new Register(GP_REGISTER_SIZE);

        not(tempNot, in);

        numberOne.setBit(0, true);
        for(int i = 1; i < GP_REGISTER_SIZE; i++) {
            numberOne.setBit(i, false);
        }

        add(out, tempNot, numberOne);
    }

    /**
     * Implements a Ripple Carry Adder (manually handling carries) to compute out = in1 + in2.
     * Assume that all registers have the same size.
     */
    public void add(Register out, Register in1, Register in2) {
        boolean carryIn = false;

        for (int i = 0; i < GP_REGISTER_SIZE; i++) {
            boolean a = in1.getBit(i);
            boolean b = in2.getBit(i);

            boolean sum = (a != b) != carryIn;
            out.setBit(i, sum);

            carryIn = (a && b) || (carryIn && (a != b));
        }

        boolean sign1 = in1.getBit(GP_REGISTER_SIZE - 1);
        boolean sign2 = in2.getBit(GP_REGISTER_SIZE - 1);
        boolean signResult = out.getBit(GP_REGISTER_SIZE - 1);

        boolean overflow = (sign1 == sign2) && (sign1 != signResult);

        updateFlags(out, carryIn, overflow);
    }

    /**
     * Return the value of the Negative flag
     */
    public boolean negativeFlag() {
        return NZCV.getBit(0);
    }

    /**
     * Return the value of the Zero flag
     */
    public boolean zeroFlag() {
        return NZCV.getBit(1);
    }

    /**
     * Return the value of the Carry flag
     */
    public boolean carryFlag() {
        return NZCV.getBit(2);
    }

    /**
     * Return the value of the Overflow flag
     */
    public boolean overflowFlag() {
        return NZCV.getBit(3);
    }
}