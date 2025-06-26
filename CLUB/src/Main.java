import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Club club = new Club();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ CLUB ---");
            System.out.println("1. Afiliar socio");
            System.out.println("2. Agregar autorizado");
            System.out.println("3. Registrar consumo");
            System.out.println("4. Pagar factura");
            System.out.println("5. Aumentar fondos");
            System.out.println("6. Total consumos de socio");
            System.out.println("7. Ver si se puede eliminar socio");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Cédula: ");
                        String ced = sc.nextLine();
                        System.out.print("Nombre: ");
                        String nom = sc.nextLine();
                        System.out.print("Tipo (VIP o REGULAR): ");
                        String tipo = sc.nextLine();
                        club.afiliarSocio(ced, nom, Socio.Tipo.valueOf(tipo.toUpperCase()));
                        System.out.println("Socio afiliado.");
                        break;
                    case 2:
                        System.out.print("Cédula del socio: ");
                        Socio s = club.buscarSocio(sc.nextLine());
                        System.out.print("Nombre autorizado: ");
                        s.agregarAutorizado(sc.nextLine());
                        System.out.println("Autorizado agregado.");
                        break;

                    case 3:
                        System.out.print("Cédula socio: ");
                        Socio s2 = club.buscarSocio(sc.nextLine());
                        System.out.print("Nombre consumidor: ");
                        String nombreConsumo = sc.nextLine();
                        System.out.print("Concepto: ");
                        String concepto = sc.nextLine();
                        System.out.print("Valor: ");
                        double valor = sc.nextDouble();
                        s2.registrarConsumo(nombreConsumo, concepto, valor);
                        System.out.println("Consumo registrado.");
                        break;

                    case 4:
                        System.out.print("Cédula socio: ");
                        Socio s3 = club.buscarSocio(sc.nextLine());
                        for (int i = 0; i < s3.darFacturas().size(); i++) {
                            System.out.println(i + ". " + s3.darFacturas().get(i));
                        }
                        System.out.print("Factura a pagar (índice): ");
                        int idx = sc.nextInt();
                        s3.pagarFactura(idx);
                        System.out.println("Factura pagada.");
                        break;

                    case 5:
                        System.out.print("Cédula socio: ");
                        Socio s4 = club.buscarSocio(sc.nextLine());
                        System.out.print("Monto: ");
                        double monto = sc.nextDouble();
                        s4.aumentarFondos(monto);
                        System.out.println("Fondos aumentados.");
                        break;

                    case 6:
                        System.out.print("Cédula socio: ");
                        System.out.println("Total consumos: $" + club.totalConsumosSocio(sc.nextLine()));
                        break;

                    case 7:
                        System.out.print("Cédula socio: ");
                        System.out.println(club.sePuedeEliminarSocio(sc.nextLine()));
                        break;
                }

            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }

        } while (opcion != 0);

        sc.close();
    }
}