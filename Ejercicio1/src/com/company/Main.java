package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Baraja baraja = new Baraja(1);
        //PintaBaraja(baraja);
        baraja.barajar();
        //PintaBaraja(baraja);
        // Jugamos a las 7 y media
        List<Carta> CartasJugador = new ArrayList<>();
        List<Carta> CartasOrdenador = new ArrayList<>();
        String opcion = "s";
        double puntuacion = 0;
        while (opcion!="n")
        {
            CartasJugador.add(baraja.robar());
            PintaCartas(CartasJugador);
            double cont = 0;
            for (int i = 0; i < CartasJugador.size(); i++) {
                cont = cont + CartasJugador.get(i).valor7yMedia();
            }
            System.out.println("Valor total: " + cont);
            if(cont < 7.5) {
                System.out.println("¿quiere más cartas? (s/n)");
                opcion = sc.nextLine();
            }
            else
            {
                System.out.println("TE PASASTE!!");
                break;
            }
            puntuacion = cont;
        }
        boolean excedido = false;
        while(!excedido)
        {
            CartasOrdenador.add(baraja.robar());
            PintaCartas(CartasOrdenador);
            double cont = 0;
            for (int i = 0; i < CartasOrdenador.size(); i++) {
                cont = cont + CartasOrdenador.get(i).valor7yMedia();
            }
            System.out.println("Valor total: " + cont);
            if(cont < 7.5) {

            } else {
                System.out.println("HAS GANADO");
                excedido = true;
            }
            if(cont>= puntuacion) {
                System.out.println("HAS PERDIDO");
                break;
            }
        }
    }

    private static void PintaCartas(List<Carta> cartasJugador)
    {
        String ANSI_RED="\u001B[31m";
        String ANSI_RESET="\u001B[0m";
        String[] poker = {"♦", "♥", "♠", "♣"};
        for (int i = 0; i < cartasJugador.size(); i++) {
            System.out.print(cartasJugador.get(i).nombreNumero());
            if(cartasJugador.get(i).palo<2) System.out.print(ANSI_RED);
            System.out.print(poker[cartasJugador.get(i).palo] + " ");
            System.out.print(ANSI_RESET);
        }
        System.out.println();
    }

    private static void PintaBaraja(Baraja li)
    {
        // Escribir toda la baraja
        int i;
        System.out.println("Baraja: ");
        if(li.numeroCartas() > 0) {
            for (i = 0; i < li.numeroCartas(); i++) {
                Carta a = li.leeBaraja(i);
                System.out.println(a.numero + " de " + a.nombrePalo());
            }
        }

    }
}
