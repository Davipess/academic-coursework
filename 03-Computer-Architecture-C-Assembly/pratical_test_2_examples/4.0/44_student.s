# Exercise 44: Compare two 4-byte strings at a0 and a1. Return 0 if equal, -1 or 1 if different.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Equal 4-byte strings 'ABCD'='ABCD' → 0
    la a0, mem10_t1a
    la a1, mem10_t1b
    jal ra, func_44
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem10_t1a: .byte 65, 66, 67, 68
mem10_t1b: .byte 64, 66, 67, 68

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_44
func_44:
    mv t0, a0
    mv t1, a1
    
    li t2, 0
    li t3, 4
    
    loop:
    bgt t2, t3, end
    addi t2, t2, 1
    lw t4, 0(t0)
    lw t5, 0(t1)
    beq t4, t5, equal
    j loop
    
    equal:
    li a0, 0
    ret
    end:
    li a0, 1
    ret
