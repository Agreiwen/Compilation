package plic.arbre.instruction.ecrire;

import plic.arbre.declaration.DeclarationConstantes;
import plic.arbre.expression.Expression;
import plic.arbre.expression.Expression.TypeExpression;
import plic.exception.semantique.PasDeDeclaration;

public class EcrireExpression extends DeclarationConstantes{

	private Expression expression ;
	
	public EcrireExpression(Expression e){
		this.expression = e;
	}
	
	public String generer() throws PasDeDeclaration {
		StringBuilder ecrire = new StringBuilder();
		if(expression.type == TypeExpression.BOOLEAN)
			ecrire.append("	# Ecrire "+(expression.valeur()==1?"vrai":"faux")+"\n");
		else
			ecrire.append("	# Ecrire "+expression.valeur()+"\n");	
		ecrire.append(	expression.generer()+"\n");
		ecrire.append("	add $sp,$sp,4 \n");
		ecrire.append("	li $v0, 1 \n");
		ecrire.append("	lw $a0,($sp) \n");
		ecrire.append("	syscall\n\n");
				
		return ecrire.toString() ;
	}
}

	
	

