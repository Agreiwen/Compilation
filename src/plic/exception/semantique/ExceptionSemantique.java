package plic.exception.semantique;

import plic.Plic;

public abstract class ExceptionSemantique extends Exception {
	
	public ExceptionSemantique(String message){
		super("ERREUR SEMANTIQUE a la ligne "+(Plic.ligne+1)+" :: "+message);
	}

}
