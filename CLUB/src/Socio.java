import java.util.ArrayList;

public class Socio {
    public enum Tipo {
        VIP, REGULAR
    }

    public static final double FONDOS_INICIALES_REGULARES = 50;
    public static final double FONDOS_INICIALES_VIP = 100;
    public static final double MONTO_MAXIMO_REGULARES = 1000;
    public static final double MONTO_MAXIMO_VIP = 5000;

    private String cedula;
    private String nombre;
    private double fondos;
    private Tipo tipoSubscripcion;
    private ArrayList<Factura> facturas;
    private ArrayList<String> autorizados;

    public Socio(String pCedula, String pNombre, Tipo pTipo) {
        cedula = pCedula;
        nombre = pNombre;
        tipoSubscripcion = pTipo;

        if (tipoSubscripcion == Tipo.VIP)
            fondos = FONDOS_INICIALES_VIP;
        else
            fondos = FONDOS_INICIALES_REGULARES;

        facturas = new ArrayList<>();
        autorizados = new ArrayList<>();
    }

    public String darNombre() {
        return nombre;
    }

    public String darCedula() {
        return cedula;
    }

    public double darFondos() {
        return fondos;
    }

    public Tipo darTipo() {
        return tipoSubscripcion;
    }

    public ArrayList<Factura> darFacturas() {
        return facturas;
    }

    public ArrayList<String> darAutorizados() {
        return autorizados;
    }

    private boolean existeAutorizado(String pNombreAutorizado) {
        return autorizados.contains(pNombreAutorizado);
    }

    private boolean tieneFacturaAsociada(String pNombreAutorizado) {
        for (Factura f : facturas) {
            if (f.darNombre().equals(pNombreAutorizado))
                return true;
        }
        return false;
    }

    public void aumentarFondos(double pFondos) {
        if (pFondos <= 0) {
            System.out.println("Debe ingresar una cantidad mayor a cero.");
            return;
        }

        double nuevoTotal = fondos + pFondos;

        if (tipoSubscripcion == Tipo.VIP && nuevoTotal > MONTO_MAXIMO_VIP) {
            System.out.println("Excede el monto máximo para socio VIP.");
        } else if (tipoSubscripcion == Tipo.REGULAR && nuevoTotal > MONTO_MAXIMO_REGULARES) {
            System.out.println("Excede el monto máximo para socio regular.");
        } else {
            fondos = nuevoTotal;
        }
    }

    public void registrarConsumo(String pNombre, String pConcepto, double pValor) {
        if (pValor > fondos) {
            System.out.println("Fondos insuficientes.");
        } else {
            facturas.add(new Factura(pNombre, pConcepto, pValor));
        }
    }

    public void agregarAutorizado(String pNombreAutorizado) {
        if (pNombreAutorizado.equals(nombre)) {
            System.out.println("El socio no puede ser su propio autorizado.");
        } else if (fondos == 0) {
            System.out.println("No hay fondos disponibles.");
        } else if (existeAutorizado(pNombreAutorizado)) {
            System.out.println("Ya existe ese autorizado.");
        } else {
            autorizados.add(pNombreAutorizado);
        }
    }

    public void eliminarAutorizado(String pNombreAutorizado) {
        if (tieneFacturaAsociada(pNombreAutorizado)) {
            System.out.println(pNombreAutorizado + " tiene facturas pendientes.");
        } else {
            autorizados.remove(pNombreAutorizado);
        }
    }

    public void pagarFactura(int pIndiceFactura) {
        if (pIndiceFactura < 0 || pIndiceFactura >= facturas.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Factura f = facturas.get(pIndiceFactura);
        if (f.darValor() > fondos) {
            System.out.println("Fondos insuficientes para pagar factura.");
        } else {
            fondos -= f.darValor();
            facturas.remove(pIndiceFactura);
        }
    }

    public String toString() {
        return cedula + " - " + nombre;
    }
}
