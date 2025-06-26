import java.util.ArrayList;

public class Socio {
    public enum Tipo { VIP, REGULAR }

    public static final double FONDOS_INICIALES_REGULARES = 50000;
    public static final double FONDOS_INICIALES_VIP = 100000;
    public static final double MONTO_MAXIMO_REGULARES = 1000000;
    public static final double MONTO_MAXIMO_VIP = 5000000;

    private String cedula;
    private String nombre;
    private double fondos;
    private Tipo tipoSubscripcion;
    private ArrayList<Factura> facturas;
    private ArrayList<String> autorizados;

    public Socio(String cedula, String nombre, Tipo tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoSubscripcion = tipo;
        this.facturas = new ArrayList<>();
        this.autorizados = new ArrayList<>();

        this.fondos = (tipo == Tipo.VIP) ? FONDOS_INICIALES_VIP : FONDOS_INICIALES_REGULARES;
    }

    public String darNombre() { return nombre; }
    public String darCedula() { return cedula; }
    public double darFondos() { return fondos; }
    public Tipo darTipo() { return tipoSubscripcion; }
    public ArrayList<Factura> darFacturas() { return facturas; }
    public ArrayList<String> darAutorizados() { return autorizados; }

    public void aumentarFondos(double monto) throws Exception {
        if (monto <= 0) throw new Exception("El monto debe ser positivo.");

        double nuevoTotal = fondos + monto;
        if (tipoSubscripcion == Tipo.VIP && nuevoTotal > MONTO_MAXIMO_VIP)
            throw new Exception("Excede el monto máximo para VIP.");
        if (tipoSubscripcion == Tipo.REGULAR && nuevoTotal > MONTO_MAXIMO_REGULARES)
            throw new Exception("Excede el monto máximo para Regular.");

        fondos = nuevoTotal;
    }

    public void registrarConsumo(String nombre, String concepto, double valor) throws Exception {
        if (valor > fondos)
            throw new Exception("Fondos insuficientes para registrar consumo.");

        facturas.add(new Factura(nombre, concepto, valor));
    }

    public void pagarFactura(int index) throws Exception {
        if (index < 0 || index >= facturas.size())
            throw new Exception("Índice de factura inválido.");

        Factura f = facturas.get(index);
        if (f.darValor() > fondos)
            throw new Exception("Fondos insuficientes para pagar la factura.");

        fondos -= f.darValor();
        facturas.remove(index);
    }

    public void agregarAutorizado(String nombre) throws Exception {
        if (fondos == 0)
            throw new Exception("No hay fondos suficientes para autorizar personas.");

        if (nombre.equals(this.nombre))
            throw new Exception("El socio no puede autorizarse a sí mismo.");

        if (autorizados.contains(nombre))
            throw new Exception("Ya existe esta persona autorizada.");

        autorizados.add(nombre);
    }

    public void eliminarAutorizado(String nombre) throws Exception {
        for (Factura f : facturas) {
            if (f.darNombre().equals(nombre))
                throw new Exception("No se puede eliminar autorizado con facturas pendientes.");
        }
        autorizados.remove(nombre);
    }

    public double calcularTotalConsumos() {
        double total = 0;
        for (Factura f : facturas) {
            total += f.darValor();
        }
        return total;
    }

    public String toString() {
        return cedula + " - " + nombre + " - Tipo: " + tipoSubscripcion + " - Fondos: $" + fondos;
    }
}
