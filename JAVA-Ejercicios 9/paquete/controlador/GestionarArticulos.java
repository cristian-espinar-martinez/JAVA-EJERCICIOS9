package paquete.controlador;

import paquete.modelo.Articulo;
import paquete.vista.VistaArticulo;

public class GestionarArticulos {
    private Articulo.ArticuloDAO articuloDAO; // Referencia al DAO de Artículo
    private VistaArticulo vistaArticulo; // Referencia a la vista de Artículo

    // Constructor que recibe el DAO y la vista
    public GestionarArticulos(Articulo.ArticuloDAO articuloDAO, VistaArticulo vistaArticulo) {
        this.articuloDAO = articuloDAO;
        this.vistaArticulo = vistaArticulo;
    }

    // Método principal que inicia el flujo de gestión de artículos
    public void iniciar() {
        int opcion;

        do {
            opcion = vistaArticulo.mostrarMenuArticulos();

            switch (opcion) {
                case 1: // Crear artículo
                    Articulo.Entidad nuevoArticulo = vistaArticulo.obtenerDatosNuevoArticulo();
                    articuloDAO.crearArticulo(nuevoArticulo);
                    vistaArticulo.mostrarMensaje("Artículo creado exitosamente.");
                    break;
                case 2: // Listar artículos
                    vistaArticulo.mostrarArticulos(articuloDAO.listarArticulos());
                    break;
                case 3: // Actualizar artículo
                    Articulo.Entidad articuloActualizado = vistaArticulo.obtenerDatosArticuloParaActualizar();
                    articuloDAO.actualizarArticulo(articuloActualizado);
                    vistaArticulo.mostrarMensaje("Artículo actualizado exitosamente.");
                    break;
                case 4: // Eliminar artículo
                    int idArticuloEliminar = vistaArticulo.obtenerIdArticulo();
                    articuloDAO.eliminarArticulo(idArticuloEliminar);
                    vistaArticulo.mostrarMensaje("Artículo eliminado exitosamente.");
                    break;
                case 0: // Volver al menú principal
                    vistaArticulo.mostrarMensaje("Volviendo al Menú Principal...");
                    break;
                default:
                    vistaArticulo.mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }
}