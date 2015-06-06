package plic.arbre.declaration;

import plic.exception.semantique.PasDeDeclaration;

public abstract class Declaration {
	public abstract String generer() throws PasDeDeclaration;
}
