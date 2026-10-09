# Exercise 24: Swap the upper and lower 16 bits of a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Small value: swap_halves(5)=327680 (0x00050000)
    li a0, 5
    jal ra, func_24
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_24
func_24:

    srli t0, a0, 16
    slli t1, a0, 16
    or t2, t1, t0
    mv a0, t2 
    ret
