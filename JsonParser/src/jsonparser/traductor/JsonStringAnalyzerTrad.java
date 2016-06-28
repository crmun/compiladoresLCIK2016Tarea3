/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.traductor;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import jsonparser.analyzer.PanicModeActionEnum;
import jsonparser.analyzer.SimbolosEnum;
import jsonparser.analyzer.TablaParsing;
import jsonparser.enums.LexemasEnum;
import jsonparser.tokenizer.EstadoReconocimiento;

/**
 *
 * @author CMunizaga
 */
public class JsonStringAnalyzerTrad {

    public JsonStringAnalyzerTrad(ArrayList<EstadoReconocimiento> tokens) {
        this.tokens = tokens;
    }        
    
    ArrayList<EstadoReconocimiento> tokens;    
    boolean produccionRepetitivaQuemada;
    public boolean realizarAnalisisSintactico(){    
        boolean exito = false;
        Stack pila = new Stack();
        Stack pilaTraduccion = new Stack();
        String traduccion = new String();
        TablaParsing tablaParsing = new TablaParsing();
        TablaParsingTrad tablaParsingTrad = new TablaParsingTrad();   
        
        //Insertamos el simbolo inicial de la gramatica
        pila.push(SimbolosEnum.JSON_PRIMA.getLexema());
        pilaTraduccion.push(SimbolosEnum.JSON_PRIMA.getLexema());
        //traduccion = SimbolosEnum.JSON_PRIMA.getLexema();
        
        for (int indexToken = 0; indexToken < tokens.size(); indexToken++) {
            this.produccionRepetitivaQuemada = false;
            EstadoReconocimiento tokenActual = tokens.get(indexToken);
            
            boolean continuarConElProximoToken = false;
            while(!continuarConElProximoToken){
                System.out.println("tokenActual="+tokenActual.getLexemaReconocido().getLexema());
                //System.out.println("cimaPila="+pila.peek());
                pila.size();
                String strPila="|";
                for (int i = 0; i < pila.size(); i++) {
                    strPila = strPila + (String)pila.get(i) + " ";
                    
                }
                strPila=strPila+"|";
                String strPilaTrad="|";
                for (int i = 0; i < pilaTraduccion.size(); i++) {
                    strPilaTrad = strPilaTrad + (String)pilaTraduccion.get(i) + " ";
                    
                }
                strPilaTrad=strPilaTrad+"|";
                System.out.println("pila="+strPila);
                System.out.println("pilaTrad="+strPilaTrad);
                System.out.println("_________________________");
                if(match(tokenActual.getLexemaReconocido(), pila.peek())){
                    System.out.println("MATCH");
                    pila.pop();
                    continuarConElProximoToken = true;
                    break;
                }else{
                    if(tablaParsing.getTablaParsing().containsKey(pila.peek())){
                        Hashtable filaTablaParsing = (Hashtable)tablaParsing.getTablaParsing().get(pila.peek());
                        Hashtable filaTablaParsingTrad = (Hashtable)tablaParsingTrad.getTablaParsing().get(pila.peek());
                        if(filaTablaParsing.containsKey(tokenActual.getLexemaReconocido().getLexema())){
                            
                            List celdaTablaParsing,celdaTablaParsingTrad = null;

                            celdaTablaParsing = (List) filaTablaParsing.get(tokenActual.getLexemaReconocido().getLexema());
                            celdaTablaParsingTrad = (List) filaTablaParsingTrad.get(tokenActual.getLexemaReconocido().getLexema());
                            for (int i = celdaTablaParsing.size()-1; i >=0; i--) {                                                                                                                                                       
                            //for (int i = 0; i <celdaTablaParsing.size(); i++) {                                                                                                                                                       
                                String simbolo = (String)celdaTablaParsing.get(i);
                                if(i==(celdaTablaParsing.size()-1)){
                                //if(i==(0)){
                                    if(simbolo.equals(PanicModeActionEnum.POP.getAccion())){
                                        System.out.println("POP");   
                                        int indiceTraduccionBlanco = pilaTraduccion.indexOf((String)pila.peek());
                                        pilaTraduccion.set(indiceTraduccionBlanco, "");
                                        pila.pop();                     
                                        
                                        //continuarConElProximoToken = true;
                                        //break;
                                    }else if(simbolo.equals(PanicModeActionEnum.SCAN.getAccion())){
                                        System.out.println("SCAN | TokenNro:"+indexToken);
                                        continuarConElProximoToken = true;
                                        break;
                                    }else{                                        
                                        String traduccionDelToken = new String();
                                        int indiceTraducir = pilaTraduccion.indexOf((String)pila.peek());
                                        for (int j = celdaTablaParsingTrad.size()-1; j >=0; j--) {
                                        //for (int j = 0; j < celdaTablaParsingTrad.size(); j++) {
                                            String simboloTraducido = (String)celdaTablaParsingTrad.get(j);
                                            if(simboloTraducido.equals(SimbolosEnum.LITERAL_CADENA.getLexema()) || simboloTraducido.equals(SimbolosEnum.LITERAL_NUM.getLexema())){
                                                simboloTraducido=tokenActual.getCadenaActual();
                                            }
                                            if(((String)pila.peek()).equals(SimbolosEnum.ATTRIBUTE_NAME.getLexema())){
                                                simboloTraducido = simboloTraducido.replaceAll("\"", "");
                                                while (pilaTraduccion.indexOf((String)pila.peek()) != -1){
                                                  int indiceTrad = pilaTraduccion.indexOf((String)pila.peek());
                                                  pilaTraduccion.set(indiceTrad, simboloTraducido);
                                              } 
                                            }
                                            //System.out.println("simboloTraducido"+simboloTraducido);
                                            //pilaTraduccion.push(simboloTraducido);
                                            if(j == celdaTablaParsingTrad.size()-1){
                                            //if(j == 0){
                                                pilaTraduccion.set(indiceTraducir, "");
                                            }
                                            pilaTraduccion.add(indiceTraducir+1, simboloTraducido);
                                            
                                            
                                            traduccionDelToken = simboloTraducido + traduccionDelToken;
                                        }
                                        //traduccion = traduccion.replaceAll((String)pila.peek(), traduccionDelToken);
//                                        while (pilaTraduccion.indexOf((String)pila.peek()) != -1){
//                                            int indiceTraducir = pilaTraduccion.indexOf((String)pila.peek());
//                                            pilaTraduccion.set(indiceTraducir, traduccionDelToken);
//                                        }                                                                                
                                        //Reemplazamos la cima de la pila por su produccion
                                        pila.pop();
                                        pila.push(simbolo);
                                    }
                                }else{
                                    pila.push(simbolo);
                                }                                                                        
                            }// termina de recorrer los simbolos de la produccion o accion                                                                                                                                                            
                            
                        }else{
                            //ERROR NO EXISTE EL SIMBOLO TERMINAL EN LA TABLA DE PARSING
                            System.out.println("ERROR NO EXISTE EL SIMBOLO TERMINAL EN LA TABLA DE PARSING");
                            continuarConElProximoToken = true;
                            break;
                        }
                    }else{
                        //ERROR NO EXISTE EL SIMBOLO NO TERMINAL EN LA TABLA DE PARSING
                        System.out.println("ERROR NO EXISTE EL SIMBOLO NO TERMINAL EN LA TABLA DE PARSING");
                        continuarConElProximoToken = true;
                        break;
                    }
                }
                
            }
        }
        if(pila.isEmpty()){
            exito = true;
        }
        System.out.println("Tamaño de la pila:"+pila.size());
        
        String strTrad=new String();
        for (int i = 0; i < pilaTraduccion.size(); i++) {
            Object get = pilaTraduccion.get(i);
            strTrad= strTrad+get;
        }
        System.out.println("traduccion="+strTrad);
        return exito;
    }
    public boolean match(LexemasEnum tokenActual, Object cimaDePila){
        if(tokenActual.getLexema().equals((String)cimaDePila)){
            return true;
        }else{
            return false;
        }
    }

    
}
