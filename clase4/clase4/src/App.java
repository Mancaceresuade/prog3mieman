public class App {

public static boolean estaOrdenada(char[] secuencia) {

    public static void imprimirPartidos(String[] equipos) {
        for (int i = 0; i < equipos.length; i++) { // 3+4n
            for (int j = i + 1; j < equipos.length; j++) { // gauss n(n-1)/2 = 4(4-1)/2= 6
                // no va en el parcial, gauss
                System.out.println(equipos[i] + "-" + equipos[j]);
            }
        }
    }

    // Método recursivo usando divide y vencerás
	public static boolean estaOrdenadaDivideVenceras(String secuencia, int inicio, int fin) {
		if (fin - inicio <= 1) {
			return true;
		}
		int medio = (inicio + fin) / 2;
		boolean izquierda = estaOrdenadaDivideVenceras(secuencia, inicio, medio);
		boolean derecha = estaOrdenadaDivideVenceras(secuencia, medio, fin);
		// Verificar el límite entre las dos mitades
		boolean limite = secuencia.charAt(medio - 1) <= secuencia.charAt(medio);
		return izquierda && derecha && limite;
	} // calculo de complejidad
    // Metodo division   T(n)= 2(n/2)+c
    // a = 2  b = 2  k = 0
    // tercer caso del metodo O(n ^( log b a) )  => O(n ^( log 2 2) ) => O(n)



    public static boolean estaOrdenadaRecursiva(String secuencia, int indice) {
        if (indice >= secuencia.length() - 1) {
            return true;
        }
        if (secuencia.charAt(indice) > secuencia.charAt(indice + 1)) {
            return false;
        }
        return estaOrdenadaRecursiva(secuencia, indice + 1);
    }  // metodo sustraccion T(n) = aT(n-1)+O(n)
    //  a = 1   b = 1  k = 0 => O(n^(k+1)) => O(n^(0+1)) => O(n)



    public static void main(String[] args) throws Exception {
        
    }
    public static boolean estaOrdenadaIterativo(char[] secuencia) {
        for (int i = 0; i < secuencia.length - 1; i++) { // 1 + 3(n+1) + n  // 1+3n+3+n=3+4n
            if (secuencia[i] > secuencia[i + 1]) { // n( 1 + 1 + 1 + 1 ) = 4n
                return false; // 1
            }
        }
        return true; //1
        //Conteo: 3+4n + 4n + 1 + 1 = 8n + 6
    } // f(n) <= c g(n)
    // 8n+6 <= 9n
    // 8n/n + 6/n <= 9n/n
    // 8 + 6/n <= 9
    // para 1, no cumple
    // ...
    // a partir de 6, cumple, 
    // por lo que f(n) pertenece g(n) para c=9 y n0>=6
}
