# Exercise 26: Return the maximum of a0 and a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: a0 larger: max(7,3)=7
    li a0, 7
    li a1, 3
    jal ra, func_26
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_26
func_26:
    bgt a0, a1, bigger
    mv a0, a1
    ret

    bigger:
    ret