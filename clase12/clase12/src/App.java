import java.util.ArrayList;

public class App {
    static int cantidadCombinaciones = 0;
    static int cantidadCombinacionesFiltro = 0;
    static ArrayList<ArrayList<Integer>> combinaciones = new ArrayList<>();
    static int optimo = Integer.MAX_VALUE; // estimado para este ejercicio 12
    public static void main(String[] args) throws Exception {
        int[] proyectos = {3,7,5,9,6,4};
        int empleados = 3;
        // empleado 1, 3 y 9  empleado 2, 5 y 7  empleado 3, 6 y 4   => maximo 12 o 10  ?
        // posiblidades 3**6  = 729
        ArrayList<Integer> com_actual = new ArrayList<>();
        // {0,0,0,0,0,0}   => todo al empleado 1 => 3+7+5+9+6+4 => 34
        // {0,1,1,0,2,2}   =>  3+9   7+5  6+4
        // [0, 0, 0, 1, 2, 2]  => emp 1 = 15  emp 2 = 9  emp 3 = 10 
        // {2,2,2,2,2,2}   => todo al empleado 3 => 3+7+5+9+6+4 => 34
        calcularOptimo(proyectos,empleados,com_actual,0);
        combinaciones.forEach(c -> System.out.println(c));
        System.out.println("Cantidad combinaciones "+ cantidadCombinaciones);
        System.out.println("Cantidad combinaciones filtro "+ cantidadCombinacionesFiltro);
        System.out.println("Optimo "+ optimo);
    }

    private static void calcularOptimo(int[] proyectos, int empleados, ArrayList<Integer> com_actual, int i) {
        int maximoActual = calcularMaximo(proyectos,empleados,com_actual);
        // caso base
        if(i==proyectos.length) {
            cantidadCombinaciones++;
            // filtro
            if(maximoActual < optimo) {
                optimo = maximoActual;
                combinaciones.clear();
                combinaciones.add(new ArrayList<>(com_actual));
                cantidadCombinacionesFiltro++;
            } else if (maximoActual == optimo) {
                combinaciones.add(new ArrayList<>(com_actual));
            }
            return;
        }
        // ramificacion
        for (int j = 0; j < empleados; j++) {
            com_actual.add(j);
            // poda
            if(maximoActual > optimo) { 
                com_actual.remove(com_actual.size()-1); // excede, continuar con otro empleado
                continue; 
            }
            calcularOptimo(proyectos, empleados, com_actual, i+1);
            com_actual.remove(com_actual.size()-1); // TRUCO backtraking
        }
    }

    private static int calcularMaximo(int[] proyectos, int empleados, ArrayList<Integer> com_actual) {
        // {3,7,5,9,6,4};
        // empleados 3
        // ejemplo combinacion actual {0,1,1,0,2,2}   =>  3+9   7+5  6+4   => maximo = 12
        // [1, 0, 2, 1, 2, 0]  => emp 1  7+4=11   emp 2 3+9=12  emp 3 5+6=11  => maximo = 12
        // Agrupar horas por empleado
        int[] horasPorEmpleado = new int[empleados];
        for (int i = 0; i < com_actual.size(); i++) {
            horasPorEmpleado[com_actual.get(i)] += proyectos[i];
        }
        int maximo = 0;
        for (int i = 0; i < horasPorEmpleado.length; i++) {
            if(horasPorEmpleado[i]>maximo){
                maximo = horasPorEmpleado[i];
            }
        }
        return maximo;
    }
}
