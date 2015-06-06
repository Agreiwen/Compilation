package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;
import plic.arbre.expression.Expression.TypeExpression;
import plic.exception.semantique.PasDeDeclaration;
import plic.exception.semantique.TypeIncompatible;

public class Somme extends ExpressionBinaire{
	
	public Somme(Expression expGauche, Expression expDroite) throws TypeIncompatible{
		super(expGauche,expDroite);
	}

	@Override
	public String generer() throws PasDeDeclaration{
		String addition = "";
		if(type == TypeExpression.BOOLEAN){
			addition = "	# additionne "+this.toString()+"\n"+
					   "	li $v0,"+this.valeur()+"\n" +
			           "	sw $v0,($sp)\n" +
			           "	add $sp,$sp,-4\n";	
		}else{
			addition = this.gauche.generer()+"\n" + this.droite.generer()+ "\n" +
		           "	# additionne "+this.toString()+"\n"+
		      	   "	add $sp,$sp,4\n" +	
		      	   "	lw $v0,($sp)\n" +
		      	   "	add $sp,$sp,4\n" +
		      	   "	lw $t8,($sp)\n" +
		      	   "	add $v0,$v0,$t8\n" +
		           "	sw $v0,($sp)\n" +
		           "	add $sp,$sp,-4\n";	
		}
		return addition;
	}

	@Override
	public int valeur() {
		// TODO Auto-generated method stub
		if(type == TypeExpression.BOOLEAN)
			return tableVeriter();
		else
			return this.gauche.valeur() + this.droite.valeur();
	}
	
	public String toString(){
		return this.gauche.valeur() +" + "+this.droite.valeur();
	}
	
	private int tableVeriter(){
		return (this.gauche.valeur() == 1 || this.gauche.valeur() == 1)?1:0;
	}

}
