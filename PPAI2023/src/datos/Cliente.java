package datos;

//----------------------------------------------------------------------------------
//------------------------------ CLIENTE -------------------------------------------

public class Cliente {
    
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    
    private int dni;
    private String nombreCompleto;
    private String nroCelular;
    
    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    
    //Constructor de la clase cliente 
    public Cliente(String nombreCompleto, int dni, String nroCelular) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.nroCelular = nroCelular ;    
    }
    
    //Se obtienen los datos del cliente
    //Metodo 18
    public String getDatos(){
        String documento= Integer.toString(dni);
        return "Datos cliente:" + "\n" + "Celular: "+ nroCelular +", DNI: "+ documento + ", Nombre: "+ nombreCompleto;
    }  
    
    public String getnombreCompleto(){
        return nombreCompleto;
    }

}
