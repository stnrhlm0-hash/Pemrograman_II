import java.sql.*;
import javax.swing.*;

public class HapusData extends JFrame {

    JTextField nimTF = new JTextField(10);
    JButton hapusBtn = new JButton("Hapus");

    Connection conn;

    public HapusData() {
        conn = koneksi.getKoneksi();

        setTitle("Hapus Data");
        setSize(250,150);
        setLayout(new java.awt.FlowLayout());

        add(new JLabel("NIM:")); add(nimTF);
        add(hapusBtn);

        hapusBtn.addActionListener(e -> hapusData());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void hapusData() {
        try {
            String sql = "DELETE FROM datanil WHERE nim=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nimTF.getText());
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil dihapus");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        new HapusData();
    }
}