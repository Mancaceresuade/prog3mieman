public class Jugador extends Persona implements IContrableJugador {
    private int puntos;

    public Jugador(int id, String nombre, int puntos) {
        super(id, nombre);
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Jugador{" + "id=" + super.toString() + ", puntos=" + puntos + '}';
    }

    @Override
    public void contratarJugador() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contratarJugador'");
    }
    
}
