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
