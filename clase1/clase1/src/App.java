import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static double calcularPromedio(int[][] mat) {
        // if(mat.length!=mat[0].length) throw new RuntimeException("calculo valido solo para matriz cuadrada");
        int suma = 0; // 1
        int cantidadFilasColumnas = mat.length; // 2  // porque es matriz cuadrada fila==columnas
        for(int fila=0; fila < cantidadFilasColumnas; fila++){ // 1 + n + 1 + n
            for(int col=0; col < cantidadFilasColumnas; col++){ // n ( 1 + n + 1 + n)
                suma = suma + mat[fila][col]; // n ( 3 n )
            }
        }
        return (suma/(cantidadFilasColumnas*cantidadFilasColumnas)); // 3
    } // f(n) = 1 + 2 + 1 + n + 1 + n + n ( 1 + n + 1 + n) + n ( 3 n ) + 3  // aplico propiedad distributiva
      // f(n) = 1 + 2 + 1 + n + 1 + n + n + n^2 + n + n^2 + 3n^2 + 3  // sumo
      // f(n) = 8 + 4n + 5n^2
      // f(n) <= c g(n)
      // 8 + 4n + 5n^2 <= 6n^2  // tomo el termino dominante, sumo constante mas 1  
      // 8/n^2 + 4n/n^2 + 5n^2/n^2 <= 6n^2/n^2  // simplifico
      // 8/n^2 + 4n/n^2 + 5 <= 6  // siguiente, busco los valores n0 que cumplen
      // para n = 1 cumple ?     no cumple (17<=6)
      // para n = 2 cumple ?     no cumple (9<=6)
      // para n = 3 cumple ?     no cumple (aprox 7 <= 6)
      // para n = 4 cumple ?     no cumple
      // para n = 5 cumple ?     cumple
      // f(n) pertence a O(n^2)  para c = 6 y n0 >= 5,46

    public static void inprimirImpares(int[] numeros) {
        int aux = numeros.length; // 2
        for(int i=0; i < aux; i++) {  // 1 + (n+1) + n
            if(numeros[i] % 2 != 0){  // 3n
                System.out.println(i); // 1n
            }
        }
        // algun cambio
    } 
    // segunda version con auxiliar
    // f(n) = 2 + 1 + n + 1 + n + 3n + n = 4 + 6n
    // f(n) = 4 + 6n
    // f(n) <= c g(n)
    // 4 + 6n <= 7n   => buscamos un valor superior en el termino dominante
    // 4/n + 6n/n <= 7n/n
    // 4/n + 6 <= 7
    // buscamos valores de n que cumplan
    // para n = 1 se cumple ?  no se cumple
    // para n = 2 se cumple ?  no se cumple
    // para n = 4 se cumple ? si    =>  f(n) pertence O(n) para n0 = 4 y c = 7

    // primera version, sin auxiliar
    // f(n) = 1 + 2(n+1) + n + 3n + 1n = 1 + 2n + 2 + n + 3n + n
    // f(n) = 3 + 7n 


    public static void main(String[] args) {

        int[] numeros = {1, 2, 3, 4, 5};
        inprimirImpares(numeros);

        int[][] mat = {{4,5,6},{7,8,9},{5,6,7}};
        System.out.println(calcularPromedio(mat));

        /*
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador(1, "Juan", 10));
        jugadores.add(new Jugador(2, "Pedro", 20));
        jugadores.add(new Jugador(3, "Maria", 30));

        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
        jugadores.stream().forEach(j -> System.out.println(j));

        Map<Integer, Jugador> mapaJugadores = new HashMap<>();
        mapaJugadores.put(1, new Jugador(1, "Juan", 10));
        mapaJugadores.put(2, new Jugador(2, "Pedro", 20));
        mapaJugadores.put(3, new Jugador(3, "Maria", 30));

        System.out.println(mapaJugadores.get(1));

        mapaJugadores.values().stream().forEach(j -> System.out.println(j));

        try {
            FileReader file = new FileReader("archivo.txt");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            int i = 10/0;
            System.out.println(i);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        String nombre = "Carlos";
        System.out.println( "Hola " + nombre + "!");
        System.out.println("Programa finalizado correctamente.");
         */
    }
}
