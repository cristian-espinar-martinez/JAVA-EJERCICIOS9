package paquete.vista;

import paquete.modelo.Articulo;

import java.util.List;
import java.util.Scanner;

public class VistaArticulo {
    private Scanner scanner = new Scanner(System.in);

    // Muestra el menú específico de artículos y devuelve la opción seleccionada
    public int mostrarMenuArticulos() {
        System.out.println("\n=== GESTIÓN DE ARTÍCULOS ===");
        System.out.println("1. Crear Artículo");
        System.out.println("2. Listar Artículos");
        System.out.println("3. Actualizar Artículo");
        System.out.println("4. Eliminar Artículo");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // Obtiene los datos de un nuevo artículo desde el usuario
    public Articulo.Entidad obtenerDatosNuevoArticulo() {
        System.out.print("Ingrese el nombre del artículo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio unitario del artículo: ");
        double precioUnitario = Double.parseDouble(scanner.nextLine());
        System.out.print("Ingrese el stock del artículo: ");
        int stock = Integer.parseInt(scanner.nextLine());

        return new Articulo.Entidad(0, nombre, precioUnitario, stock); // ID se genera automáticamente
    }

    // Obtiene los datos actualizados de un artículo desde el usuario
    public Articulo.Entidad obtenerDatosArticuloParaActualizar() {
        System.out.print("Ingrese el ID del artículo a actualizar: ");
        int idArticulo = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el nuevo nombre del artículo: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo precio unitario del artículo: ");
        double precioUnitario = Double.parseDouble(scanner.nextLine());
        System.out.print("Ingrese el nuevo stock del artículo: ");
        int stock = Integer.parseInt(scanner.nextLine());

        return new Articulo.Entidad(idArticulo, nombre, precioUnitario, stock);
    }

    // Obtiene el ID del artículo desde el usuario
    public int obtenerIdArticulo() {
        System.out.print("Ingrese el ID del artículo a eliminar: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // Muestra una lista de artículos
    public void mostrarArticulos(List<Articulo.Entidad> articulos) {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos registrados.");
        } else {
            System.out.println("=== LISTA DE ARTÍCULOS ===");
            articulos.forEach(System.out::println);
        }
    }

    // Muestra un mensaje al usuario
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}