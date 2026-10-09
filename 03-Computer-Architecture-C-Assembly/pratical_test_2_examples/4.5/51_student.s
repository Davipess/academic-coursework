# Exercise 51: Find longest run of consecutive equal values in array at a0 (count in a1).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Run of 3 at start: [1,1,1,2,3] → 3
    la a0, cfm10_t1
    li a1, 5
    jal ra, func_51
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfm10_t1: .word 1, 1, 1, 2, 3

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_51
func_51:
    # TODO: Implement the function
    ret
