/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.tokenizer;

import java.util.ArrayList;
import java.util.StringTokenizer;
import jsonparser.enums.EstadosLexemaEnum;
import jsonparser.enums.LexemasEnum;
import jsonparser.enums.RespuestaAnalisisLexicoEnum;

/**
 *
 * @author CMunizaga
 */
public class JsonStringTokenizer extends StringTokenizer{
    
    String cadenaDeEntrada;
    
    public JsonStringTokenizer(String str) {
        super(str);
        cadenaDeEntrada = str;
    }
    
    public ArrayList<LexemasEnum> realizarAnalisisLexico(){ 
        ArrayList<LexemasEnum> tokens = new ArrayList<LexemasEnum>();
        String cadenaSaliente = "";
        String cadenaEntrante = this.cadenaDeEntrada;
        String [] lineas = cadenaEntrante.split("\n");
        for(int indexLineas = 0; indexLineas < lineas.length; indexLineas++) {
            String lineaSaliente = "";
            String unaLinea = lineas[indexLineas];
            
            char[] charsLinea = unaLinea.toCharArray();
            
            EstadoReconocimiento estadoReconocimiento = null;
            for (int indexCadena = 0; indexCadena < charsLinea.length; indexCadena++) {
                char unCaracterDeLaLinea = charsLinea[indexCadena];
                if(estadoReconocimiento == null){
                    if (!(unCaracterDeLaLinea+"").isEmpty() && !(unCaracterDeLaLinea+"").equals(" ")){
                    estadoReconocimiento = new EstadoReconocimiento();                   
                    reconocer(unCaracterDeLaLinea+"", estadoReconocimiento);
                    }else{
                        lineaSaliente = lineaSaliente + unCaracterDeLaLinea;
                    }
                }else if(estadoReconocimiento != null){
                    reconocer(unCaracterDeLaLinea+"", estadoReconocimiento);                                     
                }
               
                if(estadoReconocimiento != null){               
                    if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.ESTADO_FINAL)){
                        if(estadoReconocimiento.getCodigoRespuesta().equals(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO)){
                            tokens.add(estadoReconocimiento.getLexemaReconocido());
                            lineaSaliente = lineaSaliente + estadoReconocimiento.getLexemaReconocido().getComponenteLexico() + " ";                        
                            if(estadoReconocimiento.getLexemaReconocido().equals(LexemasEnum.LITERAL_NUM)){
                                indexCadena = indexCadena - 1;
                            }
                            estadoReconocimiento = null;
                        }else if(estadoReconocimiento.getCodigoRespuesta().equals(RespuestaAnalisisLexicoEnum.ERROR_RECONOCIMIENTO)){
                            if(estadoReconocimiento.getLexemaReconocido().equals(LexemasEnum.LITERAL_NUM)){
                                indexCadena = indexCadena - 1;
                            }
                            lineaSaliente = lineaSaliente + "¡ERROR!" + " ";
                            estadoReconocimiento = null;
                        }
                    }else if(estadoReconocimiento.getCodigoRespuesta().equals(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO)){
                        estadoReconocimiento.setCadenaActual(estadoReconocimiento.getCadenaActual() + unCaracterDeLaLinea);
                    }
                }
            }//fin del for que recorre una linea caracter por caracter
            if(estadoReconocimiento != null){
                if(estadoReconocimiento.getCodigoRespuesta().equals(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO)){
                    if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.RECONOCIENDO_NUMBER)){
                       if((estadoReconocimiento.getCadenaActual()).matches(LexemasEnum.LITERAL_NUM.getExpresionRegular())){
                            //El caracter actual no corresponde al lexema
                            //Se deberá retroceder una posición en el reconocimiento de la linea
                            estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                            estadoReconocimiento.setLexemaReconocido(LexemasEnum.LITERAL_NUM);
                            estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);   
                            tokens.add(estadoReconocimiento.getLexemaReconocido());
                        }else{
                            estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.ERROR_RECONOCIMIENTO);
                            estadoReconocimiento.setLexemaReconocido(LexemasEnum.LITERAL_NUM);
                            estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
                        }
                    }else{
                        lineaSaliente = lineaSaliente + "¡ERROR!" + " ";
                    }
                    
                }else{
                    System.out.println(lineaSaliente);  
                    tokens.add(estadoReconocimiento.getLexemaReconocido());
                }
            }            
        }//fin del for que recorre las lineas del fichero o cadena de entrada.
        return tokens;
    }
    
    public ArrayList<EstadoReconocimiento> realizarAnalisisLexicoConTokens(){ 
        ArrayList<EstadoReconocimiento> tokens = new ArrayList<EstadoReconocimiento>();
        String cadenaSaliente = "";
        String cadenaEntrante = this.cadenaDeEntrada;
        String [] lineas = cadenaEntrante.split("\n");
        for(int indexLineas = 0; indexLineas < lineas.length; indexLineas++) {
            String lineaSaliente = "";
            String unaLinea = lineas[indexLineas];
            
            char[] charsLinea = unaLinea.toCharArray();
            
            EstadoReconocimiento estadoReconocimiento = null;
            for (int indexCadena = 0; indexCadena < charsLinea.length; indexCadena++) {
                char unCaracterDeLaLinea = charsLinea[indexCadena];
                if(estadoReconocimiento == null){
                    if (!(unCaracterDeLaLinea+"").isEmpty() && !(unCaracterDeLaLinea+"").equals(" ")){
                    estadoReconocimiento = new EstadoReconocimiento();                   
                    reconocer(unCaracterDeLaLinea+"", estadoReconocimiento);
                    }else{
                        lineaSaliente = lineaSaliente + unCaracterDeLaLinea;
                    }
                }else if(estadoReconocimiento != null){
                    reconocer(unCaracterDeLaLinea+"", estadoReconocimiento);                                     
                }
               
                if(estadoReconocimiento != null){               
                    if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.ESTADO_FINAL)){
                        if(estadoReconocimiento.getCodigoRespuesta().equals(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO)){
                            if(estadoReconocimiento.getLexemaReconocido().equals(LexemasEnum.LITERAL_NUM)){
                                estadoReconocimiento.setCadenaActual(estadoReconocimiento.getCadenaActual().substring(0, estadoReconocimiento.getCadenaActual().length()-1));
                            }
                            tokens.add(estadoReconocimiento);
                            lineaSaliente = lineaSaliente + estadoReconocimiento.getLexemaReconocido().getComponenteLexico() + " ";                        
                            if(estadoReconocimiento.getLexemaReconocido().equals(LexemasEnum.LITERAL_NUM)){
                                indexCadena = indexCadena - 1;
                            }
                            estadoReconocimiento = null;
                        }else if(estadoReconocimiento.getCodigoRespuesta().equals(RespuestaAnalisisLexicoEnum.ERROR_RECONOCIMIENTO)){
                            if(estadoReconocimiento.getLexemaReconocido().equals(LexemasEnum.LITERAL_NUM)){
                                indexCadena = indexCadena - 1;
                            }
                            lineaSaliente = lineaSaliente + "¡ERROR!" + " ";
                            estadoReconocimiento = null;
                        }
                    }else if(estadoReconocimiento.getCodigoRespuesta().equals(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO)){
                        estadoReconocimiento.setCadenaActual(estadoReconocimiento.getCadenaActual() + unCaracterDeLaLinea);
                    }
                }
            }//fin del for que recorre una linea caracter por caracter
            if(estadoReconocimiento != null){
                if(estadoReconocimiento.getCodigoRespuesta().equals(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO)){
                    if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.RECONOCIENDO_NUMBER)){
                       if((estadoReconocimiento.getCadenaActual()).matches(LexemasEnum.LITERAL_NUM.getExpresionRegular())){
                            //El caracter actual no corresponde al lexema
                            //Se deberá retroceder una posición en el reconocimiento de la linea
                            estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                            estadoReconocimiento.setLexemaReconocido(LexemasEnum.LITERAL_NUM);
                            estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);   
                            tokens.add(estadoReconocimiento);
                        }else{
                            estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.ERROR_RECONOCIMIENTO);
                            estadoReconocimiento.setLexemaReconocido(LexemasEnum.LITERAL_NUM);
                            estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
                        }
                    }else{
                        lineaSaliente = lineaSaliente + "¡ERROR!" + " ";
                    }
                    
                }else{
                    System.out.println(lineaSaliente);  
                    tokens.add(estadoReconocimiento);
                }
            }            
        }//fin del for que recorre las lineas del fichero o cadena de entrada.
        return tokens;
    }
    
    public void reconocer(String caracter, EstadoReconocimiento estadoReconocimiento){        
        
        if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.ESTADO_INICIAL)){   
            if(caracter.equals(LexemasEnum.L_CORCHETE.getExpresionRegular())){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.L_CORCHETE);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
                estadoReconocimiento.setCadenaActual(caracter);                
            }else if(caracter.equals(LexemasEnum.R_CORCHETE.getExpresionRegular())){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.R_CORCHETE);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
                estadoReconocimiento.setCadenaActual(caracter);                
            }else if(caracter.equals(LexemasEnum.L_LLAVE.getExpresionRegular())){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.L_LLAVE);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
                estadoReconocimiento.setCadenaActual(caracter);                
            }else if(caracter.equals(LexemasEnum.R_LLAVE.getExpresionRegular())){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.R_LLAVE);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
                estadoReconocimiento.setCadenaActual(caracter);                
            }else if(caracter.equals(LexemasEnum.COMA.getExpresionRegular())){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.COMA);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
                estadoReconocimiento.setCadenaActual(caracter);                
            }else if(caracter.equals(LexemasEnum.DOS_PUNTOS.getExpresionRegular())){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.DOS_PUNTOS);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
                estadoReconocimiento.setCadenaActual(caracter);                
            }else if(caracter.matches("t|T")){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.RECONOCIENDO_TRUE);
                estadoReconocimiento.setCadenaActual("");                
            }else if(caracter.matches("f|F")){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.RECONOCIENDO_FALSE);
                estadoReconocimiento.setCadenaActual("");                
            }else if(caracter.matches("n|N")){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.RECONOCIENDO_NULL);
                estadoReconocimiento.setCadenaActual("");                
            }else if(caracter.equals("\"")){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.RECONOCIENDO_STRING);
                estadoReconocimiento.setCadenaActual("");                
            }else if(caracter.matches("[0-9]")){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.RECONOCIENDO_NUMBER);
                estadoReconocimiento.setCadenaActual("");                
            }else{
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.ERROR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
                estadoReconocimiento.setCadenaActual("");                
            }                    
        }else if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.RECONOCIENDO_TRUE)){
            if((estadoReconocimiento.getCadenaActual()+ caracter).matches("t|tr|tru|T|Tr|Tru")){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.RECONOCIENDO_TRUE);                
            }else if((estadoReconocimiento.getCadenaActual()+ caracter).matches(LexemasEnum.PR_TRUE.getExpresionRegular())){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.PR_TRUE);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);   
                estadoReconocimiento.setCadenaActual(estadoReconocimiento.getCadenaActual()+caracter);
            }else{
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.ERROR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
            }
        }else if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.RECONOCIENDO_FALSE)){   
            if((estadoReconocimiento.getCadenaActual()+ caracter).matches("f|fa|fal|fals|F|Fa|Fal|Fals")){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.RECONOCIENDO_FALSE);                
            }else if((estadoReconocimiento.getCadenaActual()+ caracter).matches(LexemasEnum.PR_FALSE.getExpresionRegular())){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.PR_FALSE);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);   
                estadoReconocimiento.setCadenaActual(estadoReconocimiento.getCadenaActual()+caracter);
            }else{
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.ERROR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
            }                
        }else if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.RECONOCIENDO_NULL)){   
            if((estadoReconocimiento.getCadenaActual()+ caracter).matches("n|nu|nul|N|Nu|Nul")){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.RECONOCIENDO_NULL);                
            }else if((estadoReconocimiento.getCadenaActual()+ caracter).matches(LexemasEnum.PR_NULL.getExpresionRegular())){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.PR_NULL);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);   
                estadoReconocimiento.setCadenaActual(estadoReconocimiento.getCadenaActual()+caracter);
            }else{
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.ERROR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
            }
        }else if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.RECONOCIENDO_NUMBER)){
            if(caracter.matches("[0-9]") || caracter.matches("e|E") 
                    || caracter.equals("+") || caracter.equals("-") || caracter.equals(".")){
                //Se continua si los símbolos pertenecen al alfabeto
                //La validacion final se realizará al encontrar un OTHER (el primer símbolo del siguiente lexema)
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(null);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.RECONOCIENDO_NUMBER);                
            }else if((estadoReconocimiento.getCadenaActual()).matches(LexemasEnum.LITERAL_NUM.getExpresionRegular())){
                //El caracter actual no corresponde al lexema
                //Se deberá retroceder una posición en el reconocimiento de la linea
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.LITERAL_NUM);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);   
                estadoReconocimiento.setCadenaActual(estadoReconocimiento.getCadenaActual()+caracter);
            }else{
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.ERROR_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.LITERAL_NUM);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);
            }
        }else if(estadoReconocimiento.getEstadoActual().equals(EstadosLexemaEnum.RECONOCIENDO_STRING)){   
            if(caracter.equals("\"")){
                estadoReconocimiento.setCodigoRespuesta(RespuestaAnalisisLexicoEnum.EXITO_RECONOCIMIENTO);
                estadoReconocimiento.setLexemaReconocido(LexemasEnum.LITERAL_CADENA);
                estadoReconocimiento.setEstadoActual(EstadosLexemaEnum.ESTADO_FINAL);   
                estadoReconocimiento.setCadenaActual(estadoReconocimiento.getCadenaActual()+caracter);
            }
        }          
    }
}
