package datos;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------
public class TestEncuesta {

    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    public static ArrayList<Llamada> llamadas = new ArrayList<Llamada>();
    public static ArrayList<Encuesta> encuestas = new ArrayList<Encuesta>();
    public static ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
    public static ArrayList<Pregunta> preguntas2 = new ArrayList<Pregunta>();

    public static void main(String[] args) {
        //Conexion a la base de datos
        
        String BD = "jdbc:postgresql://localhost:5432/ppai";
        String usuario = "postgres";
        String password = "123456";
        
        llamadas.removeAll(llamadas);
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
            
            
            queryLlamadas.close();
            queryRespuestas.close();
            conectar.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Conexion NO realizada");
        }


        
        
        
        
    }

}