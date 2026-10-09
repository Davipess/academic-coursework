# Exercise 59: Validate if parentheses string at a0 is balanced using a stack.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if parentheses string at a0 is balanced, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: "(())" is balanced → 1
    la a0, cfh09_t1
    jal ra, func_59
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfh09_t1: .asciz "(())"

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_59
func_59:
    # TODO: Implement the function
    ret
