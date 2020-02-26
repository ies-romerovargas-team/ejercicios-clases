package com.company;

import java.util.concurrent.ExecutionException;

public class Fraccion {
    // Atributos
    int numerador;
    int denominador;

    //Constructores
    public Fraccion(int n, int d)
    {
        if(d==0) {
            throw new RuntimeException("El denominador no puede ser 0");
        } else {
            this.numerador = n;
            this.denominador = d;
        }
    }

    public Fraccion(int n)
    {
        this.numerador = n;
        this.denominador = 1;
    }

    public Fraccion(double d)
    {
        int i = 0;
        while(Math.floor(d)!=(d))
        {
            i++;
            d = d * 10;
        }
        this.numerador = (int) d;
        this.denominador = (int) Math.pow(10, i);
    }

    public int getNumerador()
    {
        return this.numerador;
    }

    public void setNumerador(int n)
    {
        this.numerador = n;
    }

    public int getDenominador()
    {
        return this.denominador;
    }

    public void setDenominador(int d)
    {
        if(d!=0) {
            this.denominador = d;
        }
        else
        {
            throw new RuntimeException("El denominador no puede ser 0");
        }
    }

    private int mcm(int num1, int num2)
    {
        int mcm = 0;
        int a = Math.max(num1, num2);
        int b = Math.min(num1, num2);
        mcm = (a / mcd(a, b)) * b;
        return mcm;
    }

    private int mcd(int num1, int num2)
    {
        int mcd = 0;
        int a = Math.max(num1, num2);
        int b = Math.min(num1, num2);
        do {
            mcd = b;
            b = a % b;
            a = mcd;
        } while(b != 0);
        return mcd;
    }

    public void simplificar()
    {
        int comun = mcd(this.numerador, this.denominador);
        this.numerador = this.numerador/comun;
        this.denominador = this.denominador/comun;
    }

    public Fraccion simplifica(Fraccion f)
    {
        int comun = mcd(f.numerador, f.denominador);
        return new Fraccion(this.numerador/comun, this.denominador/comun);
    }

    public String toString()
    {
        return this.numerador + "/" + this.denominador;
    }

    public void negate()
    {
        this.numerador = this.numerador * (-1);
    }

    public Fraccion add(Fraccion f)
    {
        int sup = this.numerador * f.denominador + f.numerador * this.denominador;
        int inf = this.denominador * f.denominador;
        return simplifica(new Fraccion(sup,inf));
    }

    public Fraccion substract(Fraccion f)
    {
        int sup = this.numerador * f.denominador - f.numerador * this.denominador;
        int inf = this.denominador * f.denominador;
        return simplifica(new Fraccion(sup,inf));
    }

    public Fraccion multiply(Fraccion f)
    {
        int sup = this.numerador * f.numerador;
        int inf = this.denominador * f.denominador;
        return simplifica(new Fraccion(sup,inf));
    }

    public Fraccion divide(Fraccion f)
    {
        int sup = this.numerador * f.denominador;
        int inf = this.denominador * f.numerador;
        return simplifica(new Fraccion(sup,inf));
    }

    public boolean equals(Fraccion f)
    {
        return simplifica(this)==simplifica(f);
    }

    public int compareTo(Fraccion f)
    {
        if(equals(f)) return 0;
        if(f.numerador/f.denominador>this.numerador/this.denominador)
        {
            return -1;
        }
        return 1;
    }
}
