.data
    var1:   .word -35 # example values (try with different values)
    var2:   .word 10
    maxval: .word 0  # must contain the max value between var1 and var2

.text
.global _start
_start:

    lw a0, var1       # first argument in a0
    lw a1, var2       # second argument in a1
    call max          # call max function
    sw a0, maxval, t0 # store result in maxval
    
    # syscall to write an integer
    li a7, 1
    ecall
    
    # newline
    li a0, 10
    li a7, 11
    ecall

    # exit(int status)
    li a0, 0          # exit status
    li a7, 93         # syscall number for exit
    ecall

# a0: first argument
# a1: second argument
# a0: return value (max between a0 and a1)
max:  
    bge a0, a1, is_greater
    mv a0, a1
is_greater:
    ret               # return address in ra
