# Exercise 34: Implement three-way comparison: return -1 if a0<a1, 0 if a0==a1, 1 if a0>a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (-1 if a0 < a1, 0 if a0 == a1, 1 if a0 > a1)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Greater: 5>3 → 1
    li a0, 5
    li a1, 3
    jal ra, func_34
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_34
func_34:
    blt a0, a1, smaller
    beq a0, a1, equal
    bgt a0, a1, bigger
    smaller:
    li a0, -1
    ret
    
    equal:
    li a0, 0
    ret
    
    bigger: 
    li a0, 1
    ret
