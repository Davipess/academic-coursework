# Exercise 55: Determine if a0 is a palindromic number (reads same forwards/backwards).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (1 if a0 is palindromic, else 0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Palindrome: 121 → 1
    li a0, 121
    jal ra, func_55
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_55
func_55:
    # TODO: Implement the function
    ret
