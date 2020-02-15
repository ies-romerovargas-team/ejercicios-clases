package com.company;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Baraja {

    // Atributos
    List<Carta> listaCartas;

    // Constructores
    public Baraja()
    {
        listaCartas = new ArrayList<>();
    }

    public Baraja(int tipoBaraja)
    {
        listaCartas = new ArrayList<>();
        if(tipoBaraja!=1 && tipoBaraja!=2)
        {
            throw new InvalidParameterException("Tipo no válido");
        }
        else
        {
            for (int i = 1; i <= 40; i++){
                Carta a = new Carta(i);
                listaCartas.add(a);
                if(tipoBaraja==2) listaCartas.add(a);
            }
        }
    }

    public Baraja(int tipoBaraja, boolean barajar)
    {
        Baraja mazo = new Baraja(tipoBaraja);
        mazo.barajar();
    }

    // Métodos
    public void barajar()
    {
        Random r = new Random();
        List<Carta> mazo1 = new ArrayList<>();
        while (listaCartas.size() != 0)
        {
            int azar = r.nextInt(listaCartas.size());
            mazo1.add(listaCartas.get(azar));
            listaCartas.remove(azar);
        }
        listaCartas.addAll(mazo1);
    }

    public void cortar(int numeroCartas)
    {
        for (int i = 0; i < numeroCartas; i++) {
            Carta a = new Carta();
            a = listaCartas.get(0);
            listaCartas.remove(0);
            listaCartas.add(a);
        }
    }

    public Carta robar()
    {
        Carta a = new Carta();
        a = listaCartas.get(0);
        listaCartas.remove(0);
        return a;
    }

    public void insertaCartaFinal(int idCarta)
    {
        Carta a = new Carta(idCarta);
        listaCartas.add(a);
    }

    public void insertaCartaPrincipio(int idCarta)
    {
        Carta a = new Carta(idCarta);
        listaCartas.add(0,a);
    }

    public void insertaCartaFinal(Carta carta)
    {
        listaCartas.add(carta);
    }

    public void insertaCartaPrincipio(Carta carta)
    {
        listaCartas.add(0, carta);
    }

    public int numeroCartas()
    {
        return listaCartas.size();
    }

    public boolean vacia()
    {
     return listaCartas.size() == 0;
    }

    public Carta leeBaraja(int orden)
    {
        Carta a = new Carta();
        a = listaCartas.get(orden);
        return a;
    }

}
