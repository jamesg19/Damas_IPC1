/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.james.damas;

import java.util.Scanner;

/**
 * 
 * @author James Gramajo 
 */
public class JuegoDeDamas {

    private static final int TAM_TABLERO = 8;

    private static char[][] tablero;
    private static int turno;

    public static void main(String[] args) {
        iniciarTablero();
        mostrarTablero();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Turno del jugador " +turno+ (turno == 1 ? "X" : "O"));
            System.out.print("Ingrese la posición de origen (fila, columna): ");
            int filaOrigen = scanner.nextInt();
            int columnaOrigen = scanner.nextInt();
            System.out.print("Ingrese la posición de destino (fila, columna): ");
            int filaDestino = scanner.nextInt();
            int columnaDestino = scanner.nextInt();
            if (mover(filaOrigen, columnaOrigen, filaDestino, columnaDestino)) {
                mostrarTablero();
                if (hayGanador()) {
                    System.out.println("¡Felicidades, jugador " + (turno == 1 ? "X" : "O") + ", has ganado!");
                    break;
                }
                cambiarTurno();
            } else {
                System.out.println("Movimiento inválido, intente de nuevo.");
            }
        }
    }
    private static void cambiarTurno() {
    if (turno == 'X') {
        turno = 'O';
    } else {
        turno = 'X';
    }
}
    private static boolean mover(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        if (filaOrigen < 0 || filaOrigen >= TAM_TABLERO ||
                columnaOrigen < 0 || columnaOrigen >= TAM_TABLERO ||
                filaDestino < 0 || filaDestino >= TAM_TABLERO ||
                columnaDestino < 0 || columnaDestino >= TAM_TABLERO ||
                tablero[filaOrigen][columnaOrigen] == ' ' ||
                tablero[filaDestino][columnaDestino] != ' ' ||
                (turno == 1 && tablero[filaOrigen][columnaOrigen] != 'X') ||
                (turno == 2 && tablero[filaOrigen][columnaOrigen] != 'O')) {
            return false;
        }

        int distanciaFila = Math.abs(filaDestino - filaOrigen);
        int distanciaColumna = Math.abs(columnaDestino - columnaOrigen);

        if (distanciaFila != distanciaColumna || distanciaFila == 0) {
            System.out.println("falso1");
            return false;
        }

        char tipoPieza = tablero[filaOrigen][columnaOrigen];

        if (tipoPieza == 'X' && filaDestino <= filaOrigen) {
            System.out.println("falso2");
            return false;
        } else if (tipoPieza == 'O' && filaDestino >= filaOrigen) {
            System.out.println("falso3");
            return false;
        }

        if (distanciaFila == 1) {
            if (Math.abs(columnaDestino - columnaOrigen) == 1) {
                tablero[filaDestino][columnaDestino] = tipoPieza;
                tablero[filaOrigen][columnaOrigen] = ' ';
                return true;
            } else {
                System.out.println("falso4");
                return false;
            }
        }

        int direccionFila = (filaDestino - filaOrigen) / distanciaFila;
        int direccionColumna = (columnaDestino - columnaOrigen) / distanciaColumna;

        for (int i = 1; i < distanciaFila; i++) {
            int filaIntermedia = filaOrigen + i * direccionFila;
            int columnaIntermedia = columnaOrigen + i * direccionColumna;
            if (tablero[filaIntermedia][columnaIntermedia] != ' ') {
                System.out.println("falso5");
                return false;
            }
        }

        tablero[filaDestino][columnaDestino] = tipoPieza;
        tablero[filaOrigen][columnaOrigen] = ' ';

        if (distanciaFila == 2) {
            int filaIntermedia = filaOrigen + direccionFila;
            int columnaIntermedia = columnaOrigen + direccionColumna;
            if (tablero[filaIntermedia][columnaIntermedia] == 'X') {
                tablero[filaIntermedia][columnaIntermedia] = ' ';
            } else if (tablero[filaIntermedia][columnaIntermedia] == 'O') {
                tablero[filaIntermedia][columnaIntermedia] = ' ';
            }
        }

        return true;
    }

    private static void iniciarTablero() {
        tablero = new char[TAM_TABLERO][TAM_TABLERO];
        for (int fila = 0; fila < TAM_TABLERO; fila++) {
            for (int columna = 0; columna < TAM_TABLERO; columna++) {

                if (fila % 2 == columna % 2) {

                    if (fila < 3) {
                        System.out.println(fila %2+" %2");
                        tablero[fila][columna] = 'X';
                    } else if (fila > 4) {
                        tablero[fila][columna] = 'O';
                    } else {
                        tablero[fila][columna] = ' ';
                    }
                } else {
                    tablero[fila][columna] = ' ';
                }
            }
        }
        turno = 1;
    }

    private static void mostrarTablero() {
        System.out.println();
        System.out.print("  ");
        for (int columna = 0; columna < TAM_TABLERO; columna++) {
            System.out.print(columna + " ");
        }
        System.out.println();
        for (int fila = 0; fila < TAM_TABLERO; fila++) {
            System.out.print(fila + " ");
            for (int columna = 0; columna < TAM_TABLERO; columna++) {
                System.out.print(tablero[fila][columna] + " ");
            }
            System.out.println(fila);
        }
        System.out.print("  ");
        for (int columna = 0; columna < TAM_TABLERO; columna++) {
            System.out.print(columna + " ");
        }
        System.out.println();
    }

    private static boolean hayGanador() {
    boolean jugador1Perdio = true;
    boolean jugador2Perdio = true;
    for (int fila = 0; fila < TAM_TABLERO; fila++) {
        for (int columna = 0; columna < TAM_TABLERO; columna++) {
            if (tablero[fila][columna] == 'X') {
                jugador1Perdio = false;
            } else if (tablero[fila][columna] == 'O') {
                jugador2Perdio = false;
            } else {
                continue;
            }
            boolean esRey = Character.isUpperCase(tablero[fila][columna]);
            if (puedeMover(fila, columna, fila - 1, columna - 1, esRey) ||
                puedeMover(fila, columna, fila - 1, columna + 1, esRey) ||
                puedeMover(fila, columna, fila + 1, columna - 1, esRey) ||
                puedeMover(fila, columna, fila + 1, columna + 1, esRey)) {
                if (tablero[fila][columna] == 'X') {
                    jugador1Perdio = false;
                } else {
                    jugador2Perdio = false;
                }
            }
        }
    }
    if (jugador1Perdio) {
        System.out.println("¡Felicidades, jugador O, has ganado!");
    } else if (jugador2Perdio) {
        System.out.println("¡Felicidades, jugador X, has ganado!");
    }
    return jugador1Perdio || jugador2Perdio;
}
    private static boolean puedeMover(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, boolean esRey) {
    if (filaDestino < 0 || filaDestino >= TAM_TABLERO || columnaDestino < 0 || columnaDestino >= TAM_TABLERO) {
        return false;
    }
    if (tablero[filaDestino][columnaDestino] != ' ') {
        return false;
    }
    if (!esRey) {
        if (turno == 'X' && filaDestino > filaOrigen) {
            return false;
        } else if (turno == 'O' && filaDestino < filaOrigen) {
            return false;
        }
    }
    int filaIntermedia = (filaOrigen + filaDestino) / 2;
    int columnaIntermedia = (columnaOrigen + columnaDestino) / 2;
    if (Math.abs(filaDestino - filaOrigen) == 2 && Math.abs(columnaDestino - columnaOrigen) == 2 &&
        tablero[filaIntermedia][columnaIntermedia] != ' ' &&
        Character.toLowerCase(tablero[filaIntermedia][columnaIntermedia]) != turno) {
        return true;
    } else if (Math.abs(filaDestino - filaOrigen) == 1 && Math.abs(columnaDestino - columnaOrigen) == 1) {
        return true;
    }
    return false;
}
    
    
}
