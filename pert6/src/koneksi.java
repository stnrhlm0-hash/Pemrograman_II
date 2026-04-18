import java.sql.*;

public class koneksi {
    public static Connection getKoneksi() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_nilai?useSSL=false&serverTimezone=UTC",
                "root",
                ""
            );

            System.out.println("Koneksi berhasil");

        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
        return conn;
    }
}