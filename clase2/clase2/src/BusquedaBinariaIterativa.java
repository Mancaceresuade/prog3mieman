public class BusquedaBinariaIterativa {

    public static int busquedaBinaria(int[] arr, int clave) {
        int inicio = 0;
        int fin = arr.length - 1;

        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;

            if (arr[medio] == clave) {
                return medio;
            } else if (arr[medio] < clave) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1; // No encontrado
    }
}
