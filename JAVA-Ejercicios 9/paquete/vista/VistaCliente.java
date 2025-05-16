package paquete.vista;

import paquete.modelo.Cliente;

import java.util.List;
import java.util.Scanner;

public class VistaCliente {
    private Scanner scanner = new Scanner(System.in);

    // Muestra el menú específico de clientes y devuelve la opción seleccionada
    public int mostrarMenuClientes() {
        System.out.println("\n=== GESTIÓN DE CLIENTES ===");
        System.out.println("1. Crear Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Actualizar Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(scanner.nextLine()); // Lee y convierte la entrada a entero
    }

    // Obtiene los datos de un nuevo cliente desde el usuario
    public Cliente.Entidad obtenerDatosNuevoCliente() {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();

        return new Cliente.Entidad(0, nombre, email, telefono); // ID se genera automáticamente
    }

    // Obtiene los datos actualizados de un cliente desde el usuario
    public Cliente.Entidad obtenerDatosClienteParaActualizar() {
        System.out.print("Ingrese el ID del cliente a actualizar: ");
        int idCliente = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el nuevo nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo email del cliente: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese el nuevo teléfono del cliente: ");
        String telefono = scanner.nextLine();

        return new Cliente.Entidad(idCliente, nombre, email, telefono);
    }

    // Obtiene el ID del cliente desde el usuario
    public int obtenerIdCliente() {
        System.out.print("Ingrese el ID del cliente a eliminar: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // Muestra una lista de clientes
    public void mostrarClientes(List<Cliente.Entidad> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            System.out.println("=== LISTA DE CLIENTES ===");
            clientes.forEach(System.out::println);
        }
    }

    // Muestra un mensaje al usuario
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}