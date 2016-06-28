/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.util.ArrayList;
import jsonparser.analyzer.JsonStringAnalyzer;
import jsonparser.enums.LexemasEnum;
import jsonparser.io.FileManager;
import jsonparser.tokenizer.EstadoReconocimiento;
import jsonparser.tokenizer.JsonStringTokenizer;
import jsonparser.traductor.JsonStringAnalyzerTrad;

/**
 *
 * @author CMunizaga
 */
public class JsonParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String cadenaDeEntrada = null;
        //Si recibimos el argumento intentamos leer el archivo y obtener las lineas
        if(args!= null && args.length > 0){                       
            cadenaDeEntrada = FileManager.obtenerLineas(args[0]);                        
        }else{//Si no recibimos argumento trabajaremos con un ejemplo en duro
            
            cadenaDeEntrada = "{  \n" +
                                 "   \"personas\":[  \n" +
                                 "      {  \n" +
                                 "         \"ci\":1234567,\n" +
                                 "         \"nombre\":\"Julio Pérez\",\n" +
                                 "         \"casado\":false,\n" +
                                 "         \"hijos\":[  \n" +
                                 "\n" +
                                 "         ]\n" +
                                 "      },\n" +
                                 "      {  \n" +
                                 "         \"ci\":7654321,\n" +
                                 "         \"nombre\":\"Juan Gómez\",\n" +
                                 "         \"casado\":true,\n" +
                                 "         \"hijos\":[  \n" +
                                 "            {  \n" +
                                 "               \"nombre\":\"Jorge\",\n" +
                                 "               \"edad\":18\n" +
                                 "            },\n" +
                                 "            {  \n" +
                                 "               \"nombre\":\"Valeria\",\n" +
                                 "               \"edad\":16\n" +
                                 "            }\n" +
                                 "         ]\n" +
                                 "      }\n" +
                                 "   ]\n" +
                                 "}";
        }
        JsonStringTokenizer tk = new JsonStringTokenizer(cadenaDeEntrada);
        
        //ArrayList<LexemasEnum> tokens = tk.realizarAnalisisLexico();
        
        //JsonStringAnalyzer analizador = new JsonStringAnalyzer(tokens);
        ArrayList<EstadoReconocimiento> tokens = tk.realizarAnalisisLexicoConTokens();
        
        //JsonStringAnalyzer analizador = new JsonStringAnalyzer(tokens);
        //analizador.realizarAnalisisSintactico();
        JsonStringAnalyzerTrad analizador = new JsonStringAnalyzerTrad(tokens);
        try{
            analizador.realizarAnalisisSintactico();
        }catch(Exception e){
            System.out.println("La cadena de entrada es incorrecta. Por favor corrija e intente nuevamente.");
        }
    }
    
}
