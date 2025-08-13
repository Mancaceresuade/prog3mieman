public class Jugador<T> {
    private int id;
    private String nombre;
    private T calificacion;

    public Jugador() {}

    public Jugador(int id, String nombre, T calificacion) {
        this.id = id;
        this.nombre = nombre;
        this.calificacion = calificacion;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "Jugador{id=" + id + ", nombre='" + nombre + "'}";
    }


    public T getCalificacion() {
        return calificacion;
    }


    public void setCalificacion(T calificacion) {
        this.calificacion = calificacion;
    }
}
