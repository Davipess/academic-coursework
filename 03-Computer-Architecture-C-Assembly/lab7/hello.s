.data
  msg: .asciz "Hello world!\n"

.text
.global _start

_start:
    # calculate string length dynamically
    la a1, msg        # message address
    mv a3, a1

count_len:
    lb t1, 0(a3)
    beqz t1, end_count
    addi a3, a3, 1
    j count_len

end_count:
    sub a2, a3, a1    # count = end - start

    # syscall write(int fd, const void *buf, size_t count)
    li a0, 1          # stdout
    li a7, 64         # syscall write
    ecall

    # syscall exit(int status)
    li a0, 0          # exit status
    li a7, 93         # syscall exit
    ecall
