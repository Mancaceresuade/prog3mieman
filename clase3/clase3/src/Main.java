import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        agregar(numeros,10);
        listar(numeros);
    }

    private static void listar(ArrayList<Integer> numeros) {
        numeros.stream().forEach(n -> System.out.println(n));
    }
    private static void agregar(ArrayList<Integer> numeros, int numero) {
        numeros.add(numero);
    }
}
