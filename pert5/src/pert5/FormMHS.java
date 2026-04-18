package pert5;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class FormMHS extends javax.swing.JFrame {

    private DefaultTableModel mod;

    public FormMHS() {
        initComponents();
        setModelTabel();
        tampilData();
    }

    // 🌼 MODEL TABEL
    void setModelTabel() {
        mod = new DefaultTableModel();
        jTable1.setModel(mod);

        mod.addColumn("NIM");
        mod.addColumn("Nama");
        mod.addColumn("Semester");
        mod.addColumn("Kelas");
    }

    // 🌼 SIMPAN DATA
    void simpanData() {
        try {
            String sql = "INSERT INTO datamhs VALUES (?, ?, ?, ?)";
            PreparedStatement pst = koneksi.getConnection().prepareStatement(sql);

            pst.setString(1, txtNim.getText());
            pst.setString(2, txtNama.getText());
            pst.setInt(3, Integer.parseInt(txtSemester.getText()));
            pst.setString(4, txtKelas.getText());

            pst.executeUpdate();
            tampilData();

        } catch (Exception e) {
            System.out.println("Error simpan: " + e.getMessage());
        }
    }

    // 🌼 TAMPIL DATA
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
            System.out.println("Error tampil: " + e.getMessage());
        }
    }

    // 🌼 INIT GUI
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        txtNim = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtSemester = new javax.swing.JTextField();
        txtKelas = new javax.swing.JTextField();

        btnSimpan = new javax.swing.JButton("Simpan");

        btnSimpan.addActionListener(evt -> simpanData());

        jScrollPane1.setViewportView(jTable1);

        // 🌸 LAYOUT UTAMA
        setLayout(new java.awt.BorderLayout());

        // 🌼 PANEL INPUT
        javax.swing.JPanel panelInput = new javax.swing.JPanel();
        panelInput.setLayout(new java.awt.GridLayout(4, 2, 10, 10));
        panelInput.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelInput.add(new javax.swing.JLabel("NIM"));
        panelInput.add(txtNim);

        panelInput.add(new javax.swing.JLabel("Nama"));
        panelInput.add(txtNama);

        panelInput.add(new javax.swing.JLabel("Semester"));
        panelInput.add(txtSemester);

        panelInput.add(new javax.swing.JLabel("Kelas"));
        panelInput.add(txtKelas);

        // 🌼 PANEL BUTTON
        javax.swing.JPanel panelButton = new javax.swing.JPanel();
        panelButton.add(btnSimpan);

        // 🌼 PANEL TABEL
        javax.swing.JPanel panelTable = new javax.swing.JPanel(new java.awt.BorderLayout());
        panelTable.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        // 🌼 GABUNG BUTTON + TABEL
        javax.swing.JPanel panelCenter = new javax.swing.JPanel(new java.awt.BorderLayout());
        panelCenter.add(panelButton, java.awt.BorderLayout.NORTH);
        panelCenter.add(panelTable, java.awt.BorderLayout.CENTER);

        // 🌼 TAMBAH KE FRAME
        add(panelInput, java.awt.BorderLayout.NORTH);
        add(panelCenter, java.awt.BorderLayout.CENTER);

        // 🌼 SETTING FRAME
        setTitle("Data Mahasiswa");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    // 🌼 MAIN METHOD
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FormMHS().setVisible(true);
        });
    }

    // 🌼 VARIABLE
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtNim, txtNama, txtSemester, txtKelas;
    private javax.swing.JButton btnSimpan;
}