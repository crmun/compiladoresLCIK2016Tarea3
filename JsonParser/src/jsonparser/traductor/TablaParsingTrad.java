/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser.traductor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Stack;
import jsonparser.analyzer.PanicModeActionEnum;
import jsonparser.analyzer.SimbolosEnum;
import jsonparser.enums.LexemasEnum;

/**
 *
 * @author CMunizaga
 */
public class TablaParsingTrad {

    Hashtable tablaTraduccion;
    
    public TablaParsingTrad() {
        this.tablaTraduccion = new Hashtable();
        //Stack st = new Stack(); 
        final String SCAN = PanicModeActionEnum.SCAN.getAccion();
        final String POP = PanicModeActionEnum.POP.getAccion();        
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable json_prima = new Hashtable();                
        json_prima.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SimbolosEnum.JSON.getLexema()));
        json_prima.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(SCAN));
        json_prima.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SimbolosEnum.JSON.getLexema()));
        json_prima.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        json_prima.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(SCAN));
        json_prima.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        json_prima.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SCAN));
        json_prima.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        json_prima.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        json_prima.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        json_prima.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        json_prima.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));        
        tablaTraduccion.put(SimbolosEnum.JSON_PRIMA.getLexema(), json_prima);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable json = new Hashtable();   
        json.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SimbolosEnum.ELEMENT.getLexema()));
        json.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(SCAN));
        json.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SimbolosEnum.ELEMENT.getLexema()));
        json.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        json.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(SCAN));
        json.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        json.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SCAN));
        json.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        json.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        json.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        json.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        json.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.JSON.getLexema(), json);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable element = new Hashtable();   
        element.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SimbolosEnum.OBJECT.getLexema()));
        element.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        element.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SimbolosEnum.ARRAY.getLexema()));
        element.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(POP));
        element.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(POP));
        element.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        element.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SCAN));
        element.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        element.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        element.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        element.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        element.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ELEMENT.getLexema(), element);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable array = new Hashtable();   
        array.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SCAN));
        array.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        //array.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SimbolosEnum.L_CORCHETE.getLexema(),SimbolosEnum.ARRAY_PRIMA.getLexema()));
        array.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList("",SimbolosEnum.ARRAY_PRIMA.getLexema()));
        array.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(POP));
        array.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(POP));
        array.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        array.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SCAN));
        array.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        array.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        array.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        array.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        array.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ARRAY.getLexema(), array);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable array_prima = new Hashtable();   
        //array_prima.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SimbolosEnum.ELEMENT_LIST.getLexema(), SimbolosEnum.R_CORCHETE.getLexema()));
        array_prima.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SimbolosEnum.ELEMENT_LIST.getLexema(), ""));
        array_prima.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        //array_prima.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SimbolosEnum.ELEMENT_LIST.getLexema(), SimbolosEnum.R_CORCHETE.getLexema()));
        array_prima.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SimbolosEnum.ELEMENT_LIST.getLexema(), ""));
        //array_prima.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(SimbolosEnum.R_CORCHETE.getLexema()));
        array_prima.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(""));
        array_prima.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(POP));
        array_prima.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        array_prima.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SCAN));
        array_prima.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        array_prima.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        array_prima.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        array_prima.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        array_prima.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ARRAY_PRIMA.getLexema(), array_prima);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable object = new Hashtable();   
        //object.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SimbolosEnum.L_LLAVE.getLexema(), SimbolosEnum.OBJECT_PRIMA.getLexema()));
        object.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList("", SimbolosEnum.OBJECT_PRIMA.getLexema()));
        object.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        object.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        object.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(POP));
        object.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(POP));
        object.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        object.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SCAN));
        object.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        object.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        object.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        object.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        object.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.OBJECT.getLexema(), object);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable object_prima = new Hashtable();   
        object_prima.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SCAN));
        //object_prima.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(SimbolosEnum.R_LLAVE.getLexema()));
        object_prima.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(""));
        object_prima.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        object_prima.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(POP));
        object_prima.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(POP));
        object_prima.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        //object_prima.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SimbolosEnum.ATTRIBUTE_LIST.getLexema(), SimbolosEnum.R_LLAVE.getLexema()));
        object_prima.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SimbolosEnum.ATTRIBUTE_LIST.getLexema(), ""));
        object_prima.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        object_prima.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        object_prima.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        object_prima.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        object_prima.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.OBJECT_PRIMA.getLexema(), object_prima);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable element_list = new Hashtable();   
        //element_list.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SimbolosEnum.ELEMENT.getLexema(), SimbolosEnum.ELEMENT_LIST_PRIMA.getLexema()));
        element_list.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList("<item>",SimbolosEnum.ELEMENT.getLexema(), "</item>", SimbolosEnum.ELEMENT_LIST_PRIMA.getLexema()));
        element_list.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        //element_list.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SimbolosEnum.ELEMENT.getLexema(), SimbolosEnum.ELEMENT_LIST_PRIMA.getLexema()));
        element_list.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList("<item>",SimbolosEnum.ELEMENT.getLexema(), "</item>", SimbolosEnum.ELEMENT_LIST_PRIMA.getLexema()));
        element_list.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(POP));
        element_list.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(SCAN));
        element_list.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        element_list.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SCAN));
        element_list.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        element_list.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        element_list.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        element_list.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        element_list.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ELEMENT_LIST.getLexema(), element_list);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable element_list_prima = new Hashtable();   
        element_list_prima.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(POP));
        element_list_prima.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        element_list_prima.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(POP));
        element_list_prima.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(POP));
        //element_list_prima.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(SimbolosEnum.COMA.getLexema(),SimbolosEnum.ELEMENT.getLexema(),SimbolosEnum.ELEMENT_LIST_PRIMA.getLexema()));
        element_list_prima.put(LexemasEnum.COMA.getLexema(),            Arrays.asList("<item>",SimbolosEnum.ELEMENT.getLexema(),"</item>",SimbolosEnum.ELEMENT_LIST_PRIMA.getLexema()));
        element_list_prima.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(POP));
        element_list_prima.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(POP));
        element_list_prima.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(POP));
        element_list_prima.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(POP));
        element_list_prima.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(POP));
        element_list_prima.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(POP));
        element_list_prima.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ELEMENT_LIST_PRIMA.getLexema(), element_list_prima);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable attribute_list = new Hashtable();   
        attribute_list.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SCAN));
        attribute_list.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        attribute_list.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        attribute_list.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        attribute_list.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(SCAN));
        attribute_list.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        //attribute_list.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SimbolosEnum.ATTRIBUTE.getLexema(), SimbolosEnum.ATTRIBUTE_LIST_PRIMA.getLexema()));
        attribute_list.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SimbolosEnum.ATTRIBUTE.getLexema(), SimbolosEnum.ATTRIBUTE_LIST_PRIMA.getLexema()));
        attribute_list.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        attribute_list.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        attribute_list.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        attribute_list.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        attribute_list.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ATTRIBUTE_LIST.getLexema(), attribute_list);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable attribute_list_prima = new Hashtable();   
        attribute_list_prima.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(POP));
        attribute_list_prima.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        attribute_list_prima.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(POP));
        attribute_list_prima.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(POP));
        //attribute_list_prima.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(SimbolosEnum.COMA.getLexema(),SimbolosEnum.ATTRIBUTE.getLexema(),SimbolosEnum.ATTRIBUTE_LIST_PRIMA.getLexema()));
        attribute_list_prima.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(SimbolosEnum.ATTRIBUTE.getLexema(),SimbolosEnum.ATTRIBUTE_LIST_PRIMA.getLexema()));
        attribute_list_prima.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(POP));
        attribute_list_prima.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(POP));
        attribute_list_prima.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(POP));
        attribute_list_prima.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(POP));
        attribute_list_prima.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(POP));
        attribute_list_prima.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(POP));
        attribute_list_prima.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ATTRIBUTE_LIST_PRIMA.getLexema(), attribute_list_prima);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable attribute = new Hashtable();   
        attribute.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SCAN));
        attribute.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        attribute.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        attribute.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        attribute.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(POP));
        attribute.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        attribute.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList("<",SimbolosEnum.ATTRIBUTE_NAME.getLexema(),">", SimbolosEnum.ATTRIBUTE_VALUE.getLexema(),"<",SimbolosEnum.ATTRIBUTE_NAME.getLexema(),"/>"));
        attribute.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        attribute.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        attribute.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        attribute.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        attribute.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ATTRIBUTE.getLexema(), attribute);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable attribute_name = new Hashtable();   
        attribute_name.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SCAN));
        attribute_name.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(SCAN));
        attribute_name.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        attribute_name.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(SCAN));
        attribute_name.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(SCAN));
        attribute_name.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(POP));
        attribute_name.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SimbolosEnum.LITERAL_CADENA.getLexema()));
        attribute_name.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SCAN));
        attribute_name.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SCAN));
        attribute_name.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SCAN));
        attribute_name.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SCAN));
        attribute_name.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ATTRIBUTE_NAME.getLexema(), attribute_name);
        
        //___________________________________________________________________________________________________________________________________________________________________________
        Hashtable attribute_value = new Hashtable();   
        attribute_value.put(LexemasEnum.L_LLAVE.getLexema(),         Arrays.asList(SimbolosEnum.ELEMENT.getLexema()));
        attribute_value.put(LexemasEnum.R_LLAVE.getLexema(),         Arrays.asList(POP));
        attribute_value.put(LexemasEnum.L_CORCHETE.getLexema(),      Arrays.asList(SimbolosEnum.ELEMENT.getLexema()));
        attribute_value.put(LexemasEnum.R_CORCHETE.getLexema(),      Arrays.asList(POP));
        attribute_value.put(LexemasEnum.COMA.getLexema(),            Arrays.asList(POP));
        attribute_value.put(LexemasEnum.DOS_PUNTOS.getLexema(),      Arrays.asList(SCAN));
        attribute_value.put(LexemasEnum.LITERAL_CADENA.getLexema(),  Arrays.asList(SimbolosEnum.LITERAL_CADENA.getLexema()));
        attribute_value.put(LexemasEnum.LITERAL_NUM.getLexema(),     Arrays.asList(SimbolosEnum.LITERAL_NUM.getLexema()));
        attribute_value.put(LexemasEnum.PR_TRUE.getLexema(),         Arrays.asList(SimbolosEnum.PR_TRUE.getLexema()));
        attribute_value.put(LexemasEnum.PR_FALSE.getLexema(),        Arrays.asList(SimbolosEnum.PR_FALSE.getLexema()));
        attribute_value.put(LexemasEnum.PR_NULL.getLexema(),         Arrays.asList(SimbolosEnum.PR_NULL.getLexema()));
        attribute_value.put(LexemasEnum.EOF.getLexema(),             Arrays.asList(POP));
        tablaTraduccion.put(SimbolosEnum.ATTRIBUTE_VALUE.getLexema(), attribute_value);                
    }

    public Hashtable getTablaParsing() {
        return tablaTraduccion;
    }
        
}
