/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.analyzer;

import jsonparser.enums.*;

/**
 *
 * @author CMunizaga
 */
public enum PanicModeActionEnum{
        
    SCAN(     "SCAN"), 
    POP(     "POP")   
    ;

    private PanicModeActionEnum(String accion){
        this.accion = accion;       
    }

    private final String accion;

    public String getAccion() {
        return accion;
    }
               
}
