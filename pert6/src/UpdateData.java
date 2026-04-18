import java.sql.*;
import javax.swing.*;

public class UpdateData extends JFrame {
    JTextField namaTF = new JTextField(10);
    JTextField nimTF = new JTextField(10);
    JTextField nil1TF = new JTextField(10);
    JTextField nil2TF = new JTextField(10);
    JTextField rataTF = new JTextField(10);
    JButton updateBtn = new JButton("Update");

    Connection conn = koneksi.getKoneksi();

    public UpdateData() {
        setTitle("Update Data");
        setSize(300,300);
        setLayout(new java.awt.FlowLayout());

        add(new JLabel("NIM:")); add(nimTF);
        add(new JLabel("Nama:")); add(namaTF);
        add(new JLabel("Nil1:")); add(nil1TF);
        add(new JLabel("Nil2:")); add(nil2TF);
        add(new JLabel("Rata:")); add(rataTF);
        add(updateBtn);

        updateBtn.addActionListener(e -> updateData());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void updateData() {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE datanil SET nama=?, nil1=?, nil2=?, rata=? WHERE nim=?"
            );

            ps.setString(1, namaTF.getText());
            ps.setString(2, nil1TF.getText());
            ps.setString(3, nil2TF.getText());
            ps.setString(4, rataTF.getText());
            ps.setString(5, nimTF.getText());

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(this, "Update berhasil");
            } else {
                JOptionPane.showMessageDialog(this, "Update gagal");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }
}
