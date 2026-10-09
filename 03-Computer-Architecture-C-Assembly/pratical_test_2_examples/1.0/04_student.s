# Exercise 04: Return 1 if a0 == a1, else 0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if a0 is even, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Equal: 5==5 → 1
    li a0, 5
    li a1, 4
    jal ra, func_04
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_04
func_04:
    beq a0, a1, equal
    li a0, 0
    ret
    
    equal:
    li a0, 1
    ret
