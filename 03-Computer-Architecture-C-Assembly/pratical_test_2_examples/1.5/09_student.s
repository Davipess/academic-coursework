# Exercise 09: If a0 == a1, return 100; else return 200.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (100 if a0 == a1, else 200)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Equal values → 100
    li a0, 5
    li a1, 5
    jal ra, func_09
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_09
func_09:
    beq a0, a1, equal
    li a0, 200
    ret
    
    equal:
    li a0, 100
    ret
