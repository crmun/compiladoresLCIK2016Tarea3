/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CMunizaga
 */
public class FileManager {
    public static String obtenerLineas(String rutaArchivo){
        String lineas="";
        try(FileInputStream inputStream = new FileInputStream(rutaArchivo)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));                      
          
            String line = reader.readLine();
            lineas = lineas + line + "\n";
            while(line != null){
                System.out.println(line);
                line = reader.readLine();
                lineas = lineas + line+ "\n";
            }           

        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return lineas;
    }
}
