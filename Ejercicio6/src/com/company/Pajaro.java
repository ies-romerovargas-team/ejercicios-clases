package com.company;

import java.time.LocalDate;

public class Pajaro extends Animal
{

    // Atributos
    EspeciePajaro especie;
    boolean cantor;

    // Constructor
    public Pajaro(String nombre, EspeciePajaro especie, LocalDate fechaNac, double peso, boolean cantante)
    {
        this.nombre = nombre;
        this.especie = especie;
        this.fechaNacimiento = fechaNac;
        this.peso = peso;
        this.cantor = cantante;
        this.comentarios = "";
    }

    // Propiedades
    public EspeciePajaro getEspecie() {
        return especie;
    }

    public boolean getCantor() {
        return cantor;
    }

    @Override
    public String toString() {
        String ficha;
        ficha = "FICHA DE PÁJARO\n";
        ficha = ficha + " Nombre: " + this.nombre + "\n";
        ficha = ficha + " Especie: " + this.especie.name() + "\n";
        ficha = ficha + " Fecha de nacimiento: " + this.fechaNacimiento + "\n";
        ficha = ficha + " Peso: " + this.peso + "\n";
        ficha = ficha + " Cantante: " + this.cantor + "\n";
        ficha = ficha + " Comentarios: " + this.comentarios + "\n";
        return ficha;
    }
}