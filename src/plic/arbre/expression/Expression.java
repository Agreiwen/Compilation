package plic.arbre.expression;

import plic.exception.semantique.PasDeDeclaration;

public abstract class Expression {
	public static int cptEtiquette = 0;
	public abstract String toString();
	public abstract String generer() throws PasDeDeclaration;
	public abstract int valeur();
	public void incCptEtiquette(){
		Expression.cptEtiquette++;
	}
	public enum TypeExpression {BOOLEAN, ARITHMETIQUE};
	public TypeExpression type;
}
