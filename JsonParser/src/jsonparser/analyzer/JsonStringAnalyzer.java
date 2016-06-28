/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.analyzer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import jsonparser.enums.LexemasEnum;

/**
 *
 * @author CMunizaga
 */
public class JsonStringAnalyzer {

    public JsonStringAnalyzer(ArrayList<LexemasEnum> tokens) {
        this.tokens = tokens;
    }        
    
    ArrayList<LexemasEnum> tokens;    
    boolean produccionRepetitivaQuemada;
    public boolean realizarAnalisisSintactico(){    
        boolean exito = false;
        Stack pila = new Stack();
        TablaParsing tablaParsing = new TablaParsing();   
        
        //Insertamos el simbolo inicial de la gramatica
        pila.push(SimbolosEnum.JSON_PRIMA.getLexema());
        
        for (int indexToken = 0; indexToken < tokens.size(); indexToken++) {
            this.produccionRepetitivaQuemada = false;
            LexemasEnum tokenActual = tokens.get(indexToken);
            
            boolean continuarConElProximoToken = false;
            while(!continuarConElProximoToken){
                System.out.println("tokenActual="+tokenActual);
                //System.out.println("cimaPila="+pila.peek());
                pila.size();
                String strPila="|";
                for (int i = 0; i < pila.size(); i++) {
                    strPila = strPila + (String)pila.get(i) + " ";
                    
                }
                strPila=strPila+"|";
                System.out.println("pila="+strPila);
                System.out.println("_________________________");
                if(match(tokenActual, pila.peek())){
                    System.out.println("MATCH");
                    pila.pop();
                    continuarConElProximoToken = true;
                    break;
                }else{
                    if(tablaParsing.getTablaParsing().containsKey(pila.peek())){
                        Hashtable filaTablaParsing = (Hashtable)tablaParsing.getTablaParsing().get(pila.peek());
                        if(filaTablaParsing.containsKey(tokenActual.getLexema())){
                            
                            List celdaTablaParsing = null;
//                            if(tieneMasDeUnaProduccion(tokenActual,pila.peek())){                                
//                                    //Si puedo observar el token siguiente lo utilizo para la decision
//                                    celdaTablaParsing = elegirProduccion(indexToken,pila.peek(),filaTablaParsing);                                     
//                            }else{
//                                celdaTablaParsing = (List) filaTablaParsing.get(tokenActual.getLexema());    
//                            }
                            celdaTablaParsing = (List) filaTablaParsing.get(tokenActual.getLexema());
                            for (int i = celdaTablaParsing.size()-1; i >=0; i--) {                                                                                                                                                       
                            //for (int i = 0; i <celdaTablaParsing.size(); i++) {                                                                                                                                                       
                                String simbolo = (String)celdaTablaParsing.get(i);
                                if(i==(celdaTablaParsing.size()-1)){
                                //if(i==(0)){
                                    if(simbolo.equals(PanicModeActionEnum.POP.getAccion())){
                                        System.out.println("POP");
                                        pila.pop();
                                        //continuarConElProximoToken = true;
                                        //break;
                                    }else if(simbolo.equals(PanicModeActionEnum.SCAN.getAccion())){
                                        System.out.println("SCAN | TokenNro:"+indexToken);
                                        continuarConElProximoToken = true;
                                        break;
                                    }else{
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
        return exito;
    }
    public boolean match(LexemasEnum tokenActual, Object cimaDePila){
        if(tokenActual.getLexema().equals((String)cimaDePila)){
            return true;
        }else{
            return false;
        }
    }

    private boolean tieneMasDeUnaProduccion(LexemasEnum tokenActual, Object cimaDePila) {
        String terminal = tokenActual.getLexema();
        String noTerminal = (String)cimaDePila;
        
        if(noTerminal.equals(SimbolosEnum.ARRAY_PRIMA.getLexema()) && 
                (terminal.equals(LexemasEnum.L_LLAVE.getLexema()) || terminal.equals(LexemasEnum.L_CORCHETE.getLexema()))){
            return true;
        }
        
        if(noTerminal.equals(SimbolosEnum.OBJECT_PRIMA.getLexema()) && terminal.equals(LexemasEnum.LITERAL_CADENA.getLexema())){
            return true;
        }
        
        if(noTerminal.equals(SimbolosEnum.ELEMENT_LIST.getLexema()) && 
                (terminal.equals(LexemasEnum.L_LLAVE.getLexema()) || terminal.equals(LexemasEnum.L_CORCHETE.getLexema()))){
            return true;
        }
        
        if(noTerminal.equals(SimbolosEnum.ATTRIBUTE_LIST.getLexema()) && terminal.equals(LexemasEnum.LITERAL_CADENA.getLexema())){
            return true;
        }
        
        return false;
    }   

    private List elegirProduccion(int indexTokenActual, Object cimaDePila, Hashtable filaTablaParsing) {
        LexemasEnum tokenActual=this.tokens.get(indexTokenActual);
        LexemasEnum tokenSiguiente=this.tokens.get(indexTokenActual+1);
        List producciones = (List)filaTablaParsing.get(tokenActual.getLexema());               
        
        String terminal = tokenActual.getLexema();
        String noTerminal = (String)cimaDePila;
        
        if(noTerminal.equals(SimbolosEnum.ARRAY_PRIMA.getLexema()) && 
                (terminal.equals(LexemasEnum.L_LLAVE.getLexema()) || terminal.equals(LexemasEnum.L_CORCHETE.getLexema()))){
            if(tokenSiguiente.equals(LexemasEnum.R_CORCHETE.getLexema())){
                return (List)producciones.get(1);
            }else{
                return (List)producciones.get(0);
            }
            
        }
        
        if(noTerminal.equals(SimbolosEnum.OBJECT_PRIMA.getLexema()) && terminal.equals(LexemasEnum.LITERAL_CADENA.getLexema())){
            if(tokenSiguiente.equals(LexemasEnum.R_LLAVE.getLexema())){
                return (List)producciones.get(1);
            }else{
                return (List)producciones.get(0);
            }
        }
        
        if(noTerminal.equals(SimbolosEnum.ELEMENT_LIST.getLexema()) && 
                (terminal.equals(LexemasEnum.L_LLAVE.getLexema()) || terminal.equals(LexemasEnum.L_CORCHETE.getLexema()))){
            //recorremos los tokens siguientes
            try{
                //Ahora que sabemos que se trata un element debemos determinar el simbolo que debemos contar para saber hasta donde termina con el fin de saber si despues viene la coma
                int posicionFinElement = encontrarPosicionFinElement(indexTokenActual);
                if(posicionFinElement!= -1){
                    //Perfecto! Encontramos llave o corechete de cierre
                    //Verificamos si despues viene la bendita coma para saber qué produccion aplicaremos
                    if(tokens.get(posicionFinElement+1).equals(LexemasEnum.COMA) && !this.produccionRepetitivaQuemada){
                        produccionRepetitivaQuemada = true;
                        return (List)producciones.get(0);
                    }else{
                        return (List)producciones.get(1);
                    }
                }

            }catch(Exception e){
                return (List)producciones.get(1);
            }
                
            return (List)producciones.get(1);            
        }
        
        if(noTerminal.equals(SimbolosEnum.ATTRIBUTE_LIST.getLexema()) && terminal.equals(LexemasEnum.LITERAL_CADENA.getLexema())){             
            //recorremos los tokens siguientes
            try{
                
                //Puede ser tanto un attribute como un attribute-list. Para que sea un attribute-list debe haber una coma despues del attribute-value
                //indexTokenActual+1 corresponde al simbolo DOS PUNTOS que viene despues del attribute-name
                //indexTokenActual+2 puede ser string, number, true, false, null o en el peor de los casos un element
                //Si indexTokenActual+2 es un element tenemos dos posibles valores: L_CORCHETE en el caso de array y L_LLAVE en el caso de object
                if(tokens.get(indexTokenActual+2).equals(LexemasEnum.L_LLAVE) || tokens.get(indexTokenActual+2).equals(LexemasEnum.L_CORCHETE)){
                    //Ahora que sabemos que se trata un element debemos determinar el simbolo que debemos contar para saber hasta donde termina con el fin de saber si despues viene la coma
                    int posicionFinElement = encontrarPosicionFinElement(indexTokenActual+2);
                    if(posicionFinElement!= -1){
                        //Perfecto! Encontramos llave o corechete de cierre
                        //Verificamos si despues viene la bendita coma para saber qué produccion aplicaremos
                        if(tokens.get(posicionFinElement+1).equals(LexemasEnum.COMA) && !this.produccionRepetitivaQuemada){
                            this.produccionRepetitivaQuemada = true;
                            return (List)producciones.get(0);
                        }else{
                            return (List)producciones.get(1);
                        }
                    }
                }else{
                    //Si no es un element podemos asumir con tranquilidad que indexTokenActual+2 es un terminal
                    //Ahora evaluaremos si le sigue una coma en cuyo caso asumiremos que la produccion tomara <element-list,element> porque sino será un simple <element>
                    if(tokens.get(indexTokenActual+3).equals(LexemasEnum.COMA) && !this.produccionRepetitivaQuemada){
                        this.produccionRepetitivaQuemada = true;
                        return (List)producciones.get(0);
                    }else{
                        return (List)producciones.get(1);
                    }
                }

            }catch(Exception e){
                return (List)producciones.get(1);
            }
            
            return (List)producciones.get(1);                      
        }
        
        return null;
    }

    int encontrarPosicionFinElement(int indexTokenInicioElement){
        int posicionFinElement = -1;
        if(tokens.get(indexTokenInicioElement).equals(LexemasEnum.L_LLAVE)){
            int contadorL_LLAVE =0;
            int posicionLlaveDeCierre = -1;
            for(int i=indexTokenInicioElement;i<this.tokens.size();i++){
                if(tokens.get(i).equals(LexemasEnum.L_LLAVE)){
                    contadorL_LLAVE++;
                }else if(tokens.get(i).equals(LexemasEnum.R_LLAVE)){                               
                    contadorL_LLAVE--;
                }  
                if(contadorL_LLAVE==0){
                    posicionLlaveDeCierre=i; 
                    break;
                }
            }
            posicionFinElement=posicionLlaveDeCierre;            
        }else if(tokens.get(indexTokenInicioElement).equals(LexemasEnum.L_CORCHETE)){ //fin del caso que verificaba LLAVE, ahora vamos por el CORCHETE                    
            //Aqui copio y pego el codigo de LLAVE y lo adapto para CORCHETE perdon por no poder hacerlo mas limpio, pulcro y elegante por falta de tiempo
            int contadorL_CORCHETE =0;
            int posicionCorcheteDeCierre = -1;
            for(int i=indexTokenInicioElement;i<this.tokens.size();i++){
                if(tokens.get(i).equals(LexemasEnum.L_CORCHETE)){
                    contadorL_CORCHETE++;
                }else if(tokens.get(i).equals(LexemasEnum.R_CORCHETE)){                               
                    contadorL_CORCHETE--;
                }  
                if(contadorL_CORCHETE==0){
                    posicionCorcheteDeCierre=i; 
                    break;
                }
            }
            posicionFinElement=posicionCorcheteDeCierre;
        }
        return posicionFinElement;
    }

}
