package com.company;

public class Videojuego {
    String nombre;
    int anno;
    Plataforma plataforma;
    TipoJuego tipoJuego;
    int valoracion;

    public int getAnno() {
        return anno;
    }

    public int getValoracion() {
        return valoracion;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoJuego getTipoJuego() {
        return tipoJuego;
    }

    public void setAnno(int anno) {
        if (anno >= 1970 && anno <= 2100)
        {
            this.anno = anno;
        }
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public void setTipoJuego(TipoJuego tipoJuego) {
        this.tipoJuego = tipoJuego;
    }

    public void setValoracion(int valoracion) {
        if (valoracion>=0 && valoracion <= 100) {
            this.valoracion = valoracion;
        }
    }

    public Videojuego(String nombre, int anno, Plataforma plataforma, TipoJuego tipoJuego, int valoracion)
    {
        try
        {
            if(nombre.equals("")) new Exception("El nombre no puede estar vacío");
            setNombre(nombre);
            setAnno(anno);
            setPlataforma(plataforma);
            setTipoJuego(tipoJuego);
            setValoracion(valoracion);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString()
    {
        String linea;
        linea = getNombre();
        linea = linea + "|" + getAnno();
        linea = linea + "|" + getPlataforma();
        linea = linea + "|" + getTipoJuego();
        linea = linea + "|" + getValoracion();
        return linea;
    }

}
