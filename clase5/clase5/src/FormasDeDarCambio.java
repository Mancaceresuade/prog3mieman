import java.util.HashMap;
import java.util.Map;

public class FormasDeDarCambio {
    private static final int[] monedas = {1, 2, 5};
    private static final Map<String, Integer> memo = new HashMap<>();
    public static void main(String[] args) {
        int[] monedas = {1,2,5};
        // System.out.println(recIngenuo(monedas, 6, 0));
        System.out.println(contar(6,0));
    }
    private static int contar(int restante, int indice) {
        if (restante == 0) return 1;
        if (restante < 0 || indice == monedas.length) return 0;
 
        String key = restante + "-" + indice;
        if (memo.containsKey(key)) return memo.get(key);
 
        // Opción 1: usar la moneda actual
        int usar = contar(restante - monedas[indice], indice);
 
        // Opción 2: no usar la moneda actual
        int noUsar = contar(restante, indice + 1);
 
        int total = usar + noUsar;
        memo.put(key, total);
        return total;
    }
    public static long recIngenuo(int[] monedas, int n, int i) {
        if(n==0) return 1;
        if(n < 0 || i == monedas.length) return 0;
        return recIngenuo(monedas, n, i+1)+recIngenuo(monedas, n-monedas[i], i);
    } // a=2 b=1 k=0  => O(2**n) 
    // 
}
