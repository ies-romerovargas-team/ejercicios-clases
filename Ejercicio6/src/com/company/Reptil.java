package com.company;

import java.time.LocalDate;

public class Reptil extends Animal
{
    // Atributos
    EspecieReptil especie;
    boolean venenoso;

    // Constructor
    public Reptil(String nombre, EspecieReptil especie, LocalDate fechaNac, double peso, boolean veneno)
    {
        this.nombre = nombre;
        this.especie= especie;
        this.fechaNacimiento = fechaNac;
        this.peso = peso;
        this.venenoso = veneno;
        this.comentarios = "";
    }

    // Propiedades
    public EspecieReptil getEspecie() {
        return especie;
    }

    public boolean getVenenoso() {
        return venenoso;
    }

    @Override
    public String toString() {
        String ficha;
        ficha = "FICHA DE REPTIL\n";
        ficha = ficha + " Nombre: " + this.nombre + "\n";
        ficha = ficha + " Especie: " + this.especie.name() + "\n";
        ficha = ficha + " Fecha de nacimiento: " + this.fechaNacimiento + "\n";
        ficha = ficha + " Peso: " + this.peso + "\n";
        ficha = ficha + " Venenoso: " + this.venenoso + "\n";
        ficha = ficha + " Comentarios: " + this.comentarios + "\n";
        return ficha;
    }
}
