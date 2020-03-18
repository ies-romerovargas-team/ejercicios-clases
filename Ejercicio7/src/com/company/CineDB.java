package com.company;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CineDB {
    Connection conn;

    //Constructor
    public CineDB(String databasename)
    {
        String connString = "jdbc:sqlite:" + databasename;
        try {
            conn = DriverManager.getConnection(connString);
            Statement st = conn.createStatement();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //Metodos
    public int insertaPelicula(String titulo, int anno, boolean tieneOscar, double valoracion)
    {
        try {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO peliculas (titulo, anno, tieneOScar, valoracion) " +
                    "VALUES ('" + titulo + "', '"+ anno +"', "+ tieneOscar + ", "+ valoracion +")";
            st.execute(sql);
            // para saber el id del ultimo insert
            String sqlId = "SELECT last_insert_rowid();";
            ResultSet rsid = st.executeQuery(sqlId);
            rsid.next();
            int idInsertado = rsid.getInt(1); // la primera -y en este caso unica- columna es 1
            return idInsertado;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertaActor(String nombre, LocalDate fechaNacimiento)
    {
        try {
            Statement st = conn.createStatement();
            String sql = "INSERT INTO actores (nombre, fechanacimiento) " +
                    "VALUES ('" + nombre + "', '" + fechaNacimiento + "')";
            st.execute(sql);
            // para saber el id del ultimo insert
            String sqlId = "SELECT last_insert_rowid();";
            ResultSet rsid = st.executeQuery(sqlId);
            rsid.next();
            return rsid.getInt(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public void asociaActorPelicula(int idActor, int idPelicula, boolean principal)
    {
        try
        {
            Statement st = conn.createStatement();
            // SI NO EXISTE UNA TUPLA CON EL MISMO ACTOR Y MISMA PELICULA HACER UN INSERT
            String sql = "SELECT * FROM actoresPeliculas WHERE idActor ="+ idActor + " AND idPelicula = " + idPelicula + ";";
            ResultSet rs = st.executeQuery(sql);
            if (!rs.next()) {
                sql = "INSERT INTO actoresPeliculas" + " VALUES (" + idActor + ", " + idPelicula + ", " + principal + ")";
                st.execute(sql);
            }
            else
            {
                System.out.println("Ya se encuentran asociados actor y pelicula");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public int buscaActor(String nombre)
    {
        try
        {
            Statement st = conn.createStatement();
            String sql = "SELECT id FROM actores WHERE nombre LIKE '%" + nombre + "%'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                return rs.getInt("id");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public int buscaPelicula(String titulo)
    {
        try
        {
            Statement st = conn.createStatement();
            String sql = "SELECT id FROM peliculas WHERE titulo LIKE '%" + titulo + "%'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                return rs.getInt("id");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public Pelicula datosPelicula(int idPelicula)
    {
        try
        {
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM peliculas WHERE id = " + idPelicula +";";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
            {
                Pelicula datos = new Pelicula();
                datos.id = idPelicula;
                datos.titulo = rs.getString("titulo");
                datos.anno = rs.getInt("anno");
                datos.tieneOscar = rs.getBoolean("tieneOscar");
                datos.valoracion = rs.getDouble("valoracion");
                return datos;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Actor datosActor(int idActor)
    {
        try
        {
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM actores WHERE id = " + idActor +";";
            ResultSet rs = st.executeQuery(sql);
            if(rs.next())
            {
                Actor datos = new Actor();
                datos.id = idActor;
                datos.nombre = rs.getString("nombre");
                datos.fechaNacimiento = LocalDate.parse(rs.getString("fechanacimiento"));
                return datos;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pelicula> listadoPeliculas()
    {
        try
        {
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM peliculas";
            ResultSet rs = st.executeQuery(sql);
            List<Pelicula> listFilms = new ArrayList<>();
            while (rs.next())
            {
                Pelicula film = new Pelicula();
                film.id = rs.getInt("id");
                film.titulo = rs.getString("titulo");
                film.anno = rs.getInt("anno");
                film.tieneOscar = rs.getBoolean("tieneOscar");
                film.valoracion = rs.getDouble("valoracion");
                listFilms.add(film);
            }
            return listFilms;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Actor> listadoActores()
    {
        try
        {
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM actores";
            ResultSet rs = st.executeQuery(sql);
            List<Actor> listStar = new ArrayList<>();
            while (rs.next())
            {
                Actor actor = new Actor();
                actor.id = rs.getInt("id");
                actor.nombre = rs.getString("nombre");
                actor.fechaNacimiento = LocalDate.parse(rs.getString("fechanacimiento"));
                listStar.add(actor);
            }
            return listStar;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pelicula> listadoPeliculasPorActores(int idActor)
    {
        try
        {
            Statement st = conn.createStatement();
            String sql = "SELECT idPelicula FROM actoresPeliculas WHERE idActor = " + idActor + ";";
            ResultSet rs = st.executeQuery(sql);
            List<Pelicula> listFilms = new ArrayList<>();
            while (rs.next())
            {
                listFilms.add(datosPelicula(rs.getInt("idPelicula")));
            }
            return listFilms;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<ActorPelicula> listadoActoresPorPelicula(int idPelicula)
    {
        try
        {
            Statement st = conn.createStatement();
            String sql = "SELECT idActor, principal FROM actoresPeliculas WHERE idPelicula = " + idPelicula + ";";
            ResultSet rs = st.executeQuery(sql);
            Actor start = new Actor();
            List<ActorPelicula> listStart = new ArrayList<>();
            while (rs.next())
            {
                ActorPelicula a = new ActorPelicula();
                start = datosActor(rs.getInt("idActor"));
                a.id = start.id ;
                a.fechaNacimiento = start.fechaNacimiento;
                a.nombre = start.nombre;
                a.principal = rs.getBoolean("principal");
                listStart.add(a);
            }
            return listStart;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
