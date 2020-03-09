package com.company;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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
        listaMonedas.add(new Moneda(TipoMoneda.EURO, 2, "€", 1, "EUR"));
    }

    static void actualizaCambio(TipoMoneda t, double cambio)
    {
        for (int i = 0; i < listaMonedas.size(); i++)
        {
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

    private void ActualizaListaInternet()
    {
        try
        {
            URL url = new URL("https://api.exchangeratesapi.io/latest?symbols=USD");
            InputStream is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String page = br.readLine();
            br.close();
            is.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
