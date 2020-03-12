package com.company;

import java.time.LocalDate;

public abstract class Animal {

    // Atributos
    protected String nombre;
    protected LocalDate fechaNacimiento;
    protected double peso;
    protected String comentarios;

    //Propiedades
    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getPeso() {
        return peso;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    //Metodos
    @Override
    public abstract String toString();
}
