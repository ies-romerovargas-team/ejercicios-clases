package com.company;

import java.net.Socket;
import java.util.Scanner;

public class ClienteBonoloto
{
    private int[] boleto;

    public ClienteBonoloto(int[] numeros)
    {
        boleto = numeros;
    }

    public int conectaCliente(String ip)
    {
        try
        {
            Socket soc = new Socket(ip, 9009);
            Scanner sc = new Scanner(soc.getInputStream());
            String linea = sc.nextLine();
            System.out.println(linea);
            soc.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 1;
    }
}
