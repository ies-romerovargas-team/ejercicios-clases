package com.company;


import java.security.InvalidParameterException;

public class Longitud {
    //Atributos
    private double cantidad;
    private UnidadLongitud unidad;

    //Constructor
    public Longitud(int value, UnidadLongitud unit)
    {
        cantidad = value;
        unidad = unit;
    }

    public Longitud(double value, UnidadLongitud unit)
    {
        cantidad = value;
        unidad = unit;
    }

    //Propiedades
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public UnidadLongitud getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadLongitud unidad) {
        this.unidad = unidad;
    }

    //Métodos
    public double valorEnMetros()
    {
        /*
        •pulgadas (1 pulgada = 0.0254 metros)
        •pies (1 pie = 0.3048 metros)
        •yardas (1 yarda = 0.9144 metros)
        */
        double factor = 1.0;
        switch (this.unidad)
        {
            case pies:
                factor = 0.3048;
                break;
            case yardas:
                factor = 0.9144;
                break;
            case pulgadas:
                factor = 0.0254;
                break;
        }
        return this.cantidad * factor;
    }

    public String toString()
    {
        String[] abbr = {"m", "in", "ft", "yd"};
        return this.cantidad + " " + abbr[this.unidad.ordinal()];
    }

    public void add(Longitud l2)
    {
        if(this.unidad.equals(l2.unidad))
        {
            this.cantidad = this.cantidad + l2.cantidad;
        } else {
            this.cantidad = valorEnMetros() + l2.valorEnMetros();
        }
    }

    public void substract(Longitud l2)
    {
        if(this.unidad.equals(l2.unidad))
        {
            this.cantidad = this.cantidad - l2.cantidad;
        } else {
            this.cantidad = valorEnMetros() - l2.valorEnMetros();
        }
    }

    public void multiply(double n)
    {
        this.cantidad = this.cantidad * n;
    }

    public void divide(double n)
    {
        if (n==0) throw new InvalidParameterException("Error: División por cero");
        this.cantidad = this.cantidad / n;
    }

    public boolean equals(Longitud l2)
    {
        return valorEnMetros() == l2.valorEnMetros();
    }

    public int compareTo(Longitud l2)
    {
        if(equals(l2)) return 0;
        if (valorEnMetros() < l2.valorEnMetros())
        {
            return -1;
        }
        return 1;
    }

}
