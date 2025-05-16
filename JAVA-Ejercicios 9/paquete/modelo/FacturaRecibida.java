package paquete.modelo;

// Librerías para trabajar con la base de datos
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import paquete.ConexionBasica; // Usamos nuestra clase de conexión

// Clase que gestiona todo lo relacionado con las facturas recibidas
public class FacturaRecibida {

    // Clase interna que representa la entidad Factura Recibida (es decir, un objeto con datos de factura)
    public static class Entidad {
        private int idFactura;
        private int idProveedor;
        private String fecha;
        private double total;

        // Constructor que inicializa una factura con sus valores
        public Entidad(int idFactura, int idProveedor, String fecha, double total) {
            this.idFactura = idFactura;
            this.idProveedor = idProveedor;
            this.fecha = fecha;
            this.total = total;
        }

        // Getters y Setters para acceder o modificar los atributos (NO MODIFICAR)
        public int getIdFactura() {
            return idFactura;
        }

        public void setIdFactura(int idFactura) {
            this.idFactura = idFactura;
        }

        public int getIdProveedor() {
            return idProveedor;
        }

        public void setIdProveedor(int idProveedor) {
            this.idProveedor = idProveedor;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        // Método para mostrar la información de la factura como texto
        public String toString() {
            return "ID: " + idFactura + ", ID Proveedor: " + idProveedor + ", Fecha: " + fecha + ", Total: " + total;
        }
    }

    // Interfaz que define las operaciones que se pueden hacer con una Factura Recibida
    public interface FacturaRecibidaDAO {
        void crearFacturaRecibida(Entidad factura);
        List<Entidad> listarFacturasRecibidas();
        void actualizarFacturaRecibida(Entidad factura);
        void eliminarFacturaRecibida(int idFactura);
    }

    // Clase que implementa lo que se puede hacer con las facturas (CRUD)
    public static class FacturaRecibidaDAOImpl implements FacturaRecibidaDAO {

        // Crea una nueva factura en la base de datos
        public void crearFacturaRecibida(Entidad factura) {
            String sql = "INSERT INTO Facturas_Recibidas (id_proveedor, fecha, total) VALUES (?, ?, ?)";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, factura.getIdProveedor());
                pstmt.setString(2, factura.getFecha());
                pstmt.setDouble(3, factura.getTotal());
                pstmt.executeUpdate();
                System.out.println("Factura Recibida creada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al crear Factura Recibida: " + e.getMessage());
            }
        }

        // Lista todas las facturas guardadas en la base de datos
        public List<Entidad> listarFacturasRecibidas() {
            List<Entidad> facturas = new ArrayList<>();
            String sql = "SELECT * FROM Facturas_Recibidas";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Entidad factura = new Entidad(
                        rs.getInt("id_factura"),
                        rs.getInt("id_proveedor"),
                        rs.getString("fecha"),
                        rs.getDouble("total")
                    );
                    facturas.add(factura);
                }
            } catch (SQLException e) {
                System.out.println("Error al listar Facturas Recibidas: " + e.getMessage());
            }
            return facturas;
        }

        // Actualiza los datos de una factura existente
        public void actualizarFacturaRecibida(Entidad factura) {
            String sql = "UPDATE Facturas_Recibidas SET id_proveedor = ?, fecha = ?, total = ? WHERE id_factura = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, factura.getIdProveedor());
                pstmt.setString(2, factura.getFecha());
                pstmt.setDouble(3, factura.getTotal());
                pstmt.setInt(4, factura.getIdFactura());
                pstmt.executeUpdate();
                System.out.println("Factura Recibida actualizada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar Factura Recibida: " + e.getMessage());
            }
        }

        // Elimina una factura de la base de datos
        public void eliminarFacturaRecibida(int idFactura) {
            String sql = "DELETE FROM Facturas_Recibidas WHERE id_factura = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idFactura);
                pstmt.executeUpdate();
                System.out.println("Factura Recibida eliminada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar Factura Recibida: " + e.getMessage());
            }
        }
    }
}
