# Exercise 13: Return a0 if a0 > 0, else return 42.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (a0 if a0 > 0, else 42)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Positive → return itself: 5→5
    li a0, 5
    jal ra, func_13
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_13
func_13:
    bgt a0, zero, func
    li a0, 42
    ret
    
    func:
    ret 
