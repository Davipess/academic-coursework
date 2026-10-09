#
# Program to select the lowest elements at the same position from two vectors
#

.data
  vector1:   .word -1, 5, 1, 1, 4
  vector2:   .word 1, 3, -1, 5, 9
  vecresult: .space 20  # contents after execution: -1, 3, -1, 1, 4
  length:    .byte 5    # number of integers in the vectors

.text
.global _start
_start:
    la a0, vector1     # address of vector1 in a0
    la a1, vector2     # address of vector2 in a1
    lb a2, length      # vectors length in a2
    la a3, vecresult   # address of vecresult in a3
    call min_elemsv

    # Print the resulting vector elements
    la s0, vecresult
    lb s1, length

print_loop:
    blez s1, done_print
    lw a0, 0(s0)
    li a7, 1
    ecall

    li a0, ' '
    li a7, 11
    ecall

    addi s0, s0, 4
    addi s1, s1, -1
    j print_loop

done_print:
    li a0, 10
    li a7, 11
    ecall

    # exit(int status)
    li a0, 0           # exit status
    li a7, 93          # syscall number for exit
    ecall

# a0: first integer array's address
# a1: second integer array's address
# a2: vectors' length
# a3: address of the result vector
min_elemsv:
    addi sp, sp, -24
    sw ra, 0(sp)
    sw s0, 4(sp)
    sw s1, 8(sp)
    sw s2, 12(sp)
    sw s3, 16(sp)

    mv s0, a0          # vector1 pointer
    mv s1, a1          # vector2 pointer
    mv s2, a2          # count
    mv s3, a3          # vecresult pointer

loop_min:
    blez s2, fim_loop
    lw a0, 0(s0)
    lw a1, 0(s1)
    call min           # result min(a0, a1) in a0
    sw a0, 0(s3)

    addi s0, s0, 4
    addi s1, s1, 4
    addi s3, s3, 4
    addi s2, s2, -1
    j loop_min

fim_loop:
    lw ra, 0(sp)
    lw s0, 4(sp)
    lw s1, 8(sp)
    lw s2, 12(sp)
    lw s3, 16(sp)
    addi sp, sp, 24
    ret

# first integer in a0
# second integer in a1
# result in a0: min(a0, a1)
min:  
    ble a0, a1, is_min
    mv a0, a1
is_min:
    ret
