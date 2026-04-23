import java.sql.*;
import javax.swing.*;

public class UpdateData extends JFrame {

    JTextField nimTF = new JTextField(10);
    JTextField namaTF = new JTextField(10);
    JTextField nil1TF = new JTextField(10);
    JTextField nil2TF = new JTextField(10);

    JButton updateBtn = new JButton("Update");

    Connection conn;

    public UpdateData() {
        conn = koneksi.getKoneksi();

        setTitle("Update Data");
        setSize(300,300);
        setLayout(new java.awt.FlowLayout());

        add(new JLabel("NIM:")); add(nimTF);
        add(new JLabel("Nama:")); add(namaTF);
        add(new JLabel("Nilai 1:")); add(nil1TF);
        add(new JLabel("Nilai 2:")); add(nil2TF);
        add(updateBtn);

        updateBtn.addActionListener(e -> updateData());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void updateData() {
        try {
            String sql = "UPDATE datanil SET nama=?, nil1=?, nil2=?, rata=? WHERE nim=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            int n1 = Integer.parseInt(nil1TF.getText());
            int n2 = Integer.parseInt(nil2TF.getText());
            double rata = (n1 + n2) / 2.0;

            pst.setString(1, namaTF.getText());
            pst.setInt(2, n1);
            pst.setInt(3, n2);
            pst.setDouble(4, rata);
            pst.setString(5, nimTF.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil diupdate");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        new UpdateData();
    }
}