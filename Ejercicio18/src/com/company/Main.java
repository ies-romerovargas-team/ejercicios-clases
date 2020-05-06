package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static String fichero = "Coches.csv";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //cargar datos al inicio
        AutoVenta miscoches = new AutoVenta();
        int n = miscoches.cargaCSV(fichero);
        System.out.println("# Se han leído " + n + " coches.");
        boolean cambios = false;
        boolean salir = false;
        while (!salir) {
            //Menú de opciones
            System.out.println();
            System.out.println("# Menú");
            System.out.println("");
            System.out.println("1. Insertar un coche");
            System.out.println("2. Buscar un coche por matricula");
            System.out.println("3. Listar coches");
            System.out.println("4. Eliminar un coche");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();
            System.out.print("Elige opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpia buffer
            switch (opcion)
            {
                case 1:
                    Coche nuevo = inserta();
                    if (nuevo.getMatricula()!=null)
                    {
                        miscoches.insertaCoche(nuevo);
                        cambios = true;
                    } else {
                        System.out.println("El vehículo que ha intentado insertar no es válido");
                    }
                    break;
                case 2:
                    System.out.print("Introduzca Matrícula: ");
                    String matBuscar = sc.nextLine();
                    Coche c = miscoches.buscaCoche(matBuscar);
                    if(c!=null)
                    {
                        System.out.println(c.toString());
                    }
                    else
                    {
                        System.out.println("La matrícula " + matBuscar + " no está en la lista.");
                    }
                    break;
                case 3:
                    System.out.println(miscoches.imprimeListado());
                    break;
                case 4:
                    System.out.print("Introduzca Matrícula: ");
                    matBuscar = sc.nextLine();
                    c = miscoches.buscaCoche(matBuscar);
                    if(c!=null)
                    {
                        miscoches.removeCoche(c);
                        System.out.println("Vehículo eliminado!!");
                    }
                    else
                    {
                        System.out.println("La matrícula " + matBuscar + " no está en la lista.");
                    }
                    break;
                case 0:
                    salir = true;
                    break;
            }
        }
        if(cambios){
            miscoches.guardaCSV(fichero);
        }
    }

    private static Coche inserta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca matrícula: ");
        String mat = sc.nextLine();
        System.out.print("Marca: ");
        String mar = sc.nextLine();
        System.out.print("Modelo: ");
        String mod = sc.nextLine();
        System.out.print("Fecha de Matriculación (dd/mm/aaaa): ");
        String fmat = sc.nextLine();
        LocalDate fecha = LocalDate.parse(fmat, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Precio (min. 500,00€): ");
        double pre = sc.nextDouble();
        return new Coche(mat, fecha, mar, mod, pre);
    }
}
