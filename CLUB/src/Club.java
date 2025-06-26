import java.util.ArrayList;

public class Club {
    public static final int MAXIMO_VIP = 3;
    private ArrayList<Socio> socios;

    public Club() {
        socios = new ArrayList<>();
    }

    public void afiliarSocio(String cedula, String nombre, Socio.Tipo tipo) throws Exception {
        if (buscarSocio(cedula) != null)
            throw new Exception("Ya existe un socio con esa cédula.");

        if (tipo == Socio.Tipo.VIP && contarSociosVIP() >= MAXIMO_VIP)
            throw new Exception("No se pueden afiliar más socios VIP.");

        socios.add(new Socio(cedula, nombre, tipo));
    }

    public int contarSociosVIP() {
        int count = 0;
        for (Socio s : socios) {
            if (s.darTipo() == Socio.Tipo.VIP)
                count++;
        }
        return count;
    }

    public Socio buscarSocio(String cedula) {
        for (Socio s : socios) {
            if (s.darCedula().equals(cedula))
                return s;
        }
        return null;
    }

    public double totalConsumosSocio(String cedula) throws Exception {
        Socio socio = buscarSocio(cedula);
        if (socio == null) throw new Exception("No existe socio con esa cédula.");

        return socio.calcularTotalConsumos();
    }

    public String sePuedeEliminarSocio(String cedula) {
        Socio socio = buscarSocio(cedula);

        if (socio == null) return "Socio no existe.";
        if (socio.darTipo() == Socio.Tipo.VIP) return "No se pueden eliminar socios VIP.";
        if (!socio.darFacturas().isEmpty()) return "No se pueden eliminar socios con facturas pendientes.";
        if (socio.darAutorizados().size() > 1) return "No se pueden eliminar socios con más de un autorizado.";

        return "Se puede eliminar.";
    }

    public ArrayList<Socio> darSocios() {
        return socios;
    }
}
