package aplikasipenjualanbarang;

import java.sql.Connection;

public class TestKoneksi {

    public static void main(String[] args) {

        Connection conn = Koneksi.getConnection();

        if (conn != null) {
            System.out.println("================================");
            System.out.println("KONEKSI DATABASE BERHASIL");
            System.out.println("================================");
        } else {
            System.out.println("================================");
            System.out.println("KONEKSI DATABASE GAGAL");
            System.out.println("================================");
        }

    }
}