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
    public void cargaFicheros(String fichero)
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

    public void reinicia()
    {
        // pone el puntero a 0
        this.puntero = 0;
    }

    public boolean busca(String cadena)
    {
        // busca la cadena dentro del texto y guarda esa posición en el puntero. Si la cadena no se encuentra, no
        // tocaremos el puntero y devolveremos false. Siempre buscará desde el principio.
        String texto = this.texto;
        int encuentra = texto.indexOf(cadena);
        if (encuentra == 0) {
            return false;
        }
        this.puntero = encuentra;
        return true;
    }

    public boolean buscaSiguiente(String cadena)
    {
        // busca la cadena dentro del texto a partir de la posición actual del puntero
        // y guarda el resultado en el puntero. Si no se encuentra, no tocamos el puntero y devolvemos false.
        String texto = this.texto;
        int encuentra = texto.indexOf(cadena, this.puntero);
        if (encuentra == 0) {
            return false;
        }
        this.puntero = encuentra;
        return true;
    }

    public String extraeCadena(String delimitador1, String delimitador2)
    {
        // A partir de la posición  actual  del  puntero,  busca  el delimitador1.
        // A  partir  de  ahí  irá guardando todos los caracteres que encuentre en una cadena hasta llegar al delimitador2
        // Devolverá la cadena obtenida. Si no se encuentra alguno de los dos delimitadores, nos devuelve null
        String devuelve = null;
        if (buscaSiguiente(delimitador1))
        {
            int inicio = this.puntero + delimitador1.length();
            if(buscaSiguiente(delimitador2))
            {
                int fin = this.puntero;
                devuelve = this.texto.substring(inicio, fin);
            }
        }
        return devuelve;
    }

    public String extraeCadenaConDelim(String delimitador1, String delimitador2)
    {
       // igual  que la  anterior,  pero en la cadena que nos devuelve incluye los delimitadores
        String devuelve = null;
        if (buscaSiguiente(delimitador1))
        {
            int inicio = this.puntero;
            if(buscaSiguiente(delimitador2))
            {
                int fin = this.puntero + delimitador2.length();
                devuelve = this.texto.substring(inicio, fin);
            }
        }
        return devuelve;
    }

    public int getPuntero() {
        return puntero;
    }

    public String getTexto() {
        return texto;
    }

    public void setPuntero(int puntero) {
        if (puntero < 0) puntero = 0;
        if (puntero > this.texto.length()) puntero = this.texto.length();
        this.puntero = puntero;
    }

}
