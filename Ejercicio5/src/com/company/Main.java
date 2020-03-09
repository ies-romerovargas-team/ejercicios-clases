package com.company;

public class Main {

    public static void main(String[] args) {
        Dinero myMoney = new Dinero(23.45,TipoMoneda.DOLAR);
        System.out.println(myMoney.toString());
    }
}