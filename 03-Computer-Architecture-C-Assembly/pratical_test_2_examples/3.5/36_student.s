# Exercise 36: Compute sum of array at a0 (count in a1) where each element is multiplied by its index.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Sum of (array[i] * i) for all i in array at a0 (count in a1))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: [1,2,3,4,5] count=5 → 0+2+6+12+20=40
    la a0, cfm09_t1
    li a1, 5
    jal ra, func_36
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfm09_t1: .word 1, 2, 3, 4, 5

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_36
func_36:
    mv t0, a0
    li t1, 0
    li t2, 0
    li t4, 0
    
    loop:
    bgt t1, a1, end
    lw t3, 0(t0)
    addi t0, t0, 4
    mv t4, t3
    mul t4, t4, t1
    addi t1, t1, 1
    add t2, t2, t4
    li t4, 0
    j loop
    
    end:
    mv a0, t2
    ret