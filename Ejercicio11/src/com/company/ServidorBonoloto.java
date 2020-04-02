package com.company;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorBonoloto
{
    Random r;

    static void lanzaServidor()
    {
        // abriremos  un ServerSocket en el  puerto  9009
        try
        {
            ServerSocket server = new ServerSocket(9009);
            ExecutorService pool = Executors.newFixedThreadPool(20);

            while(true)
            {
                Socket soc = server.accept();
                pool.execute(new subProceso(soc));
                System.out.println("Conectado: " + soc);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static class subProceso implements Runnable
    {
        private Socket soc;

        public subProceso(Socket soc)
        {
            this.soc = soc;
        }

        public void run()
        {
            try
            {
                PrintWriter pw = new PrintWriter(soc.getOutputStream(), true);

                for (int i = 1; i < 1000; i++)
                {
                    pw.println(i);
                    Thread.sleep(1000);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
