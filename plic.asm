# zone de reservation de memoire

	# initialise s7 avec sp 
	la $s7,($sp) 

	add $sp ,$sp,-4 
	add $sp ,$sp,-4 
	add $sp ,$sp,-4 
	add $sp ,$sp,-4 

# zone programme

	# Range 10 dans $v0 et l'empile
	li $v0, 10
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# Affectation a = 10
	add $sp,$sp,4 
	lw $v0,($sp) 
	sw $v0,0($s7)


	# Range 6 dans $v0 et l'empile
	li $v0, 6
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# Affectation b = 6
	add $sp,$sp,4 
	lw $v0,($sp) 
	sw $v0,4($s7)

	# Ecrire 0
	# Range a dans $v0 et l'empile
	lw $v0,0($s7)
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# Range b dans $v0 et l'empile
	lw $v0,4($s7)
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# multiplie 0 * 0
	add $sp,$sp,4
	lw $v0,($sp)
	add $sp,$sp,4
	lw $t8,($sp)
	mult $v0,$t8
	mflo $v0
	sw $v0,($sp)
	add $sp,$sp,-4

	add $sp,$sp,4 
	li $v0, 1 
	lw $a0,($sp) 
	syscall



	# Range a dans $v0 et l'empile
	lw $v0,0($s7)
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# Range b dans $v0 et l'empile
	lw $v0,4($s7)
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# multiplie 0 * 0
	add $sp,$sp,4
	lw $v0,($sp)
	add $sp,$sp,4
	lw $t8,($sp)
	mult $v0,$t8
	mflo $v0
	sw $v0,($sp)
	add $sp,$sp,-4

	# Affectation resultat = 0
	add $sp,$sp,4 
	lw $v0,($sp) 
	sw $v0,8($s7)


.data 
fonc1: .asciiz "Saisissez un entier pour resultat : /
 
.text 
	li $v0, 4 
	la $a0, fonc1 
	syscall
	li  $v0, 5
	syscall
	sw $v0,8($s7)


	# Range 5 dans $v0 et l'empile
	li $v0, 5
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# Range 2 dans $v0 et l'empile
	li $v0, 2
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# multiplie 5 * 2
	add $sp,$sp,4
	lw $v0,($sp)
	add $sp,$sp,4
	lw $t8,($sp)
	mult $v0,$t8
	mflo $v0
	sw $v0,($sp)
	add $sp,$sp,-4

	# Range 4 dans $v0 et l'empile
	li $v0, 4
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# additionne 10 + 4
	add $sp,$sp,4
	lw $v0,($sp)
	add $sp,$sp,4
	lw $t8,($sp)
	add $v0,$v0,$t8
	sw $v0,($sp)
	add $sp,$sp,-4

	# Range 4 dans $v0 et l'empile
	li $v0, 4
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# multiplie 14 * 4
	add $sp,$sp,4
	lw $v0,($sp)
	add $sp,$sp,4
	lw $t8,($sp)
	mult $v0,$t8
	mflo $v0
	sw $v0,($sp)
	add $sp,$sp,-4

	# Range 6 dans $v0 et l'empile
	li $v0, 6
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# soustrait 56 - 6
	add $sp,$sp,4
	lw $v0,($sp)
	add $sp,$sp,4
	lw $t8,($sp)
	sub $v0,$t8,$v0
	sw $v0,($sp)
	add $sp,$sp,-4

	# Range 2 dans $v0 et l'empile
	li $v0, 2
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	# divise 56 - 6 / 2
	add $sp,$sp,4
	lw $v0,($sp)
	add $sp,$sp,4
	lw $t8,($sp)
	div $t8,$v0
	mflo $v0
	sw $v0,($sp)
	add $sp,$sp,-4

	# Affectation expression = 100
	add $sp,$sp,4 
	lw $v0,($sp) 
	sw $v0,12($s7)


.data 
fonc2: .asciiz "Saisissez un entier pour expression : /
 
.text 
	li $v0, 4 
	la $a0, fonc2 
	syscall
	li  $v0, 5
	syscall
	sw $v0,12($s7)

	# Ecrire vrai
	# additionne 1 + 0
	li $v0,1
	sw $v0,($sp)
	add $sp,$sp,-4

	add $sp,$sp,4 
	li $v0, 1 
	lw $a0,($sp) 
	syscall


	# Ecrire 0
	# Range a dans $v0 et l'empile
	lw $v0,0($s7)
	sw $v0,($sp) 
	add $sp ,$sp,-4 

	add $sp,$sp,4 
	li $v0, 1 
	lw $a0,($sp) 
	syscall


	# Ecrirechaine
	.data 
	stri3: .asciiz je veux voir a
	.text 
	li $v0, 4 
	la $a0, stri3
	syscall


	# Ecrirechaine
	.data 
	stri4: .asciiz \n
	.text 
	li $v0, 4 
	la $a0, stri4
	syscall


	# Ecrirechaine
	.data 
	stri5: .asciiz ecriture d'un mot entre guillemets "triangle"
	.text 
	li $v0, 4 
	la $a0, stri5
	syscall




