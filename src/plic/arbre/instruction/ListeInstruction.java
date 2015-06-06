package plic.arbre.instruction;

import java.util.ArrayList;
import java.util.Iterator;

public class ListeInstruction implements Iterable<String>{
	
	ArrayList<String> listeInstruction;
	
	public ListeInstruction(){
		this.listeInstruction = new ArrayList<String>();
	}
	
	public void ajouterInstruction(String instruction){
		this.listeInstruction.add(instruction);
	}

	@Override
	public Iterator<String> iterator() {
		return listeInstruction.iterator();
	}
}
