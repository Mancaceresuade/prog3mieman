public class CambioGreedy {
	// Greedy con cantidades por denominación. Devuelve true si puede dar cambio exacto.
	public static boolean puedeDarCambioGreedy(int montoObjetivo, int[] denominaciones, int[] cantidadesDisponibles) {
		if (montoObjetivo < 0) return false;
		if (montoObjetivo == 0) return true;
		if (denominaciones == null || cantidadesDisponibles == null) return false;
		if (denominaciones.length != cantidadesDisponibles.length) return false;
		int tipos = denominaciones.length;

		// Ordenar índices por denominación descendente para tomar las más grandes primero
		Integer[] indices = new Integer[tipos];
		for (int i = 0; i < tipos; i++) indices[i] = i;
		java.util.Arrays.sort(indices, (a, b) -> Integer.compare(denominaciones[b], denominaciones[a]));

		int restante = montoObjetivo;
		for (int idx : indices) {
			if (restante == 0) break;
			int denom = denominaciones[idx];
			int disponibles = cantidadesDisponibles[idx];
			if (denom <= 0 || disponibles <= 0) continue;
			int maxUsar = Math.min(restante / denom, disponibles);
			restante -= maxUsar * denom;
		}
		return restante == 0;
	}

	// Greedy con lista de monedas individuales disponibles. Devuelve true si puede dar cambio exacto.
	public static boolean puedeDarCambioGreedy(int montoObjetivo, java.util.List<Integer> monedasDisponibles) {
		if (montoObjetivo < 0) return false;
		if (montoObjetivo == 0) return true;
		if (monedasDisponibles == null || monedasDisponibles.isEmpty()) return false;

		java.util.List<Integer> monedas = new java.util.ArrayList<>(monedasDisponibles);
		monedas.removeIf(v -> v == null || v <= 0);
		if (monedas.isEmpty()) return false;

		monedas.sort(java.util.Collections.reverseOrder());
		int restante = montoObjetivo;
		for (int valor : monedas) {
			if (restante == 0) break;
			if (valor <= restante) {
				restante -= valor;
			}
		}
		return restante == 0;
	}
}
