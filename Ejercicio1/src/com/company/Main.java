package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Baraja baraja = new Baraja(1);
        baraja.barajar();
        List<Carta> CartasJugador = new ArrayList<>();
        List<Carta> CartasOrdenador = new ArrayList<>();
        String opcion = "s";
        double punt1 = 0, punt2 = 0;
        while (!opcion.equals("n"))
        {
            punt1 = 0;
            CartasJugador.add(baraja.robar());
            PintaMesa(CartasJugador, CartasOrdenador);
            for (int i = 0; i < CartasJugador.size(); i++) {
                punt1 = punt1 + CartasJugador.get(i).valor7yMedia();
            }
            if(punt1 < 7.5) {
                System.out.println("¿quiere más cartas? (s/n)");
                opcion = sc.nextLine();
            }
            else
            {
                break;
            }
        }
        boolean continua = true;
        while(continua)
        {
            punt2 = 0;
            CartasOrdenador.add(baraja.robar());
            PintaMesa(CartasJugador, CartasOrdenador);
            for (int i = 0; i < CartasOrdenador.size(); i++) {
                punt2 = punt2 + CartasOrdenador.get(i).valor7yMedia();
            }
            if(punt2 < 7.5)
            {
                if(punt2 <= punt1 && punt1 <= 7.5)
                {
                    System.out.println("                                             Pidiendo otra carta...");
                    retardo();
                }
                else
                {
                    System.out.println("                                             Me planto");
                    continua = false;
                }
            }
            if (punt2 == 7.5)
            {
                System.out.println("                                             Tengo 7 y media");
                continua = false;
            }
            if (punt2 > 7.5)
            {
                System.out.println("                                             Me he pasado");
                continua = false;
            }
        }
        if((punt1 >= punt2 && punt1 < 7.5) || punt2 > 7.5)
        {
            System.out.println("HAS GANADO");
        }
        else
        {
            System.out.println("HAS PERDIDO");
        }
    }

    private static void retardo()
    {
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    private static void PintaMesa(List<Carta> cartasJuego1, List<Carta> cartasJuego2)
    {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        String ANSI_RED="\u001B[31m";
        String ANSI_RESET="\u001B[0m";
        String ANSI_YELLOW_BACKGROUND = "\u001B[103m";
        String[] poker = {"♦", "♥", "♠", "♣"};
        System.out.println("JUGADOR:                                     ORDENADOR:");
        int i, j;
        for (i = 0; i < cartasJuego1.size(); i++) {
            System.out.print(ANSI_YELLOW_BACKGROUND);
            System.out.print(cartasJuego1.get(i).nombreNumero());
            if(cartasJuego1.get(i).palo<2) System.out.print(ANSI_RED);
            System.out.print(poker[cartasJuego1.get(i).palo]);
            System.out.print(ANSI_RESET);
            System.out.print("  ");
        }
        //
        for (j = i * 4; j < 45; j++) System.out.print(" ");
        //
        for (i = 0; i < cartasJuego2.size(); i++) {
            System.out.print(ANSI_YELLOW_BACKGROUND);
            System.out.print(cartasJuego2.get(i).nombreNumero());
            if(cartasJuego2.get(i).palo<2) System.out.print(ANSI_RED);
            System.out.print(poker[cartasJuego2.get(i).palo]);
            System.out.print(ANSI_RESET);
            System.out.print("  ");
        }
        System.out.println();
        //
        double punt1 = 0, punt2 = 0;
        for (i = 0; i < cartasJuego1.size(); i++) {
            punt1 = punt1 + cartasJuego1.get(i).valor7yMedia();
        }
        for (i = 0; i < cartasJuego2.size(); i++) {
            punt2 = punt2 + cartasJuego2.get(i).valor7yMedia();
        }
        String puntuacion = "Puntos: " + punt1;
        if(punt1 > 7.5) System.out.print(ANSI_RED);
        System.out.print(puntuacion);
        System.out.print(ANSI_RESET);
        //
        for (j = puntuacion.length(); j < 45; j++) System.out.print(" ");
        //
        System.out.println("Puntos: " + punt2);
    }
}
