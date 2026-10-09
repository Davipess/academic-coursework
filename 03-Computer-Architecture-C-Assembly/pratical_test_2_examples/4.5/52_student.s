# Exercise 52: Implement strcpy: copy null-terminated string from a0 to a1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Address a1 (string copied))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Strcpy 'Hello' to dst; load first byte of dst=72 ('H')
    la a0, meh09_t1s
    la a1, meh09_t1d
    jal ra, func_52
    la a0, meh09_t1d
    lb a0, 0(a0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
meh09_t1s: .asciz "Hello"
meh09_t1d: .space 16

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_52
func_52:
    mv t0, a0          # t0 = Carteiro da ORIGEM (Onde está o texto)
    mv t1, a1          # t1 = Carteiro do DESTINO (O espaço em branco)

loop:
    lb t2, 0(t0)       # Lê a letra atual
    sb t2, 0(t1)       # Escreve imediatamente a letra no destino
    
    beq t2, zero, end  
    
    addi t0, t0, 1     # Avança origem 1 byte
    addi t1, t1, 1     # Avança destino 1 byte
    
    j loop             # Repetir para a próxima letra

end:
    mv a0, a1          
    ret