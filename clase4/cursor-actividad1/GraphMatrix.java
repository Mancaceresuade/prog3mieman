import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de grafo dirigido usando matriz de adyacencia.
 * Vértices representados por índices [0..n-1].
 */
public class GraphMatrix {
    private final int size;          // número de vértices
    private final boolean[][] adj;   // matriz de adyacencia: adj[i][j] = true si existe arista i->j

    /**
     * Inicializa el grafo con n vértices (0..n-1), sin aristas.
     */
    public GraphMatrix(int n) {
        if (n <= 0) throw new IllegalArgumentException("El tamaño debe ser > 0");
        this.size = n;
        this.adj = new boolean[n][n];
    }

    public int getSize() { return size; }

    private void checkVertex(int v) {
        if (v < 0 || v >= size) throw new IndexOutOfBoundsException("Vértice fuera de rango: " + v);
    }

    /** Agrega arista dirigida u->v */
    public void agregarArista(int u, int v) {
        checkVertex(u); checkVertex(v);
        if (u == v) throw new IllegalArgumentException("No se permiten lazos (u==v)");
        adj[u][v] = true;
    }

    /** Elimina arista dirigida u->v */
    public void eliminarArista(int u, int v) {
        checkVertex(u); checkVertex(v);
        adj[u][v] = false;
    }

    /** Verifica si existe arista u->v */
    public boolean existeArista(int u, int v) {
        checkVertex(u); checkVertex(v);
        return adj[u][v];
    }

    /** Lista de vértices adyacentes desde u (salientes) */
    public List<Integer> listarAdyacentes(int u) {
        checkVertex(u);
        List<Integer> res = new ArrayList<>();
        for (int v = 0; v < size; v++) if (adj[u][v]) res.add(v);
        return res;
    }

    /** Grado de salida de u: cantidad de aristas u->* */
    public int gradoDeSalida(int u) {
        checkVertex(u);
        int c = 0;
        for (int v = 0; v < size; v++) if (adj[u][v]) c++;
        return c;
    }

    /** Grado de entrada de v: cantidad de aristas *->v */
    public int gradoDeEntrada(int v) {
        checkVertex(v);
        int c = 0;
        for (int u = 0; u < size; u++) if (adj[u][v]) c++;
        return c;
    }

    /** Representación legible de la matriz */
    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matriz (" ).append(size).append(")\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) sb.append(adj[i][j] ? 1 : 0).append(' ');
            sb.append('\n');
        }
        return sb.toString();
    }

    // Ejemplo de uso
    public static void main(String[] args) {
        GraphMatrix g = new GraphMatrix(5);
        g.agregarArista(0, 1);
        g.agregarArista(0, 2);
        g.agregarArista(2, 4);
        g.agregarArista(3, 0);

        System.out.println(g);
        System.out.println("Existe 0->1: " + g.existeArista(0,1));
        System.out.println("Adyacentes de 0: " + g.listarAdyacentes(0));
        System.out.println("Grado salida 0: " + g.gradoDeSalida(0));
        System.out.println("Grado entrada 0: " + g.gradoDeEntrada(0));

        g.eliminarArista(0, 2);
        System.out.println("\nTras eliminar 0->2:\n" + g);
    }
}


