package datos;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Date;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class Encuesta implements IAgregado{
    
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
    //Metodo 30 - Iterator
    public boolean esEncuestaDeCliente(Date fechaEncuesta){
        if(fechaEncuesta.before(fechaFinVigencia) && fechaEncuesta.after(fechaInicioVigencia)){
            return true;
        }else{
            return false;
        }
    }

    //Se obtiene la descripcion de la Encuesta
    //Metodo 29
    //Metodo 31 - Iterator
    public String getDescripcionEncuesta(){
        return descripcion;
    }

    //Metodo 30
    //Metodo 32 - Iterator
    public ArrayList<String> getDescripcionPregunta(){
        ArrayList<String> descripcionPreguntas = new ArrayList<String>();
        
        ArrayList<Object> listaObject = new ArrayList<>(this.preguntas);
        ArrayList<Object> filtros = new ArrayList<>();
  
        
        IIterador iteradorPreguntas = crearIterador(listaObject,filtros);
        
        iteradorPreguntas.primero();
        
        while(iteradorPreguntas.haTerminado() == false){
            Object actual;
            actual = iteradorPreguntas.actual();
            
            if (actual instanceof Pregunta){
                Pregunta pregunta = (Pregunta) actual;
                descripcionPreguntas.add(pregunta.getDescripcionPregunta());
            }
            
            iteradorPreguntas.siguiente();
        }
        
        
        
        return descripcionPreguntas;
    }    
    
    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }         

    //Metodo 33 - Iterator
    @Override
    public IIterador crearIterador(ArrayList<Object> elementos, ArrayList<Object> filtros) {
        IteradorPregunta iteradorPregunta = new IteradorPregunta(elementos);
        return iteradorPregunta;
    }


}
