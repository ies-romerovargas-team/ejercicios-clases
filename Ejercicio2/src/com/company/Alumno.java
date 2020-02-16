package com.company;

public class Alumno {
    /*public String nombre;
    public int edad;
    public double calificacion;*/

    //Atributos
    String nombre;
    int edad;
    double calificacion;

    //Constructor
    public Alumno(Alumno alumno){
        setNombre(this.nombre);
        setEdad(this.edad);
        setCalificacion(this.calificacion);
    }

    //Propiedades
    public String getNombre(){return this.nombre}
    public int getEdad(){return this.edad}
    public double getCalificacion() {return this.calificacion}

    //Metodos
    public void setNombre(String a)
    {
        if(a!="")
        {
            this.nombre = a;
        }
    }

    public void setEdad(int a)
    {
        if(a>17 && a<100)
        {
            this.edad = a;
        }
    }

    public void setCalificacion(double a)
    {
        if(a>=0 && a<=10)
        {
            this.calificacion = a;
        }
    }

    public String toString()
    {
        String a = this.nombre + " " + this.edad + " " + this.calificacion;
        return a;
    }
}
