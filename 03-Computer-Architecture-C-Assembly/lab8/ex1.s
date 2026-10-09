.data
    var1:   .word 35 # example values (try with different values)
    var2:   .word 10
    result: .word 0  # must contain the addition result of var1 and var2

.text
.global _start
_start:

    lw a0, var1			# first argument must be in a0
    lw a1, var2			# second argument must be in a1
    call add    # the same as: jal add -- ra stores the return address
    sw a0, result, t0			# store the addition in variable result
    
    # syscall to write an integer
    # li a0, value	# value to write
    li a7, 1
    ecall
    
    # exit(int status)
    li a0, 0          # exit status
    li a7, 93         # syscall number for exit
    ecall

# a0: first argument
# a1: second argument
# a0: return value
add:  
    add a0, a0, a1
    ret             # the same as: jr ra  -- ra has to hve the return address

