package plic.arbre.declaration;

import plic.arbre.expression.Expression;
import plic.exception.semantique.PasDeDeclaration;
	
public abstract class DeclarationConstantes extends Declaration{
	public static int cptEtiquette = 0;
	public void incCptEtiquette(){
		Expression.cptEtiquette++;
	}
	public abstract String generer() throws PasDeDeclaration;
}
