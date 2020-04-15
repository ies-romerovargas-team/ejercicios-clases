package com.company;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String opcion = "";
        // cargamos fichero CSV
        Pokedex pokemitas = new Pokedex();
        pokemitas = new Pokedex("pokemon.csv");

        while (!opcion.equals("0")) {
            System.out.println("Base de datos de Pokemon's");
            System.out.println("------------------------");
            System.out.println();
            System.out.println("1- Mostrar todos lo Pokemon's");
            System.out.println("2- Porcentaje de capturas");
            System.out.println("3- Busca un Pokemon por su ID");
            System.out.println("4- Busca un Pokemon por su Nombre");
            System.out.println("5- Captura a un Pokemon");
            System.out.println("6- Lista de Pokemon's capturados");
            System.out.println("7- Lista de Pokemon's por TIPO");
            System.out.println("8- Lista de Pokemon's capturados por TIPO");
            System.out.println("0- Salir");
            System.out.println();
            System.out.print("Opción: ");
            opcion = sc.nextLine();
            System.out.println("\n");
            switch (opcion) {
                case "1":
                    System.out.println(pokemitas.listadoPokemon());
                    break;
                case "2":
                    System.out.println("Capturado el " + getTwoDecimals(pokemitas.porcentajeCapturas()) + "%");
                    break;
                case "3":
                    System.out.print("Indica el Id del pokemon: ");
                    int id = sc.nextInt();
                    Pokemon pocketMonster = pokemitas.buscaPokemon(id);
                    if(pocketMonster!=null)
                    {
                        System.out.println(pocketMonster.toString());
                    }
                    else System.out.println("Pokemon " + id + " no existe");
                    sc.nextLine();
                    break;
                case "4":
                    System.out.print("Indica el nombre del pokemon: ");
                    String nombre = sc.nextLine();
                    pocketMonster = pokemitas.buscaPokemon(nombre);
                    if(pocketMonster!=null)
                    {
                        System.out.println(pocketMonster.toString());
                    }
                    else System.out.println("Pokemon " + nombre + " no existe");
                    break;
                case "5":
                    System.out.print("Indica el nombre del pokemon: ");
                    nombre = sc.nextLine();
                    if(pokemitas.capturaPokemon(nombre)) {
                        System.out.println("Pokemon " + nombre + " Capturado!!");
                    } else System.out.println("Pokemon " + nombre + " no existe");
                    break;
                case "6":
                    System.out.println(pokemitas.listadoPokemonCapturados());
                    break;
                case "7":
                    System.out.print("Indica el TIPO de pokemon: ");
                    String tipo = sc.nextLine();
                    System.out.println(pokemitas.listadoPokemonTipo(tipo));
                    break;
                case "8":
                    System.out.print("Indica el TIPO de pokemon: ");
                    tipo = sc.nextLine();
                    System.out.println(pokemitas.listadoPokemonCapturadosTipo(tipo));
                    break;
                case "0":
                    pokemitas.guardaCSV("pokemon.csv");
                    System.out.println("¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
            System.out.println("\n");
        }
    }

    private static String getTwoDecimals(double value){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }

}
