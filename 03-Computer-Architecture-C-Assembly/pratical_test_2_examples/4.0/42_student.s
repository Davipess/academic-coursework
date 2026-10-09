# Exercise 42: Find the first index of value a1 in word array at a0 (8 words, return -1 if not found).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: find 3 in [1..8] → index 2
    la a0, mem05_arr
    li a1, 3
    jal ra, func_42
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem05_arr: .word 1, 2, 3, 4, 5, 6, 7, 8

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_42
func_42:
    mv t0, a0
    li t1, 0
    li t2, 8
    
    loop:
    bgt t1, t2, end
    addi t1, t1, 1
    lw t3, 0(t0)
    beq t3, a1, equal
    addi t0, t0, 4
    j loop
    
    equal:
    mv a0, t3
    ret
    
    end:
    li a0, -1
    ret
    
    
