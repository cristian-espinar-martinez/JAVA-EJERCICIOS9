package paquete.vista;

import paquete.modelo.FacturaRecibida;

import java.util.List;
import java.util.Scanner;

public class VistaFacturaRecibida {
    private Scanner scanner = new Scanner(System.in);

    // Muestra el menú específico de facturas recibidas y devuelve la opción seleccionada
    public int mostrarMenuFacturasRecibidas() {
        System.out.println("\n=== GESTIÓN DE FACTURAS RECIBIDAS ===");
        System.out.println("1. Crear Factura Recibida");
        System.out.println("2. Listar Facturas Recibidas");
        System.out.println("3. Actualizar Factura Recibida");
        System.out.println("4. Eliminar Factura Recibida");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }
    // Obtiene los datos de una nueva factura recibida desde el usuario
    public FacturaRecibida.Entidad obtenerDatosNuevaFacturaRecibida() {
        System.out.print("Ingrese el ID del proveedor: ");
        int idProveedor = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la fecha de la factura (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese el total de la factura: ");
        double total = Double.parseDouble(scanner.nextLine());

        return new FacturaRecibida.Entidad(0, idProveedor, fecha, total); // ID se genera automáticamente
    }
    // Obtiene los datos actualizados de una factura recibida desde el usuario
    public FacturaRecibida.Entidad obtenerDatosFacturaRecibidaParaActualizar() {
        System.out.print("Ingrese el ID de la factura a actualizar: ");
        int idFactura = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el nuevo ID del proveedor: ");
        int idProveedor = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la nueva fecha de la factura (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese el nuevo total de la factura: ");
        double total = Double.parseDouble(scanner.nextLine());
        return new FacturaRecibida.Entidad(idFactura, idProveedor, fecha, total);
    }
    // Obtiene el ID de la factura recibida desde el usuario
    public int obtenerIdFacturaRecibida() {
        System.out.print("Ingrese el ID de la factura a eliminar: ");
        return Integer.parseInt(scanner.nextLine());
    }
    // Muestra una lista de facturas recibidas
    public void mostrarFacturasRecibidas(List<FacturaRecibida.Entidad> facturas) {
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas recibidas registradas.");
        } else {
            System.out.println("=== LISTA DE FACTURAS RECIBIDAS ===");
            facturas.forEach(System.out::println);
        }
    }
    // Muestra un mensaje al usuario
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}