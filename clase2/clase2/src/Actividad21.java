import java.util.*;

public class Actividad21 {
    static class Factura {
        int idFactura;
        int idCliente;
        double importe;

        Factura(int idFactura, int idCliente, double importe) {
            this.idFactura = idFactura;
            this.idCliente = idCliente;
            this.importe = importe;
        }
    }

    static class Cliente {
        int idCliente;
        String nombre;

        Cliente(int idCliente, String nombre) {
            this.idCliente = idCliente;
            this.nombre = nombre;
        }
    }

    static class ResumenCliente {
        int idCliente;
        String nombre;
        double sumaImportes;

        ResumenCliente(int idCliente, String nombre, double sumaImportes) {
            this.idCliente = idCliente;
            this.nombre = nombre;
            this.sumaImportes = sumaImportes;
        }

        @Override
        public String toString() {
            return "ID: " + idCliente + ", Nombre: " + nombre + ", Total: " + sumaImportes;
        }
    }

    public static void main(String[] args) {
        List<Factura> facturas = Arrays.asList(
            new Factura(1, 100, 1500.0),
            new Factura(2, 101, 2000.0),
            new Factura(3, 100, 500.0),
            new Factura(4, 102, 3000.0)
        );

        List<Cliente> clientes = Arrays.asList(
            new Cliente(100, "Juan"),
            new Cliente(101, "Ana"),
            new Cliente(102, "Luis")
        );

        // Map idCliente -> suma de importes
        Map<Integer, Double> sumaImportesPorCliente = new HashMap<>();
        for (Factura f : facturas) {
            sumaImportesPorCliente.put(f.idCliente,
                sumaImportesPorCliente.getOrDefault(f.idCliente, 0.0) + f.importe);
        } // lineal => O(n)

        // Map idCliente -> nombre
        Map<Integer, String> nombrePorCliente = new HashMap<>();
        for (Cliente c : clientes) {
            nombrePorCliente.put(c.idCliente, c.nombre);
        } // lineal => O(n)

        // Generar lista de resumen
        List<ResumenCliente> resumen = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : sumaImportesPorCliente.entrySet()) {
            int idCliente = entry.getKey();
            String nombre = nombrePorCliente.getOrDefault(idCliente, "Desconocido");
            resumen.add(new ResumenCliente(idCliente, nombre, entry.getValue()));
        } // lineal => O(n)

        // Mostrar resultados
        for (ResumenCliente rc : resumen) {
            System.out.println(rc);
        } // lineal => O(n)
    } // conclusion general, simplificamos a O(n)
}
