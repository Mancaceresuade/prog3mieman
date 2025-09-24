import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        int[] conjunto = {1,2,3};
        ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
        ArrayList<Integer> combinacion_actual = new ArrayList<>();
        generarCombinaciones(conjunto,combinacion_actual,0,resultado);
        resultado.forEach(System.out::println);
    }

    private static void generarCombinaciones(int[] conjunto, ArrayList<Integer> combinacion_actual, int posicion,
    ArrayList<ArrayList<Integer>> resultado) {
        if(posicion == conjunto.length) {
            resultado.add(new ArrayList<>(combinacion_actual));
            return;
        }
        combinacion_actual.add(conjunto[posicion]);
        generarCombinaciones(conjunto, combinacion_actual, posicion+1, resultado);
        combinacion_actual.remove(combinacion_actual.size()-1);
        generarCombinaciones(conjunto, combinacion_actual, posicion+1, resultado);
    }

    
}
