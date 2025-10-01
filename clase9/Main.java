import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int[] numeros = {1,2,3};
		ArrayList<Integer> combinacion_actual = new ArrayList<>();
		combinacion(numeros,0,combinacion_actual); // {1,2,3}{1,2},{1,3},{1},{2,3}{2}{3}{}
	}

	private static void combinacion(int[] numeros, int i, ArrayList<Integer> combinacion_actual) {
		if(numeros.length==i) {
			combinacion_actual.forEach(e->System.out.print(e+" "));
			System.out.println(" ");
			return;			
		}
		combinacion_actual.add(numeros[i]);
		combinacion(numeros, i+1, combinacion_actual);
		// truco back
		combinacion_actual.remove(combinacion_actual.size()-1);
		combinacion(numeros, i+1, combinacion_actual);
	}
	
}
