package com.company;

import java.security.InvalidParameterException;

public class Carta {

    /*
    	1	2	3	4	5	6	7	8	9	10
    0	1	2	3	4	5	6	7	8	9	10
    1	11	12	13	14	15	16	17	18	19	20
    2	21	22	23	24	25	26	27	28	29	30
    3	31	32	33	34	35	36	37	38	39	40
    */
    // ♠♥♣♦
    // Atributos
    int numero;
    int palo;

    // Constructores
    public Carta(){
    }

    public Carta(int numero, int palo)
    {
        this.numero = numero;
        this.palo = palo;
    }

    public Carta(int id)
    {
        if(id < 1 || id >40)
        {
            throw new InvalidParameterException("Valor no válido");
        }
        else
        {
            if(id % 10 == 0)
            {
                this.palo = (int) (id / 10) - 1;
                this.numero = 10;
            }
            else
            {
                this.palo = (int) (id / 10);
                this.numero = id % 10;
            }
        }
    }

    //Propiedades
    public int getNumero() {
        return numero;
    }
    public int getPalo(){
        return palo;
    }

    //Metodos
    public String nombreNumero()
    {
        String [] nombres = {"", "A", "2", "3", "4", "5", "6", "7", "J", "Q", "K"};
        return nombres[this.numero];
    }

    public String nombrePalo()
    {
        String [] nombres = {"oros", "copas", "espadas", "bastos"};
        return nombres[this.palo];
    }

    public String nombreCarta()
    {
        return nombreNumero() + " de " + nombrePalo();
    }

    public int valorTute()
    {
        int[] valor = {0, 11, 0, 10, 0, 0, 0, 0, 2, 3, 4};
        return valor[this.numero];
    }

    public int valorMus()
    {
        int[] valor = {0, 1, 1, 10, 4, 5, 6, 7, 10, 10, 10};
        return valor[this.numero];
    }

    public double valor7yMedia()
    {
        double[] valor = {0, 1, 2, 3, 4, 5, 6, 7, 0.5, 0.5, 0.5};
        return valor[this.numero];
    }

}
