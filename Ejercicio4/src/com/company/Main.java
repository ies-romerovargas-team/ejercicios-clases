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
                int n, d;
                Fraccion f = new Fraccion();
                switch (opcion) {
                    case 1:
                        /*
                        System.out.println(numero + " => " + a.toString());
                        Scanner sc = new Scanner(System.in);
                        */
                        System.out.println("Simplificador de fracciones");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        f = new Fraccion(n, d);
                        f.setNumerador(n);
                        f.setDenominador(d);
                        f.simplificar();
                        System.out.println(n + "/" + d + " => " + f.toString());
                        break;
                    case 2:
                        System.out.println("negate()");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        f = new Fraccion(n, d);
                        f.setNumerador(n);
                        f.setDenominador(d);
                        f.simplificar();
                        f.negate();
                        System.out.println(n + "/" + d + " => " + f.toString());
                        break;
                    case 3:
                        System.out.println("add(f)");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        f = new Fraccion(n, d);
                        System.out.println("Segunda Fracción");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        Fraccion f2 = new Fraccion(n, d);
                        f = f.add(f2);
                        System.out.println(f.toString());
                        break;
                    case 4:
                        System.out.println("substract(f)");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        f = new Fraccion(n, d);
                        System.out.println("Segunda Fracción");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        Fraccion f3 = new Fraccion(n, d);
                        f = f.substract(f3);
                        System.out.println(f.toString());
                        break;
                    case 5:
                        System.out.println("multiply(f)");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        f = new Fraccion(n, d);
                        System.out.println("Segunda Fracción");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        Fraccion f4 = new Fraccion(n, d);
                        f = f.multiply(f4);
                        System.out.println(f.toString());
                        break;
                    case 6:
                        System.out.println("divide(f)");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        f = new Fraccion(n, d);
                        System.out.println("Segunda Fracción");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        Fraccion f5 = new Fraccion(n, d);
                        f = f.divide(f5);
                        System.out.println(f.toString());
                        break;
                    case 7:
                        System.out.println("equals(f)");
                        System.out.print("Introduzca numerador: ");
                        int n1 = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        int d1 = sc.nextInt();
                        f = new Fraccion(n1, d1);
                        System.out.println("Segunda Fracción");
                        System.out.print("Introduzca numerador: ");
                        int n2 = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        int d2 = sc.nextInt();
                        Fraccion f6 = new Fraccion(n2, d2);
                        if(f.equals(f6)){
                            System.out.println(n1 +"/" + d1 + " y " + n2 + "/" + d2 + " son iguales");
                        }
                        else
                        {
                            System.out.println(n1 +"/" + d1 + " y " + n2 + "/" + d2 + " son distintas");
                        }
                        break;
                    case 8:
                        System.out.println("compareTo(f)");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        f = new Fraccion(n, d);
                        System.out.println("Segunda Fracción");
                        System.out.print("Introduzca numerador: ");
                        n = sc.nextInt();
                        System.out.print("Introduzca denominador: ");
                        d = sc.nextInt();
                        Fraccion f7 = new Fraccion(n, d);
                        if(f.equals(f7)){
                            System.out.println(f.toString() +" y " + f7.toString() + " son iguales");
                        }
                        else if(f.compareTo(f7) == 1)
                        {
                            System.out.println(f.toString() +" es menor que " + f7.toString());
                        }
                        else
                        {
                            System.out.println(f.toString() +" es mayor que " + f7.toString());
                        }
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void imprimeMenu(String[] opciones, String titulo, String color) {
        int Max = 15; // Ancho mínimo
        if(titulo.length() > Max) Max = titulo.length();
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].length() > Max) Max = opciones[i].length();
        }
        if(titulo.length()%2!=0) titulo = titulo + " ";
        if(Max%2!=0) Max++;
        System.out.print(color + "╔");
        pinta("═", Max + 4);
        System.out.println("╗");
        System.out.print("║");
        pinta(" ", Max - titulo.length() + 1);
        System.out.print(ANSI_BLACK + ANSI_WHITE_BACKGROUND + titulo + ANSI_RESET);
        pinta(" ", Max - titulo.length() + 1);
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
