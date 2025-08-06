public abstract class Persona {
    private int id;
    private String nombre;
    public Persona(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public String toString() {
        return "Persona{id=" + id + ", nombre='" + nombre + "'}";
    }    
}
