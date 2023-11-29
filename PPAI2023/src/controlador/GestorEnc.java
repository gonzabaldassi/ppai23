package controlador;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

//import datos.Cliente;
import datos.Encuesta;
import datos.Llamada;
import datos.Pregunta;
import conexion.Conexion;
import java.util.Date;
import javax.swing.JOptionPane;
import pantalla.AdmPantEnc;
import java.util.ArrayList; 
import pantalla.PantCSV;
import pantalla.ImpresorEncuesta;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class GestorEnc {
    
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    
    private String datosClienteLlamadaSeleccionada;
    private String descripcionEncuesta;
    private ArrayList<String> descripcionPreguntas = new ArrayList<String>();
    private ArrayList<Pregunta> preguntasEncuestaSeleccionada;
    private int duracionLlamadaSeleccionada;
    private String estadoLlamadaSeleccionada;
    private Date fechaFinPeriodoSeleccionado;
    private Date fechaInicioPeriodoSeleccionado;
    private String formaDeVisualizacion;
    private Llamada llamadaSeleccionada;
    private ArrayList<String> respuestasClienteEncuesta;
    private Date fechaEncuesta;
    private AdmPantEnc pantalla;
    private Encuesta encuesta;
    private Encuesta encuestaSeleccionada;
    private String datosEncuestaConRespuesta;
    private ArrayList <Llamada> llamadasDePeriodo = new ArrayList<Llamada>();
    
    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    
    public GestorEnc(){
    }
    
    //Metodo 3
    public void nuevaConsulta(){}
      
    //Metodo 4
    public void filtrarLlamadas(){}

    //Metodo 8
    public void tomarFechaDesdeHasta(Date fechaDesde,Date fechaHasta){
        fechaInicioPeriodoSeleccionado = fechaDesde;
        fechaFinPeriodoSeleccionado=fechaHasta;
    }
    
    //Metodo 9
    public void validarFecha(Date fechaInicioPeriodoSeleccionado,Date fechaFinPeriodoSeleccionado){
        if (fechaInicioPeriodoSeleccionado!=null && fechaFinPeriodoSeleccionado!=null){
            if (fechaInicioPeriodoSeleccionado.after(fechaFinPeriodoSeleccionado)){
                JOptionPane.showMessageDialog(null, "Error: La fecha de incio del periodo es mayor,por favor seleccione otro periodo");
            }else{
                buscarLlamadasPeriodo(fechaInicioPeriodoSeleccionado,fechaFinPeriodoSeleccionado);  
            }     
        }else{
            JOptionPane.showMessageDialog(null, "Error: alguna de las fechas no fue ingresada");
        }
    }
    
    //Metodo 10
    public void buscarLlamadasPeriodo(Date fechaInicioPeriodoSeleccionado,Date fechaFinPeriodoSeleccionado){
        this.fechaInicioPeriodoSeleccionado=fechaInicioPeriodoSeleccionado;
        this.fechaFinPeriodoSeleccionado=fechaFinPeriodoSeleccionado;
        Conexion testEncuesta = new Conexion();
        ArrayList<Llamada> listaLlamadas = new ArrayList<Llamada>();
        testEncuesta.main(null);  //Llama al método de testEncuesta pasando null como argumento. Ahora puedes usar la lista aquí
       
        listaLlamadas.removeAll(listaLlamadas);
        listaLlamadas.addAll(testEncuesta.llamadas);
        
        llamadasDePeriodo.removeAll(llamadasDePeriodo);
        
        if (listaLlamadas != null) {  // Verificar si la lista no es nula
            for (int i = 0; i < listaLlamadas.size(); i++) {
               if(listaLlamadas.get(i).tieneEncuestaRespondida()){
                  if(listaLlamadas.get(i).esDePeriodo(fechaInicioPeriodoSeleccionado, fechaFinPeriodoSeleccionado)){
                    llamadasDePeriodo.add(listaLlamadas.get(i));
                }  
               }
            }
        } else {
            System.out.println("La lista de llamadas es nula");
        }  
    }
    
    //Metodo 15
    public void tomarLlamada(Llamada llamadaSeleccionada){
        this.llamadaSeleccionada = llamadaSeleccionada;
    }
   
    //Metodo 16
    public String buscarDatosLlamada(Llamada llamadaSeleccionada){
        datosClienteLlamadaSeleccionada=llamadaSeleccionada.getDatosCliente();
        estadoLlamadaSeleccionada= llamadaSeleccionada.getDatosEstado();
        duracionLlamadaSeleccionada= llamadaSeleccionada.getDuracionLlamada();
        
        String duracion = Integer.toString(duracionLlamadaSeleccionada);
        
        String grillaDatosLlamadaEncuestada = datosClienteLlamadaSeleccionada + "\n" + estadoLlamadaSeleccionada + "\n" + "Duracion: " + duracion + " minutos";  
        return grillaDatosLlamadaEncuestada;
    }
    
    //Metodo 22
    public String obtenerDatosEncuesta(Llamada llamadaSeleccionada){
        
        if(respuestasClienteEncuesta!=null){
            respuestasClienteEncuesta.removeAll(respuestasClienteEncuesta);
        }
        
        if(descripcionPreguntas != null){
            descripcionPreguntas.removeAll(descripcionPreguntas);
        }
        respuestasClienteEncuesta=llamadaSeleccionada.getRespuestasCliente();
        
        Conexion testEncuesta = new Conexion();
        ArrayList<Encuesta> listaEncuestas = new ArrayList<Encuesta>();
        testEncuesta.main(null);  //Llama al método de testEncuesta pasando null como argumento. Ahora puedes usar la lista aquí
       
        listaEncuestas.removeAll(listaEncuestas);
        listaEncuestas.addAll(testEncuesta.encuestas);
        
        fechaEncuesta= llamadaSeleccionada.getRespuestaDeEncuesta().get(0).getFechaEncuesta();
        
        for (int i =0; i<listaEncuestas.size();i++){
            if (listaEncuestas.get(i).esEncuestaDeCliente(fechaEncuesta)){
                encuestaSeleccionada=listaEncuestas.get(i);
                break;
            }
        }
        
        if (encuestaSeleccionada == null){
            JOptionPane.showMessageDialog(null, "No hay Encuestas");    
        }else{
            descripcionEncuesta=encuestaSeleccionada.getDescripcionEncuesta();
            preguntasEncuestaSeleccionada=encuestaSeleccionada.getPreguntas();
            descripcionPreguntas=encuestaSeleccionada.getDescripcionPregunta();
        }
        
        //ciclo for para armar los String de salida
        datosEncuestaConRespuesta = "";
        
        for(int i=0;i<descripcionPreguntas.size();i++){
            datosEncuestaConRespuesta += "Pregunta: " + descripcionPreguntas.get(i)+ ", Respuesta: "+respuestasClienteEncuesta.get(i)+"\n";
        }
        return "Descripcion encuesta: " + descripcionEncuesta + "\n" + datosEncuestaConRespuesta;
    }    
    
    //Metodo 35
    public void tomarSelCSV(String formaDeVisualizacion){
        this.formaDeVisualizacion=formaDeVisualizacion;
        if ("CSV".equals(this.formaDeVisualizacion)){
            generarCSV();
        }
    }
    
    //Metodo 36
    public void generarCSV(){
        String clienteCSV= datosClienteLlamadaSeleccionada.substring(59, datosClienteLlamadaSeleccionada.length());
        String estadoLlamadaCSV= estadoLlamadaSeleccionada;
        int duracionLlamadaCSV = duracionLlamadaSeleccionada;
        String preguntasConRespuestaCSV = "";
        for (int i = 0;i<descripcionPreguntas.size();i++ ){
           preguntasConRespuestaCSV += descripcionPreguntas.get(i) + "," + respuestasClienteEncuesta.get(i) + "\n";
        }
        
        PantCSV pantallaCSV = new PantCSV(clienteCSV, estadoLlamadaCSV, duracionLlamadaCSV, preguntasConRespuestaCSV);  
    }    
           
    public ArrayList<Llamada> getLlamadasDePeriodo() {
        return llamadasDePeriodo;
    }
    
    //PATRON SINGLETON
    
    public void tomarSelImpresion(String formaDeVisualizacion){
        this.formaDeVisualizacion=formaDeVisualizacion;
        if ("PDF".equals(this.formaDeVisualizacion)){
            imprimir();
        }
    }
    
    public void imprimir(){

        //OBTENIENDO TODOS LOS STRINGS
        String clienteCSV= datosClienteLlamadaSeleccionada.substring(59, datosClienteLlamadaSeleccionada.length());
        String estadoLlamadaCSV= estadoLlamadaSeleccionada;
        int duracionLlamadaCSV = duracionLlamadaSeleccionada;
        String preguntasConRespuestaCSV = "";
        for (int i = 0;i<descripcionPreguntas.size();i++ ){
           preguntasConRespuestaCSV += descripcionPreguntas.get(i) + " , " + respuestasClienteEncuesta.get(i) + "\n";
        }
        
        String datosFinal = clienteCSV + ";\n" + estadoLlamadaCSV + ";\n" + "Duracion: " + duracionLlamadaCSV + ";\n" + preguntasConRespuestaCSV;
        
        //OBTENEMOS LA INSTANCIA DEL SINGLETON
        ImpresorEncuesta instanciaImpresor = ImpresorEncuesta.getInstancia();
        
        //LLAMAMOS AL METODO IMPRIMIR CON EL STRING FINAL
        instanciaImpresor.imprimir(datosFinal);
    }

}
