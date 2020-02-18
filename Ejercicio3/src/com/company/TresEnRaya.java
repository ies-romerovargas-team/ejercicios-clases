package com.company;

import java.util.Random;

public class TresEnRaya {
    // Atributos
    int[][] tablero;

    // Constructor
    public TresEnRaya()
    {
        tablero = new int[3][3];
    }

    // métodos
     public void muevejugador1(int pos)
     {
         int fila = pos / 3;
         int col = pos % 3;
         if (tablero[fila][col] == 0) {
             tablero[fila][col] = 1;
         }
     }

    public void muevejugador2(int pos)
    {
        int fila = pos / 3;
        int col = pos % 3;
        if (tablero[fila][col] == 0){
            tablero[fila][col] = 2;
        }
    }
    public boolean movimientoValido(int pos){
        int fila = pos / 3;
        int col = pos % 3;
        return tablero[fila][col] == 0;
    }

    public void mueveOrdenador1()
    {
        // aleatorio
        Random r = new Random();
        int pos = r.nextInt(9);
        while(!movimientoValido(pos))
        {
            pos = r.nextInt(9);
        }
        muevejugador1(pos);
    }

    public void mueveOrdenador2()
    {
        // aleatorio
        Random r = new Random();
        int pos = r.nextInt(9);
        while(!movimientoValido(pos))
        {
            pos = r.nextInt(9);
        }
        muevejugador2(pos);
    }

    public void iniciar()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = 0;
            }
        }
    }

    public boolean quedanMovimientos()
    {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(tablero[i][j] == 0) return true;
            }
        }
        return false;
    }

    public boolean ganaJugador1()
    {
        // comprobar filas:
        for (int i = 0; i < 3; i++) {
            if(tablero[i][0] == 1 && tablero[i][1] == 1 && tablero[i][2] == 1) return true;
        }
        // comprobar columnas
        for (int i = 0; i < 3; i++) {
            if(tablero[0][1] == 1 && tablero[1][i] == 1 && tablero[2][i] == 1) return true;
        }
        // comprobar diagonal \
        if(tablero[0][0] == 1 && tablero[1][1] == 1 && tablero[2][2] == 1) return true;
        // comprobar diagonal /
        if(tablero[0][2] == 1 && tablero[1][1] == 1 && tablero[2][0] == 1) return true;
        //
        return false;
    }

    public boolean ganaJugador2()
    {
        // comprobar filas:
        for (int i = 0; i < 3; i++) {
            if(tablero[i][0] == 2 && tablero[i][1] == 2 && tablero[i][2] == 2) return true;
        }
        // comprobar columnas
        for (int i = 0; i < 3; i++) {
            if(tablero[0][1] == 2 && tablero[1][i] == 2 && tablero[2][i] == 2) return true;
        }
        // comprobar diagonal \
        if(tablero[0][0] == 2 && tablero[1][1] == 2 && tablero[2][2] == 2) return true;
        // comprobar diagonal /
        if(tablero[0][2] == 2 && tablero[1][1] == 2 && tablero[2][0] == 2) return true;
        //
        return false;
    }

    public void dibujaTablero()
    {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
        System.out.println("-  0  1  2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                switch (tablero[i][j])
                {
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print(" X ");
                        break;
                    case 2:
                        System.out.print(" O ");
                        break;
                }
            }
            System.out.println();
        }
    }
}
