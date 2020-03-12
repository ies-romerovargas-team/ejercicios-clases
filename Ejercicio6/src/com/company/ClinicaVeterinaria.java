package com.company;

import java.util.ArrayList;
import java.util.List;

public class ClinicaVeterinaria {
    //Atributos
    private List<Animal> listaAnimales;

    //Constructor
    public ClinicaVeterinaria() {
        listaAnimales = new ArrayList<>();
    }

    //Metodos
    public void insertaAnimal(Animal bicho)
    {
        listaAnimales.add(bicho);
    }

    public Animal buscaAnimal (String nombreBicho)
    {
        for (int i = 0; i < listaAnimales.size(); i++) {
            if(nombreBicho.equals(listaAnimales.get(i).nombre))
            {
                return listaAnimales.get(i);
            }
        }
        return null;
    }

    public void modificaComentarioAnimal(String nombreBicho, String newComment)
    {
        Animal bicho = buscaAnimal(nombreBicho);
        if(bicho != null)
        {
            bicho.setComentarios(newComment);
        }
        else
        {
            System.out.println("Error: bicho no encontrado");
        }
    }

    public String toString()
    {
        String lista = "";
        for (int i = 0; i < listaAnimales.size(); i++) {
            lista = lista + listaAnimales.get(i).toString() + "\n";
        }
        return lista;
    }

    public List<Animal> getListaAnimales() {
        return listaAnimales;
    }
}