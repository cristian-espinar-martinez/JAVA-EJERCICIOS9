package paquete.controlador;

import paquete.modelo.Cliente;
import paquete.vista.VistaCliente;

public class GestionarClientes {
    private Cliente.ClienteDAO clienteDAO; // Referencia al DAO de Cliente
    private VistaCliente vistaCliente; // Referencia a la vista de Cliente

    // Constructor que recibe el DAO y la vista
    public GestionarClientes(Cliente.ClienteDAO clienteDAO, VistaCliente vistaCliente) {
        this.clienteDAO = clienteDAO;
        this.vistaCliente = vistaCliente;
    }
    
    // Método principal que inicia el flujo de gestión de clientes
    public void iniciar() {
        int opcion;

        do {
            opcion = vistaCliente.mostrarMenuClientes(); // Llama al menú específico de clientes

            switch (opcion) {
                case 1: // Crear cliente
                    Cliente.Entidad nuevoCliente = vistaCliente.obtenerDatosNuevoCliente();
                    clienteDAO.crearCliente(nuevoCliente);
                    vistaCliente.mostrarMensaje("Cliente creado exitosamente.");
                    break;
                case 2: // Listar clientes
                    vistaCliente.mostrarClientes(clienteDAO.listarClientes());
                    break;
                case 3: // Actualizar cliente
                    Cliente.Entidad clienteActualizado = vistaCliente.obtenerDatosClienteParaActualizar();
                    if (clienteActualizado != null) {
                        clienteDAO.actualizarCliente(clienteActualizado);
                        vistaCliente.mostrarMensaje("Cliente actualizado exitosamente.");
                    } else {
                        vistaCliente.mostrarMensaje("Operación cancelada o ID no válido.");
                    }
                    break;
                case 4: // Eliminar cliente
                    int idClienteEliminar = vistaCliente.obtenerIdCliente();
                    clienteDAO.eliminarCliente(idClienteEliminar);
                    vistaCliente.mostrarMensaje("Cliente eliminado exitosamente.");
                    break;
                case 0: // Volver al menú principal
                    vistaCliente.mostrarMensaje("Volviendo al Menú Principal...");
                    break;
                default:
                    vistaCliente.mostrarMensaje("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }
}