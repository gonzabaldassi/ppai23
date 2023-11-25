package datos;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

import java.util.ArrayList;
import java.util.Date;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class Llamada {
    
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    
    private int numeroLlamada;
    private ArrayList<RespuestaDeCliente> respuestaDeEncuesta;
    private Cliente cliente;
    private Estado estadoActual;
    private String descripcionOperador;
    private int duracion; //Se toman solo minutos
    private boolean encuestaEnviada;
    
    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    
    //Constructor de la clase Llamada
    public Llamada(int numero,Cliente cliente, Estado estadoActual, ArrayList<RespuestaDeCliente> respuestaDeEncuesta, String descripcionOperador, int duracion, boolean encuestaEnviada) {
        this.numeroLlamada = numero;
        this.cliente = cliente;
        this.estadoActual = estadoActual;
        this.respuestaDeEncuesta = respuestaDeEncuesta;
        this.descripcionOperador = descripcionOperador;
        this.duracion = duracion;
        this.encuestaEnviada = encuestaEnviada;
    }
    
    //Metodo 11
    public boolean tieneEncuestaRespondida(){
        if(respuestaDeEncuesta != null){
          return true;
        }else{
          return false;
      }
    }

    //Metodo 12
    public boolean esDePeriodo(Date fechaInicioPeriodo, Date fechaFinPeriodo) {
        Date fechaEncuesta = respuestaDeEncuesta.get(0).getFechaEncuesta();
        if(fechaEncuesta.after(fechaInicioPeriodo) && fechaEncuesta.before(fechaFinPeriodo)){
            return true;  
      }else {
        return false;  
      }
    }    
    
    //Metodo 17
    public String getDatosCliente(){
       return cliente.getDatos();   
    }   
    
    //Metodo 19
    public String getDatosEstado(){
        return estadoActual.getEstado(); 
    }
    
    //Metodo 21
    public int getDuracionLlamada(){
       return duracion;   
    }

    //Metodo 23
    public ArrayList<String> getRespuestasCliente() {
        ArrayList<String> vectorRespuestas = new ArrayList<String>();
        for (int i=0;respuestaDeEncuesta.size()>i;i++){
            String respuesta = respuestaDeEncuesta.get(i).getRespCliente();
            vectorRespuestas.add(respuesta);
        }
        return vectorRespuestas;
    } 
    
    //Metodo 26
    public ArrayList<RespuestaDeCliente> getRespuestaDeEncuesta() {
        return respuestaDeEncuesta;
    }
    
    //Metodos de la Maquina de Estados
    public void cancelar(){}
    public void descartar(){}
    public void finalizar(){}
    public void registrarEscucha(){}
    public void seleccionar(){}
    public void derivarOperador(){}
    
    public String getDescripcionOperador() {
        return descripcionOperador;
    }

    @Override
    public String toString() { 
        return "Llamada Nro:" + numeroLlamada + ", Cliente:" + cliente.getnombreCompleto() + ", descripcionOperador:" + descripcionOperador + "Fecha:" + respuestaDeEncuesta.get(0).getFechaEncuesta();
    }
    
    public String getNumeroLlamada(){
        return String.valueOf(numeroLlamada);
    }
    
}
