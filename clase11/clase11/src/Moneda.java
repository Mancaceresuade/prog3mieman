import java.util.ArrayList;

public class Moneda {
    static ArrayList<Integer> resultado = new ArrayList<>();
    static int mejorCombinacion = Integer.MAX_VALUE;

    public static void main(String[] args) {
        int[] monedas = {1, 2, 5};
        int montoTotal = 6;
        ArrayList<Integer> com_actual = new ArrayList<>();
        combinacionMinimaMonedasSumeMontoTotal(monedas, montoTotal, 0, 0, com_actual);
        resultado.forEach(e -> System.out.println(e));
        System.out.println("Cantidad mínima de monedas: " + resultado.size());
        System.out.println("Suma total: " + resultado.stream().mapToInt(e -> e).sum());
    }

    private static void combinacionMinimaMonedasSumeMontoTotal(int[] monedas, int montoTotal, int i,
                                                               int suma_actual, ArrayList<Integer> com_actual) {
        // caso base
        if (suma_actual == montoTotal) {
            if (com_actual.size() < mejorCombinacion) {
                mejorCombinacion = com_actual.size();
                resultado = new ArrayList<>(com_actual);
            }
            return;
        }
        // poda
        if (suma_actual > montoTotal) return;
        if (com_actual.size() >= mejorCombinacion) return;

        // ramificacion
        for (int k = 0; k < monedas.length; k++) {
            com_actual.add(monedas[k]);
            combinacionMinimaMonedasSumeMontoTotal(monedas, montoTotal, i + 1, suma_actual + monedas[k], com_actual);
            com_actual.remove(com_actual.size() - 1); // Backtracking
        }
    }
}
