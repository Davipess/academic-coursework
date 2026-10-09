# Exercise 54: Find the first occurrence of byte value a1 in a byte array at address a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: find byte 30 in [10,20,30,40] → offset 2
    la a0, alh04_t1
    li a1, 30
    jal ra, func_54
    la t0, alh04_t1
    sub a0, a0, t0
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
alh04_t1: .byte 10, 20, 30, 40, 0

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_54
func_54:
    # TODO: Implement the function
    ret
