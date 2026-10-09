#
# Program to calculate the first n fibonacci numbers
#

.data
  msg0:    .asciz "Fibonacci numbers: \n"
  num:     .word 10
  results: .space 80  # saves space for max 20 fibonacci numbers
  maxnum:  .word 20

.text
.global _start
_start:	 
	la a0, msg0
	li a7, 4
	ecall

	lw a0, num
	call fib
	call printvalues
	
	# exit(0)		 
	li a0, 0
	li a7, 93
 	ecall		
  
# Generates the first n fibonacci numbers into array results
# fib(0)=0, fib(1)=1, fib(n)=fib(n-1)+fib(n-2)
fib:
	addi sp, sp, -16
	sw ra, 0(sp)
	sw s0, 4(sp)
	sw s1, 8(sp)

	mv s0, a0          # n
	la s1, results
	blez s0, fib_done

	# F(0) = 0
	li t0, 0
	sw t0, 0(s1)
	li t1, 1
	beq s0, t1, fib_done

	# F(1) = 1
	li t0, 1
	sw t0, 4(s1)

	li t2, 2           # index i
fib_loop:
	bge t2, s0, fib_done
	slli t3, t2, 2
	add t3, s1, t3
	lw t4, -4(t3)      # F(i-1)
	lw t5, -8(t3)      # F(i-2)
	add t6, t4, t5
	sw t6, 0(t3)
	addi t2, t2, 1
	j fib_loop

fib_done:
	lw ra, 0(sp)
	lw s0, 4(sp)
	lw s1, 8(sp)
	addi sp, sp, 16
	ret

# print all values in vector results
printvalues:
	addi sp, sp, -16
	sw ra, 0(sp)
	sw s0, 4(sp)
	sw s1, 8(sp)

	lw s0, num
	la s1, results

print_loop:
	blez s0, print_done
	lw a0, 0(s1)
	li a7, 1
	ecall

	li a0, ' '
	li a7, 11
	ecall

	addi s1, s1, 4
	addi s0, s0, -1
	j print_loop

print_done:
	li a0, 10
	li a7, 11
	ecall

	lw ra, 0(sp)
	lw s0, 4(sp)
	lw s1, 8(sp)
	addi sp, sp, 16
	ret
