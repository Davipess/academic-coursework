# Exercise 21: Copy 4 bytes from address a0 to address a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Copy [65,66,67,68] to dst; read first byte of dst=65
    la a0, mee08_t1s
    la a1, mee08_t1d
    jal ra, func_21
    la a0, mee08_t1d
    lb a0, 0(a0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mee08_t1s: .byte 65, 66, 67, 68
mee08_t1d: .space 4

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_21
func_21:
    lb t0, 0(a0)
    lb t1, 1(a0)
    lb t2, 2(a0)
    lb t3, 3(a0)
    
    sb t0, 0(a1)
    sb t1, 1(a1)
    sb t2, 2(a1)
    sb t3, 3(a1)
    
    ret
