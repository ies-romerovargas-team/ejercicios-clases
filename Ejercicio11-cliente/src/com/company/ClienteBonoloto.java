package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
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
        List<Integer> lista = new ArrayList<>();
        try
        {
            Socket soc = new Socket(ip, 9009);

            in = new DataInputStream(soc.getInputStream());
            out = new DataOutputStream(soc.getOutputStream());

            out.writeUTF("Init");

            String respuesta = in.readUTF();
            System.out.println(respuesta);
            if(respuesta.equals("Send")) {

                for (int i = 0; i < 6; i++) {
                    int num = in.readInt();
                    System.out.println(num);
                    lista.add(num);
                }
            }
            soc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        // Comprobando resultados
        int cont = 0;
        for (int i = 0; i < boleto.length; i++) {
            if(lista.contains(boleto[i]))
            {
                cont++;
            }
        }
        return cont;
    }
}
