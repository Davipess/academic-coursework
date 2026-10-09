# Exercise 33: Return 1 if a0 is a power of 2, else 0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Power of 2: 4=2^2 → 1
    li a0, 4
    jal ra, func_33
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_33
func_33:
    andi t0, a0, 1
    beq t0, zero, equal
    li a0, 0
    ret
    
    equal:
    li a0, 1
    ret