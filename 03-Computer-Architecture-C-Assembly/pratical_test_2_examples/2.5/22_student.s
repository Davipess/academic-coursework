# Exercise 22: Compute (a0 * 5) + (a1 * 3).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: 2*5+3*3=19
    li a0, 2
    li a1, 3
    jal ra, func_22
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_22
func_22:
    li t0 5
    li t1 3
    
    mul t2, a0, t0
    mul t3, a1, t1
    add t4, t2,t3
    mv a0,t4
    ret
