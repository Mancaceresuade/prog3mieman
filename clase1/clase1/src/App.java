import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void inprimirImpares(int[] numeros) {
        int aux = numeros.length; // 1
        for(int i=0; i < aux; i++) {  // 1 + (n+1) + n
            if(numeros[i] % 2 != 0){  // 3n
                System.out.println(i); // 1n
            }
        }
    } 
    // segunda version con auxiliar
    // f(n) = 1 + 1 + n + 1 + n + 3n + n = 3 + 6n
    // primera version, sin auxiliar
    // f(n) = 1 + 2(n+1) + n + 3n + 1n = 1 + 2n + 2 + n + 3n + n
    // f(n) = 3 + 7n 


    public static void main(String[] args) {

        int[] numeros = {1, 2, 3, 4, 5};
        System.out.println(inprimirImpares(numeros));

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
