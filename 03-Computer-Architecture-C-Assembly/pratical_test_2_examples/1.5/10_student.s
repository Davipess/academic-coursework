# Exercise 10: Return the larger of two values a0 and a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Minimum of a0 and a1)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: a0 larger: max(7,3)=7
    li a0, 7
    li a1, 3
    jal ra, func_10
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_10
func_10:
    bgt a0, a1, zero_bigger
    li a0, 0
    add a0, a0, a1
    ret

zero_bigger:

ret


