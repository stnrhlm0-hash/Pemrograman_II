package pert5;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormMHS extends JFrame {

    private DefaultTableModel mod;

    private JTable jTable1;
    private JScrollPane jScrollPane1;
    private JTextField txtNim, txtNama, txtSemester, txtKelas;
    private JButton btnSimpan;

    public FormMHS() {
        initComponents();
        setModelTabel();
        tampilData();
    }

    // 🔹 MODEL TABEL
    void setModelTabel() {
        mod = new DefaultTableModel();
        jTable1.setModel(mod);

        mod.addColumn("NIM");
        mod.addColumn("Nama");
        mod.addColumn("Semester");
        mod.addColumn("Kelas");
    }

    // 🔹 SIMPAN DATA
    void simpanData() {
        try {
            // VALIDASI INPUT
            if (txtNim.getText().isEmpty() ||
                txtNama.getText().isEmpty() ||
                txtSemester.getText().isEmpty() ||
                txtKelas.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this, "Semua field wajib diisi!");
                return;
            }

            String sql = "INSERT INTO datamhs (nim, nama, semester, kelas) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = koneksi.getConnection().prepareStatement(sql);

            pst.setString(1, txtNim.getText());
            pst.setString(2, txtNama.getText());
            pst.setInt(3, Integer.parseInt(txtSemester.getText()));
            pst.setString(4, txtKelas.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");

            tampilData();
            clearForm();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Semester harus angka!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // 🔹 TAMPIL DATA
    void tampilData() {
        mod.setRowCount(0);
        try {
            Statement st = koneksi.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM datamhs");

            while (rs.next()) {
                mod.addRow(new Object[]{
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getInt("semester"),
                    rs.getString("kelas")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error tampil: " + e.getMessage());
        }
    }

    // 🔹 CLEAR FORM
    void clearForm() {
        txtNim.setText("");
        txtNama.setText("");
        txtSemester.setText("");
        txtKelas.setText("");
    }

    // 🔹 INIT GUI
    private void initComponents() {

        jTable1 = new JTable();
        jScrollPane1 = new JScrollPane(jTable1);

        txtNim = new JTextField();
        txtNama = new JTextField();
        txtSemester = new JTextField();
        txtKelas = new JTextField();

        btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(e -> simpanData());

        // PANEL INPUT
        JPanel panelInput = new JPanel(new java.awt.GridLayout(4, 2, 10, 10));
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new JLabel("NIM"));
        panelInput.add(txtNim);

        panelInput.add(new JLabel("Nama"));
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Semester"));
        panelInput.add(txtSemester);

        panelInput.add(new JLabel("Kelas"));
        panelInput.add(txtKelas);

        // PANEL BUTTON
        JPanel panelButton = new JPanel();
        panelButton.add(btnSimpan);

        // PANEL TABEL
        JPanel panelTable = new JPanel(new java.awt.BorderLayout());
        panelTable.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        // GABUNG
        JPanel panelCenter = new JPanel(new java.awt.BorderLayout());
        panelCenter.add(panelButton, java.awt.BorderLayout.NORTH);
        panelCenter.add(panelTable, java.awt.BorderLayout.CENTER);

        setLayout(new java.awt.BorderLayout());
        add(panelInput, java.awt.BorderLayout.NORTH);
        add(panelCenter, java.awt.BorderLayout.CENTER);

        setTitle("Data Mahasiswa");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // 🔹 MAIN
    public static void main(String args[]) {

        // TES KONEKSI
        if (koneksi.getConnection() != null) {
            System.out.println("Tes koneksi sukses!");
        } else {
            System.out.println("Tes koneksi gagal!");
        }

        SwingUtilities.invokeLater(() -> {
            new FormMHS().setVisible(true);
        });
    }
}