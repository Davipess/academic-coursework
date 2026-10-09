# Exercise 40: Find the maximum value in an array of 10 words at address a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Maximum value in array of 10 words at a0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Max of [1..10]=10
    la a0, mem02_t1
    jal ra, func_40
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem02_t1: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_40
func_40:
    mv t0, a0
    lw t2, 0(t0)
    li t1, 0
    li t4, 9
    loop:
    bgt t1 t4, end
    addi t1, t1, 1
    lw t3, 4(t0)
    blt t2, t3, new_king
    addi t0, t0, 4
    j loop

    new_king:
    mv t2, t3
    addi t0, t0, 4
    j loop
    
    end:
    mv a0, t2
    ret