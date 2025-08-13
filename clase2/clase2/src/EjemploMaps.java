import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class EjemploMaps {
    
    public static void main(String[] args) {


        Jugador<Double> jugador = new Jugador<Double>();
        jugador.setCalificacion(30.3);

        AVL<Jugador<Double>> avl = new AVL<>();
        avl.insertar(new Jugador<>(1,"Juan",30.3));
        avl.inOrden();


        Jugador<String> jugador2 = new Jugador<>();
        jugador2.setCalificacion("Bueno");

        // Map<Integer,Jugador<Integer>> jugadores = new TreeMap<>();

        /*
        Map<Integer,Jugador> jugadores = new HashMap<>();
        jugadores.put(10, new Jugador(1, "Lionel Messi"));
        jugadores.put(20, new Jugador(2, "Cristiano Ronaldo"));
        jugadores.put(30, new Jugador(3, "Neymar Jr."));
        jugadores.put(40, new Jugador(4, "Kylian Mbappé"));
        jugadores.put(50, new Jugador(5, "Kevin De Bruyne"));
        jugadores.put(60, new Jugador(6, "Salas"));

        System.out.println(jugadores.get(4));

        for (Integer i : jugadores.keySet()) {
            System.out.println(jugadores.get(i));
        }

        for (Jugador j : jugadores.values()) {
            System.err.println(j);
        }

        jugadores.values().stream().forEach(j -> System.out.println(j));

        jugadores.values().stream().filter(j -> j.getNombre().equals("Salas"))
        .forEach(ju -> System.out.println(ju));; 
     */        


    }
}
