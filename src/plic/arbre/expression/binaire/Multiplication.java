package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;
import plic.arbre.expression.Expression.TypeExpression;
import plic.exception.semantique.PasDeDeclaration;
import plic.exception.semantique.TypeIncompatible;

public class Multiplication extends ExpressionBinaire{
	
	public Multiplication(Expression expGauche, Expression expDroite) throws TypeIncompatible{
		super(expGauche,expDroite);
	}

	@Override
	public String generer() throws PasDeDeclaration{
		String rep = "";
		if(type == TypeExpression.ARITHMETIQUE){
			rep = this.gauche.generer()+"\n" + this.droite.generer()+ "\n" +
		           "	# multiplie "+this.toString()+"\n"+
		      	   "	add $sp,$sp,4\n" +	
		      	   "	lw $v0,($sp)\n" +
		      	   "	add $sp,$sp,4\n" +
		      	   "	lw $t8,($sp)\n" +
		      	   "	mult $v0,$t8\n" +
				   "	mflo $v0\n" +
		           "	sw $v0,($sp)\n" +
		           "	add $sp,$sp,-4\n";		
		}else{
			rep = "	# additionne "+this.toString()+"\n"+
					   "	li $v0,"+this.valeur()+"\n" +
			           "	sw $v0,($sp)\n" +
			           "	add $sp,$sp,-4\n";
		}
		return rep;
	}

	@Override
	public int valeur() {
		// TODO Auto-generated method stub
		if(type == TypeExpression.BOOLEAN)
			return tableVeriter();
		else
			return this.gauche.valeur() * this.droite.valeur();
	}
	
	public String toString(){
		return this.gauche.valeur() +" * "+this.droite.valeur();
	}
	
	private int tableVeriter(){
		return (this.gauche.valeur() == 1 && this.droite.valeur() == 1)?1:0;
	}

}
