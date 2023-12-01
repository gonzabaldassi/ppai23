/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package datos;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Usuario
 */
public class IteradorLlamada implements IIterador {

    private ArrayList<Object> elementos;
    private ArrayList<Object> filtros;
    private int indiceActual;

    //Metodo 3 - Iterator
    public IteradorLlamada(ArrayList<Object> elementos, ArrayList<Object> filtros) {
        this.elementos = elementos;
        this.filtros = filtros;
    }
    //Metodo 6 - Iterator
    @Override
    public Object actual() {
        if (cumpleFiltro(filtros)) {
            return elementos.get(indiceActual);
        } else {
            return "";
        }
    }
    //Metodo 5 - Iterator
    @Override
    public Boolean haTerminado() {
        if (indiceActual == elementos.size()) {
            return true;
        } else {
            return false;
        }
    }
    //Metodo 7 - Iterator
    @Override
    public Boolean cumpleFiltro(ArrayList<Object> filtros) {

        Llamada llamada = (Llamada)elementos.get(indiceActual) ;
        Date fechaInicio = (Date)filtros.get(0) ;
        Date fechaFin = (Date)filtros.get(1) ;
        if (llamada.tieneEncuestaRespondida()) {
            if (llamada.esDePeriodo(fechaInicio, fechaFin)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    //Metodo 4 - Iterator
    @Override
    public void primero() {
        this.indiceActual = 0;
    }
    //Metodo 10 - Iterator
    @Override
    public void siguiente() {
        this.indiceActual += 1;
    }
}
