package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServidorChat
{
    static List<PrintWriter> listaWriters = new ArrayList<PrintWriter>();

    static void lanzaServidor()
    {
        DataOutputStream out;
        try {
            ServerSocket servidor = new ServerSocket(9012);

            Socket soc = servidor.accept();
            System.out.println("Conectado " + soc.toString());

            HiloServidor hs = new HiloServidor(soc);
            Thread hilo = new Thread(hs);
            hilo.start();
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

            try
            {
                Scanner sc = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Añadimos el PrintWriter a la lista
                listaWriters.add(out);

                // El primer mensaje es el nombre de usuario
                String name = sc.nextLine();
                System.out.println(name);
                // contestamos al usuario
                out.println("Welcome " + name);

                // mensaje al grupo anunciando la conexion y nombre de usuario
                for (int i = 0; i < listaWriters.size(); i++) {
                    listaWriters.get(i).println("Bienvenido " + name);
                }
                String mensaje = "";
                while(!mensaje.equals("/salir"))
                {
                    mensaje = sc.nextLine();
                    for (int i = 0; i < listaWriters.size(); i++) {
                        listaWriters.get(i).println(name + ": " + mensaje);
                    }
                }
                out.println("Usuario " + name + " se ha ido.");
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
