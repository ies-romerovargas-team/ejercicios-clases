package com.company;

public class Moneda {

    //Atributos
    TipoMoneda tMoneda;
    int decimales;
    String simbolo;
    double cambioEuro;

    public Moneda(TipoMoneda din, int numDec, String symb, double valor)
    {
        boolean error = false;
        if(numDec<0 || numDec>4) error = true;
        if(symb.equals("")) error = true;
        if(valor<0) error = true;
        if(!error) {
            this.tMoneda = din;
            this.decimales = numDec;
            this.simbolo = symb;
            this.cambioEuro = valor;
        }
        else
        {
            throw new RuntimeException("No puede crearse la Moneda debido a un error en los datos recibidos");
        }
    }

    public TipoMoneda gettMoneda() {
        return tMoneda;
    }

    public double getdecimales() {
        return decimales;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public double getCambioEuro() {
        return cambioEuro;
    }

    public void setCambioEuro(double cambioEuro) {
        if(cambioEuro > 0) this.cambioEuro = cambioEuro;
    }



}
