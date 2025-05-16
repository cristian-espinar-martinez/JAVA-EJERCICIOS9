package paquete.vista;

import paquete.controlador.GestionarArticulos;
import paquete.controlador.GestionarClientes;
import paquete.controlador.GestionarFacturasRecibidas;
import paquete.controlador.GestionarProveedores;
import paquete.controlador.GestionarVentas;
import paquete.modelo.Articulo;
import paquete.modelo.Cliente;
import paquete.modelo.FacturaRecibida;
import paquete.modelo.Proveedor;
import paquete.modelo.Venta;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    // Método estático para mostrar el menú principal
    public static void mostrarMenu() {
        int opcion;

        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Proveedores");
            System.out.println("3. Gestión de Artículos");
            System.out.println("4. Gestión de Facturas Recibidas");
            System.out.println("5. Gestión de Ventas");
            System.out.println("6. Informes de Ventas por Cliente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1: // Gestión de Clientes
                    iniciarGestionClientes();
                    break;
                case 2: // Gestión de Proveedores
                    iniciarGestionProveedores();
                    break;
                case 3: // Gestión de Artículos
                    iniciarGestionArticulos();
                    break;
                case 4: // Gestión de Facturas Recibidas
                    iniciarGestionFacturasRecibidas();
                    break;
                case 5: // Gestión de Ventas
                    iniciarGestionVentas();
                    break;
                case 6: // Informes de Ventas por Cliente
                    iniciarInformeVentasPorCliente();
                    break;
                case 0: // Salir
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    // Métodos para iniciar la gestión de cada entidad

    private static void iniciarGestionClientes() {
        Cliente.ClienteDAO clienteDAO = new Cliente.ClienteDAOImpl(); // Modelo
        VistaCliente vistaCliente = new VistaCliente(); // Vista
        GestionarClientes gestionarClientes = new GestionarClientes(clienteDAO, vistaCliente); // Controlador

        gestionarClientes.iniciar(); // Inicia el flujo de gestión de clientes
    }

    private static void iniciarGestionProveedores() {
        Proveedor.ProveedorDAO proveedorDAO = new Proveedor.ProveedorDAOImpl(); // Modelo
        VistaProveedor vistaProveedor = new VistaProveedor(); // Vista
        GestionarProveedores gestionarProveedores = new GestionarProveedores(proveedorDAO, vistaProveedor); // Controlador

        gestionarProveedores.iniciar(); // Inicia el flujo de gestión de proveedores
    }

    private static void iniciarGestionArticulos() {
        Articulo.ArticuloDAO articuloDAO = new Articulo.ArticuloDAOImpl(); // Modelo
        VistaArticulo vistaArticulo = new VistaArticulo(); // Vista
        GestionarArticulos gestionarArticulos = new GestionarArticulos(articuloDAO, vistaArticulo); // Controlador

        gestionarArticulos.iniciar(); // Inicia el flujo de gestión de artículos
    }

    private static void iniciarGestionFacturasRecibidas() {
        FacturaRecibida.FacturaRecibidaDAO facturaRecibidaDAO = new FacturaRecibida.FacturaRecibidaDAOImpl(); // Modelo
        VistaFacturaRecibida vistaFacturaRecibida = new VistaFacturaRecibida(); // Vista
        GestionarFacturasRecibidas gestionarFacturasRecibidas = new GestionarFacturasRecibidas(facturaRecibidaDAO, vistaFacturaRecibida); // Controlador

        gestionarFacturasRecibidas.iniciar(); // Inicia el flujo de gestión de facturas recibidas
    }

    private static void iniciarGestionVentas() {
        Venta.VentaDAO ventaDAO = new Venta.VentaDAOImpl(); // Modelo
        VistaVenta vistaVenta = new VistaVenta(); // Vista
        GestionarVentas gestionarVentas = new GestionarVentas(ventaDAO, vistaVenta); // Controlador

        gestionarVentas.iniciar(); // Inicia el flujo de gestión de ventas
    }
    private static void iniciarInformeVentasPorCliente() {
        Venta.VentaDAO ventaDAO = new Venta.VentaDAOImpl(); // Modelo
        VistaVenta vistaVenta = new VistaVenta(); // Vista

        System.out.print("Ingrese el ID del cliente para generar el informe: ");
        int idCliente = Integer.parseInt(scanner.nextLine());

        List<Venta.Entidad> ventas = ventaDAO.listarVentasPorCliente(idCliente);
        if (ventas.isEmpty()) {
            vistaVenta.mostrarMensaje("No se encontraron ventas para el cliente con ID " + idCliente);
        } else {
            vistaVenta.mostrarVentas(ventas);
        }
    }
}