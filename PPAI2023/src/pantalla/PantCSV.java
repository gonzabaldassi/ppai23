package pantalla;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class PantCSV {
    
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    
    private String clienteCSV;
    private String estadoLlamadaCSV;
    private int duracionLlamadaCSV;
    private String preguntasConRespuestaCSV;
    private String nombreArchivo;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
    String timestamp = dateFormat.format(new Date());
    
    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    
    public PantCSV(String clienteCSV,String estadoLlamadaCSV,int duracionLlamadaCSV,String preguntasConRespuestaCSV){
        this.clienteCSV=clienteCSV;
        this.estadoLlamadaCSV=estadoLlamadaCSV;
        this.duracionLlamadaCSV=duracionLlamadaCSV;
        this.preguntasConRespuestaCSV=preguntasConRespuestaCSV;
        this.nombreArchivo= "CSV_"+timestamp + ".csv";
        guardarInformacion(clienteCSV,estadoLlamadaCSV,duracionLlamadaCSV,preguntasConRespuestaCSV,nombreArchivo);    
    }
    
    public static void guardarInformacion(String clienteCSV, String estadoLlamadaCSV, int duracionLlamadaCSV,String preguntasConRespuestaCSV, String nombreArchivo){
        
        try{
            FileWriter fw = new FileWriter(nombreArchivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("Encabezado: " + "\n" + clienteCSV + "," + estadoLlamadaCSV + "," + "Duracion: " + duracionLlamadaCSV + "\n" + "Preguntas: "  + "\n" + preguntasConRespuestaCSV);
            pw.flush();
            pw.close();
            JOptionPane.showMessageDialog(null, "CSV generado correctamente");
            System.exit(0);
        }catch(Exception E){
            JOptionPane.showMessageDialog(null, "CSV no se pudo generar correctamente");
        }
    }
}
