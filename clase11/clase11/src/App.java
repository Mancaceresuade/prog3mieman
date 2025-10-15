import java.util.ArrayList;

public class App {
    static ArrayList<ObjetosMochila> resultado = new ArrayList<>();
    static double mejorValor = 0.0;

    public static void main(String[] args) throws Exception {
        int capacidad = 10;
        ArrayList<ObjetosMochila> objetosMochila = new ArrayList<>();
        cargarDatos(objetosMochila);
        ArrayList<ObjetosMochila> comb_actual = new ArrayList<>();
        maximizarSinSuperar(objetosMochila, capacidad, 0, comb_actual, 0.0, 0.0);
        System.out.println("Mejor valor: " + mejorValor);
        System.out.println("Mejor combinación:");
        resultado.forEach(e -> System.out.println(e));
    }

    private static void maximizarSinSuperar(ArrayList<ObjetosMochila> objetosMochila, int capacidad, int i,
            ArrayList<ObjetosMochila> comb_actual, Double valor_actual, Double peso_actual) {
        // Si supera la capacidad, descartar rama
        if (peso_actual > capacidad) return;

        // Si es mejor solución, actualizar resultado
        if (valor_actual > mejorValor) {
            mejorValor = valor_actual;
            resultado = new ArrayList<>(comb_actual);
        }

        // Caso base: sin más objetos
        if (i == objetosMochila.size()) return;

        // Cota: valor actual + suma de valores restantes
        /*
        double cota = valor_actual;
        for (int j = i; j < objetosMochila.size(); j++) {
            cota += objetosMochila.get(j).valor;
        }
         */
        double cota = objetosMochila.stream().mapToDouble(o -> o.valor).sum();
        // Poda: si la cota no supera la mejor solución, descartar rama
        if (cota <= mejorValor) return;

        // Ramificar: incluir el objeto actual
        comb_actual.add(objetosMochila.get(i));
        maximizarSinSuperar(objetosMochila, capacidad, i + 1, comb_actual,
                valor_actual + objetosMochila.get(i).valor, peso_actual + objetosMochila.get(i).peso);
        comb_actual.remove(comb_actual.size() - 1);

        // Ramificar: no incluir el objeto actual
        maximizarSinSuperar(objetosMochila, capacidad, i + 1, comb_actual, valor_actual, peso_actual);
    }

    private static void cargarDatos(ArrayList<ObjetosMochila> objetosMochila) {
        objetosMochila.add(new ObjetosMochila("A", 40.0, 2.0));
        objetosMochila.add(new ObjetosMochila("B", 30.0, 5.0));
        objetosMochila.add(new ObjetosMochila("C", 50.0, 10.0));
    }
}

class ObjetosMochila {
    String nombre;
    Double valor;
    Double peso;

    public ObjetosMochila(String nombre, Double valor, Double peso) {
        this.nombre = nombre;
        this.valor = valor;
        this.peso = peso;
    }

    @Override
    public String toString() {
        return nombre + " (valor: " + valor + ", peso: " + peso + ")";
    }
}
