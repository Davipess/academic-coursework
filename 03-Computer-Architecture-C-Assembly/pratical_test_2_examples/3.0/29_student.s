# Exercise 29: Sum all 10 words in an array at address a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Sum of all 10 words in array at a0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Sum [1..10]=55
    la a0, mem01_t1
    jal ra, func_29
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem01_t1: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_29
func_29:
    mv t0, a0
    li t1, 0
    li t2, 10
    li t4, 0 
    
    loop:
    beq t1, t2, end
    lw t3, 0(t0)
    addi t0, t0, 4
    add t4, t4, t3
    addi t1, t1, 1
    j loop
    ret
    
    
    end:
    mv a0, t4
    ret
    
