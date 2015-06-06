package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;
import plic.exception.semantique.PasDeDeclaration;
import plic.exception.semantique.TypeIncompatible;

public class Division extends ExpressionBinaire{

	public Division(Expression expGauche, Expression expDroite) throws TypeIncompatible {
		super(expGauche, expDroite);
	}

	@Override
	public String generer() throws PasDeDeclaration {
		return this.gauche.generer()+"\n" + this.droite.generer()+ "\n" +
		           "	# divise "+this.toString()+"\n"+
		      	   "	add $sp,$sp,4\n" +	
		      	   "	lw $v0,($sp)\n" +
		      	   "	add $sp,$sp,4\n" +
		      	   "	lw $t8,($sp)\n" +
		      	   "	div $t8,$v0\n" +
				   "	mflo $v0\n" +
		           "	sw $v0,($sp)\n" +
		           "	add $sp,$sp,-4\n";	
	}

	@Override
	public int valeur() {
		return this.gauche.valeur() * this.droite.valeur();
	}

	@Override
	public String toString() {
		return this.gauche +" / "+this.droite;
	}

}
