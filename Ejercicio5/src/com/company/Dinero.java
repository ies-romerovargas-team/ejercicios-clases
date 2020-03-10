package com.company;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Dinero {

    private double cantidad;
    private TipoMoneda tMoneda;

    private static List<Moneda> listaMonedas = new ArrayList<>();

    static {
        listaMonedas.add(new Moneda(TipoMoneda.EURO, 2, "€", 1, "EUR"));
        listaMonedas.add(new Moneda(TipoMoneda.DOLAR, 2, "$", 1, "USD"));
        listaMonedas.add(new Moneda(TipoMoneda.LIBRA, 2, "£", 1, "GBP"));
        listaMonedas.add(new Moneda(TipoMoneda.YEN, 0, "¥", 1, "JPY"));
        listaMonedas.add(new Moneda(TipoMoneda.DOLAR_CANADIENSE, 2, "C$", 1, "CAD"));
        listaMonedas.add(new Moneda(TipoMoneda.DOLAR_HONGKONES, 2, "HK$", 1, "HKD"));
        listaMonedas.add(new Moneda(TipoMoneda.CORONA_ISLANDESA, 2, "kr", 1, "ISK"));
        listaMonedas.add(new Moneda(TipoMoneda.PESO_FILIPINO, 2, "₱", 1, "PHP"));
        listaMonedas.add(new Moneda(TipoMoneda.CORONA_DANESA, 2, "kr", 1, "DKK"));
        listaMonedas.add(new Moneda(TipoMoneda.FORINTO_HUNGARO, 2, "Ft", 1, "HUF"));
        listaMonedas.add(new Moneda(TipoMoneda.CORONA_CHECA, 2, "Kč", 1, "CZK"));
        listaMonedas.add(new Moneda(TipoMoneda.CHELIN_AUSTRIACO, 2, "öS", 1, "AUD"));
        listaMonedas.add(new Moneda(TipoMoneda.LEU, 2, "LEU", 1, "RON"));
        listaMonedas.add(new Moneda(TipoMoneda.CORONA_SUECA, 2, "kr", 1, "SEK"));
        listaMonedas.add(new Moneda(TipoMoneda.RUPIA_INDONESIA, 2, "Rp", 1, "IDR"));
        listaMonedas.add(new Moneda(TipoMoneda.RUPIA_INDIA, 2, "₹", 1, "INR"));
        listaMonedas.add(new Moneda(TipoMoneda.REAL_BRASILENO, 2, "R$", 1, "BRL"));
        listaMonedas.add(new Moneda(TipoMoneda.RUBLO, 2, "руб", 1, "RUB"));
        listaMonedas.add(new Moneda(TipoMoneda.KUNA, 2, "kn", 1, "HRK"));
        listaMonedas.add(new Moneda(TipoMoneda.BAT, 2, "฿", 1, "THB"));
        listaMonedas.add(new Moneda(TipoMoneda.FRANCO_SUIZO, 2, "Fr", 1, "CHF"));
        listaMonedas.add(new Moneda(TipoMoneda.DOLAR_SINGAPURENSE, 2, "S$", 1, "SGD"));
        listaMonedas.add(new Moneda(TipoMoneda.ESLOTI, 2, "zł", 1, "PLN"));
        listaMonedas.add(new Moneda(TipoMoneda.LEV, 2, "Лв", 1, "BGN"));
        listaMonedas.add(new Moneda(TipoMoneda.LIRA_TURCA, 2, "TL", 1, "TRY"));
        listaMonedas.add(new Moneda(TipoMoneda.YUAN, 2, "¥", 1, "CNY"));
        listaMonedas.add(new Moneda(TipoMoneda.CORONA_NORUEGA, 2, "kr", 1, "NOK"));
        listaMonedas.add(new Moneda(TipoMoneda.DOLAR_NEOZELANDES, 2, "NZ$", 1, "NZD"));
        listaMonedas.add(new Moneda(TipoMoneda.RAND, 2, "ZAR", 1, "ZAR"));
        listaMonedas.add(new Moneda(TipoMoneda.PESO_MEXICANO, 2, "$", 1, "MXN"));
        listaMonedas.add(new Moneda(TipoMoneda.SEQUEL, 2, "₪", 1, "ILS"));
        listaMonedas.add(new Moneda(TipoMoneda.WON, 2, "₩", 1, "KRW"));
        listaMonedas.add(new Moneda(TipoMoneda.RINGIT, 2, "RM", 1, "MYR"));

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
        return String.format("%." + numDecimales + "f", this.cantidad) + " " + simbolo;
    }

    public String toString(TipoMoneda t){
        Dinero moneda = this.convierteEn(t);
        String simbolo = "";
        int numDecimales = 0;
        numDecimales = buscaMoneda(moneda.tMoneda).getdecimales();
        simbolo = buscaMoneda(moneda.tMoneda).getSimbolo();
        return String.format("%." + numDecimales + "f", moneda.cantidad) + " " + simbolo;
    }

    public String toRate()
    {
        String tabla = "";
        for (int i = 0; i < listaMonedas.size(); i++) {
            tabla = tabla + toString(listaMonedas.get(i).gettMoneda()) + " \n";
        }
        return tabla;
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
            System.out.print("Descargando cotizaciones desde exchangerateapi.io ");
            for (int i = 0; i < listaMonedas.size(); i++) {
                String symbol = listaMonedas.get(i).getCodigo();
                if(symbol!="EUR") {
                    System.out.print(".");
                    URL url = new URL("https://api.exchangeratesapi.io/latest?symbols=" + symbol);
                    InputStream is = url.openStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String page = br.readLine();
                    br.close();
                    is.close();
                    int pos = page.indexOf(symbol);
                    int pos2 = page.indexOf("}", pos);
                    double valor = Double.parseDouble(page.substring(pos + 5, pos2));
                    listaMonedas.get(i).setCambioEuro(valor);
                }
            }
            System.out.println();
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