/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package datos;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class IteradorEncuesta implements IIterador{
    private ArrayList<Object> elementos;
    private ArrayList<Object> filtros;
    private int indiceActual;

    public IteradorEncuesta(ArrayList<Object> elementos, ArrayList<Object> filtros) {
        this.elementos = elementos;
        this.filtros = filtros;
    }
    
    
    @Override
    public Object actual() {
        if(cumpleFiltro(filtros)){
            return elementos.get(indiceActual);
        }else{
            return "";
        }
    }

    @Override
    public Boolean haTerminado() {
        if(this.indiceActual == elementos.size()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean cumpleFiltro(ArrayList<Object> filtros) {
        Encuesta encuesta = (Encuesta)elementos.get(indiceActual);
        Date fechaEncuesta = (Date)filtros.get(0) ;
        
        if(encuesta.esEncuestaDeCliente(fechaEncuesta)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void primero() {
        this.indiceActual = 0;
    }

    @Override
    public void siguiente() {
        this.indiceActual+=1;
    }

}
