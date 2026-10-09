# Exercise 17: Skip even numbers: sum only odd numbers from a0 to a1 inclusive.
#
# Input:  a0, a1
# Output: a0 (Sum of elements in array)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: odds in [1,10] = 1+3+5+7+9 = 25
    li a0, 1
    li a1, 10
    jal ra, func_17
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_17
func_17:
    
    li t0, 0
    li t2, 0
    
    
    loop_start:
    bgt a0, a1, loop_end
    andi t0, a0, 1
    beq t0, zero, par
    add t2, t2, a0 
    
    
    par:
    addi a0, a0, 1
    j loop_start
    
    loop_end:
    mv a0, t2
    ret
    
