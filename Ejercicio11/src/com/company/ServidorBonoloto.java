package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorBonoloto
{
    Random r;

    static void lanzaServidor()
    {
        ServerSocket servidor = null;
        DataInputStream in;
        DataOutputStream out;
        try
        {
            servidor = new ServerSocket(9009);
            System.out.println("Servidor iniciado");
            while(true)
            {
                Socket soc = servidor.accept();
                System.out.println("Cliente in");

                in = new DataInputStream(soc.getInputStream());
                out = new DataOutputStream(soc.getOutputStream());

                String mensajeRecibido = in.readUTF();
                if(mensajeRecibido.equals("Init"))
                {
                    System.out.println(mensajeRecibido);
                    out.writeUTF("La combinacion ganadora es...");
                }

                soc.close();
                System.out.println("Cliente out");

            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<Integer> sorteoBonoloto()
    {
        Random r = new Random();

        //Creamos lista con 49 números
        ArrayList<Integer> bombo = new ArrayList<Integer>();
        for (int i = 1; i < 50; i++) {
            bombo.add(i);
        }
        //Creamos lista vacia
        ArrayList<Integer> combinacion = new ArrayList<Integer>();
        for (int j = 0; j < 6; j++) {
            int max = 49 - j;
            int aleatorio = r.nextInt(max);
            combinacion.add(bombo.get(aleatorio));
            bombo.remove(aleatorio);
        }
        return combinacion;
    }
}
