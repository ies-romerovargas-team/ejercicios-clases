package com.company;

public class Profesor {
    String nombre;
    String dni;
    TipoGenero genero;

    //Constructor

    public Profesor(String name, String id, TipoGenero gender)
    {
        try {
            if (name.length() > 0 && id.length() == 9 && Character.isLetter(id.charAt(8))) {
                for (int i = 0; i < 8; i++) {
                    if (Character.isLetter(id.charAt(i))) {
                        new Exception("DNI no valido");
                    }
                }
                if (gender == null) new Exception("Error: falta Genero");
            }
            else new Exception("Error: Falta nombre/dni");

            this.dni = id;
            this.nombre = name;
            this.genero = gender;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //Propiedades
    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public TipoGenero getGenero()
    {
        return genero;
    }

    public void setNombre(String name)
    {
        if (name.length() > 0)
        {
            this.nombre = name;
        }
    }

    public void setDni(String id)
    {
        if(id.length() == 9 && Character.isLetter(id.charAt(8)))
        {
            boolean valido = true;
            for (int i = 0; i < 8; i++) {
                if (Character.isLetter(id.charAt(i))) {
                    valido = false;
                }
            }
            if(valido) this.dni = id;
        }
    }

    public void setGenero(TipoGenero gender)
    {
        this.genero = gender;
    }

    public String toString()
    {
        String tratamiento;
        if(this.genero.equals(TipoGenero.Hombre))
        {
            tratamiento = "D. ";
        }
        else {
            tratamiento = "Dª. ";
        }

        return tratamiento + this.nombre.toUpperCase() + " (" + this.dni + ")";
    }
}
