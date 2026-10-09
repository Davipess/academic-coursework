#include <stdio.h>
#include <stdlib.h>
#include <math.h>



#include "gp_register.h"

int main(int argc, char* argv[]) {

    if (argc != 2) {
        printf("usage %s number\n", argv[0]);
        return 1;
    }

    gp_register r1;
    gp_register_set(r1, (int) strtol(argv[1], 0, 10));

    print_register(r1);
    printf("unsigned: %u\t signed: %d\n ", gp_register_get_unsigned(r1), gp_register_get_int(r1));

    return 0;
}