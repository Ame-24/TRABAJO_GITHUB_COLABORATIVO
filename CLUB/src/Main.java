import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Club club = new Club();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ CLUB ===");
            System.out.println("1. Agregar socio");
            System.out.println("2. Ver socios");
            System.out.println("3. Aumentar fondos");
            System.out.println("4. Registrar consumo");
            System.out.println("5. Pagar factura");
            System.out.println("6. Ver facturas");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar

            switch (opcion) {
                case 1:
                    System.out.print("Cédula: ");
                    String ced = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nom = sc.nextLine();
                    System.out.print("Tipo (VIP/REGULAR): ");
                    String tipoStr = sc.nextLine();
                    Socio.Tipo tipo = tipoStr.equalsIgnoreCase("VIP") ? Socio.Tipo.VIP : Socio.Tipo.REGULAR;
                    club.agregarSocio(ced, nom, tipo);
                    break;

                case 2:
                    for (Socio s : club.darSocios()) {
                        System.out.println(s);
                    }
                    break;

                case 3:
                    System.out.print("Cédula socio: ");
                    Socio s1 = club.buscarSocio(sc.nextLine());
                    if (s1 != null) {
                        System.out.print("Monto a añadir: ");
                        double m = sc.nextDouble();
                        s1.aumentarFondos(m);
                    } else {
                        System.out.println("Socio no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Cédula socio: ");
                    Socio s2 = club.buscarSocio(sc.nextLine());
                    if (s2 != null) {
                        System.out.print("Nombre consumidor: ");
                        String n = sc.nextLine();
                        System.out.print("Concepto: ");
                        String c = sc.nextLine();
                        System.out.print("Valor: ");
                        double v = sc.nextDouble();
                        s2.registrarConsumo(n, c, v);
                    }
                    break;

                case 5:
                    System.out.print("Cédula socio: ");
                    Socio s3 = club.buscarSocio(sc.nextLine());
                    if (s3 != null) {
                        for (int i = 0; i < s3.darFacturas().size(); i++) {
                            System.out.println(i + ". " + s3.darFacturas().get(i));
                        }
                        System.out.print("Factura a pagar (índice): ");
                        int idx = sc.nextInt();
                        s3.pagarFactura(idx);
                    }
                    break;

                case 6:
                    System.out.print("Cédula socio: ");
                    Socio s4 = club.buscarSocio(sc.nextLine());
                    if (s4 != null) {
                        for (Factura f : s4.darFacturas()) {
                            System.out.println(f);
                        }
                    }
                    break;
            }

        } while (opcion != 0);

        sc.close();
    }
}
