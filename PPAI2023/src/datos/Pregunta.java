package datos;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

import java.util.ArrayList;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class Pregunta {
    
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    
    private ArrayList<RespuestaPosible> rtaPosible;
    private String descripcionPregunta;
    
    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    
    //Constructor de la clase Pregunta
    public Pregunta(String descripcionPregunta, ArrayList<RespuestaPosible> rtaPosible) {
        this.descripcionPregunta = descripcionPregunta;
        this.rtaPosible = rtaPosible;
    }
    
    //Metodo 31
    public String getDescripcionPregunta(){
        return descripcionPregunta;
    }
        
}
