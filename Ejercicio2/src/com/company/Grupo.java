package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Grupo {
    //Atributos
    List<Alumno> listaAlumnos = new ArrayList<>();

    //Constructor
    public Grupo(){}

    //Metodos
    public void insertaAlumnoLista(Alumno a)
    {
        listaAlumnos.add(a);
    }

    public void insertaAlumnoLista(String nombre, int edad, double calificacion)
    {
        Alumno a = new Alumno();
        a.setNombre(nombre);
        a.setEdad(edad);
        a.setCalificacion(calificacion);
        insertaAlumnoLista(a);
    }

    public String toString()
    {
        String lista = "";
        for (int i = 0; i < listaAlumnos.size(); i++) {
            lista = lista + listaAlumnos.get(i).toString() + "\n";
        }
        return lista;
    }

    public void escribeFicheroBinario(String filename)
    {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            DataOutputStream dos = new DataOutputStream(fos);
            for (int i = 0; i < listaAlumnos.size() ; i++) {
                dos.writeUTF(listaAlumnos.get(i).nombre);
                dos.writeInt(listaAlumnos.get(i).edad);
                dos.writeDouble(listaAlumnos.get(i).calificacion);
            }
            dos.close();
            fos.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void leeFicheroBinario(String filename)
    {
        listaAlumnos.clear();
        List<Integer> lista = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filename);
            DataInputStream dis = new DataInputStream(fis);
            while (dis.available() > 0)
            {
                Alumno alumno = new Alumno();
                alumno.nombre = dis.readUTF();
                alumno.edad = dis.readInt();
                alumno.calificacion = dis.readDouble();
                listaAlumnos.add(alumno);
            }
            dis.close();
            fis.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void escribeFicheroTexto(String filename)
    {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaAlumnos.size(); i++) {
                Alumno alumno = new Alumno();
                alumno = listaAlumnos.get(i);
                bw.write(alumno.nombre + System.lineSeparator());
                bw.write(alumno.edad + System.lineSeparator());
                bw.write(alumno.calificacion + System.lineSeparator());
            }
            bw.close();
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void leeFicheroTexto(String filename)
    {
        if(Files.exists(Path.of(filename)))
        {
            listaAlumnos.clear();
            String texto;
            try {
                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);
                texto = br.readLine();
                while(texto != null)
                {
                    Alumno alumno = new Alumno();
                    alumno.nombre = texto;
                    alumno.edad = Integer.parseInt(br.readLine());
                    alumno.calificacion = Double.parseDouble(br.readLine());
                    listaAlumnos.add(alumno);
                    texto = br.readLine();
                }
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }

        }
        else
        {
            System.out.println("El fichero " + filename + " no existe");
        }
    }

    public void leeFicheroCSV(String filename)
    {
        if(Files.exists(Path.of(filename)))
        {
            listaAlumnos.clear();
            try {
                FileReader fr = new FileReader(filename);
                BufferedReader br = new BufferedReader(fr);
                String texto = br.readLine();
                while(texto != null)
                {
                    Alumno alumno = new Alumno();
                    String[] array = texto.split(";");
                    alumno.nombre = array[0];
                    alumno.edad = Integer.parseInt(array[1]);
                    alumno.calificacion = Double.parseDouble(array[2]);
                    listaAlumnos.add(alumno);
                    texto = br.readLine();
                }
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("El fichero " + filename + " no existe");
        }
    }

    public void escribeFicheroCSV(String filename)
    {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaAlumnos.size(); i++) {
                Alumno alumno = new Alumno();
                alumno = listaAlumnos.get(i);
                bw.write(alumno.nombre + ";" + alumno.edad + ";" + alumno.calificacion + System.lineSeparator());
            }
            bw.close();
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
