.data
    vector: .word -1, 5, 1, 1, 4
    length: .byte 5     # number of integers in the vector
    sum:    .word 0     # must contain the sum of all elements in vector

.text
.global _start

_start:
    la t0, vector       # pointer to current vector element
    lb t1, length       # remaining elements counter
    li t2, 0            # accumulator for sum

loop:
    beqz t1, endLoop
    lw t3, 0(t0)        # load element
    add t2, t2, t3      # sum += element
    addi t0, t0, 4      # advance to next word
    addi t1, t1, -1     # decrement count
    j loop

endLoop:
    la t4, sum
    sw t2, 0(t4)        # store total sum into variable sum

    # write integer to stdout
    mv a0, t2
    li a7, 1
    ecall

    # newline
    li a0, 10
    li a7, 11
    ecall

    # syscall exit(int status)
    li a0, 0            # exit status
    li a7, 93           # syscall exit
    ecall
