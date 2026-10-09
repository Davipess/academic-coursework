package org.ac;

/**
 * Class that models a CPU general-purpose register, a fast access memory used to store data to be supplied
 * to instruction execution units, such as the ALU.
 */
public class Register {

    /**
     * Size of the general-purpose registers, by default.
     */
    public static final int GP_REGISTER_SIZE = 8;

    /**
     * The bit representation of the register's contents
     */
    private final boolean[] bits;

    /**
     * Create a register with a given size
     */
    public Register(int size) {
        this.bits = new boolean[size];
    }

    /**
     * Create a register with the CPU's default register size
     */
    public Register() {
        this(GP_REGISTER_SIZE);
    }

    /**
     * Sets the bit at position index to value.
     */
    public void setBit(int index, boolean value) {
        if (index < 0 || index >= this.bits.length) {
            throw new IllegalArgumentException("Out of bounds quantity of bits");
        }
        this.bits[index] = value;
    }

    /**
     * Returns the value of the bit at position index.
     */
    public boolean getBit(int index) {
        if (index < 0 || index >= this.bits.length) {
            throw new IllegalArgumentException("Out of bounds quantity of bits");
        }
        return this.bits[index];
    }

    /**
     * Stores an integer using an N-bit two’s complement representation.
     */
    public void set(int value) {
        long tempValue = value;

        if (tempValue < 0) {
            tempValue = (long) Math.pow(2, this.bits.length) + tempValue;
        }

        for (int i = 0; i < this.bits.length; i++) {
            long remainder = tempValue % 2;

            if (remainder == 1) {
                setBit(i, true);
            } else {
                setBit(i, false);
            }

            tempValue = tempValue / 2;
        }
    }

    /**
     * Interprets the register contents as an unsigned integer.
     */
    public int getUnsignedInt() {
        int trueValue = 0;

        for (int i = 0; i < this.bits.length; i++) {
            if (getBit(i)) {
                trueValue += (int) Math.pow(2, i);
            }
        }
        return trueValue;
    }

    /**
     * Interprets the register contents as a signed integer in two’s complement.
     */
    public int getInt() {
        int trueValue = getUnsignedInt();
        int msbIndex = this.bits.length - 1;

        if (getBit(msbIndex)) {
            trueValue -= (int) Math.pow(2, this.bits.length);
        }

        return trueValue;
    }

    /**
     * Returns a string containing the register contents organized in groups of 4 bits separated by spaces (e.g., 1111 1110).
     */
    @Override
    public String toString() {
        String s = "";

        for (int i = this.bits.length - 1; i >= 0; i--) {
            if (getBit(i)) {
                s += "1";
            } else {
                s += "0";
            }

            if (i % 4 == 0 && i != 0) {
                s += " ";
            }
        }
        return s;
    }
}