# Exercise 61: Rotate array at a0 (10 words) right by a1 positions.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Modified (array rotated in-place))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Rotate [1..10] right by 2; print first (=9)
    la a0, meh12_t1
    li a1, 2
    jal ra, func_61
    la a0, meh12_t1
    lw a0, 0(a0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
meh12_t1: .word 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_61
func_61:
    # TODO: Implement the function
    ret
