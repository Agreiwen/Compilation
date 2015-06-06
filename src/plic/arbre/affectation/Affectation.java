package plic.arbre.affectation;

import plic.arbre.declaration.DeclarationConstantes;
import plic.arbre.expression.Expression;
import plic.exception.semantique.PasDeDeclaration;
import plic.exception.semantique.TypeIncompatible;
import plic.tableSymboles.Tds;

public class Affectation extends DeclarationConstantes{

	private String idf;
	private Expression e;
	
	public Affectation(String idf, Expression e) throws TypeIncompatible {
		super();
		this.idf = idf;
		this.e = e;
		if(e.type.equals(Expression.TypeExpression.BOOLEAN)){
			throw new TypeIncompatible();
		}
	}
	
	public String generer() throws PasDeDeclaration{
		 StringBuilder affectation = new StringBuilder();
		 affectation.append("\n" + e.generer() + "\n");
		 affectation.append("	# Affectation "+idf+" = "+e.valeur()+"\n"+
				 			"	add $sp,$sp,4 \n" +
		 					"	lw $v0,($sp) \n" +
		 					"	sw $v0,"+ Tds.getInstance().identifier(idf).getDeplacement() +"($s7)\n");
		 return affectation.toString() ;
	}

}
