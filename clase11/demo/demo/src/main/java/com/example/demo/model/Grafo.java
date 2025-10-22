package com.example.demo.model;

import java.util.LinkedList;

public class Grafo {
    private int V; // número de vértices
    private LinkedList<Integer>[] adj; // listas de adyacencia para cada vértice

    // Constructor
    public Grafo(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; ++i) {
            adj[i] = new LinkedList<>(); // Inicializar lista de adyacencia para cada vértice
        }
    }

    // Método para agregar una arista
    public void agregarArista(int v, int w) {
        adj[v].add(w); // Añadir w a la lista de adyacencia de v
    }

    // Implementación de BFS
    public void BFS(int inicio) {
        boolean[] visitado = new boolean[V]; // Array de visitados
        LinkedList<Integer> cola = new LinkedList<>(); // Cola para BFS

        visitado[inicio] = true; // Marcar el nodo de inicio como visitado
        cola.add(inicio); // Añadirlo a la cola

        while (!cola.isEmpty()) {
            inicio = cola.poll(); // Extraer el primer nodo de la cola
            System.out.print(inicio + " "); // Procesar el nodo

            // Obtener todos los nodos adyacentes de "inicio"
            for (int n : adj[inicio]) {
                if (!visitado[n]) {
                    visitado[n] = true; // Marcar como visitado
                    cola.add(n); // Añadir a la cola
                }
            }
        }
    }

    // Método auxiliar de DFS que se llama recursivamente
    private void DFSUtil(int v, boolean[] visitado) {
        // Marcar el vértice actual como visitado y mostrarlo
        visitado[v] = true;
        System.out.print(v + " ");

        // Recorrer todos los vértices adyacentes al vértice actual
        for (int n : adj[v]) {
            if (!visitado[n]) {
                DFSUtil(n, visitado); // Llamada recursiva para los vértices no visitados
            }
        }
    }

    // Método principal DFS que llama a DFSUtil
    public void DFS(int inicio) {
        // Array de booleanos para marcar los vértices visitados
        boolean[] visitado = new boolean[V];

        // Llamar al método recursivo DFSUtil comenzando desde el vértice "inicio"
        DFSUtil(inicio, visitado);
    }


    // Método main para ejecutar el ejemplo
}