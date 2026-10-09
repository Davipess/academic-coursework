# Exercise 14: Factorial of a0 using iteration (not recursion).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if array at a0 is sorted, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    li a0, 5
    jal ra, func_14
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_14
func_14:
    li t0, 1
    li t1, 1  
    loop_start:
    bge t0, a0, loop_end
    addi t0, t0, 1
    mul t1, t1, t0
    j loop_start
    ret
    
    
    loop_end:
    mv a0, t1
    ret
    
    
