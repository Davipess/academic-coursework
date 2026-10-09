# Exercise 53: Compute length of null-terminated string at a0 (strlen).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Length of null-terminated string at a0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: len('Hello')=5
    la a0, meh10_t1
    jal ra, func_53
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
meh10_t1: .asciz "Hello"

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_53
func_53:
    mv t0, a0
    li t1, 0
    
    loop:
    lb t2, 0(t0)
    beq t2, zero, end
    addi t3, t3, 1
    addi t0, t0, 1
    j loop
    
    end:
    mv a0, t3
    ret
