package plic.exception.lexicale;

public class ExceptionLexicale extends Exception {
	
	public ExceptionLexicale(int ligne, String symbole){
		super("ERREUR LEXICAL : ligne "+(ligne+1)+" : symbole \""+symbole+"\" non reconnu");
	}
}
