package com.company;

import java.util.Scanner;

public class Main {

    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) {

        String[] menu = {"Simplificar", "negate()", "add(f)", "substract(f)", "multiply(f)", "divide(f)", "equals(f)", "compareTo(f)"};
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int nivel = 0;
        int opcion;
        while (!salir){
            imprimeMenu(menu, "Menu principal", ANSI_BLUE);
            try {
                System.out.print("Eliga opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // buffer
                boolean turno;
                String mensaje="";
                switch (opcion) {
                    
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        double numero = 58.0;
        Fraccion a = new Fraccion(numero);
        System.out.println(numero + " => " + a.toString());
        Scanner sc = new Scanner(System.in);
        System.out.println("Simplificador de fracciones");
        System.out.print("Introduzca numerador: ");
        int n = sc.nextInt();
        System.out.print("Introduzca denominador: ");
        int d = sc.nextInt();
        a.setNumerador(n);
        a.setDenominador(d);
        a.simplificar();
        System.out.println(n + "/" + d + " => " + a.toString());
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
