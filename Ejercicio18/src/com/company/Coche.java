package com.company;

import java.security.InvalidParameterException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Coche {
    private final String matricula;
    private final LocalDate fechaMatriculacion;
    private final String marca;
    private final String modelo;
    private double precio;

    public Coche(String mat, LocalDate fmat, String mar, String mod, double pre){

            if (validamatricula(mat)) {
                this.matricula = mat;
                this.fechaMatriculacion = fmat;
                this.marca = mar;
                this.modelo = mod;
                if (pre < 500) pre = 500;
                this.precio = pre;
            } else throw new InvalidParameterException("Parámetros Inválidos");
    }

    private boolean validamatricula(String mat) {
        // 4 digitos, 3 letras may no vocal
        if(mat.length()!=7) return false;
        for (int i = 0; i < 4; i++) {
            if(!Character.isDigit(mat.charAt(i))) return false;
        }
        String permitidas = "BCDFGHJKLMNPRSTVWXYZ";
        for (int i = 4; i < 7; i++) {
            if(!permitidas.contains(String.valueOf(mat.charAt(i)))) return false;
        }
        return true;
    }

    @Override
    public String toString(){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = this.fechaMatriculacion.format(formato);
        String mar = this.marca + "           ";
        String mod = this.modelo + "           ";
        DecimalFormat df = new DecimalFormat("0.00 €");
        String formateada = "     " + df.format(this.precio);
        return this.matricula + "   " + mar.substring(0, 12) + "\t" + mod.substring(0, 12) + "\t" + fecha + " " +  "\t" + formateada.substring(formateada.length()-12,formateada.length());
    }

    public double getPrecio() {
        return precio;
    }

    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setPrecio(double pre) {
        if (pre < 500) pre = 500;
        precio = pre;
    }
}

