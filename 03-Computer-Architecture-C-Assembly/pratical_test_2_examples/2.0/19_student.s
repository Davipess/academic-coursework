# Exercise 19: Return 1 if a0 is in range [a1, a2], else 0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of computation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: In range: a0=5, a1=1, a2=10 → 1
    li a0, 5
    li a1, 1
    li a2, 10
    jal ra, func_19
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_19
func_19:

    bge a0, a1, bigger
    li a0, 0
    ret
    bigger:
    ble a0, a2, smaller
    li a0,0
    ret
    
    smaller:
    li a0, 1
    ret
    
    
