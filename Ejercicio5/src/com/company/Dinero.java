package com.company;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Dinero {

    private double cantidad;
    private TipoMoneda tMoneda;

    private static List<Moneda> listaMonedas = new ArrayList<>();

    static {
        listaMonedas.add(new Moneda(TipoMoneda.EURO, 2, "€", 1, "EUR"));
        listaMonedas.add(new Moneda(TipoMoneda.DOLAR, 2, "$", 0, "USD"));
        listaMonedas.add(new Moneda(TipoMoneda.LIBRA, 2, "£", 0, "GBP"));
        listaMonedas.add(new Moneda(TipoMoneda.YEN, 0, "¥", 0, "JPY"));
        listaMonedas.add(new Moneda(TipoMoneda.DOLAR_CANADIENSE, 0, "¥", 0, "CAD"));
        listaMonedas.add(new Moneda(TipoMoneda.DOLAR_HONGKONES, 0, "¥", 0, "HKD"));
        listaMonedas.add(new Moneda(TipoMoneda.CORONA_ISLANDESA, 0, "¥", 0, "ISK"));
        listaMonedas.add(new Moneda(TipoMoneda.PESO_FILIPINO, 0, "¥", 0, "PHP"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "DKK"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "HUF"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "CZK"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "AUD"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "RON"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "SEK"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "IDR"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "INR"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "BRL"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "RUB"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "HRK"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "THB"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "CHF"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "SGD"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "PLN"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "BGN"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "TRY"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "CNY"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "NOK"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "NZD"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "ZAR"));
        listaMonedas.add(new Moneda(TipoMoneda, 0, "¥", 0, "MXN"));



        ActualizaListaInternet();
    }

    public static void actualizaCambio(TipoMoneda t, double cambio)
    {
        for (int i = 0; i < listaMonedas.size(); i++)
        {
            if(listaMonedas.get(i).gettMoneda().equals(t)) listaMonedas.get(i).setCambioEuro(cambio);
        }
    }

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
        numDecimales = buscaMoneda(this.tMoneda).getdecimales();
        simbolo = buscaMoneda(this.tMoneda).getSimbolo();
        return String.format("%." + numDecimales + "f", this.cantidad) + simbolo;
    }

    public String toString(TipoMoneda t){
        Dinero moneda = new Dinero(0, t);
        moneda = this.convierteEn(t);
        String simbolo = "";
        int numDecimales = 0;
        numDecimales = buscaMoneda(moneda.tMoneda).getdecimales();
        simbolo = buscaMoneda(moneda.tMoneda).getSimbolo();
        return String.format("%." + numDecimales + "f", moneda.cantidad) + simbolo;
    }

    public double valorEn(TipoMoneda t)
    {
        TipoMoneda divisa;
        divisa = buscaMoneda(this.tMoneda).gettMoneda();
        double euro = this.cantidad / buscaMoneda(divisa).getCambioEuro();
        return euro * buscaMoneda(t).getCambioEuro();
    }

    public Dinero convierteEn(TipoMoneda t)
    {
        TipoMoneda divisa;
        divisa = buscaMoneda(t).gettMoneda();
        Dinero devuelve = new Dinero(valorEn(t), divisa);
        return devuelve;
    }

    private Moneda buscaMoneda(TipoMoneda t)
    {
        for (int i = 0; i < listaMonedas.size(); i++) {
            if(listaMonedas.get(i).gettMoneda().equals(t)) return listaMonedas.get(i);
        }
        return null;
    }

    static void ActualizaListaInternet()
    {
        try
        {
            for (int i = 0; i < listaMonedas.size(); i++) {
                String symbol = listaMonedas.get(i).getCodigo();
                if(symbol!="EUR") {
                    URL url = new URL("https://api.exchangeratesapi.io/latest?symbols=" + symbol);
                    InputStream is = url.openStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String page = br.readLine();
                    br.close();
                    is.close();
                    int pos = page.lastIndexOf(":");
                    double valor = Double.parseDouble(page.substring(pos, page.length() - 2));
                    listaMonedas.get(i).setCambioEuro(valor);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Métodos(operadores)
    Dinero add(Dinero d)
    {
        // Convertir d al tipo de Moneda this
        d = d.convierteEn(this.tMoneda);
        double myCantidad = this.cantidad;
        myCantidad = myCantidad + d.cantidad;
        return new Dinero(myCantidad, this.tMoneda);
    }

    Dinero substract(Dinero d)
    {
        // Convertir d al tipo de Moneda this
        d = d.convierteEn(this.tMoneda);
        double myCantidad = this.cantidad;
        myCantidad = myCantidad - d.cantidad;
        return new Dinero(myCantidad, this.tMoneda);
    }

    Dinero multiply(double d)
    {
        double myCantidad = this.cantidad;
        myCantidad = myCantidad * d;
        return new Dinero(myCantidad, this.tMoneda);
    }

    Dinero divide(double d)
    {
        double myCantidad = this.cantidad;
        if(d != 0) {
            myCantidad = myCantidad / d;
            return new Dinero(myCantidad, this.tMoneda);
        }
        return null;
    }

    Dinero negate()
    {
        return new Dinero(this.cantidad * -1, this.tMoneda);
    }

    boolean equals(Dinero d)
    {
        // Convertir d al tipo de Moneda this
        d = d.convierteEn(this.tMoneda);
        return this.cantidad == d.cantidad;
    }

    int compareTo(Dinero d)
    {
        if(this.equals(d)) return 0;
        // Convertir d al tipo de Moneda this
        d = d.convierteEn(this.tMoneda);
        if(this.cantidad > d.cantidad)
        {
            return 1;
        }
        return -1;
    }

}