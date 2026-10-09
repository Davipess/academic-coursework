# Exercise 57: Compute the sum of digits of a0 (treated as decimal).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Sum of digits of a0 (treated as decimal))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: digits(123)=1+2+3=6
    li a0, 123
    jal ra, func_57
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_57
func_57:
    # TODO: Implement the function
    ret
