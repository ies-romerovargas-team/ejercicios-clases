package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    List<Pokemon> listPokemon;

    public Pokedex(){
        listPokemon = new ArrayList<>();
    }

    public Pokedex(String filename)
    {
        listPokemon = new ArrayList<>();
        leeCSV(filename);
    }

    private void leeCSV(String filename) {
        int id;
        String nombre;
        String tipo;
        int ataque;
        int defensa;
        int vida;
        int ataqueespecial;
        int defensaespecial;
        int velocidad;
        String habilidad;
        boolean capturado;
        try
        {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            String texto = "";
            while(texto != null)
            {
                String valores[];
                valores = texto.split(",");
                if(valores.length>1){
                    id = Integer.valueOf(valores[0]);
                    nombre = valores[1];
                    tipo = valores[2];
                    ataque = Integer.valueOf(valores[3]);
                    defensa = Integer.valueOf(valores[4]);
                    vida = Integer.parseInt(valores[5]);
                    ataqueespecial = Integer.parseInt(valores[6]);
                    defensaespecial = Integer.parseInt(valores[7]);
                    velocidad = Integer.parseInt(valores[8]);
                    habilidad = valores[9];
                    if (valores[10].equals("true")) capturado = true;
                    else
                        capturado = false;
                    Pokemon pokemon = new Pokemon(id, nombre, tipo, ataque, defensa, vida, ataqueespecial, defensaespecial, velocidad, habilidad, capturado);
                    listPokemon.add(pokemon);
                }
                texto = br.readLine();
            }
            br.close();
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void guardaCSV(String filename)
    {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listPokemon.size(); i++) {
                bw.write(listPokemon.get(i).getId() + ",");
                bw.write(listPokemon.get(i).getNombre() + ",");
                bw.write(listPokemon.get(i).getTipo() + ",");
                bw.write(listPokemon.get(i).getAtaque() + ",");
                bw.write(listPokemon.get(i).getDefensa() + ",");
                bw.write(listPokemon.get(i).getVida() + ",");
                bw.write(listPokemon.get(i).getAtaqueespecial() + ",");
                bw.write(listPokemon.get(i).getDefensaespecial() + ",");
                bw.write(listPokemon.get(i).getVelocidad() + ",");
                bw.write(listPokemon.get(i).getHabilidad() + ",");
                if(listPokemon.get(i).getCapturado()) bw.write("true");
                else
                    bw.write("false");
                bw.write(System.lineSeparator());
            }
            bw.close();
            fw.close();
        }
        catch (IOException e)
        {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public boolean capturaPokemon(String nombre)
    {
        for (int i = 0; i < listPokemon.size(); i++) {
            if(listPokemon.get(i).getNombre().equals(nombre)) {
                listPokemon.get(i).setCapturado(true);
                return true;
            }
        }
        return false;
    }

    public double porcentajeCapturas()
    {
        int tot = 0, cap = 0;
        for (int i = 0; i < listPokemon.size(); i++) {
            tot++;
            if(listPokemon.get(i).getCapturado()==true) cap++;
        }
        return (double)(cap * 100) / tot;
    }

    public Pokemon buscaPokemon(int id)
    {
        for (int i = 0; i < listPokemon.size(); i++) {
            if(listPokemon.get(i).getId()==id)
                return listPokemon.get(i);
        }
        return null;
    }

    public Pokemon buscaPokemon(String nombre)
    {
        for (int i = 0; i < listPokemon.size(); i++) {
            if(listPokemon.get(i).getNombre().equals(nombre))
                return listPokemon.get(i);
        }
        return null;
    }

    public String listadoPokemon()
    {
        String string =   "ID\tNOMBRE\t\tTIPO\t\tCAPTURADO\n";
        string = string + "===============================\n";
        for (int i = 0; i < listPokemon.size(); i++) {
            string = string + listPokemon.get(i).getId() + "\t";
            string = string + listPokemon.get(i).getNombre() + "\t";
            string = string + listPokemon.get(i).getTipo() + "\t";
            if(listPokemon.get(i).getCapturado()) string = string + "SI" + "\n";
            else
                string = string + "NO" + "\n";
        }
        return string;
    }

    public String listadoPokemonCapturados()
    {
        String string =   "POKEMONS CAPTURADOS\n";
        string = string + "ID\tNOMBRE\t\tTIPO\n";
        string = string + "====================\n";
        for (int i = 0; i < listPokemon.size(); i++) {
            if(listPokemon.get(i).getCapturado())
            {
                string = string + listPokemon.get(i).getId() + "\t";
                string = string + listPokemon.get(i).getNombre() + "\t";
                string = string + listPokemon.get(i).getTipo() + "\n";
            }
        }
        return string;
    }

    public String listadoPokemonTipo(String tipo)
    {
        String string =   "POKEMONS POR TIPO\n";
        string = string + "ID\tNOMBRE\t\tCAPTURADO\n";
        string = string + "========================\n";
        for (int i = 0; i < listPokemon.size(); i++) {
            if(listPokemon.get(i).getTipo().equals(tipo))
            {
                string = string + listPokemon.get(i).getId() + "\t";
                string = string + listPokemon.get(i).getNombre() + "\t";
                if(listPokemon.get(i).getCapturado()) string = string + "SI" + "\n";
                else
                    string = string + "NO" + "\n";
            }
        }
        return string;
    }

    public String listadoPokemonCapturadosTipo(String tipo)
    {
        String string =   "POKEMONS CAPTURADOS POR TIPO\n";
        string = string + "ID\tNOMBRE\n";
        string = string + "===========\n";
        for (int i = 0; i < listPokemon.size(); i++) {
            if(listPokemon.get(i).getTipo().equals(tipo) && listPokemon.get(i).getCapturado())
            {
                string = string + listPokemon.get(i).getId() + "\t";
                string = string + listPokemon.get(i).getNombre() + "\n";
            }
        }
        return string;
    }
}
