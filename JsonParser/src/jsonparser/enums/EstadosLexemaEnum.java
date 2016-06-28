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
public enum EstadosLexemaEnum {
    ESTADO_INICIAL,
    RECONOCIENDO_STRING,
    RECONOCIENDO_NUMBER,
    RECONOCIENDO_TRUE,
    RECONOCIENDO_FALSE,
    RECONOCIENDO_NULL,
    ESTADO_FINAL;
}
