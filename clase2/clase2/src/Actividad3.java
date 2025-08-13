public class Actividad3 {

    // Complejidad asintótica:
    // T(n) = T(n-1) + O(1)
    // Método de sustracción: 
    // a = 1 (número de subproblemas), 
    // b = 1 (tamaño del subproblema: n-1 = n/1), 
    // k = 0 (trabajo fuera de la recursión es constante)
    // Por metodo de sustraccion: T(n) = O(n)
    public static int sumaRecursiva(int n) {
        if (n <= 1) return n;
        return n + sumaRecursiva(n - 1);
    }

    public static void main(String[] args) {
        int n = 10; // Puedes cambiar este valor para probar con otros números
        System.out.println("La suma de los primeros " + n + " números enteros es: " + sumaRecursiva(n));
    }
}
