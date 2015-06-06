package plic.arbre.instruction.ecrire;

import plic.arbre.declaration.DeclarationConstantes;
import plic.arbre.expression.Expression;

public class EcrireChaine extends DeclarationConstantes{
	
	private String chaine ;
	private int cpt = 0;

	public EcrireChaine(String chaine) {
		super();
		this.chaine = convertirCote(chaine);
	}

	private String convertirCote(String chaine2) {
		chaine2 = chaine2.substring(1, chaine2.length()-1);
		StringBuilder sb = new StringBuilder();
		boolean first = false;
		for(int i = 0; i < chaine2.length(); i++){
			char c = chaine2.charAt(i);
			if(c == '"'){
				first = !first;
				if(!first)
					sb.append('"');
			}else{
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public String generer() {
		incCptEtiquette();
		cpt = Expression.cptEtiquette;
		StringBuilder ecrire = new StringBuilder(); 
		ecrire.append("	# Ecrirechaine\n");
		ecrire.append("	.data \n");
		ecrire.append("	stri"+cpt+": .asciiz " + chaine +"\n");
		ecrire.append("	.text \n");
		ecrire.append("	li $v0, 4 \n");
		ecrire.append("	la $a0, stri"+ cpt +"\n");
		ecrire.append("	syscall\n\n") ;
		 
		return ecrire.toString() ;
	}

}
