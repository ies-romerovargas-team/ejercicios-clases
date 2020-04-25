package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tribunal {

    List<Profesor> listaProfesores;

    public Tribunal(String filename)
    {
        listaProfesores = new ArrayList<>();
        try
        {
            FileInputStream fis = new FileInputStream(filename);
            DataInputStream dis = new DataInputStream(fis);

            while(dis.available() > 0)
            {
                TipoGenero genero;
                String nombre = dis.readUTF();
                String dni = dis.readUTF();
                int gen = dis.readInt();
                if (gen == 0) {
                    genero = TipoGenero.Hombre;
                } else genero = TipoGenero.Mujer;
                Profesor profe = new Profesor(nombre,dni, genero);
                listaProfesores.add(profe);
            }
            dis.close();
            fis.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void eligeTribunal()
    {
        // Imprimimos lista
        System.out.println("LISTADO PROFESORES. SORTEO");
        for (int i = 0; i < listaProfesores.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(listaProfesores.get(i).toString());
        }
        // Realizamos sorteo
        Random r = new Random();
        // Y el agraciado es:
        int numero = r.nextInt(listaProfesores.size() - 1) + 1;
        System.out.println();
        System.out.println("NÚMERO: " + numero);
        // Recorremos la lista de manera secuencial para extraer dos hombres y dos mujeres
        List<Profesor> agraciados = new ArrayList<>();
        int hombres = 0;
        int mujeres = 0;
        numero--;
        while(agraciados.size()<4)
        {
            if(numero < listaProfesores.size())
            {
                if(listaProfesores.get(numero).getGenero() == TipoGenero.Hombre && hombres < 2)
                {
                    agraciados.add(listaProfesores.get(numero));
                    hombres++;
                }
                if(listaProfesores.get(numero).getGenero() == TipoGenero.Mujer && mujeres < 2)
                {
                    agraciados.add(listaProfesores.get(numero));
                    mujeres++;
                }
            }
            else
            {
                numero = -1;
            }
            numero++;
        }
        // Mostramos resultados
        System.out.println();
        System.out.println("Y los agraciados son:");
        for (int i = 0; i < agraciados.size(); i++) {
            System.out.println(agraciados.get(i).toString());
        }
    }

    public void eligeTribunalPro(){
        System.out.println();
        System.out.println("SORTEO PRO");
        System.out.println();
        List<Profesor> listaOrdenada = new ArrayList<>();
        List<Profesor> listaAuxiliar = new ArrayList<>();
        // Extraemos DNI de la lista
        for(int i=0; i < listaProfesores.size(); i++)
        {
            // modificamos los dni
            String dni = listaProfesores.get(i).getDni();
            StringBuilder reordena = new StringBuilder();
            // reordenamos numeracion
            for (int j = 7; j >= 0; j--) {
                reordena.append(dni.charAt(j));
            }
            reordena.append(dni.charAt(8));
            Profesor nuevo = new Profesor(listaProfesores.get(i).getNombre(), reordena.toString(), listaProfesores.get(i).getGenero());
            // lista paralela con idéntica ordenación pero con los dni's correctos
            Profesor auxiliar = new Profesor(listaProfesores.get(i).getNombre(), listaProfesores.get(i).getDni(), listaProfesores.get(i).getGenero());
            // recorremos la listaOrdenada para ver donde lo metemos
            int indice = 0;
            while(indice < listaOrdenada.size())
            {
                if(reordena.toString().compareTo(listaOrdenada.get(indice).getDni()) < 0)
                {
                    break;
                }
                else indice++;
            }
            listaOrdenada.add(indice, nuevo);
            listaAuxiliar.add(indice, auxiliar);
        }

        // Imprimimos lista
        System.out.println();
        System.out.println("LISTADO PROFESORES. SORTEO");
        for (int i = 0; i < listaAuxiliar.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.print(listaOrdenada.get(i).getDni()+ ". ");
            System.out.println(listaAuxiliar.get(i).toString());
        }
        // Realizamos sorteo
        Random r = new Random();
        // Y el agraciado es:
        int numero = r.nextInt(listaAuxiliar.size() - 1) + 1;
        System.out.println();
        System.out.println("NÚMERO: " + numero);
        // Recorremos la lista de manera secuencial para extraer dos hombres y dos mujeres
        List<Profesor> agraciados = new ArrayList<>();
        int hombres = 0;
        int mujeres = 0;
        numero--;
        while(agraciados.size()<4)
        {
            if(numero < listaAuxiliar.size())
            {
                if(listaAuxiliar.get(numero).getGenero() == TipoGenero.Hombre && hombres < 2)
                {
                    agraciados.add(listaAuxiliar.get(numero));
                    hombres++;
                }
                if(listaAuxiliar.get(numero).getGenero() == TipoGenero.Mujer && mujeres < 2)
                {
                    agraciados.add(listaAuxiliar.get(numero));
                    mujeres++;
                }
            }
            else
            {
                numero = -1;
            }
            numero++;
        }
        // Mostramos resultados
        System.out.println();
        System.out.println("Y los agraciados son:");
        for (int i = 0; i < agraciados.size(); i++) {
            System.out.println(agraciados.get(i).toString());
        }
    }
}
