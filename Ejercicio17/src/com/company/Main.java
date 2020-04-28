package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int saldo = 100;
        Scanner sc = new Scanner(System.in);
        boolean jugando = true;
        while(jugando){
            System.out.println("******* MENU ********");
            System.out.println();
            System.out.println("1. Comenzar Partida nueva");
            System.out.println("2. Salir");
            System.out.println();
            System.out.print("Elija opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion)
            {
                case 1:
                    boolean partida = true;
                    while(partida) {
                        saldo--;
                        PokerDeck baraja = new PokerDeck();
                        PokerHand mano = new PokerHand(baraja.drawCard(), baraja.drawCard(), baraja.drawCard(), baraja.drawCard(), baraja.drawCard());
                        System.out.println();
                        System.out.println("MONEDAS: " + saldo);
                        System.out.println(mano.toString());
                        System.out.println("1  2  3  4  5");
                        // bucle de descartes
                        System.out.println("Introduzca los números correspondientes a las cartas que desea descartar. INTRO para continuar");
                        String op = sc.nextLine();
                        for (int i = 0; i < op.length(); i++) {
                            int valor = Integer.parseInt(Character.toString(op.charAt(i))) - 1;
                            mano.hand[valor] = baraja.drawCard();
                        }
                        mano.sortHand();
                        System.out.println(mano.toString());
                        saldo = saldo + CompruebaMano(mano);
                        if(saldo<=0) {
                            System.out.println("Final de la Partida");
                            partida = false;
                        }
                    }
                    break;
                case 2:
                    jugando = false;
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        }
    }

    private static int CompruebaMano(PokerHand mano) {
        int premio = 0;
        if (mano.isRoyalFlush()) {
            System.out.println("Escalera real");
            premio = 800;
        } else if (mano.isStraightFlush()) {
            System.out.println("Escalera de color");
            premio = 50;
        } else if (mano.isPoker()) {
            System.out.println("Poker");
            premio = 25;
        } else if (mano.isFull()) {
            System.out.println("Full");
            premio = 9;
        } else if (mano.isFlush()) {
            System.out.println("Color");
            premio = 6;
        } else if (mano.isStraight()) {
            System.out.println("Escalera");
            premio = 4;
        } else if (mano.isThree()) {
            System.out.println("Trio");
            premio = 3;
        } else if (mano.isTwoPair()) {
            System.out.println("Doble Pareja");
            premio = 2;
        } else if (mano.isPair()) {
            System.out.println("Pareja");
            premio = 1;
        }
        System.out.println("Premio: " + premio);
        return premio;
    }
}
