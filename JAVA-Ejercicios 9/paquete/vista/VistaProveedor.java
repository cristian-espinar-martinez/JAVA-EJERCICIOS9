package paquete.vista;

import paquete.modelo.Proveedor;

import java.util.List;
import java.util.Scanner;

public class VistaProveedor {
    private Scanner scanner = new Scanner(System.in);

    // Muestra el menú específico de proveedores y devuelve la opción seleccionada
    public int mostrarMenuProveedores() {
        System.out.println("\n=== GESTIÓN DE PROVEEDORES ===");
        System.out.println("1. Crear Proveedor");
        System.out.println("2. Listar Proveedores");
        System.out.println("3. Actualizar Proveedor");
        System.out.println("4. Eliminar Proveedor");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }
    // Obtiene los datos de un nuevo proveedor desde el usuario
    public Proveedor.Entidad obtenerDatosNuevoProveedor() {
        System.out.print("Ingrese el nombre del proveedor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el CIF del proveedor: ");
        String cif = scanner.nextLine();
        System.out.print("Ingrese el teléfono del proveedor: ");
        String telefono = scanner.nextLine();

        return new Proveedor.Entidad(0, nombre, cif, telefono); // ID se genera automáticamente
    }
    // Obtiene los datos actualizados de un proveedor desde el usuario
    public Proveedor.Entidad obtenerDatosProveedorParaActualizar() {
        System.out.print("Ingrese el ID del proveedor a actualizar: ");
        int idProveedor = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el nuevo nombre del proveedor: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo CIF del proveedor: ");
        String cif = scanner.nextLine();
        System.out.print("Ingrese el nuevo teléfono del proveedor: ");
        String telefono = scanner.nextLine();

        return new Proveedor.Entidad(idProveedor, nombre, cif, telefono);
    }
    // Obtiene el ID del proveedor desde el usuario
    public int obtenerIdProveedor() {
        System.out.print("Ingrese el ID del proveedor a eliminar: ");
        return Integer.parseInt(scanner.nextLine());
    }
    // Muestra una lista de proveedores
    public void mostrarProveedores(List<Proveedor.Entidad> proveedores) {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.println("=== LISTA DE PROVEEDORES ===");
            proveedores.forEach(System.out::println);
        }
    }
    // Muestra un mensaje al usuario
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}