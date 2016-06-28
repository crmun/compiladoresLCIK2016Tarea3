/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.enums;

/**
 *
 * @author CMunizaga
 */
public enum LexemasEnum{
    
    L_CORCHETE(     "[",        "L_CORCHETE",       "["), 
    R_CORCHETE(     "]",        "R_CORCHETE",       "]"), 
    L_LLAVE(        "{",        "L_LLAVE",          "{"), 
    R_LLAVE(        "}",        "R_LLAVE",          "}"), 
    COMA(           ",",        "COMA",             ","), 
    DOS_PUNTOS(     ":",        "DOS_PUNTOS",       ":"), 
    LITERAL_CADENA( "string",   "STRING",   ".*"), 
    LITERAL_NUM(    "number",   "NUMBER",      "[0-9]+(\\.[0-9]+)?(([eE])([-+])?[0-9]+)?"), 
    PR_TRUE(        "true",     "PR_TRUE",          "true|TRUE"), 
    PR_FALSE(       "false",    "PR_FALSE",         "false|FALSE"), 
    PR_NULL(        "null",     "PR_NULL",          "null|NULL"), 
    EOF(            "eof",      "EOF",              "" /*Match con el fin del archivo*/);

    private LexemasEnum(String lexema, String componenteLexico, String expresionRegular){
        this.lexema = lexema;
        this.componenteLexico = componenteLexico;
        this.expresionRegular = expresionRegular;
    }

    private final String lexema;
    private final String componenteLexico;
    private final String expresionRegular;

    public String getLexema() {
        return lexema;
    }

    public String getComponenteLexico() {
        return componenteLexico;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }
        
}
