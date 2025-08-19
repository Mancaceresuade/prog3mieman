public class Factorial {
    public static int factorial(int n) {
        if (n == 0) return 1; // Caso base   => O(1)
        return n * factorial(n - 1); // Llamada recursiva
    }
    public static void main(String[] args) {
        int number = 5; int result = factorial(number);
        System.out.println("El factorial de " + number + " es " + result);
    }

    // funcion contar algo => (n)

    public static int sumarPosicionesPares(int[] arr, int index) {
        if (index >= arr.length) return 0;  // caso base => O(1)  => tres operaciones 
        if (index % 2 == 0) {
            boolean prueba = false;
            for(int i=0; i<arr.length;i++) {  
                prueba = true;
            }
            return arr[index] + sumarPosicionesPares(arr, index + 1);
        } else {
            return sumarPosicionesPares(arr, index + 1);
        }
    } // // O(n^2)  cuadratico, por la prueba en clase


}