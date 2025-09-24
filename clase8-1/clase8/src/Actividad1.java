import java.util.ArrayList;

public class Actividad1 {
    public static void main(String[] args) throws Exception {
        int[] conjunto = {2,3,5};
        int objetivo = 5;
        ArrayList<ArrayList<Integer>> resultado = new ArrayList<>();
        ArrayList<Integer> combinacion_actual = new ArrayList<>();
        generarCombinaciones(conjunto,combinacion_actual,0,resultado,objetivo,0);
        resultado.forEach(System.out::println);
    }

    private static void generarCombinaciones(int[] conjunto, ArrayList<Integer> combinacion_actual, int posicion,
    ArrayList<ArrayList<Integer>> resultado,int objetivo, int suma_actual) {
        if(posicion == conjunto.length) {
            if(suma_actual == objetivo) {
                resultado.add(new ArrayList<>(combinacion_actual));
            }
            return;
        }
        if(suma_actual > objetivo) { return;}
        combinacion_actual.add(conjunto[posicion]);
        generarCombinaciones(conjunto, combinacion_actual, posicion+1, resultado, objetivo,suma_actual+conjunto[posicion]);
        combinacion_actual.remove(combinacion_actual.size()-1);
        generarCombinaciones(conjunto, combinacion_actual, posicion+1, resultado, objetivo,suma_actual);
    }

    
}
