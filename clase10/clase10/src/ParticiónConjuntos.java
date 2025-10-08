public class ParticiónConjuntos {
   public static void main(String[] args) {
		int[] conjunto = {6, 5, 11 }; // Puedes cambiar los valores para probar otros casos
		if (!resolver(conjunto)) {
			System.out.println("No existe partición con suma igual.");
		}
	}
 
	public static boolean resolver(int[] conjunto) {
		return backtrack(conjunto, 0, new StringBuilder(), new StringBuilder(), 0, 0);
	}
 
	private static boolean backtrack(int[] conjunto, int idx, StringBuilder part1, StringBuilder part2, int suma1, int suma2) {
		if (idx == conjunto.length) {
			if (suma1 == suma2) {
				System.out.println("Partición encontrada:");
				System.out.println("Subconjunto 1: " + part1.toString());
				System.out.println("Subconjunto 2: " + part2.toString());
				return true;
			}
			return false;
		}
		// Intentar agregar el elemento actual al subconjunto 1
		part1.append(conjunto[idx]).append(" ");
		if (backtrack(conjunto, idx + 1, part1, part2, suma1 + conjunto[idx], suma2)) {
			return true;
		}
		part1.setLength(part1.length() - (conjunto[idx] + " ").length());
		// Intentar agregar el elemento actual al subconjunto 2
		part2.append(conjunto[idx]).append(" ");
		if (backtrack(conjunto, idx + 1, part1, part2, suma1, suma2 + conjunto[idx])) {
			return true;
		}
		part2.setLength(part2.length() - (conjunto[idx] + " ").length());
		return false;
	} 
}
