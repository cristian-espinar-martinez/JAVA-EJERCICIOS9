package paquete.controlador;

import paquete.modelo.Proveedor;
import paquete.vista.VistaProveedor;

public class GestionarProveedores {
    private Proveedor.ProveedorDAO proveedorDAO; // Referencia al DAO de Proveedor
    private VistaProveedor vistaProveedor; // Referencia a la vista de Proveedor

    // Constructor que recibe el DAO y la vista
    public GestionarProveedores(Proveedor.ProveedorDAO proveedorDAO, VistaProveedor vistaProveedor) {
        this.proveedorDAO = proveedorDAO;
        this.vistaProveedor = vistaProveedor;
    }

    // Método principal que inicia el flujo de gestión de proveedores
    public void iniciar() {
        int opcion;

        do {
            opcion = vistaProveedor.mostrarMenuProveedores();

            switch (opcion) {
                case 1: // Crear proveedor
                    Proveedor.Entidad nuevoProveedor = vistaProveedor.obtenerDatosNuevoProveedor();
                    proveedorDAO.crearProveedor(nuevoProveedor);
                    vistaProveedor.mostrarMensaje("Proveedor creado exitosamente.");
                    break;
                case 2: // Listar proveedores
                    vistaProveedor.mostrarProveedores(proveedorDAO.listarProveedores());
                    break;
                case 3: // Actualizar proveedor
                    Proveedor.Entidad proveedorActualizado = vistaProveedor.obtenerDatosProveedorParaActualizar();
                    if (proveedorActualizado != null) {
                        proveedorDAO.actualizarProveedor(proveedorActualizado);
                        vistaProveedor.mostrarMensaje("Proveedor actualizado exitosamente.");
                    } else {
                        vistaProveedor.mostrarMensaje("Operación cancelada o ID no válido.");
                    }
                    break;
                case 4: // Eliminar proveedor
                    int idProveedorEliminar = vistaProveedor.obtenerIdProveedor();
                    proveedorDAO.eliminarProveedor(idProveedorEliminar);
                    vistaProveedor.mostrarMensaje("Proveedor eliminado exitosamente.");
                    break;
                case 0: // Volver al menú principal
                    vistaProveedor.mostrarMensaje("Volviendo al Menú Principal...");
                    break;
                default:
                    vistaProveedor.mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }
}