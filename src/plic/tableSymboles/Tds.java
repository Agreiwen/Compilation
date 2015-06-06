package plic.tableSymboles;

import java.util.HashMap;

import plic.arbre.declaration.DeclarationChamps.Statut;
import plic.arbre.declaration.DeclarationChamps.Type;
import plic.exception.semantique.DoubleDeclaration;
import plic.exception.semantique.PasDeDeclaration;

public class Tds {
	
	HashMap<String, Symbole> tds;
	private int deplacement = 0;

	public Tds(){
		tds =  new HashMap<String, Symbole>();
	}
	
	private static Tds instance = new Tds();
	
	public static Tds getInstance(){
		return instance ;
	}
	
	public HashMap<String, Symbole> getTds() {
		return tds;
	}

	public void setTds(HashMap<String, Symbole> tds) {
		this.tds = tds;
	}

	public void ajouterChamp(Statut statut, Type type, String idf) throws DoubleDeclaration{
		// si la variable est deja declarée lance une exception 
		if (tds.containsKey(idf)){
			throw new DoubleDeclaration(idf +" est déja déclaré");
		}else{ // sinon ajoute la variable dans la hashMap
			tds.put(idf, new Symbole(statut, type, deplacement));
			this.deplacement += 4;
		}
	}	
	
	public Symbole identifier(String key) throws PasDeDeclaration{
		Symbole s = tds.get(key);
		if(s==null){
			throw new PasDeDeclaration(key +" n'a pas été déclaté !");
		}
		return tds.get(key);
	}

	

}
