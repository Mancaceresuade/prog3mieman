public class App {
    public static void main(String[] args) throws Exception {
        int[] numeros = {5, 8, 2, 10, 3};
        int maximo = maxArray(numeros);
        System.out.println("El máximo es: " + maximo);
    }

    public static int maxArray(int[] arr) {
        int aux = arr.length; // 1
        if (arr == null || aux == 0) {    // 3
            throw new IllegalArgumentException("El array no puede estar vacío");
        }
        int max = arr[0]; // 1
        for (int i = 1; i < aux; i++) { // 1 + n  + n
            int aux2 = arr[i]; // 2 n
            if ( aux2 > max) { // n
                max = aux2; // n
            }
        }
        return max; // 1
    } // f(n) = 1 + 3 + 1 + 1 + n + n + 2 n + n + n = 6 + 6n
    // f(n) <= c g(n)
    // 6 + 6n <= 7n  // termino dominante mas 1
    // 6/n + 6n/n <= 7n/n   // divido por n
    // 6/n + 6 <= 7 // simplifico
    // para n = 1 cumple ?  no cumple 12 no es menor a 7
    // a partir de 6 cumple  por lo que f(n) pertenece a O(n)  para c = 7 y n0 mayor o igual a 6
}
