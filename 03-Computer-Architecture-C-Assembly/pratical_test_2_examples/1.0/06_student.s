# Exercise 06: Load array element at index a1 from array at address a0 (each element is 4 bytes).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Value at array[a1] (4-byte word at address a0 + a1*4))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Load arr[1] from [10,20,30,40] → 20
    la a0, mee11_arr
    li a1, 1
    jal ra, func_06
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mee11_arr: .word 10, 20, 30, 40

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_06
func_06:
    li t0, 0
    li t1, 4
    mul t0, a1, t1
    add t0, a0, t0
    lw a0, 0(t0)
    ret
