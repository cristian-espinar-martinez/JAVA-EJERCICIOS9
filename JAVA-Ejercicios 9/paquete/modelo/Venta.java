package paquete.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import paquete.ConexionBasica;

public class Venta {

    // Clase principal que representa la entidad Venta
    public static class Entidad {
        private int idVenta;
        private int idCliente;
        private int idArticulo;
        private int cantidad;
        private String fechaVenta;

        // Constructor
        public Entidad(int idVenta, int idCliente, int idArticulo, int cantidad, String fechaVenta) {
            this.idVenta = idVenta;
            this.idCliente = idCliente;
            this.idArticulo = idArticulo;
            this.cantidad = cantidad;
            this.fechaVenta = fechaVenta;
        }

        // Getters y Setters (siguiendo el mismo patrón que Cliente)
        public int getIdVenta() {
            return idVenta;
        }

        public void setIdVenta(int idVenta) {
            this.idVenta = idVenta;
        }

        public int getIdCliente() {
            return idCliente;
        }

        public void setIdCliente(int idCliente) {
            this.idCliente = idCliente;
        }

        public int getIdArticulo() {
            return idArticulo;
        }

        public void setIdArticulo(int idArticulo) {
            this.idArticulo = idArticulo;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public String getFechaVenta() {
            return fechaVenta;
        }

        public void setFechaVenta(String fechaVenta) {
            this.fechaVenta = fechaVenta;
        }

        @Override
        public String toString() {
            return "ID: " + idVenta + ", ID Cliente: " + idCliente + ", ID Artículo: " + idArticulo +
                   ", Cantidad: " + cantidad + ", Fecha Venta: " + fechaVenta;
        }
    }

    // Interfaz DAO para Venta
    public interface VentaDAO {
        void crearVenta(Entidad venta);
        List<Entidad> listarVentas();
        List<Entidad> listarVentasPorCliente(int idCliente);
        void actualizarVenta(Entidad venta);
        void eliminarVenta(int idVenta);
    }

    // Implementación de la interfaz VentaDAO
    public static class VentaDAOImpl implements VentaDAO {
        public void crearVenta(Entidad venta) {
            String sql = "INSERT INTO Ventas (id_cliente, id_articulo, cantidad, fecha_venta) VALUES (?, ?, ?, ?)";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, venta.getIdCliente());
                pstmt.setInt(2, venta.getIdArticulo());
                pstmt.setInt(3, venta.getCantidad());
                pstmt.setString(4, venta.getFechaVenta());
                pstmt.executeUpdate();
                System.out.println("Venta creada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al crear venta: " + e.getMessage());
            }
        }

        public List<Entidad> listarVentas() {
            List<Entidad> ventas = new ArrayList<>();
            String sql = "SELECT * FROM Ventas";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Entidad venta = new Entidad(
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad"),
                        rs.getString("fecha_venta")
                    );
                    ventas.add(venta);
                }
            } catch (SQLException e) {
                System.out.println("Error al listar ventas: " + e.getMessage());
            }
            return ventas;
        }
        public List<Entidad> listarVentasPorCliente(int idCliente) {
            List<Entidad> ventas = new ArrayList<>();
            String sql = "SELECT * FROM Ventas WHERE id_cliente = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idCliente);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Entidad venta = new Entidad(
                        rs.getInt("id_venta"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad"),
                        rs.getString("fecha_venta")
                    );
                    ventas.add(venta);
                }
            } catch (SQLException e) {
                System.out.println("Error al listar ventas por cliente: " + e.getMessage());
            }
            return ventas;
        }

        public void actualizarVenta(Entidad venta) {
            String sql = "UPDATE Ventas SET id_cliente = ?, id_articulo = ?, cantidad = ?, fecha_venta = ? WHERE id_venta = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, venta.getIdCliente());
                pstmt.setInt(2, venta.getIdArticulo());
                pstmt.setInt(3, venta.getCantidad());
                pstmt.setString(4, venta.getFechaVenta());
                pstmt.setInt(5, venta.getIdVenta());
                pstmt.executeUpdate();
                System.out.println("Venta actualizada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar venta: " + e.getMessage());
            }
        }

        public void eliminarVenta(int idVenta) {
            String sql = "DELETE FROM Ventas WHERE id_venta = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idVenta);
                pstmt.executeUpdate();
                System.out.println("Venta eliminada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar venta: " + e.getMessage());
            }
        }
    }
}
