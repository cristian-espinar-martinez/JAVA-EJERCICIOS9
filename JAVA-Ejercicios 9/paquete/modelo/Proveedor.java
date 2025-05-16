package paquete.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import paquete.ConexionBasica;

public class Proveedor {

    // Clase interna que representa la entidad Proveedor
    public static class Entidad {
        private int idProveedor;
        private String nombre;
        private String cif;
        private String telefono;

        // Constructor
        public Entidad(int idProveedor, String nombre, String cif, String telefono) {
            this.idProveedor = idProveedor;
            this.nombre = nombre;
            this.cif = cif;
            this.telefono = telefono;
        }

        // Getters y setters tal cual los tienes, sin cambios
        public int getIdProveedor() { return idProveedor; }
        public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getCif() { return cif; }
        public void setCif(String cif) { this.cif = cif; }
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }

        // Representación en texto del proveedor
        public String toString() {
            return "ID: " + idProveedor + ", Nombre: " + nombre + ", CIF: " + cif + ", Teléfono: " + telefono;
        }
    }

    // Interfaz DAO con las operaciones para manejar proveedores
    public interface ProveedorDAO {
        void crearProveedor(Entidad proveedor);
        List<Entidad> listarProveedores();
        void actualizarProveedor(Entidad proveedor);
        void eliminarProveedor(int idProveedor);
    }

    // Implementación de la interfaz ProveedorDAO
    public static class ProveedorDAOImpl implements ProveedorDAO {
        
        // Crear proveedor nuevo en la BD
        public void crearProveedor(Entidad proveedor) {
            String sql = "INSERT INTO Proveedores (nombre, cif, telefono) VALUES (?, ?, ?)";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, proveedor.getNombre());
                pstmt.setString(2, proveedor.getCif());
                pstmt.setString(3, proveedor.getTelefono());
                pstmt.executeUpdate();
                System.out.println("Proveedor creado exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al crear proveedor: " + e.getMessage());
            }
        }

        // Listar todos los proveedores de la BD
        public List<Entidad> listarProveedores() {
            List<Entidad> proveedores = new ArrayList<>();
            String sql = "SELECT * FROM Proveedores";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Entidad proveedor = new Entidad(
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre"),
                        rs.getString("cif"),
                        rs.getString("telefono")
                    );
                    proveedores.add(proveedor);
                }
            } catch (SQLException e) {
                System.out.println("Error al listar proveedores: " + e.getMessage());
            }
            return proveedores;
        }

        // Actualizar datos de un proveedor existente
        public void actualizarProveedor(Entidad proveedor) {
            String sql = "UPDATE Proveedores SET nombre = ?, cif = ?, telefono = ? WHERE id_proveedor = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, proveedor.getNombre());
                pstmt.setString(2, proveedor.getCif());
                pstmt.setString(3, proveedor.getTelefono());
                pstmt.setInt(4, proveedor.getIdProveedor());
                pstmt.executeUpdate();
                System.out.println("Proveedor actualizado exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar proveedor: " + e.getMessage());
            }
        }

        // Eliminar un proveedor por su ID
        public void eliminarProveedor(int idProveedor) {
            String sql = "DELETE FROM Proveedores WHERE id_proveedor = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idProveedor);
                pstmt.executeUpdate();
                System.out.println("Proveedor eliminado exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar proveedor: " + e.getMessage());
            }
        }
    }
}

