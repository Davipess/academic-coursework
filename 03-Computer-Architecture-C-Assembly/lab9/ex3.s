#
# Program to extract the higher half-word from all integers in a vector
#

.data
  vector:    .word 0x00304578, 0x00330050, 0x00375550, 0x00327500
  vecresult: .space 8   # contents after execution: 0x0030, 0x0033, 0x0037, 0x0032
  length:    .byte 4    # number of integers in the vector

.text
.global _start
_start:
    la a0, vector       # address of vector in a0
    lb a1, length       # vector's length in a1
    la a2, vecresult    # address of vecresult in a2
    call extract   

    # print extracted half-words in hex
    la s0, vecresult
    lb s1, length

print_loop:
    blez s1, done_print
    lh a0, 0(s0)
    li a7, 34          # syscall 34: print integer in hexadecimal
    ecall

    li a0, ' '
    li a7, 11
    ecall

    addi s0, s0, 2     # half-words are 2 bytes
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

# a0: address of integer array
# a1: vector's length
# a2: address of half-word array
extract:
    addi sp, sp, -20
    sw ra, 0(sp)
    sw s0, 4(sp)
    sw s1, 8(sp)
    sw s2, 12(sp)

    mv s0, a0          # input vector
    mv s1, a1          # length
    mv s2, a2          # output vector

extract_loop:
    blez s1, extract_done
    lw a0, 0(s0)
    call high_half
    sh a0, 0(s2)       # store 16-bit half-word

    addi s0, s0, 4
    addi s2, s2, 2
    addi s1, s1, -1
    j extract_loop

extract_done:
    lw ra, 0(sp)
    lw s0, 4(sp)
    lw s1, 8(sp)
    lw s2, 12(sp)
    addi sp, sp, 20
    ret

# a0: integer value
# result in a0: two most significant bytes (higher half-word)
high_half:  
    srli a0, a0, 16
    ret  
