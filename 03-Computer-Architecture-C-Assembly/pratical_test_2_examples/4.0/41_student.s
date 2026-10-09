# Exercise 41: Count how many words in array at a0 (10 words) are greater than a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Count >5 in [1..10] → 5 (values 6-10)
    la a0, mem03_t1
    li a1, 5
    jal ra, func_41
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem03_t1: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_41
func_41:
    mv t0, a0
    li t1, 0
    li t2, 0
    li t4, 10
    
    loop:
    bgt t1, t4, end
    addi t1, t1, 1
    lw t3, 0(t0)
    bgt t3, a1, bigger
    addi t0, t0, 4
    j loop

    
    bigger:
    addi t2,t2, 1
    addi t0, t0, 4
    j loop
    
    end:
    mv a0, t2
    ret
