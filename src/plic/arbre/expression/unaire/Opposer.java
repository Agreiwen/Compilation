package plic.arbre.expression.unaire;

import plic.arbre.expression.Expression;
import plic.arbre.expression.Expression.TypeExpression;
import plic.exception.semantique.PasDeDeclaration;

public class Opposer extends Expression{
	
	private Expression expression;
	private int val;
	
	public Opposer(Expression e){
		expression = e;
		if(expression.type.equals(Expression.TypeExpression.BOOLEAN))
			val = (expression.valeur()==1)?0:1;
		else
			val = expression.valeur() - (expression.valeur()*2);
	} 
	
	public String generer() throws PasDeDeclaration {
		StringBuilder opposer = new StringBuilder();
		opposer.append("	# Range "+ val +" dans $v0 et l'empile\n"+
				  "	li $v0, " + val + "\n" +
				  "	sw $v0,($sp) \n" +
				  "	add $sp ,$sp,-4 \n");

		return opposer.toString() ;
	}

	public int valeur() {
		return val;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return val+"";
	}
	
}
