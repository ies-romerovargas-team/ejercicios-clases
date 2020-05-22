package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        double d;
        Longitud myLenght = new Longitud(0, UnidadLongitud.metros);
        while (!salir) {
            //Menú de opciones

            System.out.println();
            System.out.println("# Menú");
            System.out.println("");
            System.out.println("Valor actual: \u001B[34m" + myLenght.toString() + "\u001B[0m");
            System.out.println("");
            System.out.println("1. Actualizar longitud");
            System.out.println("2. Convertir a metros");
            System.out.println("3. Sumar longitud");
            System.out.println("4. Restar longitud");
            System.out.println("5. Multiplicar");
            System.out.println("6. Dividir");
            System.out.println("7. Comparador");
            System.out.println();
            System.out.println("0. Salir");
            System.out.println();
            System.out.print("Elige opcion: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // limpia buffer
            switch (opcion)
            {
                case 1:
                    myLenght = crearUnidad();
                    break;
                case 2:
                    System.out.println(myLenght.valorEnMetros() + " m");
                    break;
                case 3:
                    Longitud toSum = crearUnidad();
                    myLenght.add(toSum);
                    break;
                case 4:
                    Longitud toSub = crearUnidad();
                    myLenght.substract(toSub);
                    break;
                case 5:
                    System.out.print("Indique una cantidad: ");
                    d = sc.nextDouble();
                    myLenght.multiply(d);
                    break;
                case 6:
                    System.out.print("Indique una cantidad: ");
                    d = sc.nextDouble();
                    if(d!=0) {
                        myLenght.divide(d);
                    } else System.out.println("Error. No es posible dividir por 0");
                    break;
                case 7:
                    Longitud toComp = crearUnidad();
                    if(myLenght.compareTo(toComp)==0)
                    {
                        System.out.println("Son iguales");
                    } else if (myLenght.compareTo(toComp) < 0)
                    {
                        System.out.println(myLenght + " es menor que " + toComp);
                    } else {
                        System.out.println(myLenght + " es mayor que " + toComp);
                    }
                    break;
                case 0:

                    salir = true;
                    break;
            }
        }
    }

    private static Longitud crearUnidad() {
        Longitud l = new Longitud(0, UnidadLongitud.metros);
        Scanner sc = new Scanner(System.in);
        System.out.print("Indique una cantidad: ");
        double d = sc.nextDouble();
        sc.nextLine(); // limpia buffer
        System.out.print("Indique una Unidad de medida (m: metros | in: pulgadas | ft: pies | yr: yardas): ");
        String unit = sc.nextLine();
        l.setCantidad(d);
        while(true) {
            switch (unit) {
                case "m":
                    l.setUnidad(UnidadLongitud.metros);
                    return l;
                case "in":
                    l.setUnidad(UnidadLongitud.pulgadas);
                    return l;
                case "ft":
                    l.setUnidad(UnidadLongitud.pies);
                    return l;
                case "yr":
                    l.setUnidad(UnidadLongitud.yardas);
                    return l;
                default:
                    System.out.println("Unidad de medida incorrecta (m: metros | in: pulgadas | ft: pies | yr: yardas)");
            }
        }
    }
}
