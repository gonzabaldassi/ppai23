/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package datos;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public interface IAgregado {
    IIterador crearIterador(ArrayList<Object> elementos, ArrayList<Object> filtros);
}
