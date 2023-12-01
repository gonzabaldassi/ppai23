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
    //Metodo 15 - Iterator
    public IteradorRespuestaCliente(ArrayList<Object> elementos) {
        this.elementos = elementos;
    }
    
    
    //Metodo 18 - Iterator
    @Override
    public Object actual() {
        return elementos.get(indiceActual);
    }
    //Metodo 17 - Iterator
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
    //Metodo 16 - Iterator
    @Override
    public void primero() {
        this.indiceActual=0;
    }
    //Metodo 21 - Iterator
    @Override
    public void siguiente() {
        this.indiceActual+=1;
    }

}
