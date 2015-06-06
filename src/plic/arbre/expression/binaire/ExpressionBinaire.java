package plic.arbre.expression.binaire;

import plic.arbre.expression.Expression;
import plic.exception.semantique.PasDeDeclaration;
import plic.exception.semantique.TypeIncompatible;

public abstract class ExpressionBinaire extends Expression {

	protected Expression droite;
	protected Expression gauche;
	public abstract String generer() throws PasDeDeclaration;
	public abstract int valeur();
	public ExpressionBinaire(Expression expGauche, Expression expDroite) throws TypeIncompatible{
		gauche = expGauche;
		droite = expDroite;	
		if(gauche.type == droite.type)
			type = gauche.type;
		else
			throw new TypeIncompatible();
	}

}
