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
        String password = "050Rober3110";
        
        llamadas.removeAll(llamadas);
        try {
            Connection conectar = DriverManager.getConnection(BD, usuario, password);
            PreparedStatement queryLlamadas;
            PreparedStatement queryRespuestas;
            PreparedStatement queryRespuestasPosibles;

            ResultSet resultadoLlamadas;
            ResultSet resultadoRespuestas;
            ResultSet resultadoRespuestasPosibles;

            

            queryLlamadas = conectar.prepareCall("SELECT * FROM Llamada INNER JOIN Estado ON Llamada.estado = Estado.id INNER JOIN Cliente ON Llamada.cliente = Cliente.id");
            queryRespuestas = conectar.prepareCall("SELECT * FROM RespuestaDeCliente INNER JOIN RespuestaPosible ON RespuestaDeCliente.respuestaPosible = RespuestaPosible.id INNER JOIN Llamada ON RespuestaDeCliente.llamada = Llamada.numeroLlamada");
            queryRespuestasPosibles = conectar.prepareCall("SELECT * FROM RespuestaPosible");

            resultadoLlamadas = queryLlamadas.executeQuery();
            resultadoRespuestas = queryRespuestas.executeQuery();
            resultadoRespuestasPosibles = queryRespuestasPosibles.executeQuery();

            while (resultadoLlamadas.next()) {

                int numeroLlamada = resultadoLlamadas.getInt(1);
                ArrayList<RespuestaDeCliente> respuestas = new ArrayList<RespuestaDeCliente>();

                while (resultadoRespuestas.next()) {
                    /*int numeroRespuestaPosible = resultadoRespuestas.getInt(3);
                    if (resultadoRespuestas.getInt(4) == numeroLlamada) {
                        while (resultadoRespuestasPosibles.next()) {
                            if (numeroRespuestaPosible == resultadoRespuestasPosibles.getInt(1)) {
                                RespuestaPosible respuestaPosible = new RespuestaPosible(resultadoRespuestasPosibles.getString(2), resultadoRespuestasPosibles.getInt(3));
                                RespuestaDeCliente respuestaDeCliente = new RespuestaDeCliente(respuestaPosible, resultadoRespuestas.getDate(2));
                                respuestas.add(respuestaDeCliente);
                            }
                        }

                    }*/
                    if (resultadoRespuestas.getInt(4) == numeroLlamada) {
                        Date inputDate = resultadoRespuestas.getDate(2);
                        String outputDateFormat = "MM/dd/yyyy";

                        SimpleDateFormat outputFormat = new SimpleDateFormat(outputDateFormat);

                        String outputDateString = outputFormat.format(inputDate);
                        //System.out.println("Fecha de entrada: " + inputDate);
                        //System.out.println("Fecha de salida: " + outputDateString);
                        
                        
                        Date f = new Date(outputDateString);
                        RespuestaPosible respuestaPosible = new RespuestaPosible(resultadoRespuestas.getString(6), resultadoRespuestas.getInt(7));
                        RespuestaDeCliente respuestaDeCliente = new RespuestaDeCliente(respuestaPosible, f);
                        respuestas.add(respuestaDeCliente);
                    }

                }

                Cliente cliente = new Cliente(resultadoLlamadas.getString(12), resultadoLlamadas.getInt(13), resultadoLlamadas.getString(14));
                Estado estado = new Estado(resultadoLlamadas.getString(10));

                Llamada llamada = new Llamada(resultadoLlamadas.getInt(1), cliente, estado, respuestas, resultadoLlamadas.getString(2), resultadoLlamadas.getInt(4), resultadoLlamadas.getBoolean(5));
                llamadas.add(llamada);
                System.out.println(llamada);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Conexion NO realizada");
        }

        //----------------------------------------------------------------------------------
        //------------------------------ OBJETOS -------------------------------------------
        //Creacion de objetos necesarios para el funcionamiento del CU
        ArrayList<RespuestaPosible> vectorRespuestasCalificacion = new ArrayList<RespuestaPosible>();
        ArrayList<RespuestaPosible> vectorRespuestasSiNo = new ArrayList<RespuestaPosible>();

        //Creacion respuestas de clientes y agregacion al vector correspondiente
        ArrayList<RespuestaDeCliente> respuestas1 = new ArrayList<RespuestaDeCliente>();
        ArrayList<RespuestaDeCliente> respuestas2 = new ArrayList<RespuestaDeCliente>();
        ArrayList<RespuestaDeCliente> respuestas3 = new ArrayList<RespuestaDeCliente>();
        ArrayList<RespuestaDeCliente> respuestas4 = new ArrayList<RespuestaDeCliente>();
        ArrayList<RespuestaDeCliente> respuestas5 = new ArrayList<RespuestaDeCliente>();

        //Creacion objetos clientes
        Cliente cliente1 = new Cliente("Roger Federer", 20357987, "3534078294"); //Enc1
        Cliente cliente2 = new Cliente("Novak Djokovic", 22567987, "3534678341"); //Enc1
        Cliente cliente3 = new Cliente("Rafael Nadal", 22345876, "3534079234"); //Enc2
        Cliente cliente4 = new Cliente("Carlos Alcaraz", 40398523, "3538908213"); //Enc2
        Cliente cliente5 = new Cliente("Maria Sharapova", 27997312, "3536098123"); //Enc1

        //Fecha para las respuestas del cliente
        Date fecha1 = new Date("06/17/2023"); //enc1
        Date fecha2 = new Date("04/23/2023"); //enc1
        Date fecha3 = new Date("07/25/2023"); //enc2
        Date fecha4 = new Date("09/05/2023"); //enc2
        Date fecha5 = new Date("01/02/2023"); //enc1

        //Creacion de objetos preguntas y su agregacion al vector correspondiente
        Pregunta pregunta1 = new Pregunta("¿Como calificaria el servicio?", vectorRespuestasCalificacion);
        Pregunta pregunta2 = new Pregunta("¿Estas conforme con el servicio?", vectorRespuestasSiNo);
        Pregunta pregunta3 = new Pregunta("¿Le recomendaria el servicio a un amigo?", vectorRespuestasSiNo);
        Pregunta pregunta4 = new Pregunta("¿Como fue la calidad de la llamada?", vectorRespuestasCalificacion);

        encuestas.removeAll(encuestas);
        //Encuesta1
        Date fechaInicioVigencia = new Date("01/01/2023");
        Date fechaFinVigencia = new Date("06/30/2023");
        Encuesta encuesta1 = new Encuesta(preguntas, "encuesta1", fechaInicioVigencia, fechaFinVigencia);
        encuestas.add(encuesta1);

        preguntas.removeAll(preguntas);
        preguntas.add(pregunta1);
        preguntas.add(pregunta2);
        preguntas.add(pregunta3);

        //Encuesta2
        Date fechaInicioVigencia2 = new Date("07/01/2023");
        Date fechaFinVigencia2 = new Date("12/31/2023");
        Encuesta encuesta2 = new Encuesta(preguntas2, "encuesta2", fechaInicioVigencia2, fechaFinVigencia2);
        encuestas.add(encuesta2);

        preguntas2.removeAll(preguntas2);
        preguntas2.add(pregunta2);
        preguntas2.add(pregunta3);
        preguntas2.add(pregunta4);

        //Creacion objetos estados
        Estado iniciada = new Estado("Iniciada");
        Estado finalizada = new Estado("Finalizada");

        //Creacion respuestas posibles y agregacion al vector correspondiente
        RespuestaPosible rtaPosible1 = new RespuestaPosible("Si", 1);
        RespuestaPosible rtaPosible2 = new RespuestaPosible("No", 0);
        vectorRespuestasSiNo.add(rtaPosible1);
        vectorRespuestasSiNo.add(rtaPosible2);

        //Segundo array con respuestas posibles
        RespuestaPosible rtaPosibleC1 = new RespuestaPosible("Malo", 1);
        RespuestaPosible rtaPosibleC2 = new RespuestaPosible("Decente", 2);
        RespuestaPosible rtaPosibleC3 = new RespuestaPosible("Bueno", 3);
        RespuestaPosible rtaPosibleC4 = new RespuestaPosible("Excelente", 4);

        vectorRespuestasCalificacion.add(rtaPosibleC1);
        vectorRespuestasCalificacion.add(rtaPosibleC2);
        vectorRespuestasCalificacion.add(rtaPosibleC3);
        vectorRespuestasCalificacion.add(rtaPosibleC4);

        //Respuestas del cliente 1 //Enc1
        RespuestaDeCliente respuesta11 = new RespuestaDeCliente(rtaPosibleC2, fecha1);
        RespuestaDeCliente respuesta12 = new RespuestaDeCliente(rtaPosible2, fecha1);
        RespuestaDeCliente respuesta13 = new RespuestaDeCliente(rtaPosible1, fecha1);

        respuestas1.add(respuesta11);
        respuestas1.add(respuesta12);
        respuestas1.add(respuesta13);

        //Respuestas del cliente 2 //Enc1
        RespuestaDeCliente respuesta21 = new RespuestaDeCliente(rtaPosibleC4, fecha2);
        RespuestaDeCliente respuesta22 = new RespuestaDeCliente(rtaPosible1, fecha2);
        RespuestaDeCliente respuesta23 = new RespuestaDeCliente(rtaPosible1, fecha2);
        respuestas2.add(respuesta21);
        respuestas2.add(respuesta22);
        respuestas2.add(respuesta23);

        //Respuestas del cliente 5 //Enc1
        RespuestaDeCliente respuesta51 = new RespuestaDeCliente(rtaPosibleC1, fecha5);
        RespuestaDeCliente respuesta52 = new RespuestaDeCliente(rtaPosible1, fecha5);
        RespuestaDeCliente respuesta53 = new RespuestaDeCliente(rtaPosible2, fecha5);
        respuestas5.add(respuesta51);
        respuestas5.add(respuesta52);
        respuestas5.add(respuesta53);

        //Respuestas del cliente 3 //Enc2
        RespuestaDeCliente respuesta31 = new RespuestaDeCliente(rtaPosible1, fecha3);
        RespuestaDeCliente respuesta32 = new RespuestaDeCliente(rtaPosible1, fecha3);
        RespuestaDeCliente respuesta33 = new RespuestaDeCliente(rtaPosibleC3, fecha3);
        respuestas3.add(respuesta31);
        respuestas3.add(respuesta32);
        respuestas3.add(respuesta33);

        //Respuestas del cliente 4 //Enc2
        RespuestaDeCliente respuesta41 = new RespuestaDeCliente(rtaPosible2, fecha4);
        RespuestaDeCliente respuesta42 = new RespuestaDeCliente(rtaPosible2, fecha4);
        RespuestaDeCliente respuesta43 = new RespuestaDeCliente(rtaPosibleC1, fecha4);
        respuestas4.add(respuesta41);
        respuestas4.add(respuesta42);
        respuestas4.add(respuesta43);

        //Creacion objetos llamadas
        Llamada llamada1 = new Llamada(1, cliente1, finalizada, respuestas1, "Prueba1", 10, true);
        Llamada llamada2 = new Llamada(2, cliente2, iniciada, null, "Prueba 2", 0, false);
        Llamada llamada3 = new Llamada(3, cliente2, finalizada, respuestas2, "Prueba 3", 9, true);
        Llamada llamada4 = new Llamada(4, cliente3, finalizada, respuestas3, "Prueba 4", 7, true);
        Llamada llamada5 = new Llamada(5, cliente4, finalizada, respuestas4, "Prueba 5", 14, true);
        Llamada llamada6 = new Llamada(6, cliente5, finalizada, respuestas5, "Prueba 6", 5, true);
        
        
        llamadas.add(llamada1);
        llamadas.add(llamada2);
        llamadas.add(llamada3);
        llamadas.add(llamada4);
        llamadas.add(llamada5);
        llamadas.add(llamada6);

    }

}
