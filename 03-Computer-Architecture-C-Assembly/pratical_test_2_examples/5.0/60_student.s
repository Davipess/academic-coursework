# Exercise 60: Compute LCS (longest common subsequence) length of two strings at a0 and a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Length of longest common subsequence of strings at a0 and a1)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Identical strings: LCS('ABCD','ABCD')=4
    la a0, cfm12_t1s1
    la a1, cfm12_t1s2
    jal ra, func_60
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfm12_t1s1: .asciz "ABCD"
cfm12_t1s2: .asciz "ABCD"

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_60
func_60:
    # TODO: Implement the function
    ret
