import java.util.Arrays;

public class FixtureTorneo {

    private String[] equipos;
    private String[][] tablaFixture;
    private int n;

    public FixtureTorneo(String[] equipos) {
        if (equipos == null || equipos.length == 0 || (equipos.length & (equipos.length - 1)) != 0) {
            throw new IllegalArgumentException("El número de equipos debe ser una potencia de 2.");
        }
        this.equipos = equipos;
        this.n = equipos.length;
        this.tablaFixture = new String[n - 1][n];
    }

    public void generarYMostrar() {
        generarTabla(0, 0, equipos);
        imprimirFixture();
    }
    
    private void generarTabla(int fila, int col, String[] equiposSubconjunto) {
        int size = equiposSubconjunto.length;
        if (size == 2) {
            tablaFixture[fila][col] = equiposSubconjunto[1];
            tablaFixture[fila][col + 1] = equiposSubconjunto[0];
            return;
        }

        int mitad = size / 2;
        String[] equiposIzq = Arrays.copyOfRange(equiposSubconjunto, 0, mitad);
        String[] equiposDer = Arrays.copyOfRange(equiposSubconjunto, mitad, size);

        // 1. Generar sub-fixture superior izquierdo
        generarTabla(fila, col, equiposIzq);

        // 2. Generar sub-fixture inferior izquierdo
        generarTabla(fila + mitad, col, equiposDer);
        
        // 3. Llenar el cuadrante superior derecho
        for (int i = 0; i < mitad - 1; i++) {
            for (int j = 0; j < mitad; j++) {
                tablaFixture[fila + i][col + mitad + j] = tablaFixture[fila + mitad + i][col + j];
            }
        }
        
        // 4. Llenar el cuadrante inferior derecho
        for (int i = 0; i < mitad - 1; i++) {
            for (int j = 0; j < mitad; j++) {
                tablaFixture[fila + mitad + i][col + mitad + j] = tablaFixture[fila + i][col + j];
            }
        }
    }

    private void imprimirFixture() {
        System.out.println("--- Fixture del Torneo ---");
        System.out.print("Jornadas\t");
        for (String equipo : equipos) {
            System.out.print(equipo + "\t");
        }
        System.out.println();
        
        for (int i = 0; i < n - 1; i++) {
            System.out.print("Jornada " + (i + 1) + "\t");
            for (int j = 0; j < n; j++) {
                System.out.print(tablaFixture[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String[] equipos = {"A", "B", "C", "D", "E", "F", "G", "H"}; // n = 8
        FixtureTorneo torneo = new FixtureTorneo(equipos);
        torneo.generarYMostrar();
    }
}