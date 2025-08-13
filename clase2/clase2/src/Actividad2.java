public class Actividad2 {
    static class Cliente {
        int idCliente;
        String nombre;

        Cliente(int idCliente, String nombre) {
            this.idCliente = idCliente;
            this.nombre = nombre;
        }
    }

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

    static class ClienteImporte {
        int idCliente;
        String nombre;
        double sumaImportes;

        ClienteImporte(int idCliente, String nombre, double sumaImportes) {
            this.idCliente = idCliente;
            this.nombre = nombre;
            this.sumaImportes = sumaImportes;
        }

        @Override
        public String toString() {
            return "ClienteImporte{idCliente=" + idCliente + ", nombre='" + nombre + "', sumaImportes=" + sumaImportes + "}";
        }
    }

    public static void main(String[] args) {
        Cliente[] clientes = {
            new Cliente(1, "Juan"),
            new Cliente(2, "Ana"),
            new Cliente(3, "Luis")
        }; // 1

        Factura[] facturas = {
            new Factura(101, 1, 100.0),
            new Factura(102, 2, 200.0),
            new Factura(103, 1, 150.0),
            new Factura(104, 3, 300.0),
            new Factura(105, 2, 50.0)
        }; //1

        ClienteImporte[] resultado = new ClienteImporte[clientes.length]; // 3

        for (int i = 0; i < clientes.length; i++) {
            double suma = 0;
            for (Factura f : facturas) {
                if (f.idCliente == clientes[i].idCliente) {
                    suma += f.importe;
                }
            }
            resultado[i] = new ClienteImporte(clientes[i].idCliente, clientes[i].nombre, suma);
        }  // cuadratico

        for (ClienteImporte ci : resultado) {
            System.out.println(ci);
        } // n   
    }
}
