package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique nombre de usuario: ");
        String usuario = sc.next();
        Cliente cliente = new Cliente();
        cliente.conectar("127.0.0.1", usuario);
    }
}
