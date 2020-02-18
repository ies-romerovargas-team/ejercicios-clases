package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) {
        Random r = new Random();
        String[] menu = {"jugador vs jugador", "jugador vs ordenador", "ordenador vs jugador", "ordenador vs ordenador", "nivel (0-1-2-3)"};
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        while (!salir) {// write your code here
            imprimeMenu(menu, "Menu principal", ANSI_BLUE);
            TresEnRaya juego = new TresEnRaya();
            try {
                System.out.print("Eliga opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // buffer
                boolean turno;
                String mensaje="";
                switch (opcion) {
                    case 1:
                        juego.iniciar();
                        juego.dibujaTablero();
                        turno = true;
                        System.out.println("Introduzca Jugadas por turnos (formato: f-c)");
                        while(juego.quedanMovimientos())
                        {
                            if(turno){
                                if(juego.ganaJugador2()) {
                                    break;
                                } else
                                    {
                                    boolean jugadaValida = false;
                                    int posicion = 9;
                                    while(!jugadaValida)
                                    {
                                        System.out.println("Jugada J1: ");
                                        String[] jugada = sc.nextLine().split("-");
                                        posicion = Integer.parseInt(jugada[0]) * 2 + Integer.parseInt(jugada[1]) + Integer.parseInt(jugada[0]);
                                        System.out.println(posicion);
                                        if(juego.movimientoValido(posicion)) jugadaValida = true;
                                    }
                                    juego.muevejugador1(posicion);
                                    juego.dibujaTablero();
                                    turno = false;
                                }
                            }
                            else {
                                if(juego.ganaJugador1()) {
                                    break;
                                } else
                                {
                                    boolean jugadaValida = false;
                                    int posicion = 9;
                                    while(!jugadaValida)
                                    {
                                        System.out.println("Jugada J2: ");
                                        String[] jugada = sc.nextLine().split("-");
                                        posicion = Integer.parseInt(jugada[0]) * 2 + Integer.parseInt(jugada[1]) + Integer.parseInt(jugada[0]);
                                        System.out.println(posicion);
                                        if(juego.movimientoValido(posicion)) jugadaValida = true;
                                    }
                                    juego.muevejugador2(posicion);
                                    juego.dibujaTablero();
                                    turno = true;
                                }
                            }
                        }
                        if(juego.ganaJugador1()) mensaje = "Gana J1 (X)";
                        if(juego.ganaJugador2()) mensaje = "Gana J2 (O)";
                        if(mensaje.equals(""))
                        {
                            System.out.println("No hay más movimientos");
                        }
                        else System.out.println(mensaje);
                        break;
                    case 2:
                        juego.iniciar();
                        juego.dibujaTablero();
                        turno = true;
                        System.out.println("Introduzca Jugadas por turnos (formato: f-c)");
                        while(juego.quedanMovimientos())
                        {
                            if(turno)
                            {
                                if(juego.ganaJugador2())
                                {
                                    break;
                                }
                                else
                                {
                                    boolean jugadaValida = false;
                                    int posicion = 9;
                                    while(!jugadaValida)
                                    {
                                        System.out.println("Jugada J1: ");
                                        String[] jugada = sc.nextLine().split("-");
                                        posicion = Integer.parseInt(jugada[0]) * 2 + Integer.parseInt(jugada[1]) + Integer.parseInt(jugada[0]);
                                        System.out.println(posicion);
                                        if(juego.movimientoValido(posicion)) jugadaValida = true;
                                    }
                                    juego.muevejugador1(posicion);
                                    juego.dibujaTablero();
                                    turno = false;
                                }
                            }
                            else
                            {
                                if(juego.ganaJugador1())
                                {
                                    break;
                                }
                                else
                                {
                                    juego.mueveOrdenador2();
                                    juego.dibujaTablero();
                                    turno = true;
                                }
                            }
                        }
                        if(juego.ganaJugador1()) mensaje = "Gana J1 (X)";
                        if(juego.ganaJugador2()) mensaje = "Gana J2 (O)";
                        if(mensaje.equals(""))
                        {
                            System.out.println("No hay más movimientos");
                        }
                        else System.out.println(mensaje);
                        break;
                    case 3:
                        juego.iniciar();
                        juego.dibujaTablero();
                        turno = true;
                        System.out.println("Introduzca Jugadas por turnos (formato: f-c)");
                        while(juego.quedanMovimientos())
                        {
                            if(turno){
                                if(juego.ganaJugador2()) {
                                    break;
                                } else
                                {
                                    juego.mueveOrdenador1();
                                    juego.dibujaTablero();
                                    turno = false;
                                }
                            }
                            else {
                                if(juego.ganaJugador1()) {
                                    break;
                                } else
                                {
                                    boolean jugadaValida = false;
                                    int posicion = 9;
                                    while(!jugadaValida)
                                    {
                                        System.out.println("Jugada J2: ");
                                        String[] jugada = sc.nextLine().split("-");
                                        posicion = Integer.parseInt(jugada[0]) * 2 + Integer.parseInt(jugada[1]) + Integer.parseInt(jugada[0]);
                                        System.out.println(posicion);
                                        if(juego.movimientoValido(posicion)) jugadaValida = true;
                                    }
                                    juego.muevejugador2(posicion);
                                    juego.dibujaTablero();
                                    turno = true;
                                }
                            }
                        }
                        if(juego.ganaJugador1()) mensaje = "Gana J1 (X)";
                        if(juego.ganaJugador2()) mensaje = "Gana J2 (O)";
                        if(mensaje.equals(""))
                        {
                            System.out.println("No hay más movimientos");
                        }
                        else System.out.println(mensaje);
                        break;
                    case 4:
                        juego.iniciar();
                        juego.dibujaTablero();
                        turno = true;
                        System.out.println("Sólo mira y aprende");
                        while(juego.quedanMovimientos())
                        {
                            if(turno){
                                if(juego.ganaJugador2()) {
                                    break;
                                } else
                                {
                                    juego.mueveOrdenador1();
                                    juego.dibujaTablero();
                                    turno = false;
                                }
                            }
                            else {
                                if(juego.ganaJugador1()) {
                                    break;
                                } else
                                {
                                    juego.mueveOrdenador2();
                                    juego.dibujaTablero();
                                    turno = true;
                                }
                            }
                            try
                            {
                                Thread.sleep(1000);
                            }catch(InterruptedException e){}
                        }
                        if(juego.ganaJugador1()) mensaje = "Gana J1 (X)";
                        if(juego.ganaJugador2()) mensaje = "Gana J2 (O)";
                        if(mensaje.equals(""))
                        {
                            System.out.println("No hay más movimientos");
                        }
                        else System.out.println(mensaje);
                        break;
                    case 5:
                        System.out.println("Nivel actual: " + juego.nivel);
                        System.out.print("Introduzca nuevo nivel (0/3): ");
                        int y = sc.nextInt();
                        juego.setNivel(y);
                        juego.nivel = y;
                        break;
                    case 0:
                        salir=true;
                        break;
                }
            } catch (Exception a) {
                System.out.println(a.getMessage());
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
