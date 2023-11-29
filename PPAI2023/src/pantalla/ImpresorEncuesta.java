package pantalla;

//----------------------------------------------------------------------------------
//----------------------------------------------------------------------------------

import com.aspose.pdf.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

//----------------------------------------------------------------------------------
//------------------------------ CLASE ---------------------------------------------

public class ImpresorEncuesta {
    
    //----------------------------------------------------------------------------------
    //------------------------------ ATRIBUTOS -----------------------------------------
    
    private static ImpresorEncuesta instancia;
    
    int contadorInstanciasImpresas = 0;
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
    String timestamp = dateFormat.format(new Date());
    
    //----------------------------------------------------------------------------------
    //------------------------------ METODOS -------------------------------------------
    
    private ImpresorEncuesta() {
    }
    
    public static ImpresorEncuesta getInstancia(){
        if (instancia == null){
            instancia = new ImpresorEncuesta();
        }
        return instancia;
    }
    
    public void imprimir(String encuesta){
        
        try{
            Document documento = new Document();
            Page pagina = documento.getPages().add();
            //Agregar el texto a la pagina
            pagina.getParagraphs().add(new TextFragment(encuesta));
        
            documento.save("PDF_"+ timestamp + String.valueOf(contadorInstanciasImpresas) + ".pdf");
            
            contadorInstanciasImpresas += 1;
            
            JOptionPane.showMessageDialog(null, "PDF generado correctamente");
            //System.exit(0);
        }catch(Exception E){
            JOptionPane.showMessageDialog(null, "PDF no se pudo generar correctamente");
        }
    }
}
