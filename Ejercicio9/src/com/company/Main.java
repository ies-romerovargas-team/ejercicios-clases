package com.company;

import java.util.Scanner;

public class Main {

        static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) {
            String opcion = "";
            Hora h = new Hora(3600);

            while (!opcion.equals("0")) {
                System.out.println("Clase Hora");
                System.out.println("------------------------");
                System.out.println();
                System.out.println("1- Mostrar hora");
                System.out.println("2- Fijar Nueva Hora");
                System.out.println("3- Sumar horas");
                System.out.println("4- Restar horas");
                System.out.println("0- Salir");
                System.out.println();
                System.out.print("Opción: ");
                opcion = sc.nextLine();
                System.out.println("\n");
                int hor, min, seg;
                Hora NuevaHora;
                switch (opcion) {
                    case "1":
                        System.out.println(h.toString());
                        break;
                    case "2":
                        System.out.println("Escribe la hora:");
                        hor = sc.nextInt();
                        System.out.println("Escribe los minutos (0-59):");
                        min = sc.nextInt();
                        System.out.println("Escribe los segundos (0-59):");
                        seg = sc.nextInt();
                        sc.nextLine();
                        h.setHoras(hor);
                        h.setMinutos(min);
                        h.setSegundos(seg);
                        System.out.print("Hora modificada: ");
                        System.out.println(h.toString());
                        break;
                    case "3":
                        System.out.print("Hora actual: ");
                        System.out.println(h.toString());
                        System.out.println("Escribe la hora:");
                        hor = sc.nextInt();
                        System.out.println("Escribe los minutos (0-59):");
                        min = sc.nextInt();
                        System.out.println("Escribe los segundos (0-59):");
                        seg = sc.nextInt();
                        sc.nextLine();
                        NuevaHora = new Hora(hor, min, seg);
                        h.add(NuevaHora);
                        System.out.print("Hora modificada: ");
                        System.out.println(h.toString());
                        break;
                    case "4":
                        System.out.print("Hora actual: ");
                        System.out.println(h.toString());
                        System.out.println("Escribe la hora:");
                        hor = sc.nextInt();
                        System.out.println("Escribe los minutos (0-59):");
                        min = sc.nextInt();
                        System.out.println("Escribe los segundos (0-59):");
                        seg = sc.nextInt();
                        sc.nextLine();
                        NuevaHora = new Hora(hor, min, seg);
                        h.substract(NuevaHora);
                        System.out.print("Hora modificada: ");
                        System.out.println(h.toString());
                        break;
                    case "0":
                        System.out.println("¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
                System.out.println("\n");
            }
        }
    }