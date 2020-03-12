package com.company;

import java.time.LocalDate;

public class Perro extends Animal
{
    // Atributos
    RazaPerro raza;
    String microchip;

    // Constructor
    public Perro(String nombre, RazaPerro raza, LocalDate fechaNac, double peso, String microchip)
    {
        this.nombre = nombre;
        this.raza = raza;
        this.fechaNacimiento = fechaNac;
        this.peso = peso;
        this.microchip = microchip;
        this.comentarios = "";
    }

    // Propiedades
    public RazaPerro getRaza() {
        return raza;
    }

    public String getMicrochip() {
        return microchip;
    }

    @Override
    public String toString() {
        String ficha;
        ficha = "FICHA DE PERRO\n";
        ficha = ficha + " Nombre: " + this.nombre + "\n";
        ficha = ficha + " Raza: " + this.raza.name() + "\n";
        ficha = ficha + " Fecha de nacimiento: " + this.fechaNacimiento + "\n";
        ficha = ficha + " Peso: " + this.peso + "\n";
        ficha = ficha + " Microchip: " + this.microchip + "\n";
        ficha = ficha + " Comentarios: " + this.comentarios + "\n";
        return ficha;
    }
}
