import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        /*
        HashMap<Integer, Integer> memo = new HashMap<>();
        System.out.println(fibonacci(0,memo));
        System.out.println(fibonacci(1,memo));
        System.out.println(fibonacci(2,memo));
        System.out.println(fibonacci(3,memo));
        System.out.println(fibonacci(4,memo));
        System.out.println(fibonacci(5,memo));
        System.out.println(fibonacci(6,memo));
        System.out.println(fibonacci(7,memo));
        System.out.println(fibonacci(8,memo));
        */
        fibonacciEficiente(8);
    }

    public static int fibonacciEficiente(int n) {
        if(n<=0) return 0;    // 2          
        if (n==1) return 1;       // 2
        int[] fib = new int[n+1];     // 2
        fib[0] = 0;  // 1                    
        fib[1] = 1;  // 1                    
        int contar = 0;
        for(int i=2; i<=n; i++) {        // 1 + n + 1 - 2 + n - 1
            contar++;
            fib[i] = fib[i-1] + fib[i-2];  // 6 n - 1
        }
        System.out.println(contar);
        return fib[n];           // 2
    } // f(n) aprox. 5 + 8n => O(n)



    public int fibonacciDinamico(int n) {
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return fibonacciMemo(n, memo);
    }
    private int fibonacciMemo(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
        return memo[n];
    } // O(n)

    public static int fibonacci(int numero, HashMap<Integer, Integer> memo) {
        if(memo.containsKey(numero)) return memo.get(numero);
        if(numero<=0) return 0;
        if(numero==1) return 1;
        if(numero==2) return 1;
        int aux = fibonacci(numero-1)+fibonacci(numero-2);
        memo.put(numero, aux);
        return aux;
    }  // O(n)

    public static int fibonacci(int numero) {
        if(numero<=0) return 0;
        if(numero==1) return 1;
        if(numero==2) return 1;
        return fibonacci(numero-1)+fibonacci(numero-2);
    }  // a=2 b=1 k=0  metodo sustraccion
    // caso 3, O(a ** (n div b))  => O(2**n)
    //                        fib(8)
    //                    fib(6) + fib(7)
    //            fib(4)+fib(5)    fib(5)+fib(6)
    //    fib(2)+fib(3) fib(3)+fib(4)  fib(3)+fib(4) fib(4)+fib(5)
    //    ....
}




