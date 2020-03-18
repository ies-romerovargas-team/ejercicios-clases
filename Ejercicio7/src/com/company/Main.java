package com.company;

import javax.swing.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {

    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args)
    {
        String[] menu = {"insertaPelicula", "listarPeliculas", "listarActores", "listarPeliculasxActor", "informacionPelicula"};
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;
        CineDB dataBaseFilm = new CineDB("cine.db");
        while(!salir)
        {
            imprimeMenu(menu, "Menu principal", ANSI_BLUE);
            try
            {
                System.out.print("Eliga opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // buffer
                switch (opcion)
                {
                    case 1:
                        newFilm(dataBaseFilm);
                        break;
                    case 2:
                        listarPeliculas(dataBaseFilm);
                        break;
                    case 3:
                        listarActores(dataBaseFilm);
                        break;
                    case 4:
                        listarPeliculasxActor(dataBaseFilm);
                        break;
                    case 5:
                        informacionPelicula(dataBaseFilm);
                        break;
                    case 0:
                        salir = true;
                        break;
                    default:
                        System.out.println("ERROR: Opcion no válida");
                }
            }
            catch (InputMismatchException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static void informacionPelicula(CineDB base)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca título de la película: ");
        String nombre = sc.nextLine();
        int idPelicula = base.buscaPelicula(nombre);
        if(idPelicula != -1){
            Pelicula pelicula = base.datosPelicula(idPelicula);
            System.out.println(ANSI_BLUE + "Película:" + ANSI_RESET);
            System.out.println(pelicula.id + ". " + pelicula.anno + " | " + pelicula.titulo);
            System.out.println(ANSI_BLUE + "Elenco:" + ANSI_RESET);
            List<ActorPelicula> actores = base.listadoActoresPorPelicula(idPelicula);
            if(actores!=null)
            {
                for (int i = 0; i < actores.size(); i++) {
                    ActorPelicula a = actores.get(i);
                    System.out.println(a.id + ". " + a.principal + " | " + a.nombre + " | " + a.fechaNacimiento);
                }
            }
        }
    }

    private static void listarPeliculasxActor(CineDB base)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca nombre de actor/actriz: ");
        String nombre = sc.nextLine();
        int idActor = base.buscaActor(nombre);
        if(idActor!=-1){
            List<Pelicula> listado = base.listadoPeliculasPorActores(idActor);
            for (int i = 0; i < listado.size(); i++) {
                Pelicula pelicula = listado.get(i);
                System.out.println(pelicula.id + ". " + pelicula.anno + " | " + pelicula.titulo);
            }
        } else System.out.println("Actor/actriz no encontrado/a");

    }

    private static void listarActores(CineDB dataBaseFilm)
    {
        List<Actor> listado = dataBaseFilm.listadoActores();
        for (int i = 0; i < listado.size(); i++) {
            Actor actor = listado.get(i);
            System.out.println(actor.id + ". " + actor.nombre + " | " + actor.fechaNacimiento);
        }
    }

    private static void listarPeliculas(CineDB dataBaseFilm)
    {
        List<Pelicula> listado = dataBaseFilm.listadoPeliculas();
        for (int i = 0; i < listado.size(); i++) {
            Pelicula pelicula = listado.get(i);
            System.out.println(pelicula.id + ". " + pelicula.anno + " | " + pelicula.titulo);
        }
    }

    private static void newFilm(CineDB base)
    {
        // capturar datos de la pelicula
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "Introduzca datos de la pelicula" + ANSI_RESET);
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        System.out.print("Año: ");
        int anno = sc.nextInt();
        System.out.print("¿Ha obtenido algún oscar? (true, false): ");
        boolean tieneOscar = sc.nextBoolean();
        System.out.print("Valoración (0-10): ");
        double val = sc.nextDouble();
        int res = base.insertaPelicula(titulo, anno, tieneOscar, val);
        if(res!=0)
        {
            System.out.println("Insertado registro id " + res);
            // bucle para añadir/buscar actores
            boolean continuar = true;
            System.out.println(ANSI_BLUE + "Introduzca actores. Teclee 'FIN' para finalizar"+ ANSI_RESET);
            while(continuar)
            {
                sc.nextLine(); // buffer
                System.out.print("Introduzca nombre de actor/actriz (o 'FIN'): ");
                String nombre = sc.nextLine();
                if(!nombre.equals("FIN"))
                {
                    int idActor = base.buscaActor(nombre);
                    boolean nuevo = true;
                    if(idActor!=-1)
                    {
                        // Hallado un actor
                        Actor actor = base.datosActor(idActor);
                        System.out.println("¿Quiere asociar el siguiente actor/actriz a la película? (s/n)");
                        System.out.println(ANSI_BLUE + actor.id + ": Nombre: " +ANSI_RESET + actor.nombre + ANSI_BLUE +" Fecha Nac: "+ ANSI_RESET + actor.fechaNacimiento);
                        String resp = sc.nextLine();
                        if(resp.equals("s"))
                        {
                            System.out.print("¿Es protagonista? (true, false): ");
                            boolean prota = sc.nextBoolean();
                            base.asociaActorPelicula(idActor, res, prota);
                            nuevo = false;
                        }
                    }
                    if(nuevo)
                    {
                        // NO se han encontrado coincidencias, luego seguimos
                        System.out.print("Introduzca fecha nacimiento 'yyyy-MM-dd': ");
                        String fechaNac = sc.nextLine();
                        LocalDate fechaNac2 = LocalDate.parse(fechaNac);
                        System.out.print("¿Es protagonista? (true, false): ");
                        boolean prota = sc.nextBoolean();
                        base.asociaActorPelicula(base.insertaActor(nombre,fechaNac2), res, prota);
                    }
                }
                else continuar = false;
            }
        }
    }

    public static void imprimeMenu(String[] opciones, String titulo, String color) {
        int Max = 15; // Ancho mínimo
        for (int i = 0; i < opciones.length; i++) {
            if (opciones[i].length() > Max) Max = opciones[i].length() + 1;
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
