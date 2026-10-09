
#
# Program to convert a string to lowercase
#

.data
  msg: .ascii "Hello World!\n\0"

.text
.global _start
_start:	 
    la a0, msg 		
    call write		# jal write
    call converts   	# jal  converts

    la a0, msg 		
	call write		# jal write
   
    # exit(int status)
    li	a0, 0        # exit status
    li	a7, 93       # syscall number for exit
    ecall

# void converts( char str[] )
# a0: string address 
converts:
	addi sp, sp -16
	sw ra, 0(sp)
	sw s0, 4(sp)
	
	mv s0, a0
	
	
	loop: 
	lbu a0, 0(s0)
	beq a0, zero, end_conv

    	call lowercase
   
   	sb a0, 0(s0)
   	addi s0, s0, 1
   	j loop
   	
   	end_conv:
   	lw ra, 0(sp)
   	lw s0, 4(sp)
   	addi sp, sp, 16

	 ret

# char lowercase( char ch )
# ao has the character to be converted to lower case
# result: a0 
lowercase:  

    li t0, 'A'
    blt a0, t0, end
    
    li t0, 'Z'
    bgt a0, t0, end
    
    addi a0,a0, 32
    
    end:
    	ret    		# jr ra

# write( char str[] ) – syscall
# argument a0: string's address 
write:
    li 	a7, 4        
    ecall 
    ret   		# jr ra 
    
