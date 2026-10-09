.data
  msg1: .asciz "Hello \n"
  msg2: .asciz "Tuesdays \n"
  
.text
.global _start
    
_start: 
    # syscall to write original msg1
    la a0, msg1
    li a7, 4        
    ecall
    
    # capitalize msg1
    la a0, msg1
    call caps
    
    # syscall to write capitalized msg1
    la a0, msg1
    li a7, 4        
    ecall
    
    # syscall to write original msg2
    la a0, msg2
    li a7, 4        
    ecall
    
    # capitalize msg2
    la a0, msg2
    call caps

    # syscall to write capitalized msg2
    la a0, msg2
    li a7, 4        
    ecall

    # exit system call
    li a0, 0         # exit status
    li a7, 93 
    ecall       
   
# a0 has the address of the string 
caps:
    mv t0, a0

caps_loop:
    lb t1, 0(t0)
    beqz t1, caps_done
    li t2, 'a'
    li t3, 'z'
    blt t1, t2, not_lower
    bgt t1, t3, not_lower
    addi t1, t1, -32 # convert lowercase ASCII to uppercase
    sb t1, 0(t0)

not_lower:
    addi t0, t0, 1
    j caps_loop

caps_done:
    ret
