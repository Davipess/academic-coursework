# Exercise 58: Find longest increasing subsequence in array at a0 (count in a1), return length.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Length of longest increasing subsequence in array at a0 (count in a1))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: LIS([1,3,2,4,5])=4
    la a0, cfh08_t1
    li a1, 5
    jal ra, func_58
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfh08_t1: .word 1, 3, 2, 4, 5

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_58
func_58:
    # TODO: Implement the function
    ret
