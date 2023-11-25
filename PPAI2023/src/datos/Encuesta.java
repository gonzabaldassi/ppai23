package datos;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Date;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class Encuesta {
    
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    
    private ArrayList<Pregunta> preguntas;
    private String descripcion;
    private Date fechaFinVigencia;
    private Date fechaInicioVigencia;
    
    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    
    //Constructor de la clase Encuesta
    public Encuesta(ArrayList<Pregunta> preguntas, String descripcion, Date fechaInicioVigencia, Date fechaFinVigencia) {
        this.preguntas = preguntas;
        this.descripcion = descripcion;
        this.fechaInicioVigencia = fechaInicioVigencia;
        this.fechaFinVigencia = fechaFinVigencia;
    }
    
    //Metodo 28
    public boolean esEncuestaDeCliente(Date fechaEncuesta){
        if(fechaEncuesta.before(fechaFinVigencia) && fechaEncuesta.after(fechaInicioVigencia)){
            return true;
        }else{
            return false;
        }
    }

    //Se obtiene la descripcion de la Encuesta
    //Metodo 29
    public String getDescripcionEncuesta(){
        return descripcion;
    }

    //Metodo 30
    public ArrayList<String> getDescripcionPregunta(){
        ArrayList<String> descripcionPreguntas = new ArrayList<String>();
        for (int i =0;i<preguntas.size();i++){
                descripcionPreguntas.add(preguntas.get(i).getDescripcionPregunta());
        }
        return descripcionPreguntas;
    }    
    
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }         
}
