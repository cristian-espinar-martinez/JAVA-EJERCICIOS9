package paquete.modelo;

// Importamos lo necesario para conectar con la base de datos
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import paquete.ConexionBasica; // Importamos la clase para la conexión

public class Cliente {

    // Clase que representa un cliente con sus atributos y métodos
    public static class Entidad {
        private int idCliente;
        private String nombre;
        private String email;
        private String telefono;

        // Constructor con todos los datos del cliente
        public Entidad(int idCliente, String nombre, String email, String telefono) {
            this.idCliente = idCliente;
            this.nombre = nombre;
            this.email = email;
            this.telefono = telefono;
        }

        // Getters y setters para acceder/modificar los datos del cliente
        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        // Método para mostrar los datos del cliente en texto
        public String toString() {
            return "ID: " + idCliente + ", Nombre: " + nombre + ", Email: " + email + ", Teléfono: " + telefono;
        }
    }

    // Interfaz que dice qué operaciones se pueden hacer con clientes
    public interface ClienteDAO {
        void crearCliente(Entidad cliente);
        List<Entidad> listarClientes();
        void actualizarCliente(Entidad cliente);
        void eliminarCliente(int idCliente);
    }

    // Clase que implementa la interfaz y conecta con la base de datos
    public static class ClienteDAOImpl implements ClienteDAO {

        // Método para insertar un cliente nuevo en la BD
        public void crearCliente(Entidad cliente) {
            String sql = "INSERT INTO Clientes (nombre, email, telefono) VALUES (?, ?, ?)";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cliente.getNombre());
                pstmt.setString(2, cliente.getEmail());
                pstmt.setString(3, cliente.getTelefono());
                pstmt.executeUpdate();
                System.out.println("Cliente creado exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al crear cliente: " + e.getMessage());
            }
        }

        // Método para obtener todos los clientes de la BD
        public List<Entidad> listarClientes() {
            List<Entidad> clientes = new ArrayList<>();
            String sql = "SELECT * FROM Clientes";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Entidad cliente = new Entidad(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("telefono")
                    );
                    clientes.add(cliente);
                }
            } catch (SQLException e) {
                System.out.println("Error al listar clientes: " + e.getMessage());
            }
            return clientes;
        }

        // Método para actualizar los datos de un cliente
        public void actualizarCliente(Entidad cliente) {
            String sql = "UPDATE Clientes SET nombre = ?, email = ?, telefono = ? WHERE id_cliente = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cliente.getNombre());
                pstmt.setString(2, cliente.getEmail());
                pstmt.setString(3, cliente.getTelefono());
                pstmt.setInt(4, cliente.getIdCliente());
                pstmt.executeUpdate();
                System.out.println("Cliente actualizado exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar cliente: " + e.getMessage());
            }
        }

        // Método para borrar un cliente de la BD por su ID
        public void eliminarCliente(int idCliente) {
            String sql = "DELETE FROM Clientes WHERE id_cliente = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idCliente);
                pstmt.executeUpdate();
                System.out.println("Cliente eliminado exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
        }
    }
}
