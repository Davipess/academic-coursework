# Exercise 25: Count occurrences of value a1 in array of 5 words at a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: 3 appears 3 times in [3,5,3,7,3]
    la a0, cfe07_t1
    li a1, 3
    jal ra, func_25
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfe07_t1: .word 3, 5, 3, 7, 3

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_25
func_25:
    mv t0, a0
    li t1, 0
    li t3, 0
    li t4, 5
    
    loop:
    blt t3, t4, calculate
    mv a0, t1
    ret

    calculate:
    lw t2, 0(t0)
    addi t0, t0, 4
    addi t3, t3, 1
     beq t2, a1, equal
     j loop
     ret
    
    equal:
    addi t1, t1, 1
    j loop
    ret
    