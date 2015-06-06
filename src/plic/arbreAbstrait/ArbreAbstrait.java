package plic.arbreAbstrait;

import java.util.ArrayList;

import plic.arbre.classe.Classe;
import plic.exception.semantique.PasDeDeclaration;
import plic.tableSymboles.Tds;

public class ArbreAbstrait {
	
	ArrayList<Classe> arbre;
	
	public ArbreAbstrait (Classe c){
		arbre = new ArrayList<Classe>();
		arbre.add(c);
	}
	
	public String generer() throws PasDeDeclaration{		
		StringBuilder strbr = new StringBuilder();
		strbr.append("# zone de reservation de memoire\n\n");
		strbr.append("	# initialise s7 avec sp \n"); 
		strbr.append("	la $s7,($sp) \n\n");
		for(int i=0;i<Tds.getInstance().getTds().size();i++){	
			strbr.append("	add $sp ,$sp,-4 \n");
		}
		strbr.append("\n# zone programme\n");;
		for(Classe c : arbre) strbr.append(c.generer() +"\n");
		return strbr.toString();
	}

	public String toString(){
		StringBuilder res = new StringBuilder();
		for(Classe c : arbre) res.append(c.toString() + "\n")  ;
		return res.toString();
	}

}
