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
    
    //Metodo 25 - Iterator
    public IteradorEncuesta(ArrayList<Object> elementos, ArrayList<Object> filtros) {
        this.elementos = elementos;
        this.filtros = filtros;
    }
    
    //Metodo 28 - Iterator
    @Override
    public Object actual() {
        if(cumpleFiltro(filtros)){
            return elementos.get(indiceActual);
        }else{
            return "";
        }
    }
    //Metodo 27 - Iterator
    @Override
    public Boolean haTerminado() {
        if(this.indiceActual == elementos.size()){
            return true;
        }else{
            return false;
        }
    }
    //Metodo 29 - Iterator
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
    //Metodo 26 - Iterator
    @Override
    public void primero() {
        this.indiceActual = 0;
    }
    //Metodo 30 - Iterator
    @Override
    public void siguiente() {
        this.indiceActual+=1;
    }

}
