/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pertemuan.pkg7;

import java.io.InputStream;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.view.*;

/**
 *
 * @author Anisa
 */
public class CetakLaporan extends javax.swing.JFrame {

    Connection conn;
    Statement st;
    ResultSet rs;
    DefaultTableModel model;

    /**
     * Creates new form CetakLaporan
     */
    public CetakLaporan() {
        initComponents();
        koneksi();
        tampilData();
    }
    
    // ===================== KONEKSI =====================
    public void koneksi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/laporan",
                "root",
                ""
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    // ===================== TAMPIL DATA =====================
    public void tampilData() {
        try {
            model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM nilai");

            while (rs.next()) {
                Object[] row = {
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("matkul"),
                    rs.getInt("nil1"),
                    rs.getInt("nil2"),
                    rs.getDouble("rata")
                };
                model.addRow(row);
            }

            lblHasil.setText("Total data: " + model.getRowCount() + " mahasiswa");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tampil: " + e.getMessage());
        }
    }

   
    // ===================== SIMPAN DATA =====================
    public void simpanData() {
        try {
            String nim    = txtNim.getText().trim();
            String nama   = txtNama.getText().trim();
            String matkul = txtMatkul.getText().trim();
            String sNil1  = txtNil1.getText().trim();
            String sNil2  = txtNil2.getText().trim();

            if (nim.isEmpty() || nama.isEmpty() || matkul.isEmpty()
                    || sNil1.isEmpty() || sNil2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }

            int nil1    = Integer.parseInt(sNil1);
            int nil2    = Integer.parseInt(sNil2);
            double rata = (nil1 + nil2) / 2.0;

            // Cek apakah NIM sudah ada
            PreparedStatement cek = conn.prepareStatement(
                "SELECT nim FROM nilai WHERE nim=?");
            cek.setString(1, nim);
            ResultSet rsCek = cek.executeQuery();

            if (rsCek.next()) {
                // UPDATE
                PreparedStatement ps = conn.prepareStatement(
                    "UPDATE nilai SET nama=?, matkul=?, nil1=?, nil2=?, rata=? WHERE nim=?");
                ps.setString(1, nama);
                ps.setString(2, matkul);
                ps.setInt(3, nil1);
                ps.setInt(4, nil2);
                ps.setDouble(5, rata);
                ps.setString(6, nim);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
            } else {
                // INSERT
                PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO nilai (nim, nama, matkul, nil1, nil2, rata) VALUES (?,?,?,?,?,?)");
                ps.setString(1, nim);
                ps.setString(2, nama);
                ps.setString(3, matkul);
                ps.setInt(4, nil1);
                ps.setInt(5, nil2);
                ps.setDouble(6, rata);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
            }

            tampilData();
            bersihForm();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai 1 dan Nilai 2 harus berupa angka!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal simpan: " + e.getMessage());
        }
    }

    // ===================== UPDATE DATA =====================
    public void updateData() {
        try {
            String nim    = txtNim.getText().trim();
            String nama   = txtNama.getText().trim();
            String matkul = txtMatkul.getText().trim();
            String sNil1  = txtNil1.getText().trim();
            String sNil2  = txtNil2.getText().trim();

            if (nim.isEmpty() || nama.isEmpty() || matkul.isEmpty()
                    || sNil1.isEmpty() || sNil2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }

            int nil1    = Integer.parseInt(sNil1);
            int nil2    = Integer.parseInt(sNil2);
            double rata = (nil1 + nil2) / 2.0;

            PreparedStatement ps = conn.prepareStatement(
                "UPDATE nilai SET nama=?, matkul=?, nil1=?, nil2=?, rata=? WHERE nim=?");
            ps.setString(1, nama);
            ps.setString(2, matkul);
            ps.setInt(3, nil1);
            ps.setInt(4, nil2);
            ps.setDouble(5, rata);
            ps.setString(6, nim);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
                tampilData();
                bersihForm();
            } else {
                JOptionPane.showMessageDialog(this, "NIM tidak ditemukan!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Nilai 1 dan Nilai 2 harus berupa angka!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal update: " + e.getMessage());
        }
    }

    // ===================== HAPUS DATA =====================
    public void hapusData() {
        String nim = txtNim.getText().trim();
        if (nim.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data dari tabel terlebih dahulu!");
            return;
        }
        int konfirm = JOptionPane.showConfirmDialog(this,
            "Hapus data NIM: " + nim + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirm == JOptionPane.YES_OPTION) {
            try {
                PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM nilai WHERE nim=?");
                ps.setString(1, nim);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                tampilData();
                bersihForm();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal hapus: " + e.getMessage());
            }
        }
    }

    // ===================== BERSIH FORM =====================
    public void bersihForm() {
        txtNim.setText("");
        txtNama.setText("");
        txtMatkul.setText("");
        txtNil1.setText("");
        txtNil2.setText("");
        lblRata.setText("rata");
        txtNim.requestFocus();
    }

    // ===================== CETAK LAPORAN =====================
    public void cetakLaporan() {
        try {
        InputStream is = getClass().getResourceAsStream("/pertemuan/pkg7/Pert7.jasper");
        
        if (is == null) {
            JOptionPane.showMessageDialog(this, "File Pert7.jasper tidak ditemukan!");
            return;
        }

        java.util.Map<String, Object> params = new java.util.HashMap<>();
        JasperPrint print = JasperFillManager.fillReport(is, params, conn);

        JasperViewer viewer = new JasperViewer(print, false);
        viewer.setTitle("Laporan Nilai Mahasiswa");
        viewer.setVisible(true);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal cetak: " + e.getMessage());
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblRata = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        txtNim = new javax.swing.JTextField();
        txtMatkul = new javax.swing.JTextField();
        txtNil1 = new javax.swing.JTextField();
        txtNil2 = new javax.swing.JTextField();
        simpanData = new javax.swing.JButton();
        updateData = new javax.swing.JButton();
        hapusData = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblHasil = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nama");

        jLabel2.setText("NIM");

        jLabel3.setText("Mata Kuliah");

        jLabel4.setText("Nilai 1");

        jLabel5.setText("Nilai 2");

        simpanData.setText("Simpan");
        simpanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanDataActionPerformed(evt);
            }
        });

        updateData.setText("Update");
        updateData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDataActionPerformed(evt);
            }
        });

        hapusData.setText("Hapus");
        hapusData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusDataActionPerformed(evt);
            }
        });

        jButton4.setText("Bersih");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Cetak");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NIM", "NAMA", "MATKUL", "NILAI1", "NILAI2", "RATA-RATA"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setText("Rata - Rata");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(simpanData)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(updateData)
                                        .addGap(47, 47, 47)
                                        .addComponent(hapusData)
                                        .addGap(30, 30, 30)
                                        .addComponent(jButton4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                            .addComponent(txtNim)
                                            .addComponent(txtMatkul)
                                            .addComponent(txtNil1)
                                            .addComponent(txtNil2)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblRata, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jButton5)
                                .addGap(0, 113, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lblHasil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(23, 23, 23))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMatkul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtNil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNil2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblHasil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addComponent(lblRata)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanData)
                    .addComponent(updateData)
                    .addComponent(hapusData)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        cetakLaporan();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void simpanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanDataActionPerformed
        // TODO add your handling code here:
        simpanData();
    }//GEN-LAST:event_simpanDataActionPerformed

    private void updateDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDataActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_updateDataActionPerformed

    private void hapusDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusDataActionPerformed
        // TODO add your handling code here:
        hapusData();
    }//GEN-LAST:event_hapusDataActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        bersihForm();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();
        if (row >= 0){
            txtNim.setText(model.getValueAt(row, 0).toString());
            txtNama.setText(model.getValueAt(row, 1).toString());
            txtMatkul.setText(model.getValueAt(row, 2).toString());
            txtNil1.setText(model.getValueAt(row, 3).toString());
            txtNil2.setText(model.getValueAt(row, 4).toString());
            lblRata.setText(model.getValueAt(row, 5).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CetakLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CetakLaporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapusData;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblHasil;
    private javax.swing.JLabel lblRata;
    private javax.swing.JButton simpanData;
    private javax.swing.JTextField txtMatkul;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNil1;
    private javax.swing.JTextField txtNil2;
    private javax.swing.JTextField txtNim;
    private javax.swing.JButton updateData;
    // End of variables declaration//GEN-END:variables
}
