package com.company;

import java.security.InvalidParameterException;

public class Hora {
    private int segundos;

    public Hora(int s) {
        if (s >= 0) segundos = s;
        else throw new InvalidParameterException("Los segundos no pueden ser negativos");
    }

    public Hora(int h, int m, int s) {
        try {
            boolean continuar = true;
            if (s < 0 || s > 59) continuar = false;
            if (m < 0 || m > 59) continuar = false;
            if (continuar) {
                segundos = h * 3600 + m * 60 + s;
            } else {
                throw new Exception("Parámetros incorrectos");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getSegundos() {
        // return segundos - ((getHoras() * 3600) + (getMinutos() * 60));
        return segundos % 60;
    }

    public int getMinutos() {
        // return (segundos - (3600 * getHoras())) / 60;
        return (segundos / 60) % 60;
    }

    public int getHoras() {
        return segundos / 3600;
    }

    public void setSegundos(int seg) {

        if (seg >= 0 && seg <= 59) {
            int h = getHoras();
            int m = getMinutos();
            segundos = 3600 * h + 60 * m + seg;
        } else throw new InvalidParameterException("Valor no permitido");
    }

    public void setMinutos(int min) {
            if (min >= 0 && min <= 59) {
                int h = getHoras();
                int s = getSegundos();
                segundos = 3600 * h + 60 * min + s;
            } else throw new InvalidParameterException("Valor no permitido");
    }

    public void setHoras(int horas) {
        int h = horas;
        int m = getMinutos();
        int s = getSegundos();
        s = 3600 * h + 60 * m + s;
        segundos = s;
    }

    // metodos

    // todo comprobar que las horas no sean negativas

    public void sumaHoras(int horas) {
        segundos = segundos + 3600 * horas;
    }

    public void sumaMinutos(int minutos) {
        segundos = segundos + 60 * minutos;
    }

    public void sumaSegundos(int segundos) {
        this.segundos = this.segundos + segundos;
    }

    // Metodos operadores

    public void add(Hora hora) {
        segundos = segundos + hora.segundos;
    }

    public void substract(Hora hora) {
        try {
            if (segundos > hora.segundos) {
                segundos = segundos - hora.segundos;
            } else throw new Exception("Valor fuera de rango");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        String min = String.valueOf(getMinutos());
        String seg = String.valueOf(getSegundos());
        if (min.length() == 1) min = "0" + min;
        if (seg.length() == 1) seg = "0" + seg;
        return getHoras() + ":" + min + ":" + seg;
    }
}
