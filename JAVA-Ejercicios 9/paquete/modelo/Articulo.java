package paquete.modelo;

// Importamos lo necesario para la base de datos
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import paquete.ConexionBasica; // Conexión a la base de datos

// Clase principal que representa un artículo
public class Articulo {

    // Clase interna que define cómo es un artículo
    public static class Entidad {
        private int idArticulo;
        private String nombre;
        private double precioUnitario;
        private int stock;

        // Constructor con todos los datos del artículo
        public Entidad(int idArticulo, String nombre, double precioUnitario, int stock) {
            this.idArticulo = idArticulo;
            this.nombre = nombre;
            this.precioUnitario = precioUnitario;
            this.stock = stock;
        }

        // Getters y setters normales, no tocar (sirven para acceder y modificar los datos)
        public int getIdArticulo() {
            return idArticulo;
        }

        public void setIdArticulo(int idArticulo) {
            this.idArticulo = idArticulo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getPrecioUnitario() {
            return precioUnitario;
        }

        public void setPrecioUnitario(double precioUnitario) {
            this.precioUnitario = precioUnitario;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        // Para mostrar los datos del artículo en forma de texto
        public String toString() {
            return "ID: " + idArticulo + ", Nombre: " + nombre + ", Precio Unitario: " + precioUnitario + ", Stock: " + stock;
        }
    }
    // Interfaz que dice qué métodos tiene que tener el DAO de artículo
    public interface ArticuloDAO {
        void crearArticulo(Entidad articulo);
        List<Entidad> listarArticulos();
        void actualizarArticulo(Entidad articulo);
        void eliminarArticulo(int idArticulo);
    }
    // Clase que implementa lo que hace cada método del DAO
    public static class ArticuloDAOImpl implements ArticuloDAO {
        // Método para insertar un artículo nuevo
        public void crearArticulo(Entidad articulo) {
            String sql = "INSERT INTO Articulos (nombre, precio_unitario, stock) VALUES (?, ?, ?)";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, articulo.getNombre());
                pstmt.setDouble(2, articulo.getPrecioUnitario());
                pstmt.setInt(3, articulo.getStock());
                pstmt.executeUpdate();
                System.out.println("Artículo creado exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al crear artículo: " + e.getMessage());
            }
        }
        // Método para sacar una lista de todos los artículos
        public List<Entidad> listarArticulos() {
            List<Entidad> articulos = new ArrayList<>();
            String sql = "SELECT * FROM Articulos";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Entidad articulo = new Entidad(
                        rs.getInt("id_articulo"),
                        rs.getString("nombre"),
                        rs.getDouble("precio_unitario"),
                        rs.getInt("stock")
                    );
                    articulos.add(articulo);
                }
            } catch (SQLException e) {
                System.out.println("Error al listar artículos: " + e.getMessage());
            }
            return articulos;
        }
        // Método para actualizar un artículo que ya existe
        public void actualizarArticulo(Entidad articulo) {
            String sql = "UPDATE Articulos SET nombre = ?, precio_unitario = ?, stock = ? WHERE id_articulo = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, articulo.getNombre());
                pstmt.setDouble(2, articulo.getPrecioUnitario());
                pstmt.setInt(3, articulo.getStock());
                pstmt.setInt(4, articulo.getIdArticulo());
                pstmt.executeUpdate();
                System.out.println("Artículo actualizado exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar artículo: " + e.getMessage());
            }
        }
        // Método para eliminar un artículo por su ID
        public void eliminarArticulo(int idArticulo) {
            String sql = "DELETE FROM Articulos WHERE id_articulo = ?";
            try (Connection conn = ConexionBasica.obtenerConexion();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idArticulo);
                pstmt.executeUpdate();
                System.out.println("Artículo eliminado exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar artículo: " + e.getMessage());
            }
        }
    }
}
