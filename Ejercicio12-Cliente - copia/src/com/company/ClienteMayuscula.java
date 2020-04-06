package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMayuscula {

    static void conectaCliente(String ip)
    {

        try
        {
            Socket soc = new Socket(ip, 9010);

            HiloCliente hr = new HiloCliente(soc);
            Thread hilo = new Thread(hr);
            hilo.start();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static class HiloCliente implements Runnable
    {
        Socket socket;

        HiloCliente(Socket socket)
        {
            this.socket = socket;
        }

        public void run()
        {
            DataInputStream in;
            DataOutputStream out;
            try
            {
                Scanner sc = new Scanner(System.in);
                out = new DataOutputStream(socket.getOutputStream());
                in = new DataInputStream(socket.getInputStream());

                String n = sc.nextLine();
                out.writeUTF(n);

                String respuesta = in.readUTF();
                System.out.println(respuesta);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
