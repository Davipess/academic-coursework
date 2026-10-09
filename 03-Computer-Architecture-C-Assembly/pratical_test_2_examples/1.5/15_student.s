# Exercise 15: Sum all 4 words in an array at address a0. Array has 4 words.
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Sum of 4 words in array at a0)
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Normal: [1,2,3,4] → 10
    la a0, mee07_t1
    jal ra, func_15
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mee07_t1: .word 1, 2, 3, 4

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_15
func_15:
    # --- PREPARAÇÃO ---
    mv t0, a0          # t0 vai ser o nosso "Carteiro", guardando a morada atual
    li t1, 0           # t1 vai ser a caixa da SOMA TOTAL
    li t2, 0           # t2 vai ser o nosso CONTADOR (de 0 a 4)
    li t3, 4           # t3 guarda o limite de voltas (4)

loop_start:
    beq t2, t3, loop_end
    lw t4, 0(t0)
    add t1, t1, t4
    addi t0, t0, 4
    addi t2, t2, 1
    j loop_start

loop_end:
    mv a0, t1          
    ret                
