package plic.arbre.classe;

import plic.arbre.declaration.ListeDeclaration;
import plic.exception.semantique.PasDeDeclaration;

public class Classe {
	
	protected ListeDeclaration listeDeclaration;
	protected String idf;
	
	public Classe(ListeDeclaration ldecl, String idf){
		this.listeDeclaration = ldecl;
		this.idf = idf;
	}

	public String generer() throws PasDeDeclaration {
		StringBuilder res = new StringBuilder();
		if(this.listeDeclaration != null){
			//res.append(Tds.getInstance().generer());
			res.append(listeDeclaration.generer());
		}else{
			res.append("");
		}
		return res.toString();
	}

}
