package plic.arbre.expression.unaire;

import plic.arbre.declaration.DeclarationChamps;
import plic.arbre.expression.Expression;
import plic.arbre.expression.Expression.TypeExpression;
import plic.exception.semantique.PasDeDeclaration;
import plic.tableSymboles.Tds;

public class Unaire extends Expression {

	private int valeur;
	private String idf, bool;
	private boolean entier = true;
	
	public Unaire(int val,boolean isBoolean){
		this.valeur = val;
		type = TypeExpression.ARITHMETIQUE;
		entier = true;
	}
	
	public Unaire(String val,boolean isBoolean){
		this.bool = val;
		type = TypeExpression.BOOLEAN;
		entier = true;
		this.valeur = (bool.equals("vrai"))?1:0;
	}

	public Unaire(String i) throws PasDeDeclaration {
		this.idf = i;
		type = (Tds.getInstance().identifier(idf).getType()==DeclarationChamps.Type.BOOLEAN)?TypeExpression.BOOLEAN:TypeExpression.ARITHMETIQUE;
		entier = false;
	}

	public String generer() throws PasDeDeclaration {
		StringBuilder unaire = new StringBuilder();
		if(entier){
			unaire.append("	# Range "+ this.valeur +" dans $v0 et l'empile\n"+
						  "	li $v0, " + this.valeur + "\n" +
						  "	sw $v0,($sp) \n" +
						  "	add $sp ,$sp,-4 \n");
		}else{
			unaire.append("	# Range "+ this.idf +" dans $v0 et l'empile\n"+
					  	  "	lw $v0,"+ Tds.getInstance().identifier(idf).getDeplacement() +"($s7)\n" +
					  	  "	sw $v0,($sp) \n" +
					  	  "	add $sp ,$sp,-4 \n");
		}
		return unaire.toString() ;
	}
	

	public int valeur() {
		return valeur;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return valeur+"";
	}

}
