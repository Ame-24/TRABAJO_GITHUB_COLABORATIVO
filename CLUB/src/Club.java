import java.util.ArrayList;

public class Club {
    private ArrayList<Socio> socios;

    public Club() {
        socios = new ArrayList<>();
    }

    public void agregarSocio(String cedula, String nombre, Socio.Tipo tipo) {
        socios.add(new Socio(cedula, nombre, tipo));
    }

    public Socio buscarSocio(String cedula) {
        for (Socio s : socios) {
            if (s.darCedula().equals(cedula))
                return s;
        }
        return null;
    }

    public ArrayList<Socio> darSocios() {
        return socios;
    }
}
