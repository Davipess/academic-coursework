# Exercise 47: Count the number of 1-bits in a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: popcount(7)=3 via Kernighan
    li a0, 7
    jal ra, func_47
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_47
func_47:
    # TODO: Implement the function
    ret
