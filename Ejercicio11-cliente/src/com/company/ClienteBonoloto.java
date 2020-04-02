package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClienteBonoloto
{
    private int[] boleto = new int[6];

    public ClienteBonoloto(int[] numeros)
    {
        boleto = numeros;
    }

    public int conectaCliente(String ip)
    {
        DataInputStream in;
        DataOutputStream out;
        try
        {
            Socket soc = new Socket(ip, 9009);

            in = new DataInputStream(soc.getInputStream());
            out = new DataOutputStream(soc.getOutputStream());

            out.writeUTF("Init");

            System.out.println(in.readUTF());
            soc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 1;
    }
}
