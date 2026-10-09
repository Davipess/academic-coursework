# Exercise 37: Reverse the order of 6 words in array at address a0 (in-place).
#
# Input:  a0 (and possibly a1, a2 - see spec)
# Output: a0 (Address a0 (array reversed in-place))
#
# Do not modify this spec comment. Implement the function below.


.text
main:
    # Test: Reverse [1,2,3,4,5,6] → [6,5,4,3,2,1]; first=6
    la a0, mem04_t1
    jal ra, func_37
    la a0, mem04_t1
    lw a0, 0(a0)
    li a7, 1
    ecall
    li a7, 10
    ecall

.data
mem04_t1: .word 1, 2, 3, 4, 5, 6

# ============================================================
# TODO: Implement the function below
# ============================================================

.text
.global func_37
func_37:
    # --- PREPARAÇÃO DOS CARTEIROS ---
    mv t0, a0          # Carteiro ESQUERDO começa na base (casa 0)
    
    # Carteiro DIREITO começa na última casa (casa 20)
    # Como não podemos somar 20 diretamente no 'mv', fazemos em dois passos:
    mv t1, a0          
    addi t1, t1, 20    

loop_start:
    bge t0, t1, loop_end

    lw t2, 0(t0)       
    lw t3, 0(t1)      

    sw t3, 0(t0)       
    sw t2, 0(t1)       

    addi t0, t0, 4     
    addi t1, t1, -4    

    j loop_start       
    
loop_end:
    ret