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
public class IteradorRespuestaCliente implements IIterador{
    private ArrayList<Object> elementos;
    private int indiceActual;

    public IteradorRespuestaCliente(ArrayList<Object> elementos) {
        this.elementos = elementos;
    }
    
    
    
    @Override
    public Object actual() {
        return elementos.get(indiceActual);
    }

    @Override
    public Boolean haTerminado() {
        if(indiceActual == elementos.size()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean cumpleFiltro(ArrayList<Object> filtros) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void primero() {
        this.indiceActual=0;
    }

    @Override
    public void siguiente() {
        this.indiceActual+=1;
    }

}
