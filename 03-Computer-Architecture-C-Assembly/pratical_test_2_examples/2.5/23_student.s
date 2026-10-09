# Exercise 23: Rotate a0 right by 1 bit (treat as circular shift).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: ROL1(5)=10 (0101→1010)
    li a0, 5
    jal ra, func_23
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_23
func_23:
    
    li t2, 1
    mv t0, a0
    andi t0, t0, 1
    beq t0, zero, no_one
    srl a0, a0, t2
    xori a0, a0, 8
    ret
    
    no_one:
    srl a0, a0, t2
    ret
