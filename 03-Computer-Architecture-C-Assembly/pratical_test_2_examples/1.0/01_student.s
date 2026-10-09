# Exercise 01: Multiply the input value in a0 by 3.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if a0 > 0, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    li a0, 5
    jal ra, func_01
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_01
func_01:
    li t0, 3
    mul a0, a0, t0
    ret
