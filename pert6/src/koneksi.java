import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {

    private static Connection conn;

    public static Connection getKoneksi() {
        try {
            if (conn == null || conn.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                String url = "jdbc:mysql://localhost:3306/pert4?useSSL=false&serverTimezone=UTC";
                String user = "root";
                String pass = "";

                conn = DriverManager.getConnection(url, user, pass);

                System.out.println("Koneksi berhasil ke database");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }

        return conn;
    }
}