# Exercise 08: Compute the absolute value of a0.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Absolute value of a0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Positive stays: |5|=5
    li a0, 7
    jal ra, func_08
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_08
func_08:
    srli t0, a0, 31
    andi t1, t0, 1
    beq t1, zero, positive
    sub a0, zero, a0
    ret
    
    positive:
    
    ret
