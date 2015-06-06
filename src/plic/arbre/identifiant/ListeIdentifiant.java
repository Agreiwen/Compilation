package plic.arbre.identifiant;

import java.util.ArrayList;
import java.util.Iterator;

public class ListeIdentifiant implements Iterable<String>{
	
	ArrayList<String> listeIdentifiant;
	
	public ListeIdentifiant(){
		this.listeIdentifiant = new ArrayList<String>();
	}
	
	public void ajouterIdentifiant(String identifiant){
		this.listeIdentifiant.add(identifiant);
	}

	@Override
	public Iterator<String> iterator() {
		return listeIdentifiant.iterator();
	}
}
