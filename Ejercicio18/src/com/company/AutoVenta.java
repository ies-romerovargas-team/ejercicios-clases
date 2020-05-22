package com.company;

import java.io.*;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AutoVenta {

    private final List<Coche> listaCoches;

    public AutoVenta()
    {
        listaCoches = new ArrayList<>();
    }

    public int cargaCSV(String fichero)
    {
        String texto;
        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            texto = br.readLine();
            if(!texto.equals("Matricula;Marca;Modelo;Fecha de Matriculacion;Precio")) { 
                throw  new InvalidParameterException("Archivo no válido");
            }
            while(texto != null)
            {
                texto = br.readLine();
                if(texto!= null){
                    String[] valores = texto.split(";");
                    String mat = valores[0];
                    LocalDate fMat = LocalDate.parse(valores[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String mar = valores[1];
                    String mod = valores[2];
                    double pre = Double.parseDouble(valores[4]);
                    Coche coche = new Coche(mat,fMat,mar,mod,pre);
                    listaCoches.add(coche);
                }
            }
            System.out.println();

            br.close();
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return listaCoches.size();
    }

    public void guardaCSV(String fichero)
    {
        String a;
        try {
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Matricula;Marca;Modelo;Fecha de Matriculacion;Precio" + System.lineSeparator());
            for (Coche listaCoch : listaCoches) {
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fecha = listaCoch.getFechaMatriculacion().format(formato);
                String coche = listaCoch.getMatricula() + ";" + listaCoch.getMarca() + ";" +
                        listaCoch.getModelo() + ";" + fecha + ";" +
                        listaCoch.getPrecio();
                bw.write(coche + System.lineSeparator());
            }

            bw.close();
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void insertaCoche(Coche c)
    {
        listaCoches.add(c);
    }

    public void removeCoche(Coche c)
    {
        listaCoches.remove(c);
    }

    public Coche buscaCoche(String matricula)
    {
        for (Coche listaCoch : listaCoches) {
            if (listaCoch.getMatricula().equals(matricula)) {
                return listaCoch;
            }
        }
        return null;
    }

    public String imprimeListado()
    {
        StringBuilder devuelve = new StringBuilder(System.lineSeparator());
        devuelve.append("Matrícula Marca\t\t\tModelo\t\t\tFecha Matri.\tPrecio").append(System.lineSeparator());
        devuelve.append(System.lineSeparator());
        for (Coche listaCoch : listaCoches) {
            devuelve.append(listaCoch.toString()).append(System.lineSeparator());
        }
        return devuelve.toString();
    }
}
