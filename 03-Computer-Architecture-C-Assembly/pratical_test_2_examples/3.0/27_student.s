# Exercise 27: Return the minimum of a0 and a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Minimum of a0 and a1)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: a1 smaller: min(7,3)=3
    li a0, 2
    li a1, 3
    jal ra, func_27
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_27
func_27:
    blt a0, a1, smaller
    mv a0, a1
    ret

    smaller:
    ret