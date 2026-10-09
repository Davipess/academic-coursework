# Exercise 20: Count digits in decimal number a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Three digits: 123 → 3
    li a0, 123
    jal ra, func_20
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_20
func_20:
    
    li t0, 0
    li t1, 10
    
    loop_start:
    bgt a0, zero, division
    
    mv a0, t0
    ret
    division:
    div a0, a0, t1
    addi t0, t0, 1
    j loop_start
    
