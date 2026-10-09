# Exercise 11: Loop from a0 to a1, return the sum.
#
# Input:  a0, a1
# Output: a0 (Sum of integers from a0 to a1 (inclusive))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: sum(1,5)=1+2+3+4+5=15
    li a0, 1
    li a1, 5
    jal ra, func_11
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_11
func_11:
    li t0, 0

loop_start:
    bgt a0, a1, loop_end
    add t0, t0, a0
    addi a0, a0, 1
    j loop_start

loop_end:
    mv a0, t0
    ret



