# Exercise 48: Reverse the bit order of a0 (32-bit).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Bit 0 to bit 31: reverse(1)=-2147483648 (0x80000000)
    li a0, 1
    jal ra, func_48
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_48
func_48:
    # TODO: Implement the function
    ret
