package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BuscaTexto {
    private String texto;
    private int puntero;

    // Constructores
    public BuscaTexto(){
        this.texto = "";
    }

    public BuscaTexto(String cadena)
    {
        this.texto = cadena;
    }

    // Métodos
    private void cargaFicheros(String fichero)
    {
        // lee el fichero de texto que le pasamos y guarda su contenido en el atributo texto
        String textFile = "";
        try
        {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea = br.readLine();
            while(linea != null)
            {
                textFile = textFile + linea + "\n";
                linea = br.readLine();
            }

            br.close();
            fr.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        this.texto = textFile;
    }

    private void reinicia()
    {
        // pone el puntero a 0
        this.puntero = 0;
    }

    private boolean busca(String cadena)
    {
        // busca la cadena dentro del texto y guarda esa posición en el puntero. Si la cadena no se encuentra, no
        // tocaremos el puntero y devolveremos false. Siempre buscará desde el principio.
        String texto = this.texto;
        int encuentra = texto.indexOf(cadena);
        if (encuentra==0) return false;
        this.puntero = encuentra;
        return true;
    }


}
