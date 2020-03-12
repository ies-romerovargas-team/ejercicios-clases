package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean salir = false;
        String nombre, fecha, raza, microchip, especie;
        LocalDate tolocalDate;
        double peso;
        Scanner sc = new Scanner(System.in);
        int opcion;
        ClinicaVeterinaria myClinic = new ClinicaVeterinaria();
        String ficheroApp = "myClinic.bin";
        if(Files.exists(Path.of(ficheroApp)))
        {
            System.out.println("Leyendo datos...");
            leeFichero(myClinic, ficheroApp);
            System.out.println("Done!!");
        }
        while(!salir){
            // Imprime menu
            System.out.println("MENU");
            System.out.println(); // Opciones
            System.out.println("1. Añadir perro");
            System.out.println("2. Añadir gato");
            System.out.println("3. Añadir pájaro");
            System.out.println("4. Añadir reptil");
            System.out.println();
            System.out.println("5. Imprime lista");
            System.out.println("0. Salir");
            System.out.println();
            System.out.println("Elija opcion: ");
            opcion = sc.nextInt();

            switch (opcion)
            {
                case 1:
                    // Común
                    sc = new Scanner(System.in);
                    System.out.print("Indique un nombre: ");
                    nombre = sc.nextLine();
                    System.out.print("Indique fecha nacimiento: ");
                    fecha = sc.nextLine();
                    tolocalDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    System.out.print("Indique el peso: ");
                    peso = sc.nextDouble();
                    System.out.print("Indique la raza: ");
                    sc.nextLine(); //buffer
                    raza = sc.nextLine();
                    RazaPerro toRazaPerro = RazaPerro.valueOf(raza);
                    System.out.print("Indique nº microchip: ");
                    microchip = sc.nextLine();
                    Perro perro = new Perro(nombre, toRazaPerro, tolocalDate, peso, microchip);
                    myClinic.insertaAnimal(perro);
                    break;
                case 2:
                    // Común
                    sc = new Scanner(System.in);
                    System.out.print("Indique un nombre: ");
                    nombre = sc.nextLine();
                    System.out.print("Indique fecha nacimiento: ");
                    fecha = sc.nextLine();
                    tolocalDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    System.out.print("Indique el peso: ");
                    peso = sc.nextDouble();
                    sc.nextLine();  //buffer
                    System.out.print("Indique la raza: ");
                    raza = sc.nextLine();
                    RazaGato toRazaGato = RazaGato.valueOf(raza);
                    System.out.print("Indique nº microchip: ");
                    microchip = sc.nextLine();
                    Gato gato = new Gato(nombre, toRazaGato, tolocalDate, peso, microchip);
                    myClinic.insertaAnimal(gato);
                    break;
                case 3:
                    // Común
                    sc = new Scanner(System.in);
                    System.out.print("Indique un nombre: ");
                    nombre = sc.nextLine();
                    System.out.print("Indique fecha nacimiento: ");
                    fecha = sc.nextLine();
                    tolocalDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    System.out.print("Indique el peso: ");
                    peso = sc.nextDouble();
                    sc.nextLine(); //buffer
                    System.out.print("Indique la especie: ");
                    especie = sc.nextLine();
                    EspeciePajaro toEspeciePajaro = EspeciePajaro.valueOf(especie);
                    System.out.print("¿Canta?: ");
                    boolean canta = sc.nextBoolean();
                    Pajaro pajaro = new Pajaro(nombre, toEspeciePajaro, tolocalDate, peso, canta);
                    myClinic.insertaAnimal(pajaro);
                    break;
                case 4:
                    // Común
                    sc = new Scanner(System.in);
                    System.out.print("Indique un nombre: ");
                    nombre = sc.nextLine();
                    System.out.print("Indique fecha nacimiento: ");
                    fecha = sc.nextLine();
                    tolocalDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    System.out.print("Indique el peso: ");
                    peso = sc.nextDouble();
                    sc.nextLine(); //buffer
                    System.out.print("Indique la especie: ");
                    especie = sc.nextLine();
                    EspecieReptil toEspecieReptil = EspecieReptil.valueOf(especie);
                    System.out.print("¿Es venenoso?: ");
                    boolean veneno = sc.nextBoolean();
                    Reptil reptil = new Reptil(nombre, toEspecieReptil, tolocalDate, peso, veneno);
                    myClinic.insertaAnimal(reptil);
                    break;
                case 5:
                    // Imprime lista
                    System.out.println(myClinic.toString());
                    break;
                case 0:
                    System.out.println("Guardando datos...");
                    escribeFichero(myClinic,ficheroApp);
                    System.out.println("Done!!");
                    salir = true;
            }
        }
    }

    private static void leeFichero(ClinicaVeterinaria myClinic, String ficheroApp)
    {
        // Esta funcion se ejecuta al inicio y la lista debe estar vacía
        List<Integer> lista = new ArrayList<>();
        try
        {
            FileInputStream fis = new FileInputStream(ficheroApp);
            DataInputStream dis = new DataInputStream(fis);
            while(dis.available() > 0)
            {
                String nombre, raza, especie, microchip, comment;
                double peso;
                boolean canta, veneno;
                long fecha;
                LocalDate toLocalDate;
                // leer los registros
                String tipo = dis.readUTF();
                switch (tipo)
                {
                    case "perro":
                        nombre = dis.readUTF();
                        raza = dis.readUTF();
                        RazaPerro toRazaPerro = RazaPerro.valueOf(raza);
                        fecha = dis.readLong();
                        toLocalDate = LocalDate.ofEpochDay(fecha);
                        peso = dis.readDouble();
                        microchip = dis.readUTF();
                        comment = dis.readUTF();
                        Perro perro = new Perro(nombre, toRazaPerro, toLocalDate, peso, microchip);
                        perro.setComentarios(comment);
                        myClinic.insertaAnimal(perro);
                        break;
                    case "gato":
                        nombre = dis.readUTF();
                        raza = dis.readUTF();
                        RazaGato toRazaGato = RazaGato.valueOf(raza);
                        fecha = dis.readLong();
                        toLocalDate = LocalDate.ofEpochDay(fecha);
                        peso = dis.readDouble();
                        microchip = dis.readUTF();
                        comment = dis.readUTF();
                        Gato gato = new Gato(nombre, toRazaGato, toLocalDate, peso, microchip);
                        gato.setComentarios(comment);
                        myClinic.insertaAnimal(gato);
                        break;
                    case "pajaro":
                        nombre = dis.readUTF();
                        especie = dis.readUTF();
                        EspeciePajaro toEspeciePajaro = EspeciePajaro.valueOf(especie);
                        fecha = dis.readLong();
                        toLocalDate = LocalDate.ofEpochDay(fecha);
                        peso = dis.readDouble();
                        canta = dis.readBoolean();
                        comment = dis.readUTF();
                        Pajaro pajaro = new Pajaro(nombre, toEspeciePajaro, toLocalDate, peso, canta);
                        pajaro.setComentarios(comment);
                        myClinic.insertaAnimal(pajaro);
                        break;
                    case "reptil":
                        nombre = dis.readUTF();
                        especie = dis.readUTF();
                        EspecieReptil toEspecieReptil = EspecieReptil.valueOf(especie);
                        fecha = dis.readLong();
                        toLocalDate = LocalDate.ofEpochDay(fecha);
                        peso = dis.readDouble();
                        veneno = dis.readBoolean();
                        comment = dis.readUTF();
                        Reptil reptil = new Reptil(nombre, toEspecieReptil, toLocalDate, peso, veneno);
                        reptil.setComentarios(comment);
                        myClinic.insertaAnimal(reptil);
                        break;
                }
            }
            dis.close();
            fis.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void escribeFichero(ClinicaVeterinaria myClinic, String ficheroApp)
    {
        try {
            FileOutputStream fos = new FileOutputStream(ficheroApp);
            DataOutputStream dos = new DataOutputStream(fos);
            List<Animal> lista = myClinic.getListaAnimales();
            for (int i = 0; i < lista.size() ; i++) {
                //perro
                if(lista.get(i) instanceof Perro)
                {
                    dos.writeUTF("perro");
                    dos.writeUTF(lista.get(i).getNombre());
                    dos.writeUTF(((Perro) lista.get(i)).getRaza().name());
                    dos.writeLong(lista.get(i).getFechaNacimiento().toEpochDay());
                    dos.writeDouble(lista.get(i).getPeso());
                    dos.writeUTF(((Perro) lista.get(i)).getMicrochip());
                    dos.writeUTF(lista.get(i).getComentarios());
                }
                //gato
                if(lista.get(i) instanceof Gato)
                {
                    dos.writeUTF("gato");
                    dos.writeUTF(lista.get(i).getNombre());
                    dos.writeUTF(((Gato) lista.get(i)).getRaza().name());
                    dos.writeLong(lista.get(i).getFechaNacimiento().toEpochDay());
                    dos.writeDouble(lista.get(i).getPeso());
                    dos.writeUTF(((Gato) lista.get(i)).getMicrochip());
                    dos.writeUTF(lista.get(i).getComentarios());
                }
                //pajaro
                if(lista.get(i) instanceof Pajaro)
                {
                    dos.writeUTF("pajaro");
                    dos.writeUTF(lista.get(i).getNombre());
                    dos.writeUTF(((Pajaro) lista.get(i)).getEspecie().name());
                    dos.writeLong(lista.get(i).getFechaNacimiento().toEpochDay());
                    dos.writeDouble(lista.get(i).getPeso());
                    dos.writeBoolean(((Pajaro) lista.get(i)).getCantor());
                    dos.writeUTF(lista.get(i).getComentarios());
                }
                //reptil
                if(lista.get(i) instanceof Reptil)
                {
                    dos.writeUTF("reptil");
                    dos.writeUTF(lista.get(i).getNombre());
                    dos.writeUTF(((Reptil) lista.get(i)).getEspecie().name());
                    dos.writeLong(lista.get(i).getFechaNacimiento().toEpochDay());
                    dos.writeDouble(lista.get(i).getPeso());
                    dos.writeBoolean(((Reptil) lista.get(i)).getVenenoso());
                    dos.writeUTF(lista.get(i).getComentarios());
                }
            }
            dos.close();
            fos.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}