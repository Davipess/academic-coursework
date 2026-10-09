# Exercise 49: Find second-largest value in array at a0 (10 words).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Second-largest value in array of 10 words at a0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Ascending: second largest of [1..10] = 9
    la a0, cfm03_t1
    jal ra, func_49
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfm03_t1: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_49
func_49:
    # TODO: Implement the function
    ret
