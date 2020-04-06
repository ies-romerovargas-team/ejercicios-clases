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

            while(!msgEnviar.equals(""))
            {
                out = new DataOutputStream(soc.getOutputStream());
                msgEnviar = sc.nextLine();
                out.writeUTF(msgEnviar);
            }
            hilo.stop();
            soc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    static void conectar(String ip)
    {
        try
        {
            Socket soc = new Socket(ip, 9011);
            HiloRecibir hr = new HiloRecibir(soc);
            Thread hilo = new Thread(hr);
            hilo.start();
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
                while(salir)
                {
                    in = new DataInputStream(socket.getInputStream());
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

    private static class HiloEnviar implements Runnable
    {
        Socket socket;

        volatile boolean salir = false;

        HiloEnviar(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            DataOutputStream in;
            Scanner sc = new Scanner(System.in);
            try
            {
                while(salir)
                {
                    in = new DataOutputStream(socket.getOutputStream());
                    String mensaje = sc.nextLine();
                    in.writeUTF(mensaje);
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
