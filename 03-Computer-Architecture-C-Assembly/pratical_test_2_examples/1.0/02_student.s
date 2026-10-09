# Exercise 02: Return 1 if a0 > 0, else 0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if a0 > 0, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    li a0, 5
    jal ra, func_02
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_02
func_02:
    li t0, 0
    bgt a0, t0, bigger
    li a0, 0
    ret

bigger:
li a0, 1
ret 
