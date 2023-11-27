/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package datos;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface IIterador {
     Object actual();
     Boolean haTerminado();
     Boolean cumpleFiltro(ArrayList<Object> filtros);
     void primero();
     void siguiente();
    
}
