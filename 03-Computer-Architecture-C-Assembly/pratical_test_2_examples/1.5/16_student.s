# Exercise 16: Swap two words: word at a0 and word at a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of pattern matching)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Swap 42 and 99; word at a0 becomes 99
    la a0, mee10_t1a
    la a1, mee10_t1b
    jal ra, func_16
    la a0, mee10_t1a
    lw a0, 0(a0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mee10_t1a: .word 42
mee10_t1b: .word 99

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_16
func_16:
    lw t2, 0(a0)
    lw t3, 0(a1)
    
    sw t3, 0(a0)
    sw t2, 0(a1)
    
    ret
