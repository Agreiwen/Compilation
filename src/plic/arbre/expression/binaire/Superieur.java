package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;
import plic.arbre.expression.Expression.TypeExpression;
import plic.exception.semantique.PasDeDeclaration;
import plic.exception.semantique.TypeIncompatible;

public class Superieur extends ExpressionBinaire{
	
	private int cpt = 0;
	public Superieur(Expression expGauche, Expression expDroite) throws TypeIncompatible{
		super(expGauche,expDroite);
		type = TypeExpression.BOOLEAN;
	}

	@Override
	public String generer() throws PasDeDeclaration{
		incCptEtiquette();
		cpt = Expression.cptEtiquette;
		return this.gauche.generer()+"\n" + this.droite.generer()+ "\n" +
	           "	# Compare "+this.toString()+"\n"+
	      	   "	add $sp,$sp,4\n" +
	      	   "	lw $v0,($sp)\n" +
	      	   "	add $sp,$sp,4\n" +
	      	   "	lw $t8,($sp)\n" +
	      	   "	sub $v0,$v0,$t8\n" +
			   "	bgez $v0 sinon"+cpt+"\n" +
	           "	alors"+cpt+":\n" +
	           "	li $v0, 1\n" + 
	           "	sw $v0,0($sp)\n" +
	           "	add $sp,$sp,-4\n" +
	           "	j finsi"+cpt+"\n" + 
	           "	sinon"+cpt+":\n" + 
	           "	li $v0, 0\n" + 
	       	   "	sw $v0,0($sp)\n" + 
	           "	add $sp,$sp,-4\n" +
	       	   "	finsi"+cpt+":\n";
	}

	@Override
	public int valeur() {
		// TODO Auto-generated method stub
		if(this.gauche.valeur() > this.droite.valeur())
			return 1;
		return 0;
	}
	
	public String toString(){
		return this.gauche.valeur() +" > "+this.droite.valeur();
	}

}
