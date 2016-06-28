/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.tokenizer;

import jsonparser.enums.EstadosLexemaEnum;
import jsonparser.enums.LexemasEnum;
import jsonparser.enums.RespuestaAnalisisLexicoEnum;

/**
 *
 * @author CMunizaga
 */
public class EstadoReconocimiento {
    
    private RespuestaAnalisisLexicoEnum codigoRespuesta;
    private LexemasEnum lexemaReconocido;
    private EstadosLexemaEnum estadoActual;
    private String cadenaActual;

    public EstadoReconocimiento(RespuestaAnalisisLexicoEnum codigoRespuesta, LexemasEnum lexemaReconocido, EstadosLexemaEnum estadoActual, String cadenaActual) {
        this.codigoRespuesta = codigoRespuesta;
        this.lexemaReconocido = lexemaReconocido;
        this.estadoActual = estadoActual;
        this.cadenaActual = cadenaActual;
    }

    EstadoReconocimiento(RespuestaAnalisisLexicoEnum codigoRespuesta, LexemasEnum lexemaReconocido) {
        this.codigoRespuesta = codigoRespuesta;
        this.lexemaReconocido = lexemaReconocido;
        this.estadoActual = null;
        this.cadenaActual = null;
    }

    EstadoReconocimiento() {
        this.codigoRespuesta = RespuestaAnalisisLexicoEnum.CONTINUAR_RECONOCIMIENTO;
        this.lexemaReconocido = null;
        this.estadoActual = EstadosLexemaEnum.ESTADO_INICIAL;
        
    }

    public RespuestaAnalisisLexicoEnum getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(RespuestaAnalisisLexicoEnum codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public LexemasEnum getLexemaReconocido() {
        return lexemaReconocido;
    }

    public void setLexemaReconocido(LexemasEnum lexemaReconocido) {
        this.lexemaReconocido = lexemaReconocido;
    }

    public EstadosLexemaEnum getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadosLexemaEnum estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getCadenaActual() {
        return cadenaActual;
    }

    public void setCadenaActual(String cadenaActual) {
        this.cadenaActual = cadenaActual;
    }
    
}
