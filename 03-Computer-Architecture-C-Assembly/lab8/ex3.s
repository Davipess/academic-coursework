.data
    vector: .word -1, 5, 1, 1, 4
    length: .byte 5 # number of integers in the vector
    major:  .word 0 # must contain the biggest of all elements in vector

.text
.global _start
_start:
    la a0, vector     # vector's base address
    lb a1, length     # length of vector
    call max_vec      # find max
    sw a0, major, t0  # store in major
 
    # syscall to write an integer (a0 holds max)
    li a7, 1
    ecall

    # newline
    li a0, 10
    li a7, 11
    ecall

    # exit(0)
    li a0, 0          # exit status
    li a7, 93         # syscall number for exit
    ecall

# a0: vector's address
# a1: number of integers in the vector
# a0: returns the max element in the vector
max_vec:
    blez a1, empty_vec
    lw t0, 0(a0)      # initialize max with first element
    addi a0, a0, 4    # advance pointer
    addi a1, a1, -1   # decrement count

loop_vec:
    blez a1, done_vec
    lw t1, 0(a0)
    ble t1, t0, next_elem
    mv t0, t1         # new max found
next_elem:
    addi a0, a0, 4
    addi a1, a1, -1
    j loop_vec

done_vec:
    mv a0, t0
    ret

empty_vec:
    li a0, 0
    ret