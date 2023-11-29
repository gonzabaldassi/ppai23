package conexion;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
import datos.Cliente;
import datos.Encuesta;
import datos.Estado;
import datos.Llamada;
import datos.Pregunta;
import datos.RespuestaDeCliente;
import datos.RespuestaPosible;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------
public class Conexion {
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    public static ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
    public static ArrayList<Encuesta> encuestas = new ArrayList<Encuesta>();


    public static void main(String[] args) {
        //Conexion a la base de datos
        
        String BD = "jdbc:postgresql://localhost:5432/ppai";
        String usuario = "postgres";
        String password = "123456";
        
        llamadas.removeAll(llamadas);

        encuestas.removeAll(encuestas);
        try {
            Connection conectar = DriverManager.getConnection(BD, usuario, password);
            PreparedStatement queryLlamadas;
            PreparedStatement queryRespuestas;
            

            ResultSet resultadoLlamadas;
            ResultSet resultadoRespuestas;
                      
            queryLlamadas = conectar.prepareCall("SELECT * FROM Llamada INNER JOIN Estado ON Llamada.estado = Estado.id INNER JOIN Cliente ON Llamada.cliente = Cliente.id", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            queryRespuestas = conectar.prepareCall("SELECT * FROM RespuestaDeCliente INNER JOIN RespuestaPosible ON RespuestaDeCliente.respuestaPosible = RespuestaPosible.id INNER JOIN Llamada ON RespuestaDeCliente.llamada = Llamada.numeroLlamada", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
               

            resultadoLlamadas = queryLlamadas.executeQuery();
            resultadoRespuestas = queryRespuestas.executeQuery();
            
            
            while (resultadoLlamadas.next()) {
                
                int numeroLlamada = resultadoLlamadas.getInt(1);
                ArrayList<RespuestaDeCliente> respuestas = new ArrayList<RespuestaDeCliente>();
               
                while (resultadoRespuestas.next()) {
                    
                    if (resultadoRespuestas.getInt(5) == numeroLlamada) {
                        Date inputDate = resultadoRespuestas.getDate(3);
                        String outputDateFormat = "MM/dd/yyyy";
                        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat);
                        String outputDateString = outputFormat.format(inputDate);  
                        Date f = new Date(outputDateString);
                        RespuestaPosible respuestaPosible = new RespuestaPosible(resultadoRespuestas.getString(7), resultadoRespuestas.getInt(8));
                        RespuestaDeCliente respuestaDeCliente = new RespuestaDeCliente(respuestaPosible, f);
                        respuestas.add(respuestaDeCliente);
                    }

                }
                resultadoRespuestas.beforeFirst();
                
                Cliente cliente = new Cliente(resultadoLlamadas.getString(12), resultadoLlamadas.getInt(13), resultadoLlamadas.getString(14));
                Estado estado = new Estado(resultadoLlamadas.getString(10));
                if (!respuestas.isEmpty()){
                    Llamada llamada = new Llamada(resultadoLlamadas.getInt(1), cliente, estado, respuestas, resultadoLlamadas.getString(2), resultadoLlamadas.getInt(4), resultadoLlamadas.getBoolean(5));
                    llamadas.add(llamada);
            
                }else{
                    Llamada llamada = new Llamada(resultadoLlamadas.getInt(1), cliente, estado, null, resultadoLlamadas.getString(2), resultadoLlamadas.getInt(4), resultadoLlamadas.getBoolean(5));
                    
                    llamadas.add(llamada);
                }
                
                
                
            }
            
            

            
            
            PreparedStatement queryEncuestas;
            PreparedStatement queryPreguntas;
            PreparedStatement queryEncuestaAPregunta;
            PreparedStatement queryRespuestasPosibles;
            PreparedStatement queryPreguntaARespuestaPosible;
            

            ResultSet resultadoEncuestas;
            ResultSet resultadoPreguntas;
            ResultSet resultadoEncuestaAPregunta;
            ResultSet resultadoRespuestasPosibles;
            ResultSet resultadoPreguntaARespuestaPosible;
            
            
            queryEncuestas = conectar.prepareCall("SELECT * FROM Encuesta", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            queryPreguntas = conectar.prepareCall("SELECT * FROM Pregunta", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            queryEncuestaAPregunta = conectar.prepareCall("SELECT * FROM EncuestaAPregunta", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            queryRespuestasPosibles = conectar.prepareCall("SELECT * FROM RespuestaPosible", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            queryPreguntaARespuestaPosible = conectar.prepareCall("SELECT * FROM PreguntaARespuestaPosible", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            resultadoEncuestas = queryEncuestas.executeQuery();
            resultadoEncuestaAPregunta = queryEncuestaAPregunta.executeQuery();
            resultadoPreguntas = queryPreguntas.executeQuery();
            resultadoPreguntaARespuestaPosible = queryPreguntaARespuestaPosible.executeQuery();
            resultadoRespuestasPosibles = queryRespuestasPosibles.executeQuery();
            
            Map<Pregunta, Integer> mapPreguntas = new HashMap<>();
            mapPreguntas.clear();
            while (resultadoPreguntas.next()){
                int numeroPregunta = resultadoPreguntas.getInt(1);
                ArrayList<RespuestaPosible> respuestasPosibles = new ArrayList<>();
                while (resultadoPreguntaARespuestaPosible.next()){
                    if (numeroPregunta == resultadoPreguntaARespuestaPosible.getInt(3)){
                        int numeroRespuestaPosible = resultadoPreguntaARespuestaPosible.getInt(2);
                        
                        while (resultadoRespuestasPosibles.next()){
                            if (numeroRespuestaPosible == resultadoRespuestasPosibles.getInt(1)){
                                RespuestaPosible respuestaPosible = new RespuestaPosible(resultadoRespuestasPosibles.getString(2), resultadoRespuestasPosibles.getInt(3));
                                respuestasPosibles.add(respuestaPosible);
                            } 
                        }
                    }
                }
                
                Pregunta pregunta = new Pregunta(resultadoPreguntas.getString(2), respuestasPosibles);
                mapPreguntas.put(pregunta,resultadoPreguntas.getInt(1));
            }
            
            
            
            while(resultadoEncuestas.next()){
                ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
                int numeroEncuesta = resultadoEncuestas.getInt(1);
                while(resultadoEncuestaAPregunta.next()){
                    if (resultadoEncuestaAPregunta.getInt(2)==numeroEncuesta){
                        for (Map.Entry<Pregunta, Integer> entry : mapPreguntas.entrySet()){
                            
                            Pregunta pregunta = entry.getKey();
                            Integer idPregunta = entry.getValue();
                            if (idPregunta == resultadoEncuestaAPregunta.getInt(3)){
                                preguntas.add(pregunta);
                            }
                        }
                    }
                }
                resultadoEncuestaAPregunta.beforeFirst();
                
                Date fechaInicio = resultadoEncuestas.getDate(4);
                Date fechaFin = resultadoEncuestas.getDate(3);
                String outputDateFormat = "MM/dd/yyyy";
                SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat);
                String outputDateStringInicio = outputFormat.format(fechaInicio);  
                Date fInicio = new Date(outputDateStringInicio);
                String outputDateStringFin = outputFormat.format(fechaFin);  
                Date fFin = new Date(outputDateStringFin);
                
                Encuesta encuesta = new Encuesta(preguntas, resultadoEncuestas.getString(2), fInicio, fFin);
                encuestas.add(encuesta);
                
            }
            
            
            
            
            
            queryLlamadas.close();
            queryRespuestas.close();                        
            queryEncuestas.close();
            queryPreguntas.close(); 
            queryEncuestaAPregunta.close();
            queryRespuestasPosibles.close();
            queryPreguntaARespuestaPosible.close(); 
            
            
            conectar.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Conexion NO realizada");
        }


        
        
        
        
    }

}