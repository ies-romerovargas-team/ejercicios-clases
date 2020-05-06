package com.company;

public class Pokemon {

    // todo poner privates
    int id;
    String nombre;
    String tipo;
    int ataque;
    int defensa;
    int vida;
    int ataqueespecial;
    int defensaespecial;
    int velocidad;
    String habilidad;
    boolean capturado;

    //Reset
    public static final String ANSI_RESET = "\u001B[0m";
    //Colores de letra
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Pokemon(int id,String nombre, String tipo, int ataque, int defensa, int vida, int ataqueespecial, int defensaespecial, int velocidad, String habilidad, boolean capturado)
    {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.vida = vida;
        this.ataqueespecial = ataqueespecial;
        this.defensaespecial = defensaespecial;
        this.velocidad = velocidad;
        this.habilidad = habilidad;
        this.capturado = capturado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public int getDefensaespecial() {
        return defensaespecial;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getAtaqueespecial() {
        return ataqueespecial;
    }

    public boolean getCapturado(){
        return capturado;
    }

    public void setCapturado(boolean capturado){
        this.capturado = capturado;
    }

    public String toString()
    {
        String string = "";
        string = "Id: " + ANSI_BLUE + this.id + ANSI_RESET +  " ";
        string = string + "Nombre: " + ANSI_BLUE + this.nombre + ANSI_RESET + "\n";
        string = string + "Tipo: " + ANSI_BLUE + this.tipo + ANSI_RESET +  "\n";
        string = string + "Ataque: " + ANSI_BLUE  + this.ataque + ANSI_RESET +  " ";
        string = string + "Defensa: " + ANSI_BLUE + this.defensa + ANSI_RESET +  "\n";
        string = string + "Vida: " + ANSI_BLUE + this.vida + ANSI_RESET +  "\n";
        string = string + "Ataque Especial: " + ANSI_BLUE + this.ataqueespecial + ANSI_RESET +  " ";
        string = string + "Defensa Especial: " + ANSI_BLUE + this.defensaespecial + ANSI_RESET +  "\n";
        string = string + "Velocidad: " + ANSI_BLUE + this.velocidad + ANSI_RESET +  " ";
        string = string + "Habilidad: " + ANSI_BLUE + this.habilidad + ANSI_RESET +  "\n";
        if (capturado) string = string  + ANSI_RED + "Capturado" + ANSI_RESET;
        else
            string = string + ANSI_GREEN + "No capturado" +  ANSI_RESET;
        return string;
    }
}
