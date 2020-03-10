package com.company;

public class Moneda {

    //Atributos
    private TipoMoneda tMoneda;
    private int decimales;
    private String simbolo;
    private double cambioEuro;
    private String codigo;

    public Moneda(TipoMoneda din, int numDec, String symb, double valor, String codigo)
    {
        boolean error = false;
        if(numDec < 0 || numDec > 4) error = true;
        if(symb.equals("")) error = true;
        if(valor < 0) error = true;
        if(!error) {
            this.tMoneda = din;
            this.decimales = numDec;
            this.simbolo = symb;
            this.cambioEuro = valor;
            this.codigo = codigo;
        }
        else
        {
            throw new RuntimeException("No puede crearse la Moneda debido a un error en los datos recibidos");
        }
    }

    public TipoMoneda gettMoneda() {
        return tMoneda;
    }

    public int getdecimales() {
        return decimales;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getCodigo() { return codigo; }

    public double getCambioEuro() {
        return cambioEuro;
    }

    public void setCambioEuro(double cambioEuro) {
        if(cambioEuro > 0) this.cambioEuro = cambioEuro;
    }

}
