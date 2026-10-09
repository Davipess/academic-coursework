package org.ac;

/**
 * Class that models a 32-bit floating point register in IEEE 754 format.
 */
public class FP32Register {

    /**
     * Size of the register
     */
    private static final int FP32_REGISTER_SIZE = 32;

    /**
     * Contents of the register
     */
    final boolean[] bits = new boolean[FP32_REGISTER_SIZE];

    /**
     * Stores the given value in IEEE 754 32-bit format.
     */
    public void set(float value) {
        if (value == 0.0f || value == -0.0f) {
            for (int i = 0; i < FP32_REGISTER_SIZE; i++) {
                bits[i] = false;
            }
            return;
        }

        if (value < 0.0f) {
            bits[31] = true;
            value = -value;
        } else {
            bits[31] = false;
        }

        int exponent = 0;

        while (value >= 2.0f) {
            value = value / 2.0f;
            exponent++;
        }

        while (value < 1.0f) {
            value = value * 2.0f;
            exponent--;
        }

        int storedExponent = exponent + 127;
        for (int i = 23; i <= 30; i++) {
            int remainder = storedExponent % 2;
            if (remainder == 1) {
                bits[i] = true;
            } else {
                bits[i] = false;
            }
            storedExponent = storedExponent / 2;
        }

        value = value - 1.0f;
        for (int i = 22; i >= 0; i--) {
            value = value * 2.0f;
            if (value >= 1.0f) {
                bits[i] = true;
                value = value - 1.0f;
            } else {
                bits[i] = false;
            }
        }
    }

    /**
     * Returns the register contents as a float.
     */
    public float get() {
        boolean isZero = true;
        for (int i = 0; i < 31; i++) {
            if (bits[i] == true) {
                isZero = false;
                break;
            }
        }

        if (isZero == true) {
            if (bits[31] == true) {
                return -0.0f;
            }
            return 0.0f;
        }

        int storedExponent = 0;
        for (int i = 23; i <= 30; i++) {
            if (bits[i] == true) {
                storedExponent += (int) Math.pow(2, i - 23);
            }
        }
        int exponent = storedExponent - 127;

        float mantissa = 0.0f;
        float power = 0.5f;

        for (int i = 22; i >= 0; i--) {
            if (bits[i] == true) {
                mantissa += power;
            }
            power = power / 2.0f;
        }

        float result = (1.0f + mantissa) * (float) Math.pow(2, exponent);

        if (bits[31] == true) {
            result = -result;
        }

        return result;
    }

    /**
     * Returns a string in the format <S> | <exp> | <mant>, where exponent and mantissa are grouped in blocks
     * of 4 bits separated by spaces.
     */
    @Override
    public String toString() {
        String s = "";

        if (bits[31] == true) {
            s += "1";
        } else {
            s += "0";
        }

        s += " | ";

        for (int i = 30; i >= 23; i--) {
            if (bits[i] == true) {
                s += "1";
            } else {
                s += "0";
            }

            if (i == 27) {
                s += " ";
            }
        }

        s += " | ";

        int count = 0;
        for (int i = 22; i >= 0; i--) {
            if (bits[i] == true) {
                s += "1";
            } else {
                s += "0";
            }

            count++;
            if (count % 4 == 0 && i != 0) {
                s += " ";
            }
        }

        return s;
    }
}