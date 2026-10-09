# Exercise 46: Compute Fibonacci number F(a0) where F(0)=0, F(1)=1, F(n)=F(n-1)+F(n-2).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (F(a0): Fibonacci number where F(0)=0, F(1)=1, F(n)=F(n-1)+F(n-2))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: F(6)=8
    li a0, 6
    jal ra, func_46
    li a7, 1
    ecall
    li a7, 10
    ecall

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_46
func_46:

    addi sp, sp, 16
    lw ra, ra, 12(sp)
    lw s0, s0, 8(sp)
    mv s0, a0
    
    li t0, 0
    li t1, 0
    li t2, 1
    
    loop:
    bgt t0, a0, end
    addi t0, t0, 1
    add t3, t2, t1
    mv t1, t2
    mv t2, t3
    j loop
    
    end:
    add t4, t3, t2
    sw ra, ra, 12(sp)
    sw s0, s0, 8(sp)
    addi sp, sp, -16
    mv s0, a0
    mv a0, t4
    ret
