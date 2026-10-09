#
# Program to calculate the factorial of a number
#

.data
	msg0: .asciz "Number? "
	msg1: .asciz "factorial("
	msg2: .asciz ") == "
	num:  .word 5

.text
.global _start
_start:	 
	lw a0, num
	call fact
	mv s0, a0          # save result

	# print "factorial("
	la a0, msg1
	li a7, 4
	ecall

	# print num
	lw a0, num
	li a7, 1
	ecall

	# print ") == "
	la a0, msg2
	li a7, 4
	ecall

	# print factorial result
	mv a0, s0
	li a7, 1
	ecall

	# newline
	li a0, 10
	li a7, 11
	ecall
	
	# exit(0)		 
	li a0, 0
	li a7, 93
 	ecall
    
# int fact( n ) ==> fact(0)==1; fact(1)==1; fact(n)==n*fact(n-1)
fact:
	addi sp, sp, -16
	sw ra, 0(sp)
	sw s0, 4(sp)

	mv s0, a0
	li t0, 1
	ble s0, t0, base_case

	addi a0, s0, -1
	call fact
	mul a0, a0, s0
	j fact_done

base_case:
	li a0, 1

fact_done:
	lw ra, 0(sp)
	lw s0, 4(sp)
	addi sp, sp, 16
	ret
