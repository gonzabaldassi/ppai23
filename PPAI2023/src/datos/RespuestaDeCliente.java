package datos;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

import java.util.Date;   

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class RespuestaDeCliente {
    
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    
    private RespuestaPosible respuestaSeleccionada;
    private Date fechaEncuesta;
    
    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    
    //Constructor de la clase RespuestaDeCliente
    public RespuestaDeCliente(RespuestaPosible respuestaSeleccionada, Date fechaEncuesta) {
        this.respuestaSeleccionada = respuestaSeleccionada;
        this.fechaEncuesta = fechaEncuesta;
    }
    
    //Metodo 24
    //Metodo 19 - Iterator
    public String getRespCliente() {
        return respuestaSeleccionada.getDescripcionRta();
    } 

    //Metodo 27
    //Metodo 23 - Iterator
    public Date getFechaEncuesta() {
        return fechaEncuesta;
    } 
    
}
