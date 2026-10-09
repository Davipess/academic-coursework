# Exercise 07: Store value a2 at index a1 in array at address a0 (each element is 4 bytes).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if a0 < a1, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Store 99 at index 1; read back arr[1]=99
    la a0, mee12_t1
    li a1, 1
    li a2, 99
    jal ra, func_07
    la t0, mee12_t1
    lw a0, 4(t0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mee12_t1: .word 10, 20, 30, 40

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_07
func_07:
    li t0, 4
    mul t1, a1, t0
    add t1, a0, t1
    sw a2, 0(t1)
    ret
