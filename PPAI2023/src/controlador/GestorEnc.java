package controlador;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

//import datos.Cliente;
import datos.Encuesta;
import datos.IAgregado;
import datos.IIterador;
import datos.IteradorLlamada;
import datos.Llamada;
import datos.Pregunta;
import datos.TestEncuesta;
import java.util.Date;
import javax.swing.JOptionPane;
import pantalla.AdmPantEnc;
import java.util.ArrayList; 
import java.util.Arrays;
import pantalla.PantCSV;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class GestorEnc implements IAgregado{
    
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
        
        ArrayList<Object> filtros = new ArrayList<>();
        filtros.add(this.fechaInicioPeriodoSeleccionado);
        filtros.add(this.fechaFinPeriodoSeleccionado);
        
        TestEncuesta testEncuesta = new TestEncuesta();
        ArrayList<Llamada> listaLlamadas = new ArrayList<Llamada>();
       
        testEncuesta.main(null);  //Llama al método de testEncuesta pasando null como argumento. Ahora puedes usar la lista aquí
     
        listaLlamadas.removeAll(listaLlamadas);
        listaLlamadas.addAll(testEncuesta.llamadas);
        
        llamadasDePeriodo.removeAll(llamadasDePeriodo);
        
        ArrayList<Object> listaObject = new ArrayList<>(listaLlamadas);
        
        if (listaLlamadas != null){
            IIterador iteradorLlamada = crearIterador(listaObject, filtros);
            iteradorLlamada.primero();
            
            while(iteradorLlamada.haTerminado() == false){
                Object actual;
                actual = iteradorLlamada.actual();
                if(actual!=""){
                    if(actual instanceof Llamada){
                        Llamada llamada = (Llamada) actual;
                        llamadasDePeriodo.add(llamada);
                        
                    }
                }
                
                iteradorLlamada.siguiente();
            }
        }else{
            System.out.println("La lista de llamadas es nula");
        }
        
        //Luego de comprobar que ande borrar esto:
//        if (listaLlamadas != null) {  // Verificar si la lista no es nula
//            for (int i = 0; i < listaLlamadas.size(); i++) {
//               if(listaLlamadas.get(i).tieneEncuestaRespondida()){
//                  if(listaLlamadas.get(i).esDePeriodo(fechaInicioPeriodoSeleccionado, fechaFinPeriodoSeleccionado)){
//                    llamadasDePeriodo.add(listaLlamadas.get(i));
//                }  
//               }
//            }
//        } else {
//            System.out.println("La lista de llamadas es nula");
//        }  

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
        
        
        
        TestEncuesta testEncuesta = new TestEncuesta();
        ArrayList<Encuesta> listaEncuestas = new ArrayList<Encuesta>();
        testEncuesta.main(null);  //Llama al método de testEncuesta pasando null como argumento. Ahora puedes usar la lista aquí
       
        listaEncuestas.removeAll(listaEncuestas);
        listaEncuestas.addAll(testEncuesta.encuestas);
        
        fechaEncuesta= llamadaSeleccionada.getRespuestaDeEncuesta().get(0).getFechaEncuesta(); //ESTO QUEDA COMO ESTÁ PERO HAY QUE VERIFICAR BIEN EN EL DIAG. DE SECUENCIA COMO PONERLO
        
        
        
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
        if (this.formaDeVisualizacion=="CSV"){
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

    @Override
    public IIterador crearIterador(ArrayList<Object> elementos, ArrayList<Object> filtros) {
        IteradorLlamada iteradorLlamada = new IteradorLlamada(elementos, filtros);
        return iteradorLlamada;
    }

}
