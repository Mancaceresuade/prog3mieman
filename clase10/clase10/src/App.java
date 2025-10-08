public class App {
    static int N;

    public static void main(String[] args) {
        N = 4; // Puedes cambiar el tamaño del tablero aquí
        int[] tablero = new int[N]; // tablero[i] = columna donde está la torre en la fila i
        resolverTorres(tablero, 0);
    }

    // Intenta colocar una torre en la fila 'fila'
    public static void resolverTorres(int[] tablero, int fila) {
        if (fila == N) {
            imprimirSolucion(tablero);
            return;
        }

        for (int col = 0; col < N; col++) {
            if (esSeguro(tablero, fila, col)) {
                tablero[fila] = col;
                resolverTorres(tablero, fila + 1);
            }
        }
    }

    // Verifica que no haya otra torre en la misma columna
    public static boolean esSeguro(int[] tablero, int fila, int col) {
        for (int i = 0; i < fila; i++) {
            if (tablero[i] == col) {
                return false; // hay una torre en la misma columna
            }
        }
        return true;
    }

    // Imprime el tablero
    public static void imprimirSolucion(int[] tablero) {
        System.out.println("Solución:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tablero[i] == j)
                    System.out.print("T ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
