# Exercise 30: Copy a null-terminated string from address a0 to address a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Address a1 (string copied))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Copy 'Hello'; read first byte of dst='H'=72
    la a0, mem06_t1s
    la a1, mem06_t1d
    jal ra, func_30
    la a0, mem06_t1d
    lb a0, 0(a0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem06_t1s: .asciz "Hello"
mem06_t1d: .space 16

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_30
func_30:
    mv t0, a0          
    mv t1, a1          

loop:
    lb t2, 0(t0)       
    sb t2, 0(t1)       
    
    beq t2, zero, end  
    
    addi t0, t0, 1     
    addi t1, t1, 1     
    
    j loop            

end:
    mv a0, a1          
    ret
