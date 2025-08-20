public class App {
    public static void main(String[] args) throws Exception {
        int [][] matriz = {{3,5,6},{2,5,1},{3,2,1}}; 
        int [][] matriz2 = {{3,5,1},{2,5,1},{3,2,1}}; 
        System.out.println(todasLasColumnasUnElementoMultiploDe(matriz,2));// true
        System.out.println(todasLasColumnasUnElementoMultiploDe(matriz2,2));// false
        // Realizar implementacion y calculo de complejidad
        // la matriz es cuadrada
    }

    private static boolean todasLasColumnasUnElementoMultiploDe(int[][] matriz, int elemento) {
        boolean rta = true; // 1
        int aux = matriz[0].length; // 3
        for (int col = 0; col < aux; col++) { // 1 + n +1 + n 
            rta = rta && columnaUnElementoMultiploDe(matriz,col,elemento); 
            // n(1+1+h(n)) => n ( 1 + 1 + 6 + 7n ) = n + n + 6n + 7n^2
        }
        return rta;    // 1
    } // f(n) = 1+3+1 + n +1 + n +n + n + 6n + 7n^2 + 1
    // f(n) = 7 + 10n + 7n^2
    // f(n) <= c g(n)
    // 7 + 10n + 7n^2 <= 8n^2  // termino dominante mas 1
    // 7/n^2 + 10n/n^2 + 7n^2/n^2 <= 8n^2/n^2  //divido por n^2
    // 7/n^2 + 10/n + 7 <= 8 // simplifico
    // para n0 = 1 cumple ?  24 <= 8 no cumple
    // ...
    // para n0 = 7  no cumple
    // para n0 = 11 , por lo que f(n) pertenece a O(n^2) para c=8 y n0>=7

    private static boolean columnaUnElementoMultiploDe(int[][] matriz, int col, int elemento) {
        boolean rta = false; // 1
        int aux = matriz.length; // 2
        for (int i = 0; i < aux; i++) { // 1 + n +1 + n
            rta = rta || matriz[i][col]%elemento==0; // 5 n 
        }
        return rta; // 1
    } // h(n) = 1+2+1+n+1+n+5n+1 = 6+7n

    // convertir esta funcion columnaUnElementoMultiploDe a recursiva  
    // calcular complejidad
    public static boolean elementoMultiploRec(int[][] matriz, int n, int col) {
        if (col >= matriz[0].length) {
            return true;
        }
        if (!hayMultiploEnColumna(matriz, n, col, 0)) {
            return false;
        }
        return elementoMultiploRec(matriz, n, col + 1);
    } // a = 1 b = no aplica k = 1  // porque hay metodos lineales
    // aplicando regla de sustraccion , segundo caso a = 1
    // por lo que O(n^(1+1))  => O(n^2)

    public static boolean hayMultiploEnColumna(int[][] matriz, int n, int col, int fila) {
        if (fila >= matriz.length) {
            return false; // 
        }
        if (matriz[fila][col] % n == 0) {
            return true;
        }
        return hayMultiploEnColumna(matriz, n, col, fila + 1);
    } // a = 1 b = no aplica k = 0  // 
    // aplicando regla de sustraccion , segundo caso a = 1
    // por lo que O(n^(0+1))  => O(n)

}
