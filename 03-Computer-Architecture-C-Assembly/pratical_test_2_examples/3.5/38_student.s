# Exercise 38: Multiply each word in array at a0 (5 words) by a1, store result back.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Address a0 (array modified))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Multiply [1,2,3,4,5] by 3; first=3
    la a0, mem08_t1
    li a1, 3
    jal ra, func_38
    la a0, mem08_t1
    lw a0, 0(a0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem08_t1: .word 1, 2, 3, 4, 5

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_38
func_38:
    mv t0, a0
    li t2, 0 	
    li t3, 5
    
    loop:
    bgt t2, t3, end
    addi t2, t2, 1
    lw t1, 0(t0)
    mul t4, t1, a1
    sw t4, 0(t0)
    li t4, 0
    addi t0, t0, 4
    j loop
    
    end:
    
    ret
