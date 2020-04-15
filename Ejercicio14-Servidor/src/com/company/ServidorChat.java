package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServidorChat
{
    //Reset
    public static final String ANSI_RESET = "\u001B[0m";
    //Colores de letra
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";


    static List<PrintWriter> listaWriters = new ArrayList<PrintWriter>();

    static void lanzaServidor()
    {
        DataOutputStream out;
        try {
            ServerSocket servidor = new ServerSocket(9012);
            while(true) {
                Socket soc = servidor.accept();
                System.out.println("Conectado " + soc.toString());

                HiloServidor hs = new HiloServidor(soc);
                Thread hilo = new Thread(hs);
                hilo.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static class HiloServidor implements Runnable
    {
        Socket socket;

        volatile boolean salir = false;

        HiloServidor(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            String colores[] = {ANSI_BLUE, ANSI_CYAN, ANSI_GREEN, ANSI_PURPLE, ANSI_RED, ANSI_YELLOW};
            try
            {
                LocalDateTime ahora = LocalDateTime.now();
                Scanner sc = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Añadimos el PrintWriter a la lista
                listaWriters.add(out);

                // El primer mensaje es el nombre de usuario
                String name = sc.nextLine();
                System.out.println("[LOG] " + ahora.toString() + " El usuario se llama " + name);
                // Mensaje de bienvenida sólo al Cliente
                out.println(ANSI_RED + "Welcome " + name + ANSI_RESET);

                // mensaje al grupo anunciando la conexion y nombre de usuario
                for (int i = 0; i < listaWriters.size(); i++) {
                    listaWriters.get(i).println("El usuario " + name + " se ha unido al Chat");
                    //listaWriters.get(i).toString() Identificador del objeto
                }
                String mensaje = "";
                while(!mensaje.equals("/salir"))
                {
                    mensaje = sc.nextLine();
                    for (int i = 0; i < listaWriters.size(); i++) {
                        // Enviando mensaje a todos, excepto al cliente de este hilo,
                        if(listaWriters.get(i)!=out && !mensaje.equals("/salir")) {
                            System.out.println("[LOG] " + ahora.toString() + " " + name + ": " + mensaje);
                            listaWriters.get(i).println(colores[i%6] + name + ANSI_RESET + ": " + mensaje);
                        }
                    }
                }
                System.out.println("[LOG] " + ahora.toString() + " Usuario " + name + " desconectado");
                for (int i = 0; i < listaWriters.size(); i++) {
                    if(listaWriters.get(i)!=out) {
                        listaWriters.get(i).println(colores[i%6] + name + " Desconectado " + ANSI_RESET);
                    }
                }
                listaWriters.remove(out);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        public void stop()
        {
            salir = true;
        }
    }
}
