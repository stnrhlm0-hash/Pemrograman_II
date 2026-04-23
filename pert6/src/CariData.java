import java.sql.*;
import javax.swing.*;

public class CariData extends JFrame {
    JTextField namaTF = new JTextField(10);
    JTextField nimTF = new JTextField(10);
    JTextField nil1TF = new JTextField(10);
    JTextField nil2TF = new JTextField(10);
    JTextField rataTF = new JTextField(10);
    JButton cariBtn = new JButton("Cari");

    Connection conn;

    public CariData() {
        // ambil koneksi
        conn = koneksi.getKoneksi();

        setTitle("Cari Data");
        setSize(300,300);
        setLayout(new java.awt.FlowLayout());

        add(new JLabel("Nama:")); add(namaTF);
        add(cariBtn);
        add(new JLabel("NIM:")); add(nimTF);
        add(new JLabel("Nil1:")); add(nil1TF);
        add(new JLabel("Nil2:")); add(nil2TF);
        add(new JLabel("Rata:")); add(rataTF);

        cariBtn.addActionListener(e -> cariData());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void cariData() {
    if (conn == null) {
        JOptionPane.showMessageDialog(this, "Koneksi database gagal!");
        return;
    }

    try {
        String sql = "SELECT * FROM datanil WHERE nama LIKE ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + namaTF.getText() + "%");

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            nimTF.setText(rs.getString("nim"));
            nil1TF.setText(rs.getString("nil1"));
            nil2TF.setText(rs.getString("nil2"));
            rataTF.setText(rs.getString("rata"));
        } else {
            JOptionPane.showMessageDialog(this, "Data tidak ada");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

    // ✅ INI YANG BIKIN BISA LANGSUNG DIJALANKAN
    public static void main(String[] args) {
        new CariData();
    }
}