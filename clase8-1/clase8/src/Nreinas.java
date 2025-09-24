public class Nreinas { 

    public static void main(String[] args) {
        int n = 4;
        int[][] tablero = new int[n][n];
        backtrack(0, n, tablero);
    }    

    private static void backtrack(int fila, int n, int[][] tablero) {
        if(fila==n) {
            imprimir(tablero);
            return;
        }
        for (int c = 0; c < tablero.length; c++) {
            if(es_valido(tablero,fila,c)) {
                colocar_reina(tablero,fila,c,true);
                backtrack(fila+1, n, tablero);
                quitar_reina(tablero,fila,c);
            }
        }
    }

    private static void quitar_reina(int[][] tablero, int fila, int c) {
        tablero[fila][c] = 0;
    }

    private static boolean es_valido(int[][] tablero, int fila, int col) {
        for (int i = 0; i < fila; i++) {
            if (tablero[i][col] == 1) return false;
            int diag1 = col - (fila - i);
            int diag2 = col + (fila - i);
            if (diag1 >= 0 && tablero[i][diag1] == 1) return false;
            if (diag2 < tablero.length && tablero[i][diag2] == 1) return false;
        }
        return true;
    }

    private static void colocar_reina(int[][] tablero, int fila, int col, boolean poner) {
        tablero[fila][col] = poner ? 1 : 0;
    }

    private static void imprimir(int[][] tablero) {
        for (int[] fila : tablero) {
            for (int celda : fila) {
                System.out.print(celda == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }


}