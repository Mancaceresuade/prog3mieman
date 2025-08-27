public class TorneoDV {

    public static void main(String[] args) {
        int[][] fixture = generarFixture(8);
        imprimirFixture(fixture);        
    }

    public static int[][] generarFixture(int n) {
        int[][] fixture = new int[n][n - 1];
        construirFixture(fixture, 0, n);
        return fixture;
    }
 
    private static void construirFixture(int[][] fixture, int inicio, int n) {
        if (n == 2) {
            fixture[inicio][0] = inicio + 2; // equipo inicio vs inicio+1
            fixture[inicio + 1][0] = inicio + 1;
            return;
        }
 
        int mitad = n / 2;
        // Recursivamente generar fixtures para cada mitad
        construirFixture(fixture, inicio, mitad);
        construirFixture(fixture, inicio + mitad, mitad);
 
        // Ahora intercalar partidos entre los dos grupos
        for (int i = 0; i < mitad; i++) {
            for (int j = 0; j < mitad; j++) {
                int jornada = j + mitad - 1;
                int equipoA = inicio + i;
                int equipoB = inicio + mitad + (i + j) % mitad;
                fixture[equipoA][jornada] = equipoB + 1;
                fixture[equipoB][jornada] = equipoA + 1;
            }
        }
    } // a=2   b=2  k = 2   => O(n^2)
 
    public static void imprimirFixture(int[][] fixture) {
        for (int jornada = 0; jornada < fixture[0].length; jornada++) {
            System.out.println("Jornada " + (jornada + 1));
            boolean[] usado = new boolean[fixture.length];
            for (int equipo = 0; equipo < fixture.length; equipo++) {
                if (!usado[equipo]) {
                    int rival = fixture[equipo][jornada] - 1;
                    System.out.println("  Equipo " + (equipo + 1) + " vs Equipo " + (rival + 1));
                    usado[equipo] = true;
                    usado[rival] = true;
                }
            }
        }
    }
}
