public class Entrenador extends Persona {
    private String especialidad;

    public Entrenador(int id, String nombre, String especialidad) {
        super(id, nombre);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Entrenador{" + "id=" + super.toString() + ", especialidad='" + especialidad + '\'' + '}';
    }
    
}
