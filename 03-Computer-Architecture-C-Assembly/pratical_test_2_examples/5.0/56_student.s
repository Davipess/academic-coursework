# Exercise 56: Find the position of the highest set bit in a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Position of highest set bit in a0 (0-indexed from right))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: 8=2^3: highest bit at position 3
    li a0, 8
    jal ra, func_56
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_56
func_56:
    # TODO: Implement the function
    ret
