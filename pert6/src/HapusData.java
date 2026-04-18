import java.sql.*;
import javax.swing.*;

public class HapusData extends JFrame {
    JTextField nimTF = new JTextField(10);
    JButton hapusBtn = new JButton("Hapus");

    Connection conn = koneksi.getKoneksi();

    public HapusData() {
        setTitle("Hapus Data");
        setSize(300,150);
        setLayout(new java.awt.FlowLayout());

        add(new JLabel("NIM:")); add(nimTF);
        add(hapusBtn);

        hapusBtn.addActionListener(e -> hapusData());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void hapusData() {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM datanil WHERE nim=?"
            );

            ps.setString(1, nimTF.getText());

            int confirm = JOptionPane.showConfirmDialog(this, "Yakin hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(this, "Data dihapus");
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal hapus");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
