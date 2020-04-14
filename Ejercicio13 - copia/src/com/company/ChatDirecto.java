package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatDirecto
{
    static void escuchar()
    {
        DataOutputStream out;
        try
        {
            ServerSocket servidor = new ServerSocket(9011);
            Scanner sc = new Scanner(System.in);

            String msgEnviar = "Init";
            Socket soc = servidor.accept();
            // Implementamos una clase runnable
            HiloRecibir hr = new HiloRecibir(soc);
            // Lanzamos la tarea a través de un Thread
            Thread hilo = new Thread(hr);
            hilo.start();
            out = new DataOutputStream(soc.getOutputStream());
            while(!msgEnviar.equals(""))
            {
                msgEnviar = sc.nextLine();
                out.writeUTF(msgEnviar);
            }
            hr.stop();
            soc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    static void conectar(String ip)
    {
        DataOutputStream out;
        try
        {
            String msgEnviar = "Init";
            Scanner sc = new Scanner(System.in);
            Socket soc = new Socket(ip, 9011);
            HiloRecibir hr = new HiloRecibir(soc);
            Thread hilo = new Thread(hr);
            hilo.start();
            out = new DataOutputStream(soc.getOutputStream());
            while(!msgEnviar.equals(""))
            {
                msgEnviar = sc.nextLine();
                out.writeUTF(msgEnviar);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static class HiloRecibir implements Runnable
    {
        Socket socket;

        volatile boolean salir = false;

        HiloRecibir(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            DataInputStream in;
            try
            {
                System.out.println("Escuchando!!");
                in = new DataInputStream(socket.getInputStream());
                while(!salir)
                {
                    System.out.println("while");
                    String mensaje = in.readUTF();
                    System.out.println(mensaje);
                }
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
