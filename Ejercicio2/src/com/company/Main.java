package com.company;

import java.awt.image.PixelInterleavedSampleModel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) {
        String[] menu = {"insertaAlumnoLista(alumno)", "insertaAlumnoLista(nombre, edad, nota)", "toString",
                "leeFicheroBinario", "escribeFicheroBinario", "leeFicheroTexto", "escribeFicheroTexto",
                "leeFicheroCSV", "escribeFicheroCSV"};
        Scanner sc = new Scanner(System.in);
        Grupo grupo = new Grupo();
        boolean salir = false;
        String a, cualquierTecla;
        int opcion; //Guardamos la opcion del usuario
        // Comprobamos si existen datos anteriores
        String ficheroApp = "FicheroAlumnos.bin";
        if(Files.exists(Path.of(ficheroApp)))
        {
            grupo.leeFicheroBinario(ficheroApp);
        }
        while (!salir) {// write your code here
            imprimeMenu(menu, "Menu principal", ANSI_BLUE);
            Alumno al = new Alumno();
            try{
                System.out.print("Eliga opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // buffer
                switch (opcion) {
                    case 1:
                        //insertaAlumnoLista(alumno)
                        al.nombre = sc.nextLine();
                        al.edad = sc.nextInt();
                        al.calificacion = sc.nextDouble();
                        grupo.insertaAlumnoLista(al);
                        break;
                    case 2:
                        //insertaAlumnoLista(nombre, edad, nota)
                        al.nombre = sc.nextLine();
                        al.edad = sc.nextInt();
                        al.calificacion = sc.nextDouble();
                        grupo.insertaAlumnoLista(al.nombre, al.edad, al.calificacion);
                        break;
                    case 3:
                        //toString
                        System.out.println(grupo.toString());
                        break;
                    case 4:
                        //leeFicheroBinario
                        System.out.println("Leyendo fichero de datos " + ficheroApp + "...");
                        grupo.leeFicheroBinario(ficheroApp);
                        break;
                    case 5:
                        //escribeFicheroBinario
                        System.out.println("Escribiendo fichero de datos " + ficheroApp);
                        grupo.escribeFicheroBinario(ficheroApp);
                        break;
                    case 6:
                        //leeFicheroTexto
                        System.out.print("Introduce nombre de fichero (.txt): ");
                        a = sc.nextLine();
                        grupo.leeFicheroTexto(a);
                        break;
                    case 7:
                        //escribeFicheroTexto
                        System.out.print("Introduce nombre de fichero (.txt)): ");
                        a = sc.nextLine();
                        grupo.escribeFicheroTexto(a);
                        break;
                    case 8:
                        //leeFicheroCSV
                        System.out.print("Introduce nombre de fichero (.csv)): ");
                        a = sc.nextLine();
                        grupo.leeFicheroCSV(a);
                        break;
                    case 9:
                        //escribeFicheroCSV
                        System.out.print("Introduce nombre de fichero (.csv): ");
                        a = sc.nextLine();
                        grupo.escribeFicheroCSV(a);
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("ERROR: Opcion no válida");
                }
                if (!salir) {
                    System.out.println("Introduzca cualquier carácter + Intro para continuar:");
                    cualquierTecla = sc.next();
                }
            }catch (InputMismatchException e){
                System.out.println("ERROR: Debes insertar un número. " + e.getMessage());
                sc.next();
            }
        }
    }

    public static void imprimeMenu(String[] opciones, String titulo, String color) {
        int Max = 15; // Ancho mínimo
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].length() > Max) Max = opciones[i].length();
        }
        System.out.print(color + "╔");
        pinta("═", Max + 4);
        System.out.println("╗");
        System.out.print("║");
        pinta(" ", Max/2 - titulo.length()/2 + 2);
        System.out.print(ANSI_BLACK + ANSI_WHITE_BACKGROUND + titulo + ANSI_RESET);
        pinta(" ", Max/2 - titulo.length()/2 + 2);
        System.out.println(color + "║");
        System.out.print("╠");
        pinta("═", Max + 4);
        System.out.println("╣");
        for (int i = 1; i <= opciones.length; i++) {
            System.out.print(color + "║" + ANSI_RESET);
            if (i < 10) System.out.print(" ");
            System.out.print(i + ". " + opciones[i - 1]);
            pinta(" ", Max - opciones[i - 1].length());
            System.out.print(color + "║" + ANSI_RESET);
            System.out.println();
        }
        System.out.print(color + "╠");
        pinta("═", Max + 4);
        System.out.println("╣");
        System.out.print("║ " + ANSI_RESET + "0. Salir");
        pinta(" ", Max - 5);
        System.out.println(color + "║");
        System.out.print("╚");
        pinta("═", Max + 4);
        System.out.println("╝" + ANSI_RESET);
    }

    private static void pinta(String s, int i)
    {
        for (int j = 0; j < i; j++) {
            System.out.print(s);
        }
    }

}
