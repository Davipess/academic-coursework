# Exercise 39: Check if array at a0 (count in a1) is sorted in ascending order. Return 1 or 0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if array at a0 (count in a1) is sorted ascending, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Sorted: [1,2,3,4,5] → 1
    la a0, cfm08_t1
    li a1, 5
    jal ra, func_39
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfm08_t1: .word 1, 2, 3, 4, 5

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_39
func_39:
    mv t0, a0
    li t3, 4
    li t4, 0
    
    loop:
    bgt t4, t3, end
    addi t4, t4, 1
    lw t1, 0(t0)
    lw t2, 4(t0)
    bgt t1, t2, not_equal
    addi t1, t1, 4
    addi, t2, t2 4
    j loop
    
    not_equal:
    li a0, 0
    ret
    
    end:
    li a0, 1
    ret
    
    
    
