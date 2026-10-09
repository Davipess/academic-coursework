# Exercise 50: Remove duplicates from sorted array at a0 (count in a1), return new length.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Has duplicates: [1,1,2,3,3,4] count=6 → new length 4
    la a0, cfm07_t1
    li a1, 6
    jal ra, func_50
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfm07_t1: .word 1, 1, 2, 3, 3, 4

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_50
func_50:
    # TODO: Implement the function
    ret
