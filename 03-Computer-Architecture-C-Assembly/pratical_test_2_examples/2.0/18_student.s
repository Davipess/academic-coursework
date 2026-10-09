# Exercise 18: Find first number >= a1 in array of 5 words at a0, return index or -1.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Result of operation)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: first >=5 in [1,3,5,7,9] → index 2
    la a0, cfe09_t1
    li a1, 5
    jal ra, func_18
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
cfe09_t1: .word 1, 3, 5, 7, 9

# ============================================================
# TODO: Implement the function below
# ============================================================


.text
.global func_18
func_18:
    # --- 1. PREPARAÇÃO ---
    mv t0, a0          # t0 é o "Carteiro" (guarda a morada que vai avançar de 4 em 4)
    li t1, 0           # t1 é o nosso "Índice" (vai contar 0, 1, 2, 3, 4)
    li t2, 5           # t2 é o nosso limite (porque o enunciado diz "array of 5 words")

loop_start:
    beq t1, t2, not_found

    lw t3, 0(t0)       # O t3 recebe o número que estava na casa atual

    bge t3, a1, found

    addi t0, t0, 4     # Avança a morada para a próxima casa (4 bytes)
    addi t1, t1, 1     # Avança o nosso índice (ex: do 0 para o 1)
    
    j loop_start       # Volta para cima para avaliar a próxima casa!

# ==========================================
# CAMINHOS DE SAÍDA DA FUNÇÃO
# ==========================================

found:
    mv a0, t1          # Copiamos o índice para a gaveta de saída (a0)
    ret                # Voltamos para a main

not_found:
    li a0, -1          # O exercício manda devolver -1 se falhar.
    ret                # Voltamos para a main
    bigger:
