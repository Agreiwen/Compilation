package plic;

import java.io.IOException;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		if(args.length == 2){
		  	Plic p = new Plic(args[0],args[1]);
		  	p.gereCodeAssembleur();
		}else{
			System.out.println("Nombres arguments incorrect : ./Plic fichierSource fichierDestination");
	  }
	}
}
