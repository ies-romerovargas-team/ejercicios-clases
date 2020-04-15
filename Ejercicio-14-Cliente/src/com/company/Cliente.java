package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente
{
    static void conectar(String ip, String usuario)
    {
        PrintWriter out;
        try
        {
            String msgEnviar = "";
            Scanner sc = new Scanner(System.in);
            Socket soc = new Socket(ip, 9012);
            HiloRecibir hr = new HiloRecibir(soc);
            Thread hilo = new Thread(hr);
            hilo.start();
            out = new PrintWriter(soc.getOutputStream(), true);
            out.println(usuario);
            while(!msgEnviar.equals("/salir"))
            {
                msgEnviar = sc.nextLine();
                out.println(msgEnviar);
                out.flush();
            }
            soc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static class HiloRecibir implements Runnable
    {
        Socket socket;

        HiloRecibir(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            try
            {
                Scanner sc = new Scanner(socket.getInputStream());

                while(true)
                {
                    System.out.println(sc.nextLine());
                }
            }
            catch (Exception e)
            {
                //e.printStackTrace();
                System.out.println("Conexión finalizada");

            }
        }
    }

}
