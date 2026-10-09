# Exercise 12: Sum integers from 1 to a0 (inclusive).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Sum of integers from 1 to a0 (inclusive))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Sum 1..5=15
    li a0, 5
    jal ra, func_12
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_12
func_12:
    li t0, 1
    li t1, 0
    loop_start:
    bgt t0, a0, loop_end
    add t1, t1, t0
    addi t0, t0, 1
    j loop_start
    
    loop_end:
    mv a0, t1
    ret