public class App {
    public static void main(String[] args) throws Exception {
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(4));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(6));
        System.out.println(fibonacci(7));
        System.out.println(fibonacci(8));
    }

    public static int fibonacci(int numero) {
        if(numero<=0) return 0;
        if(numero==1) return 1;
        if(numero==2) return 1;
        return fibonacci(numero-1)+fibonacci(numero-2);
    }
}
