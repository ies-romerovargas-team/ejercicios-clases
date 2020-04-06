package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        int[] numbers = new int[6];
        System.out.println("Introduzca 6 numeros (1-49)");
        for (int i = 0; i < numbers.length; i++) {
            boolean repite = true;
            int a = 0;
            while (repite) {
                System.out.print("Numero " + (i + 1) + ": ");
                a = sc.nextInt();
                if (a > 0 && a < 50) {
                    numbers[i] = a;
                    repite = false;
                    int x = 0;
                    while (x < i) {
                        if (numbers[x] == a) {
                            repite = true;
                        }
                        x++;
                    }
                }
            }

        }
        ClienteBonoloto boleto = new ClienteBonoloto(numbers);
        int aciertos = boleto.conectaCliente("127.0.0.1");
        System.out.println("Aciertos: " + aciertos);
    }
}
