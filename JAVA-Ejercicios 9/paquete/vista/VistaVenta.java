package paquete.vista;

import paquete.modelo.Venta;

import java.util.List;
import java.util.Scanner;

public class VistaVenta {
    private Scanner scanner = new Scanner(System.in);

    // Muestra el menú específico de ventas y devuelve la opción seleccionada
    public int mostrarMenuVentas() {
        System.out.println("\n=== GESTIÓN DE VENTAS ===");
        System.out.println("1. Crear Venta");
        System.out.println("2. Listar Ventas");
        System.out.println("3. Actualizar Venta");
        System.out.println("4. Eliminar Venta");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // Obtiene los datos de una nueva venta desde el usuario
    public Venta.Entidad obtenerDatosNuevaVenta() {
        System.out.print("Ingrese el ID del cliente: ");
        int idCliente = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el ID del artículo: ");
        int idArticulo = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la fecha de la venta (YYYY-MM-DD): ");
        String fechaVenta = scanner.nextLine();

        return new Venta.Entidad(0, idCliente, idArticulo, cantidad, fechaVenta); // ID se genera automáticamente
    }

    // Obtiene los datos actualizados de una venta desde el usuario
    public Venta.Entidad obtenerDatosVentaParaActualizar() {
        System.out.print("Ingrese el ID de la venta a actualizar: ");
        int idVenta = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el nuevo ID del cliente: ");
        int idCliente = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el nuevo ID del artículo: ");
        int idArticulo = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la nueva cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la nueva fecha de la venta (YYYY-MM-DD): ");
        String fechaVenta = scanner.nextLine();

        return new Venta.Entidad(idVenta, idCliente, idArticulo, cantidad, fechaVenta);
    }

    // Obtiene el ID de la venta desde el usuario
    public int obtenerIdVenta() {
        System.out.print("Ingrese el ID de la venta a eliminar: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // Muestra una lista de ventas
    public void mostrarVentas(List<Venta.Entidad> ventas) {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            System.out.println("=== LISTA DE VENTAS ===");
            ventas.forEach(System.out::println);
        }
    }

    // Muestra un mensaje al usuario
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}	