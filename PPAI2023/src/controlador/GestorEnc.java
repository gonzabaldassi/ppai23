package controlador;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
//import datos.Cliente;
import datos.Encuesta;
import datos.IAgregado;
import datos.IIterador;
import datos.IteradorEncuesta;
import datos.IteradorLlamada;
import datos.Llamada;
import datos.Pregunta;
import conexion.Conexion;
import java.util.Date;
import javax.swing.JOptionPane;
import pantalla.AdmPantEnc;
import java.util.ArrayList;
import java.util.Arrays;
import pantalla.PantCSV;
import pantalla.ImpresorEncuesta;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------
public class GestorEnc implements IAgregado {

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
    private ArrayList<Llamada> llamadasDePeriodo = new ArrayList<Llamada>();

    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    public GestorEnc() {
    }

    //Metodo 3
    public void nuevaConsulta() {
    }

    //Metodo 4
    public void filtrarLlamadas() {
    }

    //Metodo 8
    public void tomarFechaDesdeHasta(Date fechaDesde, Date fechaHasta) {
        fechaInicioPeriodoSeleccionado = fechaDesde;
        fechaFinPeriodoSeleccionado = fechaHasta;
    }

    //Metodo 9
    public void validarFecha(Date fechaInicioPeriodoSeleccionado, Date fechaFinPeriodoSeleccionado) {
        if (fechaInicioPeriodoSeleccionado != null && fechaFinPeriodoSeleccionado != null) {
            if (fechaInicioPeriodoSeleccionado.after(fechaFinPeriodoSeleccionado)) {
                JOptionPane.showMessageDialog(null, "Error: La fecha de incio del periodo es mayor,por favor seleccione otro periodo");
                llamadasDePeriodo.removeAll(llamadasDePeriodo);
            } else {
                buscarLlamadasPeriodo(fechaInicioPeriodoSeleccionado, fechaFinPeriodoSeleccionado);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: alguna de las fechas no fue ingresada");
            llamadasDePeriodo.removeAll(llamadasDePeriodo);
        }
    }

    
    //Metodo 10
    public void buscarLlamadasPeriodo(Date fechaInicioPeriodoSeleccionado, Date fechaFinPeriodoSeleccionado) {
        this.fechaInicioPeriodoSeleccionado = fechaInicioPeriodoSeleccionado;
        this.fechaFinPeriodoSeleccionado = fechaFinPeriodoSeleccionado;

        ArrayList<Object> filtros = new ArrayList<>();
        filtros.add(this.fechaInicioPeriodoSeleccionado);
        filtros.add(this.fechaFinPeriodoSeleccionado);

        Conexion testEncuesta = new Conexion();
        ArrayList<Llamada> listaLlamadas = new ArrayList<Llamada>();

        testEncuesta.main(null);  //Llama al método de testEncuesta pasando null como argumento. Ahora puedes usar la lista aquí

        listaLlamadas.removeAll(listaLlamadas);
        listaLlamadas.addAll(testEncuesta.llamadas);

        llamadasDePeriodo.removeAll(llamadasDePeriodo);

        ArrayList<Object> listaObject = new ArrayList<>(listaLlamadas);

        if (listaLlamadas != null) {
            IIterador iteradorLlamada = crearIterador(listaObject, filtros);
            iteradorLlamada.primero();

            while (iteradorLlamada.haTerminado() == false) {
                Object actual;
                actual = iteradorLlamada.actual();
                if (actual != "") {
                    if (actual instanceof Llamada) {
                        Llamada llamada = (Llamada) actual;
                        llamadasDePeriodo.add(llamada);
                    }
                }

                iteradorLlamada.siguiente();
            }
        } else {
            System.out.println("La lista de llamadas es nula");
        }
        
    }

    //Metodo 15
    public void tomarLlamada(Llamada llamadaSeleccionada) {
        this.llamadaSeleccionada = llamadaSeleccionada;
    }

    //Metodo 16
    public String buscarDatosLlamada(Llamada llamadaSeleccionada) {
        datosClienteLlamadaSeleccionada = llamadaSeleccionada.getDatosCliente();
        estadoLlamadaSeleccionada = llamadaSeleccionada.getDatosEstado();
        duracionLlamadaSeleccionada = llamadaSeleccionada.getDuracionLlamada();

        String duracion = Integer.toString(duracionLlamadaSeleccionada);

        String grillaDatosLlamadaEncuestada = datosClienteLlamadaSeleccionada + "\n" + estadoLlamadaSeleccionada + "\n" + "Duracion: " + duracion + " minutos";
        return grillaDatosLlamadaEncuestada;
    }

    //Metodo 22
    public String obtenerDatosEncuesta(Llamada llamadaSeleccionada) {

        if (respuestasClienteEncuesta != null) {
            respuestasClienteEncuesta.removeAll(respuestasClienteEncuesta);
        }

        if (descripcionPreguntas != null) {
            descripcionPreguntas.removeAll(descripcionPreguntas);
        }
        
        //Acá se instancia el getRespuestasCliente que luego en la llamada se crea el iterador para las RespuestaCliente.
        respuestasClienteEncuesta = llamadaSeleccionada.getRespuestasCliente();
        
        
        fechaEncuesta = llamadaSeleccionada.getRespuestaDeEncuesta().get(0).getFechaEncuesta(); //ESTO QUEDA COMO ESTÁ PERO HAY QUE VERIFICAR BIEN EN EL DIAG. DE SECUENCIA COMO PONERLO

        Conexion testEncuesta = new Conexion();
        ArrayList<Encuesta> listaEncuestas = new ArrayList<Encuesta>();
        testEncuesta.main(null);  //Llama al método de testEncuesta pasando null como argumento. Ahora puedes usar la lista aquí

        listaEncuestas.removeAll(listaEncuestas);
        listaEncuestas.addAll(testEncuesta.encuestas);


        //Creacion del iterador para las encuestas
        ArrayList<Date> filtrosFecha = new ArrayList<Date>();

        filtrosFecha.removeAll(filtrosFecha);
        filtrosFecha.add(fechaEncuesta);

        ArrayList<Object> listaObject = new ArrayList<>(listaEncuestas);
        ArrayList<Object> filtrosObject = new ArrayList<>(filtrosFecha);

        IIterador iteradorEncuestas = crearIterador(listaObject, filtrosObject);

        iteradorEncuestas.primero();

        while (iteradorEncuestas.haTerminado() == false) {
            Object actual;
            actual = iteradorEncuestas.actual();
            
            if (actual != "") {
                if (actual instanceof Encuesta) {
                    Encuesta encuesta = (Encuesta) actual;
                    descripcionEncuesta = encuesta.getDescripcionEncuesta();
                    descripcionPreguntas = encuesta.getDescripcionPregunta();
                }
            }
            
            iteradorEncuestas.siguiente();
        }


        //ciclo for para armar los String de salida
        datosEncuestaConRespuesta = "";

        for (int i = 0; i < descripcionPreguntas.size(); i++) {
            datosEncuestaConRespuesta += "Pregunta: " + descripcionPreguntas.get(i) + ", Respuesta: " + respuestasClienteEncuesta.get(i) + "\n";
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
    public void generarCSV() {
        String clienteCSV = datosClienteLlamadaSeleccionada.substring(59, datosClienteLlamadaSeleccionada.length());
        String estadoLlamadaCSV = estadoLlamadaSeleccionada;
        int duracionLlamadaCSV = duracionLlamadaSeleccionada;
        String preguntasConRespuestaCSV = "";
        for (int i = 0; i < descripcionPreguntas.size(); i++) {
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

    @Override
    public IIterador crearIterador(ArrayList<Object> elementos, ArrayList<Object> filtros) {
        if (elementos.get(0) instanceof Llamada) {
            IteradorLlamada iteradorLlamada = new IteradorLlamada(elementos, filtros);
            return iteradorLlamada;
        } else {
            IteradorEncuesta iteradorEncuesta = new IteradorEncuesta(elementos, filtros);
            return iteradorEncuesta;
        }
    }

}
