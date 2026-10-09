#include "gp_register.h"

#include <stdio.h>
#include <assert.h>
#include <math.h>

void gp_register_set_bit(gp_register reg, int index, bool value) {
	 assert (index >= 0 && index < GP_REGISTER_SIZE);
        reg[index] = value;
}

int gp_register_get_bit(const gp_register reg, int index) {
	 assert (index >= 0 && index < GP_REGISTER_SIZE); 
        return reg[index];
}

void gp_register_set(gp_register reg, int value) {
    long tempValue = value;

        if (tempValue < 0) {
            tempValue = (long) pow(2, GP_REGISTER_SIZE) + tempValue;
        }

        for (int i = 0; i < GP_REGISTER_SIZE; i++) {
            long remainder = tempValue % 2;

            if (remainder == 1) {
                gp_register_set_bit(reg ,i, true);
            } else {
                gp_register_set_bit(reg ,i, false);
            }

            tempValue = tempValue / 2;
        }
}

unsigned int gp_register_get_unsigned(const gp_register reg) {
    int trueValue = 0;

        for (int i = 0; i < GP_REGISTER_SIZE; i++) {
            if (gp_register_get_bit(reg, i)) {
                trueValue += (int) pow(2, i);
            }
        }
        return trueValue;
}

int gp_register_get_int(const gp_register reg) {
    int trueValue = gp_register_get_unsigned(reg);
        int msbIndex = GP_REGISTER_SIZE - 1;

        if (gp_register_get_bit(reg, msbIndex)) {
            trueValue -= (int) pow(2, GP_REGISTER_SIZE);
        }

        return trueValue;
}

void print_register(const gp_register reg) {
    printf ("register: ");
    for (int i = GP_REGISTER_SIZE-1; i >=0; i--) {
        printf ("%d", gp_register_get_bit(reg, i));
        if (i%4 == 0 && i != 0) printf (" ");
    }
    printf ("\n");
}