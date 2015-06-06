package plic.analyse ;

import java_cup.runtime.Symbol;
import plic.Plic;
import plic.exception.lexicale.ExceptionLexicale;

%%

%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup
   
%{
  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

entier = [0-9]+
bool = vrai | faux
idf = [a-zA-Z][a-zA-Z0-9]*
statut = publique | privee
type = entier|booleen
chaine = \"([^[\"]]|([\"]{2}))*\"
commentaireSlashSlash = [/][/].*

%%

{commentaireSlashSlash}	{}
"classe" 	{ Plic.addToken("classe"); Plic.setLigne(yyline); return symbol(CodesLexicaux.classe); }
"ecrire" 	{ Plic.addToken("ecrire"); Plic.setLigne(yyline); return symbol(CodesLexicaux.ecrire); }
"lire" 		{ Plic.addToken("lire"); Plic.setLigne(yyline); return symbol(CodesLexicaux.lire); }
"fin" 		{ Plic.addToken("fin"); Plic.setLigne(yyline); return symbol(CodesLexicaux.fin); }
{entier} 	{ Plic.addToken("{entier}"); Plic.setLigne(yyline); return symbol(CodesLexicaux.cste_ent, yytext()); }
{statut} 	{ Plic.addToken("{statut}"); Plic.setLigne(yyline); return symbol(CodesLexicaux.statut, yytext()); }
{type} 		{ Plic.addToken("{type}"); Plic.setLigne(yyline); return symbol(CodesLexicaux.type, yytext()); }
{bool} 		{ Plic.addToken("{bool}"); Plic.setLigne(yyline); return symbol(CodesLexicaux.bool, yytext()); }
{chaine} 	{ Plic.addToken("{chaine}"); Plic.setLigne(yyline); return symbol(CodesLexicaux.cste_chaine, yytext()); }
{idf} 		{ Plic.addToken("{idf}"); Plic.setLigne(yyline); return symbol(CodesLexicaux.idf, yytext()); }
"+" 	 	{ Plic.addToken("+"); Plic.setLigne(yyline); return symbol(CodesLexicaux.plus); }
"-" 	 	{ Plic.addToken("-"); Plic.setLigne(yyline); return symbol(CodesLexicaux.moins); }
"*" 	 	{ Plic.addToken("*"); Plic.setLigne(yyline); return symbol(CodesLexicaux.fois); }
"/"			{ Plic.addToken("/"); Plic.setLigne(yyline); return symbol(CodesLexicaux.diviser); }
"<" 	 	{ Plic.addToken("<"); Plic.setLigne(yyline); return symbol(CodesLexicaux.inf); }
">" 	 	{ Plic.addToken(">"); Plic.setLigne(yyline); return symbol(CodesLexicaux.sup); }
"==" 	 	{ Plic.addToken("=="); Plic.setLigne(yyline); return symbol(CodesLexicaux.test_egale); }
"!=" 		{ Plic.addToken("!="); Plic.setLigne(yyline); return symbol(CodesLexicaux.test_different); }
"=" 	 	{ Plic.addToken("="); Plic.setLigne(yyline); return symbol(CodesLexicaux.egale); }
"(" 		{ Plic.addToken("("); Plic.setLigne(yyline); return symbol(CodesLexicaux.parenthese_ouvert); }
")" 		{ Plic.addToken(")"); Plic.setLigne(yyline); return symbol(CodesLexicaux.parenthese_fermer); }
";" 		{ Plic.addToken(";"); Plic.setLigne(yyline); return symbol(CodesLexicaux.pointvirgule); }
"," 		{ Plic.addToken(","); Plic.setLigne(yyline); return symbol(CodesLexicaux.virgule); }

\s			{Plic.setLigne(yyline); }
.	{ throw new ExceptionLexicale(yyline, yytext());}
