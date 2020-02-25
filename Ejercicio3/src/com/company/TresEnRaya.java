package com.company;

import java.util.Random;

public class TresEnRaya {
    // Atributos
    int[][] tablero;
    int nivel;

    // Constructor
    public TresEnRaya() {
        nivel = 0;
        tablero = new int[3][3];
    }

    // métodos
    public void setNivel(int n) {
        nivel = n;
    }

    public void muevejugador1(int pos) {
        int fila = pos / 3;
        int col = pos % 3;
        if (tablero[fila][col] == 0) {
            tablero[fila][col] = 1;
        }
    }

    public void muevejugador2(int pos) {
        int fila = pos / 3;
        int col = pos % 3;
        if (tablero[fila][col] == 0) {
            tablero[fila][col] = 2;
        }
    }

    public boolean movimientoValido(int pos) {
        int fila = pos / 3;
        int col = pos % 3;
        return tablero[fila][col] == 0;
    }

    public void mueveOrdenador1() {
        int pos = 9;
        Random r = new Random();
        if (nivel > 0) {
            // ia
            // 1ª Norma: ver si podemos ganar en este movimiento
            // comprobar filas:
            for (int i = 0; i < 3; i++) {
                if (tablero[i][0] == 1 && tablero[i][1] == 1 && tablero[i][2] == 0) pos = i + 2 + (i * 2);
                if (tablero[i][0] == 1 && tablero[i][1] == 0 && tablero[i][2] == 1) pos = i + 1 + (i * 2);
                if (tablero[i][0] == 0 && tablero[i][1] == 1 && tablero[i][2] == 1) pos = i + (i * 2);
            }
            // comprobar columnas
            for (int i = 0; i < 3; i++) {
                if (tablero[0][i] == 1 && tablero[1][i] == 1 && tablero[2][i] == 0) pos = i + 6;
                if (tablero[0][i] == 1 && tablero[1][i] == 0 && tablero[2][i] == 1) pos = i + 3;
                if (tablero[0][i] == 0 && tablero[1][i] == 1 && tablero[2][i] == 1) pos = i;
            }
            // comprobar diagonal \
            if (tablero[0][0] == 1 && tablero[1][1] == 1 && tablero[2][2] == 0) pos = 8;
            if (tablero[0][0] == 1 && tablero[1][1] == 0 && tablero[2][2] == 1) pos = 4;
            if (tablero[0][0] == 0 && tablero[1][1] == 1 && tablero[2][2] == 1) pos = 0;
            // comprobar diagonal /
            if (tablero[0][2] == 1 && tablero[1][1] == 1 && tablero[2][0] == 0) pos = 2;
            if (tablero[0][2] == 1 && tablero[1][1] == 0 && tablero[2][0] == 1) pos = 4;
            if (tablero[0][2] == 0 && tablero[1][1] == 1 && tablero[2][0] == 1) pos = 6;
        }
        if(pos == 9 && nivel > 1) {
            // 2º norma: intentar tapar el hueco
            // comprobar filas:
            for (int i = 0; i < 3; i++) {
                if (tablero[i][0] == 2 && tablero[i][1] == 2 && tablero[i][2] == 0) pos = i + 2 + (i * 2);
                if (tablero[i][0] == 2 && tablero[i][1] == 0 && tablero[i][2] == 2) pos = i + 1 + (i * 2);
                if (tablero[i][0] == 0 && tablero[i][1] == 2 && tablero[i][2] == 2) pos = i + (i * 2);
            }
            // comprobar columnas
            for (int i = 0; i < 3; i++) {
                if (tablero[0][i] == 2 && tablero[1][i] == 2 && tablero[2][i] == 0) pos = i + 6;
                if (tablero[0][i] == 2 && tablero[1][i] == 0 && tablero[2][i] == 2) pos = i + 3;
                if (tablero[0][i] == 0 && tablero[1][i] == 2 && tablero[2][i] == 2) pos = i;
            }
            // comprobar diagonal \
            if (tablero[0][0] == 2 && tablero[1][1] == 2 && tablero[2][2] == 0) pos = 8;
            if (tablero[0][0] == 2 && tablero[1][1] == 0 && tablero[2][2] == 2) pos = 4;
            if (tablero[0][0] == 0 && tablero[1][1] == 2 && tablero[2][2] == 2) pos = 0;
            // comprobar diagonal /
            if (tablero[0][2] == 2 && tablero[1][1] == 2 && tablero[2][0] == 0) pos = 2;
            if (tablero[0][2] == 2 && tablero[1][1] == 0 && tablero[2][0] == 2) pos = 4;
            if (tablero[0][2] == 0 && tablero[1][1] == 2 && tablero[2][0] == 2) pos = 6;
        }
        if(pos == 9 && nivel>2)
        {
            // 3ª norma. Alcanzar ventaja ocupando el centro
            if(tablero[1][1]==0) pos = 4;
        }
        //
        if(pos==9) {
            // aleatorio, por defecto si el nivel es 0 y si no ha sido posible encontrar una casilla mejor
            pos = r.nextInt(9);
            while (!movimientoValido(pos)) {
                pos = r.nextInt(9);
            }
        }
        muevejugador1(pos);
    }

    public void mueveOrdenador2() {
        int pos = 9;
        Random r = new Random();
        if (nivel > 0) {
            // ia
            // 1ª Norma: ver si podemos ganar en este movimiento
            // comprobar filas:
            for (int i = 0; i < 3; i++) {
                if (tablero[i][0] == 2 && tablero[i][1] == 2 && tablero[i][2] == 0) pos = i + 2 + (i * 2);
                if (tablero[i][0] == 2 && tablero[i][1] == 0 && tablero[i][2] == 2) pos = i + 1 + (i * 2);
                if (tablero[i][0] == 0 && tablero[i][1] == 2 && tablero[i][2] == 2) pos = i + (i * 2);
            }
            // comprobar columnas
            for (int i = 0; i < 3; i++) {
                if (tablero[0][i] == 2 && tablero[1][i] == 2 && tablero[2][i] == 0) pos = i + 6;
                if (tablero[0][i] == 2 && tablero[1][i] == 0 && tablero[2][i] == 2) pos = i + 3;
                if (tablero[0][i] == 0 && tablero[1][i] == 2 && tablero[2][i] == 2) pos = i;
            }
            // comprobar diagonal \
            if (tablero[0][0] == 2 && tablero[1][1] == 2 && tablero[2][2] == 0) pos = 8;
            if (tablero[0][0] == 2 && tablero[1][1] == 0 && tablero[2][2] == 2) pos = 4;
            if (tablero[0][0] == 0 && tablero[1][1] == 2 && tablero[2][2] == 2) pos = 0;
            // comprobar diagonal /
            if (tablero[0][2] == 2 && tablero[1][1] == 2 && tablero[2][0] == 0) pos = 2;
            if (tablero[0][2] == 2 && tablero[1][1] == 0 && tablero[2][0] == 2) pos = 4;
            if (tablero[0][2] == 0 && tablero[1][1] == 2 && tablero[2][0] == 2) pos = 6;
        }
        if(pos == 9 && nivel > 1) {
            // 2º norma: intentar tapar el hueco
            // comprobar filas:
            for (int i = 0; i < 3; i++) {
                if (tablero[i][0] == 1 && tablero[i][1] == 1 && tablero[i][2] == 0) pos = i + 2 + (i * 2);
                if (tablero[i][0] == 1 && tablero[i][1] == 0 && tablero[i][2] == 1) pos = i + 1 + (i * 2);
                if (tablero[i][0] == 0 && tablero[i][1] == 1 && tablero[i][2] == 1) pos = i + (i * 2);
            }
            // comprobar columnas
            for (int i = 0; i < 3; i++) {
                if (tablero[0][i] == 1 && tablero[1][i] == 1 && tablero[2][i] == 0) pos = i + 6;
                if (tablero[0][i] == 1 && tablero[1][i] == 0 && tablero[2][i] == 1) pos = i + 3;
                if (tablero[0][i] == 0 && tablero[1][i] == 1 && tablero[2][i] == 1) pos = i;
            }
            // comprobar diagonal \
            if (tablero[0][0] == 1 && tablero[1][1] == 1 && tablero[2][2] == 0) pos = 8;
            if (tablero[0][0] == 1 && tablero[1][1] == 0 && tablero[2][2] == 1) pos = 4;
            if (tablero[0][0] == 0 && tablero[1][1] == 1 && tablero[2][2] == 1) pos = 0;
            // comprobar diagonal /
            if (tablero[0][2] == 1 && tablero[1][1] == 1 && tablero[2][0] == 0) pos = 6;
            if (tablero[0][2] == 1 && tablero[1][1] == 0 && tablero[2][0] == 1) pos = 4;
            if (tablero[0][2] == 0 && tablero[1][1] == 1 && tablero[2][0] == 1) pos = 2;
        }
        if(pos == 9 && nivel>2)
        {
            // 3ª norma. Alcanzar ventaja ocupando el centro
            if(tablero[1][1]==0) pos = 4;
        }
        //
        if(pos==9) {
            // aleatorio, por defecto si el nivel es 0 y si no ha sido posible encontrar una casilla mejor
            pos = r.nextInt(9);
            while (!movimientoValido(pos)) {
                pos = r.nextInt(9);
            }
        }
        muevejugador2(pos);
    }

    public void iniciar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = 0;
            }
        }
    }

    public boolean quedanMovimientos() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == 0) return true;
            }
        }
        return false;
    }

    public boolean ganaJugador1() {
        // comprobar filas:
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == 1 && tablero[i][1] == 1 && tablero[i][2] == 1) return true;
        }
        // comprobar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == 1 && tablero[1][i] == 1 && tablero[2][i] == 1) return true;
        }
        // comprobar diagonal \
        if (tablero[0][0] == 1 && tablero[1][1] == 1 && tablero[2][2] == 1) return true;
        // comprobar diagonal /
        if (tablero[0][2] == 1 && tablero[1][1] == 1 && tablero[2][0] == 1) return true;
        //
        return false;
    }

    public boolean ganaJugador2() {
        // comprobar filas:
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == 2 && tablero[i][1] == 2 && tablero[i][2] == 2) return true;
        }
        // comprobar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == 2 && tablero[1][i] == 2 && tablero[2][i] == 2) return true;
        }
        // comprobar diagonal \
        if (tablero[0][0] == 2 && tablero[1][1] == 2 && tablero[2][2] == 2) return true;
        // comprobar diagonal /
        if (tablero[0][2] == 2 && tablero[1][1] == 2 && tablero[2][0] == 2) return true;
        //
        return false;
    }

    public void dibujaTablero() {
        String texto[] = {"   ", " X ", " O "};
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
        System.out.println("    0   1   2");
        System.out.println("  ╔═══╦═══╦═══╗");
        System.out.println(0 + " ║" + texto[tablero[0][0]] + "│" + texto[tablero[0][1]] + "│" + texto[tablero[0][2]] + "║");
        System.out.println("  ╠───┼───┼───╣");
        System.out.println(1 + " ║" + texto[tablero[1][0]] + "│" + texto[tablero[1][1]] + "│" + texto[tablero[1][2]] + "║");
        System.out.println("  ╠───┼───┼───╣");
        System.out.println(2 + " ║" + texto[tablero[2][0]] + "│" + texto[tablero[2][1]] + "│" + texto[tablero[2][2]] + "║");
        System.out.println("  ╚═══╩═══╩═══╝");
    }
}
