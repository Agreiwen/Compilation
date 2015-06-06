package plic.arbre.declaration;

import java.util.ArrayList;
import java.util.Iterator;

import plic.exception.semantique.PasDeDeclaration;

public class ListeDeclaration implements Iterable<Declaration>{
	
	ArrayList<Declaration> listeDeclaration;
	
	public ListeDeclaration(){
		this.listeDeclaration = new ArrayList<Declaration>();
	}
	
	public void ajouterDeclaration(Declaration declaration){
		this.listeDeclaration.add(declaration);
	}

	public String generer(){
		StringBuilder strbr = new StringBuilder();
		for(Declaration decl : listeDeclaration)
			if(decl instanceof DeclarationConstantes){
				try {
					strbr.append(decl.generer()+"\n");
				} catch (PasDeDeclaration e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return strbr.toString();
	}

	@Override
	public Iterator<Declaration> iterator() {
		return listeDeclaration.iterator();
	}
}
