# Exercise 05: If a0 > 0, return 1; else return 0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if a0 > 0, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    li a0, 5
    jal ra, func_05
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_05
func_05:
    bgt a0, zero, bigger
    li a0, 0
    ret
    
    bigger:
    li a0, 1
    ret
