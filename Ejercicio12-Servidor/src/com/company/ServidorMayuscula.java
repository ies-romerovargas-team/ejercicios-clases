package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMayuscula {

    static void lanzaServidor()
    {
        try
        {
            ServerSocket servidor = new ServerSocket(9010);

            while(true)
            {
                Socket soc = servidor.accept();
                Runnable nuevoCliente = new HiloServidor(soc);
                Thread hilo = new Thread(nuevoCliente);
                hilo.start();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static class HiloServidor implements Runnable
    {
        Socket socket;
        private volatile int i;


        public HiloServidor(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            DataInputStream in;
            DataOutputStream out;
            try
            {
                //TODO: Devolver el texto en mayúsculas
                System.out.println("Conectado un cliente");
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                String mensajeRecibido = in.readUTF();
                System.out.println("Recibido: " + mensajeRecibido);
                // devolver mayusculas
                mensajeRecibido = mensajeRecibido.toUpperCase();
                out.writeUTF(mensajeRecibido);
                System.out.println("Enviado: " + mensajeRecibido);

                socket.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
