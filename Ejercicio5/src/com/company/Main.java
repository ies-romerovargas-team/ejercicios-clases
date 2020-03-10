package com.company;


import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("INDIQUE TIPO DE MONEDA [EUR | USD | GBP | YEN]: ");
        String strMoneda = sc.nextLine();
        System.out.println("INDIQUE CANTIDAD: ");
        double cantidad = sc.nextDouble();
        TipoMoneda moneda = TipoMoneda.EURO;
        switch (strMoneda)
        {
            case "EUR":
                moneda = TipoMoneda.EURO;
                break;
            case "USD":
                moneda = TipoMoneda.DOLAR;
                break;
            case "GBP":
                moneda = TipoMoneda.LIBRA;
                break;
            case "YEN":
                moneda = TipoMoneda.YEN;
                break;
        }
        System.out.print("Redondeo: ");
        Dinero myMoney = new Dinero(cantidad, moneda);
        System.out.println(myMoney.toString());
        System.out.print("Valor en EUROS: ");
        System.out.println(myMoney.valorEn(TipoMoneda.EURO));
        System.out.print("Valor en DOLARES: ");
        System.out.println(myMoney.valorEn(TipoMoneda.DOLAR));
        System.out.print("Valor en LIBRAS: ");
        System.out.println(myMoney.valorEn(TipoMoneda.LIBRA));
        System.out.print("Valor en YENES: ");
        System.out.println(myMoney.valorEn(TipoMoneda.YEN));
        System.out.println();
        System.out.println("Convertir");
        Dinero euros = new Dinero(0, TipoMoneda.EURO);
        Dinero dolares = new Dinero(0, TipoMoneda.DOLAR);
        Dinero libras = new Dinero(0, TipoMoneda.LIBRA);
        Dinero yenes = new Dinero(0, TipoMoneda.YEN);
        euros = myMoney.convierteEn(TipoMoneda.EURO);
        dolares = myMoney.convierteEn(TipoMoneda.DOLAR);
        libras = myMoney.convierteEn(TipoMoneda.LIBRA);
        yenes = myMoney.convierteEn(TipoMoneda.YEN);
        System.out.println(euros.toString());
        System.out.println(dolares.toString());
        System.out.println(libras.toString());
        System.out.println(yenes.toString());
        System.out.println();
        System.out.println("toString(EURO)");
        System.out.println(myMoney.toString(TipoMoneda.EURO));
        System.out.println("toString(DOLAR)");
        System.out.println(myMoney.toString(TipoMoneda.DOLAR));
        System.out.println("toString(LIBRA)");
        System.out.println(myMoney.toString(TipoMoneda.LIBRA));
        System.out.println("toString(YEN)");
        System.out.println(myMoney.toString(TipoMoneda.YEN));
    }
}