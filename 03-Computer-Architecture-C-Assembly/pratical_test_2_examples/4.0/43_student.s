# Exercise 43: Count occurrences of byte value a1 in 12-byte buffer at a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Count 'A'(65) in buffer with 3 As → 3
    la a0, mem09_t1
    li a1, 65
    jal ra, func_43
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem09_t1: .byte 65, 72, 65, 76, 76, 79, 65, 87, 79, 82, 76, 68

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_43
func_43:
    mv t0, a0
    li t1, 0
    li t2, 12
    li t4, 0
    
    loop:
    bgt t1, t2, end
    addi t1, t1, 1
    lb t3, 0(t0)
    beq t3, a1, equal
    addi t0, t0, 1
    j loop
    
    equal:
    addi t0, t0, 1
    addi t4, t4, 1
    j loop
    
    end:
    mv a0, t4
    ret
