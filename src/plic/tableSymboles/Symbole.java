package plic.tableSymboles;

import plic.arbre.declaration.DeclarationChamps.Statut;
import plic.arbre.declaration.DeclarationChamps.Type;

public class Symbole {
	
	private Statut statut;
	private Type type;
	private int deplacement ;
    
    public Symbole(Statut statut, Type type, int deplacement) {
    	this.statut = statut;
    	this.type = type;
        this.deplacement = deplacement;
    }
 
    public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getDeplacement() {
        return deplacement;
    }
 
    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }
    
    public String toString(){
    	return statut+" "+type+" "+deplacement;
    }
}
