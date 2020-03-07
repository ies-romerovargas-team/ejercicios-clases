package com.company;

import java.util.ArrayList;
import java.util.List;

public class Dinero {

    double cantidad;
    TipoMoneda tMoneda;

    public Dinero(double c, TipoMoneda t)
    {
        this.cantidad = c;
        this.tMoneda = t;
    }

    public Dinero(int c, TipoMoneda t)
    {
        this.cantidad = c;
        this.tMoneda = t;
    }

    public TipoMoneda gettMoneda() {
        return tMoneda;
    }

    public void settMoneda(TipoMoneda tMoneda) {
        this.tMoneda = tMoneda;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String toString(){
        String simbolo = "";
        int numDecimales = 0;
        for (int i = 0; i < listaMonedas.size(); i++) {
            if(listaMonedas.get(i).tMoneda.equals(this.tMoneda)) {
                simbolo = listaMonedas.get(i).simbolo;
                numDecimales = listaMonedas.get(i).decimales;
            }
        }
        return Math.round(this.cantidad)  + " " + this.tMoneda.name() + simbolo;
    }

    public double valorEn(TipoMoneda t)
    {
        return 0.2;
    }

    static List<Moneda> listaMonedas = new ArrayList<>();

    static {
        listaMonedas.add(new Moneda(TipoMoneda.EURO, 2, "€", 1));
    }

    static void actualizaCambio(TipoMoneda t, double cambio)
    {
        for (int i = 0; i < listaMonedas.size(); i++) {
            if(listaMonedas.get(i).tMoneda.equals(t)) listaMonedas.get(i).cambioEuro = cambio;
        }
    }

    private Moneda buscaMoneda(TipoMoneda t)
    {
        for (int i = 0; i < listaMonedas.size(); i++) {
            if(listaMonedas.get(i).tMoneda.equals(t)) return listaMonedas.get(i);
        }
        return null;
    }
}
