package plic;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import plic.analyse.AnalyseurLexical;
import plic.analyse.AnalyseurSyntaxique;
import plic.arbreAbstrait.ArbreAbstrait;


public class Plic {
	
	public static int ligne = 0;
	private String fichierSource;
	private String fichierDestination;
	private static ArrayList<String> alToken;
	
	public static String derniereGrammaire = "";
	
	public Plic(String fs,String fr ){
		fichierSource = fs;
		fichierDestination = fr;
		alToken = new ArrayList<String>();
	}
	
	public static void setLigne(int ligne){
		Plic.ligne = ligne;
	}
	
	public static String getAllUsageWithTokens(){
		ArrayList<String> dejaFait = new ArrayList<String>();
		StringBuilder rep = new StringBuilder();
		rep.append("\n");
		if(alToken.isEmpty()){
			ArrayList<String> usages = getUsageForToken("classe");
			for (String usage : usages){
				if(!dejaFait.contains(usage)){
					rep.append(usage+"\n");
					dejaFait.add(usage);
				}
			}
		}else{
			for (String s : alToken) {
				ArrayList<String> usages = getUsageForToken(s);
				for (String usage : usages){
					if(!dejaFait.contains(usage)){
						rep.append(usage+"\n");
						dejaFait.add(usage);
					}
				}
			}
		}
		return rep.toString();
	}
	
	public static final String USAGE_CLASS = "classe {identificateur} {declaration}* fin";
	public static final String USAGE_ECRIRE = "ecrire {expression}|{chaine} ;";
	public static final String USAGE_ECRIRE_CHAINE = "ecrire {constante chaine} ;";
	public static final String USAGE_LIRE = "lire {identificateur} ;";
	public static final String USAGE_EXP_ENTIERE = "{expression entiere}";
	public static final String USAGE_EXP_BOOLEEN = "{expression booleenne}";
	public static final String USAGE_EXP_PARENTHESE = "({expression})";
	public static final String USAGE_DECL_CHAMP = "{statut} {type} {identificateur} [,{identificateur}]* ;";
	public static final String USAGE_AFFECT = "{identificateur} = {expression entiere} ;";
	public static final String USAGE_PLUS = "{expression} + {expression}";
	public static final String USAGE_MOINS = "{expression} - {expression}";
	public static final String USAGE_OPPOSER = "- {expression}";
	public static final String USAGE_FOIS = "{expression} * {expression}";
	public static final String USAGE_DIVISION = "{expression entiere} / {expression entiere}";
	public static final String USAGE_INFERIEUR = "{expression entiere} < {expression entiere}";
	public static final String USAGE_SUPERIEUR = "{expression entiere} > {expression entiere}";
	public static final String USAGE_EGALE = "{expression} == {expression}";
	public static final String USAGE_DIFFERENT = "{expression} != {expression}";
	
	public static ArrayList<String> getUsageForToken(String token){
		ArrayList<String> rep = new ArrayList<String>();
		if(token.equals("classe")){
			rep.add(USAGE_CLASS);
		}else if(token.equals("ecrire")){
			rep.add(USAGE_ECRIRE);
		}else if(token.equals("lire")){
			rep.add(USAGE_LIRE);
		}else if(token.equals("fin")){
			rep.add(USAGE_CLASS);
		}else if(token.equals("{entier}")){
			rep.add(USAGE_EXP_ENTIERE);
		}else if(token.equals("{statut}")){
			rep.add(USAGE_DECL_CHAMP);
		}else if(token.equals("{type}")){
			rep.add(USAGE_DECL_CHAMP);
		}else if(token.equals("{bool}")){
			rep.add(USAGE_EXP_BOOLEEN);
		}else if(token.equals("{chaine}")){
			rep.add(USAGE_ECRIRE_CHAINE);
		}else if(token.equals("{idf}")){
			rep.add(USAGE_CLASS);
			rep.add(USAGE_DECL_CHAMP);
			rep.add(USAGE_LIRE);
			rep.add(USAGE_AFFECT);
		}else if(token.equals("+")){
			rep.add(USAGE_PLUS);
		}else if(token.equals("-")){
			rep.add(USAGE_MOINS);
			rep.add(USAGE_OPPOSER);
		}else if(token.equals("*")){
			rep.add(USAGE_FOIS);
		}else if(token.equals("/")){
			rep.add(USAGE_DIVISION);
		}else if(token.equals("<")){
			rep.add(USAGE_INFERIEUR);
		}else if(token.equals(">")){
			rep.add(USAGE_SUPERIEUR);
		}else if(token.equals("==")){
			rep.add(USAGE_EGALE);
		}else if(token.equals("!=")){
			rep.add(USAGE_DIFFERENT);
		}else if(token.equals("=")){
			rep.add(USAGE_AFFECT);
		}else if(token.equals("(")){
			rep.add(USAGE_EXP_PARENTHESE);
		}else if(token.equals(")")){
			rep.add(USAGE_EXP_PARENTHESE);
		}else if(token.equals(";")){
			rep.add(USAGE_AFFECT);
			rep.add(USAGE_DECL_CHAMP);
			rep.add(USAGE_ECRIRE);
			rep.add(USAGE_LIRE);
		}else if(token.equals(",")){
			rep.add(USAGE_DECL_CHAMP);
		}
		return rep;
	}
	
	public static void addToken(String t){
		if(t.equals("{idf}")){
			if(alToken.isEmpty()){
				alToken.add(t);
			}else{
				if(alToken.get(0).equals("classe")){
					alToken.clear();
				}else{
					alToken.add(t);
				}
			}
		}else{
			alToken.add(t);
		}
	}

	public static void clearTokens(){
		String dernier = alToken.get(alToken.size()-1);
		alToken.clear();
		alToken.add(dernier);
	}
	
	public static String getTokensString(){
		StringBuilder sb = new StringBuilder();
		for (String s : alToken) {
			sb.append(s+" ");
		}
		if(sb.toString().equals("")){
			sb.append("{classe}");
		}
		return sb.toString();
	}
	
	@SuppressWarnings({ "deprecation" })
	public void gereCodeAssembleur(){
		PrintWriter pw;
		AnalyseurSyntaxique as;
		ArbreAbstrait arbre = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(fichierDestination+".asm")));	
			as = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(fichierSource)));
			arbre = (ArbreAbstrait) as.parse().value;
			String res = arbre.generer();
			pw.write(res+"\n");
			pw.close();
			System.out.println("COMPILATION OK\n");
		} catch(FileNotFoundException ex){// retourne erreur lorsque le fichier destination est incorrect
			System.out.println(ex.toString());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
