package com.company;

import java.util.ArrayList;
import java.util.List;

public class Coleccion {
    List<Videojuego> lista;

    public Coleccion()
    {
        lista = new ArrayList<>();
    }

    public void insertaVideojuego(Videojuego v)
    {
        lista.add(v);
    }

    public void eliminaVideojuego (int pos)
    {
        lista.remove(pos);
    }

    public Videojuego getVideojuego(int pos)
    {
        return lista.get(pos);
    }

    @Override
    public String toString()
    {
        String devuelve;
        // cabecera
        devuelve =              "Videojuego                    Año    Plataforma      Tipo de juego   Val.\n";
        devuelve = devuelve +   "-------------------------------------------------------------------------\n";
        for (int i = 0; i < lista.size(); i++) {
            Plataforma plat = lista.get(i).getPlataforma();
            TipoJuego tipo = lista.get(i).getTipoJuego();
            devuelve = devuelve + (lista.get(i).getNombre() + "               ").substring(0, 29);
            devuelve = devuelve + " " + lista.get(i).getAnno() + "   ";
            devuelve = devuelve + (plat + "                       ").substring(0, 15);
            devuelve = devuelve + " " + (tipo + "               ").substring(0, 15);
            devuelve = devuelve + " " + lista.get(i).getValoracion() + "\n";
        }
        return devuelve;
    }

    public String toStringNum()
    {
        String devuelve = "";
        for (int i = 0; i < lista.size(); i++) {
            devuelve = devuelve + (i + 1) + " " + lista.get(i).getNombre() + "\n";
        }
        return devuelve;
    }
}
