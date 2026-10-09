# Exercise 35: Sum array at a0 (count in a1) only including values > 0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Sum of positive values in array at a0 (count in a1))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Mixed: sum positives of [1,-2,3,-4,5]=9
    la a0, cfm02_t1
    li a1, 5
    jal ra, func_35
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfm02_t1: .word 1, -2, 3, -4, 5

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_35
func_35:
    
    mv t0, a0
    li t1, 0
    li t2, 0 
    loop:
    beq t1, a1, end
    addi t1, t1, 1
    lw t3, 0(t0)
    addi t0, t0, 4
    bgt t3, zero, sum
    j loop
    
    sum:
    add t2, t2, t3
    j loop
    
    end:
    mv a0, t2
    ret
