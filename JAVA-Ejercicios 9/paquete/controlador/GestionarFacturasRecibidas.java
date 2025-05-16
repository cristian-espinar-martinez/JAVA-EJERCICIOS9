package paquete.controlador;

import paquete.modelo.FacturaRecibida;
import paquete.vista.VistaFacturaRecibida;

public class GestionarFacturasRecibidas {
    private FacturaRecibida.FacturaRecibidaDAO facturaRecibidaDAO; // Referencia al DAO de Factura Recibida
    private VistaFacturaRecibida vistaFacturaRecibida; // Referencia a la vista de Factura Recibida

    // Constructor que recibe el DAO y la vista
    public GestionarFacturasRecibidas(FacturaRecibida.FacturaRecibidaDAO facturaRecibidaDAO, VistaFacturaRecibida vistaFacturaRecibida) {
        this.facturaRecibidaDAO = facturaRecibidaDAO;
        this.vistaFacturaRecibida = vistaFacturaRecibida;
    }

    // Método principal que inicia el flujo de gestión de facturas recibidas
    public void iniciar() {
        int opcion;

        do {
            opcion = vistaFacturaRecibida.mostrarMenuFacturasRecibidas();

            switch (opcion) {
                case 1: // Crear factura recibida
                    FacturaRecibida.Entidad nuevaFactura = vistaFacturaRecibida.obtenerDatosNuevaFacturaRecibida();
                    facturaRecibidaDAO.crearFacturaRecibida(nuevaFactura);
                    vistaFacturaRecibida.mostrarMensaje("Factura Recibida creada exitosamente.");
                    break;
                case 2: // Listar facturas recibidas
                    vistaFacturaRecibida.mostrarFacturasRecibidas(facturaRecibidaDAO.listarFacturasRecibidas());
                    break;
                case 3: // Actualizar factura recibida
                    FacturaRecibida.Entidad facturaActualizada = vistaFacturaRecibida.obtenerDatosFacturaRecibidaParaActualizar();
                    if (facturaActualizada != null) {
                        facturaRecibidaDAO.actualizarFacturaRecibida(facturaActualizada);
                        vistaFacturaRecibida.mostrarMensaje("Factura Recibida actualizada exitosamente.");
                    } else {
                        vistaFacturaRecibida.mostrarMensaje("Operación cancelada o ID no válido.");
                    }
                    break;
                case 4: // Eliminar factura recibida
                    int idFacturaEliminar = vistaFacturaRecibida.obtenerIdFacturaRecibida();
                    facturaRecibidaDAO.eliminarFacturaRecibida(idFacturaEliminar);
                    vistaFacturaRecibida.mostrarMensaje("Factura Recibida eliminada exitosamente.");
                    break;
                case 0: // Volver al menú principal
                    vistaFacturaRecibida.mostrarMensaje("Volviendo al Menú Principal...");
                    break;
                default:
                    vistaFacturaRecibida.mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }
}