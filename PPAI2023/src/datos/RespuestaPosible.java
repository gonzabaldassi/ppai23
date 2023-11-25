package datos;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class RespuestaPosible {
    
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    
    private String descripcion;
    private int valor;
    
    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    
    //Constructor de la clase RespuestaPosible
    public RespuestaPosible(String descripcion, int valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }
    
    //Se retorna la descripcion de la respuesta
    //Metodo 25
    public String getDescripcionRta(){
        return descripcion; 
    }
    
}
