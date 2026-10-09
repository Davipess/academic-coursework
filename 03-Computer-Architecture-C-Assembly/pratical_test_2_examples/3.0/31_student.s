# Exercise 31: Compute checksum of 8-byte block at a0 (xor all bytes).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Sum bytes [1..8]=36
    la a0, mem07_t1
    jal ra, func_31
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem07_t1: .byte 1, 2, 3, 4, 5, 6, 7, 8

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_31
func_31:
    mv t0, a0
    li t1, 0
    li t2, 8
    li t4, 0
    
    loop:
    beq t1, t2, end
    lb t3, 0(t0)
    add t4, t4, t3
    addi t0, t0, 1
    addi t1, t1, 1
    j loop
   
   end:
   mv a0, t4
   ret
