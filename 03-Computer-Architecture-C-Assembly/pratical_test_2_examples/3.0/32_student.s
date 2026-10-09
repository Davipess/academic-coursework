# Exercise 32: Fill a 10-word array at a0 with value a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Fill 10 words with 42; first=42
    la a0, mem11_t1
    li a1, 42
    jal ra, func_32
    la a0, mem11_t1
    lw a0, 0(a0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem11_t1: .space 40

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_32
func_32:
    mv t0, a0
    li t1, 0
    li t2, 10
    
    loop:
    beq t1, t2, end
    sw a1, 0(t0)
    addi t1, t1, 1
    addi t0, t0, 4
    j loop
    
    end:
    mv a0, t0
    ret
    
    
    
    
