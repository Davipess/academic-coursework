# Exercise 03: Return 1 if a0 is even, else 0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if a0 is even, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Even number: 4 → 1
    li a0, 4
    jal ra, func_03
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_03
func_03:
    andi a0, a0, 1
    li t0, 1
    beq a0, t0, e_impar
    li a0, 1        
    ret            
    
e_impar:
    li a0, 0         
    ret             
