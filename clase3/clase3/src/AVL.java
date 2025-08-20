public class AVL<T extends Comparable<T>> {
    
    Nodo<T> raiz;

    class Nodo<U> {
        U elem;
        Nodo<U> izq;
        Nodo<U> der;

    }

    // Método para obtener la altura de un nodo
    private int altura(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return Math.max(altura(nodo.izq), altura(nodo.der)) + 1;
    }

    // Método para obtener el factor de balanceo de un nodo
    private int balance(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return altura(nodo.izq) - altura(nodo.der);
    }

    // Rotación simple a la derecha
    private Nodo<T> rotarDerecha(Nodo<T> y) {
        Nodo<T> x = y.izq;
        Nodo<T> T2 = x.der;
        x.der = y;
        y.izq = T2;
        return x;
    }

    // Rotación simple a la izquierda
    private Nodo<T> rotarIzquierda(Nodo<T> x) {
        Nodo<T> y = x.der;
        Nodo<T> T2 = y.izq;
        y.izq = x;
        x.der = T2;
        return y;
    }

    // Insertar un elemento en el AVL
    public void insertar(T elem) {
        raiz = insertarRec(raiz, elem);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T elem) {
        if (nodo == null) {
            Nodo<T> nuevo = new Nodo<>();
            nuevo.elem = elem;
            return nuevo;
        }

        if (elem.compareTo(nodo.elem) < 0) {
            nodo.izq = insertarRec(nodo.izq, elem);
        } else if (elem.compareTo(nodo.elem) > 0) {
            nodo.der = insertarRec(nodo.der, elem);
        } else {
            return nodo; // No se permiten duplicados
        }

        int balanceo = balance(nodo);

        // Rotaciones para balancear
        if (balanceo > 1 && elem.compareTo(nodo.izq.elem) < 0)
            return rotarDerecha(nodo);

        if (balanceo < -1 && elem.compareTo(nodo.der.elem) > 0)
            return rotarIzquierda(nodo);

        if (balanceo > 1 && elem.compareTo(nodo.izq.elem) > 0) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }

        if (balanceo < -1 && elem.compareTo(nodo.der.elem) < 0) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }
    // Método para imprimir el árbol en orden
    public void inOrden() {
        inOrdenRec(raiz);
        System.out.println();
    }

    private void inOrdenRec(Nodo<T> nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izq);
            System.out.print(nodo.elem + " ");
            inOrdenRec(nodo.der);
        }
    }

    public int getHeight() {
        return getHeightRec(raiz);
    }
 
    private int getHeightRec(Nodo<T> nodo) {
        if (nodo == null) return 0;
        int leftHeight = getHeightRec(nodo.izq);
        int rightHeight = getHeightRec(nodo.der);
        return Math.max(leftHeight, rightHeight) + 1;
    } // O(n)  a=2 k=0  b=2




}
