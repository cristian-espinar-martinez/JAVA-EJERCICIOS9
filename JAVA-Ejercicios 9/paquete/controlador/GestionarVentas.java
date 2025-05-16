package paquete.controlador;

import paquete.modelo.Venta;
import paquete.vista.VistaVenta;

public class GestionarVentas {
    private Venta.VentaDAO ventaDAO; // Referencia al DAO de Venta
    private VistaVenta vistaVenta; // Referencia a la vista de Venta

    // Constructor que recibe el DAO y la vista
    public GestionarVentas(Venta.VentaDAO ventaDAO, VistaVenta vistaVenta) {
        this.ventaDAO = ventaDAO;
        this.vistaVenta = vistaVenta;
    }

    // Método principal que inicia el flujo de gestión de ventas
    public void iniciar() {
        int opcion;

        do {
            opcion = vistaVenta.mostrarMenuVentas();

            switch (opcion) {
                case 1: // Crear venta
                    Venta.Entidad nuevaVenta = vistaVenta.obtenerDatosNuevaVenta();
                    ventaDAO.crearVenta(nuevaVenta);
                    vistaVenta.mostrarMensaje("Venta creada exitosamente.");
                    break;
                case 2: // Listar ventas
                    vistaVenta.mostrarVentas(ventaDAO.listarVentas());
                    break;
                case 3: // Actualizar venta
                    Venta.Entidad ventaActualizada = vistaVenta.obtenerDatosVentaParaActualizar();
                    if (ventaActualizada != null) {
                        ventaDAO.actualizarVenta(ventaActualizada);
                        vistaVenta.mostrarMensaje("Venta actualizada exitosamente.");
                    } else {
                        vistaVenta.mostrarMensaje("Operación cancelada o ID no válido.");
                    }
                    break;
                case 4: // Eliminar venta
                    int idVentaEliminar = vistaVenta.obtenerIdVenta();
                    ventaDAO.eliminarVenta(idVentaEliminar);
                    vistaVenta.mostrarMensaje("Venta eliminada exitosamente.");
                    break;
                case 0: // Volver al menú principal
                    vistaVenta.mostrarMensaje("Volviendo al Menú Principal...");
                    break;
                default:
                    vistaVenta.mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }
}