.text
.global func_28
func_28:
    li t2, 0           
    
loop:
    beq a0, zero, end
    andi t3, a0, 1
    bne t3, zero, is_one
    srli a0, a0, 1
    j loop
    
is_one:
    addi t2, t2, 1    
    srli a0, a0, 1     
    j loop
    
end:
    mv a0, t2          
    ret