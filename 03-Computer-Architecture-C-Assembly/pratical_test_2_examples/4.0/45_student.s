# Exercise 45: Transpose 2x3 word matrix at a0, store result at a1 (row-major).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Transpose [[1,2,3],[4,5,6]] → 3x2; first of result=1
    la a0, mem12_t1s
    la a1, mem12_t1d
    jal ra, func_45
    la a0, mem12_t1d
    lw a0, 0(a0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem12_t1s: .word 1, 2, 3, 4, 5, 6
mem12_t1d: .space 24

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_45
func_45:
    mv t0, a1
    
    lw t5, 0(a0)
    lw t1, 4(a0)
    lw t2, 8(a0)
    lw t3, 12(a0)
    lw t4, 16(a0)
    lw t6, 20(a0)
    
    sw t3, 4(t0)
    sw t4, 8(t0)
    sw t1, 12(t0)
    sw t2, 16(t0)
    
    sw t6, 20(t0)
    sw t5, 0(t0)
    
    mv a1, t0
    ret
