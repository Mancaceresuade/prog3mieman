public class BusquedaBinariaRecursiva {
    public static int busquedaBinaria(int[] arr, int valor, int inicio, int fin) {
        if (inicio > fin) {
            return -1;
        }
        int medio = inicio + (fin - inicio) / 2;
        if (arr[medio] == valor) {
            return medio;
        } else if (arr[medio] > valor) {
            return busquedaBinaria(arr, valor, inicio, medio - 1);
        } else {
            return busquedaBinaria(arr, valor, medio + 1, fin);
        }
    }
    
}
