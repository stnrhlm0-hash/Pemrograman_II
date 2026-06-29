package aplikasipenjualanbarang;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class Penjualan extends JFrame {

    private CardLayout cardLayout;
    private JPanel panelKonten;

    public Penjualan() {

        setTitle("Aplikasi Penjualan Barang");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ================= HEADER =================
        JLabel header = new JLabel(
                "APLIKASI PENJUALAN BARANG",
                SwingConstants.CENTER);

        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setOpaque(true);
        header.setBackground(new Color(144, 202, 249));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 60));

        // ================= MENU =================
        JMenuBar menuBar = new JMenuBar();

        JMenu menuMaster = new JMenu("Data Master");
        JMenu menuTransaksi = new JMenu("Transaksi");
        JMenu menuLaporan = new JMenu("Laporan");
        JMenu menuKeluar = new JMenu("Keluar");

        JMenuItem mBarang = new JMenuItem("Data Barang");
        JMenuItem mCustomer = new JMenuItem("Data Customer");
        JMenuItem mSupplier = new JMenuItem("Data Supplier");
        JMenuItem mInventory = new JMenuItem("Inventory Barang");

        JMenuItem mPenjualan = new JMenuItem("Transaksi Penjualan");
        JMenuItem mLapTransaksi =
                new JMenuItem("Laporan Transaksi");
        JMenuItem mLapInventory =
                new JMenuItem("Laporan Inventory");
        JMenuItem mKeluar = new JMenuItem("Keluar Aplikasi");

        menuMaster.add(mBarang);
        menuMaster.add(mCustomer);
        menuMaster.add(mSupplier);
        menuMaster.add(mInventory);

        menuTransaksi.add(mPenjualan);
        menuLaporan.add(mLapTransaksi);
        menuLaporan.add(mLapInventory);
        menuKeluar.add(mKeluar);

        menuBar.add(menuMaster);
        menuBar.add(menuTransaksi);
        menuBar.add(menuLaporan);
        menuBar.add(menuKeluar);

        setJMenuBar(menuBar);

        // ================= CARD LAYOUT =================
        cardLayout = new CardLayout();
        panelKonten = new JPanel(cardLayout);

        JPanel welcome = new JPanel();
        welcome.setBackground(new Color(240,248,255));
        welcome.setLayout(new GridBagLayout());

        JLabel lblWelcome = new JLabel(
                "<html><center>"
                + "<h1>Selamat Datang</h1>"
                + "<p>Sistem Informasi Penjualan Barang</p>"
                + "<p>Berbasis Java dan MySQL</p>"
                + "<br>"
                + "<p>Kelola Data Barang, Customer, Supplier,</p>"
                + "<p>Inventory dan Transaksi dengan Mudah</p>"
                + "</center></html>");

        welcome.add(lblWelcome);

        panelKonten.add(welcome, "WELCOME");

        // sementara panel kosong dulu
        panelKonten.add(createBarangPanel(), "BARANG");
        panelKonten.add(createCustomerPanel(), "CUSTOMER");
        panelKonten.add(createSupplierPanel(), "SUPPLIER");
        panelKonten.add(createInventoryPanel(), "INVENTORY");
        panelKonten.add(createTransaksiPanel(), "TRANSAKSI");
        panelKonten.add(
            createLaporanTransaksiPanel(),
            "LAPTRANSAKSI");

        panelKonten.add(
            createLaporanInventoryPanel(),
            "LAPINVENTORY");
        // ================= EVENT MENU =================

        mBarang.addActionListener(e ->
                cardLayout.show(panelKonten, "BARANG"));

        mCustomer.addActionListener(e ->
                cardLayout.show(panelKonten, "CUSTOMER"));

        mSupplier.addActionListener(e ->
                cardLayout.show(panelKonten, "SUPPLIER"));
        
        mInventory.addActionListener(e ->
        cardLayout.show(panelKonten, "INVENTORY"));

        mPenjualan.addActionListener(e ->
                cardLayout.show(panelKonten, "TRANSAKSI"));
        
       mLapTransaksi.addActionListener(e ->
                cardLayout.show(panelKonten, "LAPTRANSAKSI"));

        mLapInventory.addActionListener(e ->
                cardLayout.show(panelKonten, "LAPINVENTORY"));

        mKeluar.addActionListener(e ->
                System.exit(0));

        setLayout(new BorderLayout());

        add(header, BorderLayout.NORTH);
        add(panelKonten, BorderLayout.CENTER);
    }
    
        private JPanel createBarangPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240,248,255));
        
                panel.setBorder(
            BorderFactory.createEmptyBorder(
                20,20,20,20
            )
        );
        
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4,2,20,15));
        form.setBorder(BorderFactory.createEmptyBorder(20,250,20,250));

        JTextField txtIdBarang = new JTextField();
        JTextField txtNamaBarang = new JTextField();
        JTextField txtHarga = new JTextField();
        JTextField txtStok = new JTextField();

        form.add(new JLabel("ID Barang"));
        form.add(txtIdBarang);

        form.add(new JLabel("Nama Barang"));
        form.add(txtNamaBarang);

        form.add(new JLabel("Harga"));
        form.add(txtHarga);

        form.add(new JLabel("Stok"));
        form.add(txtStok);

        JPanel tombol = new JPanel();

        JButton btnSimpan = new JButton("Simpan");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        tombol.add(btnSimpan);
        tombol.add(btnUpdate);
        tombol.add(btnHapus);
        tombol.add(btnReset);

        String[] kolom = {
            "ID Barang",
            "Nama Barang",
            "Harga",
            "Stok"
        };

        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(null, kolom);

        JTable tabel = new JTable(model);

        JScrollPane scroll = new JScrollPane(tabel);

       btnSimpan.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "INSERT INTO barang "
                        + "(id_barang,nama_barang,harga,stok) "
                        + "VALUES (?,?,?,?)";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, txtIdBarang.getText());
                pst.setString(2, txtNamaBarang.getText());
                pst.setInt(3,
                        Integer.parseInt(txtHarga.getText()));
                pst.setInt(4,
                        Integer.parseInt(txtStok.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Data Barang Berhasil Disimpan");

                model.addRow(new Object[]{
                    txtIdBarang.getText(),
                    txtNamaBarang.getText(),
                    txtHarga.getText(),
                    txtStok.getText()
                });

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Gagal Menyimpan Data\n"
                        + ex.getMessage());

            }
        });

        btnReset.addActionListener(e -> {

            txtIdBarang.setText("");
            txtNamaBarang.setText("");
            txtHarga.setText("");
            txtStok.setText("");

            tabel.clearSelection();
        });

        tabel.getSelectionModel().addListSelectionListener(e -> {

            int row = tabel.getSelectedRow();

            if(row >= 0){

                txtIdBarang.setText(model.getValueAt(row,0).toString());
                txtNamaBarang.setText(model.getValueAt(row,1).toString());
                txtHarga.setText(model.getValueAt(row,2).toString());
                txtStok.setText(model.getValueAt(row,3).toString());

            }

        });

        btnUpdate.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "UPDATE barang SET "
                        + "nama_barang=?, "
                        + "harga=?, "
                        + "stok=? "
                        + "WHERE id_barang=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, txtNamaBarang.getText());
                pst.setInt(2,
                        Integer.parseInt(txtHarga.getText()));
                pst.setInt(3,
                        Integer.parseInt(txtStok.getText()));
                pst.setString(4,
                        txtIdBarang.getText());

                pst.executeUpdate();

                int row = tabel.getSelectedRow();

                if(row >= 0){

                    model.setValueAt(
                            txtIdBarang.getText(),
                            row,
                            0);

                    model.setValueAt(
                            txtNamaBarang.getText(),
                            row,
                            1);

                    model.setValueAt(
                            txtHarga.getText(),
                            row,
                            2);

                    model.setValueAt(
                            txtStok.getText(),
                            row,
                            3);

                }

                JOptionPane.showMessageDialog(
                        null,
                        "Data Berhasil Diupdate");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }
        });

        btnHapus.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "DELETE FROM barang "
                        + "WHERE id_barang=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(
                        1,
                        txtIdBarang.getText());

                pst.executeUpdate();

                int row = tabel.getSelectedRow();

                if(row >= 0){

                    model.removeRow(row);

                }

                JOptionPane.showMessageDialog(
                        null,
                        "Data Berhasil Dihapus");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }

        });
        
        JLabel judul = new JLabel(
        "DATA BARANG",
        SwingConstants.CENTER);

        judul.setFont(
                new Font("Segoe UI",
                         Font.BOLD,
                         18));
        
        JPanel atas = new JPanel(new BorderLayout());
        
        atas.add(judul, BorderLayout.NORTH);
        atas.add(form, BorderLayout.CENTER);
        atas.add(tombol, BorderLayout.SOUTH);
       
        panel.add(atas, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }
        
        private JPanel createCustomerPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240,248,255));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4,2,20,15));
        form.setBorder(BorderFactory.createEmptyBorder(20,250,20,250));

        JTextField txtId = new JTextField();
        JTextField txtNama = new JTextField();
        JTextField txtAlamat = new JTextField();
        JTextField txtHp = new JTextField();

        form.add(new JLabel("ID Customer"));
        form.add(txtId);

        form.add(new JLabel("Nama Customer"));
        form.add(txtNama);

        form.add(new JLabel("Alamat"));
        form.add(txtAlamat);

        form.add(new JLabel("No HP"));
        form.add(txtHp);

        JPanel tombol = new JPanel();

        JButton btnSimpan = new JButton("Simpan");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        tombol.add(btnSimpan);
        tombol.add(btnUpdate);
        tombol.add(btnHapus);
        tombol.add(btnReset);

        String[] kolom = {
            "ID Customer",
            "Nama Customer",
            "Alamat",
            "No HP"
        };

        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(null, kolom);

        JTable tabel = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabel);

        btnSimpan.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "INSERT INTO customer "
                        + "(id_customer,nama_customer,alamat,no_hp) "
                        + "VALUES (?,?,?,?)";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, txtId.getText());
                pst.setString(2, txtNama.getText());
                pst.setString(3, txtAlamat.getText());
                pst.setString(4, txtHp.getText());

                pst.executeUpdate();

                model.addRow(new Object[]{
                    txtId.getText(),
                    txtNama.getText(),
                    txtAlamat.getText(),
                    txtHp.getText()
                });

                JOptionPane.showMessageDialog(
                        null,
                        "Data Customer Berhasil Disimpan");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }

        });

        btnReset.addActionListener(e -> {

            txtId.setText("");
            txtNama.setText("");
            txtAlamat.setText("");
            txtHp.setText("");
            
            tabel.clearSelection();
        });

        tabel.getSelectionModel().addListSelectionListener(e -> {

            int row = tabel.getSelectedRow();

            if(row >= 0){

                txtId.setText(model.getValueAt(row,0).toString());
                txtNama.setText(model.getValueAt(row,1).toString());
                txtAlamat.setText(model.getValueAt(row,2).toString());
                txtHp.setText(model.getValueAt(row,3).toString());

            }

        });

        btnUpdate.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "UPDATE customer SET "
                        + "nama_customer=?, "
                        + "alamat=?, "
                        + "no_hp=? "
                        + "WHERE id_customer=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, txtNama.getText());
                pst.setString(2, txtAlamat.getText());
                pst.setString(3, txtHp.getText());
                pst.setString(4, txtId.getText());

                pst.executeUpdate();

                int row = tabel.getSelectedRow();

                if(row >= 0){

                    model.setValueAt(txtId.getText(), row, 0);
                    model.setValueAt(txtNama.getText(), row, 1);
                    model.setValueAt(txtAlamat.getText(), row, 2);
                    model.setValueAt(txtHp.getText(), row, 3);

                }

                JOptionPane.showMessageDialog(
                        null,
                        "Data Customer Berhasil Diupdate");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }
        });

        btnHapus.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "DELETE FROM customer "
                        + "WHERE id_customer=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(
                        1,
                        txtId.getText());

                pst.executeUpdate();

                int row = tabel.getSelectedRow();

                if(row >= 0){

                    model.removeRow(row);

                }

                JOptionPane.showMessageDialog(
                        null,
                        "Data Customer Berhasil Dihapus");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }
        });

        JLabel judul = new JLabel(
                "DATA CUSTOMER",
                SwingConstants.CENTER);

        judul.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        18));

        JPanel atas = new JPanel(new BorderLayout());

        atas.add(judul, BorderLayout.NORTH);
        atas.add(form, BorderLayout.CENTER);
        atas.add(tombol, BorderLayout.SOUTH);

        panel.add(atas, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }
        
        private JPanel createSupplierPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240,248,255));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4,2,20,15));
        form.setBorder(BorderFactory.createEmptyBorder(20,250,20,250));

        JTextField txtId = new JTextField();
        JTextField txtNama = new JTextField();
        JTextField txtAlamat = new JTextField();
        JTextField txtHp = new JTextField();

        form.add(new JLabel("ID Supplier"));
        form.add(txtId);

        form.add(new JLabel("Nama Supplier"));
        form.add(txtNama);

        form.add(new JLabel("Alamat"));
        form.add(txtAlamat);

        form.add(new JLabel("No HP"));
        form.add(txtHp);

        JPanel tombol = new JPanel();

        JButton btnSimpan = new JButton("Simpan");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        tombol.add(btnSimpan);
        tombol.add(btnUpdate);
        tombol.add(btnHapus);
        tombol.add(btnReset);

        String[] kolom = {
            "ID Supplier",
            "Nama Supplier",
            "Alamat",
            "No HP"
        };

        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(null, kolom);

        JTable tabel = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabel);

        btnSimpan.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "INSERT INTO supplier "
                        + "(id_supplier,nama_supplier,alamat,no_hp) "
                        + "VALUES (?,?,?,?)";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, txtId.getText());
                pst.setString(2, txtNama.getText());
                pst.setString(3, txtAlamat.getText());
                pst.setString(4, txtHp.getText());

                pst.executeUpdate();

                model.addRow(new Object[]{
                    txtId.getText(),
                    txtNama.getText(),
                    txtAlamat.getText(),
                    txtHp.getText()
                });

                JOptionPane.showMessageDialog(
                        null,
                        "Data Supplier Berhasil Disimpan");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }
        });

        btnReset.addActionListener(e -> {

            txtId.setText("");
            txtNama.setText("");
            txtAlamat.setText("");
            txtHp.setText("");
            
            tabel.clearSelection();
        });

        tabel.getSelectionModel().addListSelectionListener(e -> {

            int row = tabel.getSelectedRow();

            if(row >= 0){

                txtId.setText(model.getValueAt(row,0).toString());
                txtNama.setText(model.getValueAt(row,1).toString());
                txtAlamat.setText(model.getValueAt(row,2).toString());
                txtHp.setText(model.getValueAt(row,3).toString());

            }

        });

        btnUpdate.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "UPDATE supplier SET "
                        + "nama_supplier=?, "
                        + "alamat=?, "
                        + "no_hp=? "
                        + "WHERE id_supplier=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, txtNama.getText());
                pst.setString(2, txtAlamat.getText());
                pst.setString(3, txtHp.getText());
                pst.setString(4, txtId.getText());

                pst.executeUpdate();

                int row = tabel.getSelectedRow();

                if(row >= 0){

                    model.setValueAt(txtId.getText(), row, 0);
                    model.setValueAt(txtNama.getText(), row, 1);
                    model.setValueAt(txtAlamat.getText(), row, 2);
                    model.setValueAt(txtHp.getText(), row, 3);

                }

                JOptionPane.showMessageDialog(
                        null,
                        "Data Supplier Berhasil Diupdate");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }
        });
        
        btnHapus.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "DELETE FROM supplier "
                        + "WHERE id_supplier=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(
                        1,
                        txtId.getText());

                pst.executeUpdate();

                int row = tabel.getSelectedRow();

                if(row >= 0){

                    model.removeRow(row);

                }

                JOptionPane.showMessageDialog(
                        null,
                        "Data Supplier Berhasil Dihapus");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }
        });

        JLabel judul = new JLabel(
                "DATA SUPPLIER",
                SwingConstants.CENTER);

        judul.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        18));

        JPanel atas = new JPanel(new BorderLayout());

        atas.add(judul, BorderLayout.NORTH);
        atas.add(form, BorderLayout.CENTER);
        atas.add(tombol, BorderLayout.SOUTH);

        panel.add(atas, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }
        
        private JPanel createInventoryPanel() {

            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(new Color(240,248,255));
            panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            JPanel form = new JPanel();
            form.setLayout(new GridLayout(5,2,20,15));
            form.setBorder(BorderFactory.createEmptyBorder(20,250,20,250));

            JTextField txtIdBarang = new JTextField();
            JTextField txtNamaBarang = new JTextField();
            JTextField txtStok = new JTextField();
            JTextField txtMasuk = new JTextField();
            JTextField txtKeluar = new JTextField();

            form.add(new JLabel("ID Barang"));
            form.add(txtIdBarang);

            form.add(new JLabel("Nama Barang"));
            form.add(txtNamaBarang);

            form.add(new JLabel("Stok Saat Ini"));
            form.add(txtStok);

            form.add(new JLabel("Barang Masuk"));
            form.add(txtMasuk);

            form.add(new JLabel("Barang Keluar"));
            form.add(txtKeluar);

            JPanel tombol = new JPanel();

            JButton btnHitung = new JButton("Hitung Stok");
            JButton btnSimpan = new JButton("Simpan");
            JButton btnUpdate = new JButton("Update");
            JButton btnHapus = new JButton("Hapus");
            JButton btnReset = new JButton("Reset");

            tombol.add(btnHitung);
            tombol.add(btnSimpan);
            tombol.add(btnUpdate);
            tombol.add(btnHapus);
            tombol.add(btnReset);

            String[] kolom = {
                "ID Barang",
                "Nama Barang",
                "Stok"
            };

            javax.swing.table.DefaultTableModel model =
                    new javax.swing.table.DefaultTableModel(
                            null,
                            kolom);
            
            try {

                Connection conn =
                        Koneksi.getConnection();

                String sql =
                        "SELECT * FROM inventory";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery();

                while(rs.next()){

                    model.addRow(new Object[]{

                        rs.getString("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("stok_saat_ini")

                    });

                }

            } catch(Exception e){

                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage());

            }
            
            JTable tabel = new JTable(model);

            JScrollPane scroll =
                    new JScrollPane(tabel);

            // HITUNG STOK
            btnHitung.addActionListener(e -> {

                try {

                    int stok =
                            Integer.parseInt(txtStok.getText());

                    int masuk = txtMasuk.getText().isEmpty()
                            ? 0
                            : Integer.parseInt(txtMasuk.getText());

                    int keluar = txtKeluar.getText().isEmpty()
                            ? 0
                            : Integer.parseInt(txtKeluar.getText());

                    int stokBaru =
                            stok + masuk - keluar;

                    txtStok.setText(
                            String.valueOf(stokBaru));

                } catch(Exception ex){

                    JOptionPane.showMessageDialog(
                            null,
                            "Input harus angka!");

                }

            });

            // SIMPAN
            btnSimpan.addActionListener(e -> {

                try {

                    Connection conn =
                            Koneksi.getConnection();

                    String sql =
                            "INSERT INTO inventory "
                            + "(id_barang,nama_barang,"
                            + "stok_saat_ini,barang_masuk,barang_keluar) "
                            + "VALUES (?,?,?,?,?)";

                    PreparedStatement pst =
                            conn.prepareStatement(sql);

                    pst.setString(1, txtIdBarang.getText());
                    pst.setString(2, txtNamaBarang.getText());
                    pst.setInt(3,
                            Integer.parseInt(txtStok.getText()));
                    pst.setInt(4,
                            Integer.parseInt(txtMasuk.getText()));
                    pst.setInt(5,
                            Integer.parseInt(txtKeluar.getText()));

                    pst.executeUpdate();

                    model.addRow(new Object[]{
                        txtIdBarang.getText(),
                        txtNamaBarang.getText(),
                        txtStok.getText()
                    });

                    JOptionPane.showMessageDialog(
                            null,
                            "Data Inventory Berhasil Disimpan");

                } catch(Exception ex){

                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage());

                }

            });

            // KLIK TABEL
            tabel.getSelectionModel().addListSelectionListener(e -> {

                int row = tabel.getSelectedRow();

                if(row >= 0){

                    txtIdBarang.setText(
                            model.getValueAt(row,0).toString());

                    txtNamaBarang.setText(
                            model.getValueAt(row,1).toString());

                    txtStok.setText(
                            model.getValueAt(row,2).toString());

                }

            });

            // UPDATE
            btnUpdate.addActionListener(e -> {

                try {

                    Connection conn =
                            Koneksi.getConnection();

                    String sql =
                            "UPDATE inventory SET "
                            + "nama_barang=?, "
                            + "stok_saat_ini=?, "
                            + "barang_masuk=?, "
                            + "barang_keluar=? "
                            + "WHERE id_barang=?";

                    PreparedStatement pst =
                            conn.prepareStatement(sql);

                    pst.setString(1, txtNamaBarang.getText());
                    pst.setInt(2,
                            Integer.parseInt(txtStok.getText()));
                    pst.setInt(3,
                            Integer.parseInt(txtMasuk.getText()));
                    pst.setInt(4,
                            Integer.parseInt(txtKeluar.getText()));
                    pst.setString(5,
                            txtIdBarang.getText());

                    pst.executeUpdate();

                    int row = tabel.getSelectedRow();

                    if(row >= 0){

                        model.setValueAt(
                                txtIdBarang.getText(),
                                row,
                                0);

                        model.setValueAt(
                                txtNamaBarang.getText(),
                                row,
                                1);

                        model.setValueAt(
                                txtStok.getText(),
                                row,
                                2);

                    }

                    JOptionPane.showMessageDialog(
                            null,
                            "Data Inventory Berhasil Diupdate");

                } catch(Exception ex){

                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage());

                }

            });

            // HAPUS
            btnHapus.addActionListener(e -> {

                try {

                    Connection conn =
                            Koneksi.getConnection();

                    String sql =
                            "DELETE FROM inventory "
                            + "WHERE id_barang=?";

                    PreparedStatement pst =
                            conn.prepareStatement(sql);

                    pst.setString(
                            1,
                            txtIdBarang.getText());

                    pst.executeUpdate();

                    int row = tabel.getSelectedRow();

                    if(row >= 0){

                        model.removeRow(row);

                    }

                    JOptionPane.showMessageDialog(
                            null,
                            "Data Inventory Berhasil Dihapus");

                } catch(Exception ex){

                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage());

                }
            });

            // RESET
            btnReset.addActionListener(e -> {

                txtIdBarang.setText("");
                txtNamaBarang.setText("");
                txtStok.setText("");
                txtMasuk.setText("");
                txtKeluar.setText("");

                tabel.clearSelection();

            });

            JLabel judul = new JLabel(
                    "INVENTORY BARANG",
                    SwingConstants.CENTER);

            judul.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            18));

            JPanel atas =
                    new JPanel(new BorderLayout());

            atas.add(judul, BorderLayout.NORTH);
            atas.add(form, BorderLayout.CENTER);
            atas.add(tombol, BorderLayout.SOUTH);

            panel.add(atas, BorderLayout.NORTH);
            panel.add(scroll, BorderLayout.CENTER);

            return panel;
        }
        
        private JPanel createTransaksiPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240,248,255));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(6,2,20,15));
        form.setBorder(BorderFactory.createEmptyBorder(20,250,20,250));

        JTextField txtId = new JTextField();
        JTextField txtTanggal = new JTextField();
        JTextField txtCustomer = new JTextField();
        JTextField txtBarang = new JTextField();
        JTextField txtJumlah = new JTextField();
        JTextField txtTotal = new JTextField();

        txtTotal.setEditable(false);

        form.add(new JLabel("ID Transaksi"));
        form.add(txtId);

        form.add(new JLabel("Tanggal"));
        form.add(txtTanggal);

        form.add(new JLabel("Customer"));
        form.add(txtCustomer);

        form.add(new JLabel("Barang"));
        form.add(txtBarang);

        form.add(new JLabel("Jumlah"));
        form.add(txtJumlah);

        form.add(new JLabel("Total"));
        form.add(txtTotal);

        JPanel tombol = new JPanel();

        JButton btnHitung = new JButton("Hitung");
        JButton btnSimpan = new JButton("Simpan");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        tombol.add(btnHitung);
        tombol.add(btnSimpan);
        tombol.add(btnUpdate);
        tombol.add(btnHapus);
        tombol.add(btnReset);

        String[] kolom = {
            "ID",
            "Tanggal",
            "Customer",
            "Barang",
            "Jumlah",
            "Total"
        };

        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(null, kolom);

        JTable tabel = new JTable(model);
        JScrollPane scroll = new JScrollPane(tabel);

        btnHitung.addActionListener(e -> {

            try {

                int jumlah =
                        Integer.parseInt(txtJumlah.getText());

                int hargaSatuan = 50000;

                int total = jumlah * hargaSatuan;

                txtTotal.setText(
                        String.valueOf(total));

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Jumlah harus angka!");

            }

        });

        btnSimpan.addActionListener(e -> {

            try {

                Connection conn = Koneksi.getConnection();

                String sql =
                        "INSERT INTO transaksi "
                        + "(id_transaksi,tanggal,customer,barang,jumlah,total) "
                        + "VALUES (?,?,?,?,?,?)";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, txtId.getText());
                pst.setString(2, txtTanggal.getText());
                pst.setString(3, txtCustomer.getText());
                pst.setString(4, txtBarang.getText());
                pst.setInt(5,
                        Integer.parseInt(txtJumlah.getText()));
                pst.setInt(6,
                        Integer.parseInt(txtTotal.getText()));

                pst.executeUpdate();

                model.addRow(new Object[]{
                    txtId.getText(),
                    txtTanggal.getText(),
                    txtCustomer.getText(),
                    txtBarang.getText(),
                    txtJumlah.getText(),
                    txtTotal.getText()
                });

                JOptionPane.showMessageDialog(
                        null,
                        "Transaksi Berhasil Disimpan");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }
        });

        btnReset.addActionListener(e -> {

            txtId.setText("");
            txtTanggal.setText("");
            txtCustomer.setText("");
            txtBarang.setText("");
            txtJumlah.setText("");
            txtTotal.setText("");

            tabel.clearSelection();
        });

        tabel.getSelectionModel().addListSelectionListener(e -> {

            int row = tabel.getSelectedRow();

            if(row >= 0){

                txtId.setText(model.getValueAt(row,0).toString());
                txtTanggal.setText(model.getValueAt(row,1).toString());
                txtCustomer.setText(model.getValueAt(row,2).toString());
                txtBarang.setText(model.getValueAt(row,3).toString());
                txtJumlah.setText(model.getValueAt(row,4).toString());
                txtTotal.setText(model.getValueAt(row,5).toString());

            }

        });

       btnUpdate.addActionListener(e -> {

            try {

                Connection conn =
                        Koneksi.getConnection();

                String sql =
                        "UPDATE transaksi SET "
                        + "tanggal=?, "
                        + "customer=?, "
                        + "barang=?, "
                        + "jumlah=?, "
                        + "total=? "
                        + "WHERE id_transaksi=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(1, txtTanggal.getText());
                pst.setString(2, txtCustomer.getText());
                pst.setString(3, txtBarang.getText());
                pst.setInt(4,
                        Integer.parseInt(txtJumlah.getText()));
                pst.setInt(5,
                        Integer.parseInt(txtTotal.getText()));
                pst.setString(6, txtId.getText());

                pst.executeUpdate();

                int row = tabel.getSelectedRow();

                if(row >= 0){

                    model.setValueAt(txtId.getText(), row, 0);
                    model.setValueAt(txtTanggal.getText(), row, 1);
                    model.setValueAt(txtCustomer.getText(), row, 2);
                    model.setValueAt(txtBarang.getText(), row, 3);
                    model.setValueAt(txtJumlah.getText(), row, 4);
                    model.setValueAt(txtTotal.getText(), row, 5);

                }

                JOptionPane.showMessageDialog(
                        null,
                        "Transaksi Berhasil Diupdate");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }
        });

        btnHapus.addActionListener(e -> {

            try {

                Connection conn =
                        Koneksi.getConnection();

                String sql =
                        "DELETE FROM transaksi "
                        + "WHERE id_transaksi=?";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                pst.setString(
                        1,
                        txtId.getText());

                pst.executeUpdate();

                int row = tabel.getSelectedRow();

                if(row >= 0){

                    model.removeRow(row);

                }

                JOptionPane.showMessageDialog(
                        null,
                        "Transaksi Berhasil Dihapus");

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }
        });

        JLabel judul = new JLabel(
                "TRANSAKSI PENJUALAN",
                SwingConstants.CENTER);

        judul.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        18));

        JPanel atas = new JPanel(new BorderLayout());

        atas.add(judul, BorderLayout.NORTH);
        atas.add(form, BorderLayout.CENTER);
        atas.add(tombol, BorderLayout.SOUTH);

        panel.add(atas, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }
        
    private JPanel createLaporanTransaksiPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel judul = new JLabel(
                "LAPORAN TRANSAKSI",
                SwingConstants.CENTER);

        judul.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        String[] kolom = {
            "ID",
            "Tanggal",
            "Customer",
            "Barang",
            "Jumlah",
            "Total"
        };

        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(
                        null,
                        kolom);
        
                try {

            Connection conn =
                    Koneksi.getConnection();

            String sql =
                    "SELECT * FROM transaksi";

            PreparedStatement pst =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    pst.executeQuery();

            while(rs.next()){

                model.addRow(new Object[]{

                    rs.getString("id_transaksi"),
                    rs.getString("tanggal"),
                    rs.getString("customer"),
                    rs.getString("barang"),
                    rs.getInt("jumlah"),
                    rs.getInt("total")

                });

            }

        } catch(Exception e){

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage());

        }
        
        JTable tabel = new JTable(model);

        JScrollPane scroll =
                new JScrollPane(tabel);
        
        JButton btnRefresh =
        new JButton("Refresh Data");
        
        btnRefresh.setPreferredSize(
        new Dimension(100,30));
        
        btnRefresh.setBackground(
        new Color(144,202,249));

        btnRefresh.setForeground(
                Color.WHITE);
        
        btnRefresh.addActionListener(e -> {

            model.setRowCount(0);

            try {

                Connection conn =
                        Koneksi.getConnection();

                String sql =
                        "SELECT * FROM transaksi";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery();

                while(rs.next()){

                    model.addRow(new Object[]{

                        rs.getString("id_transaksi"),
                        rs.getString("tanggal"),
                        rs.getString("customer"),
                        rs.getString("barang"),
                        rs.getInt("jumlah"),
                        rs.getInt("total")

                    });

                }

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }

        });
        
        JPanel atas = new JPanel(new BorderLayout());

        atas.add(judul, BorderLayout.CENTER);
        atas.add(btnRefresh, BorderLayout.EAST);

        panel.add(atas, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createLaporanInventoryPanel() {

        JPanel panel = new JPanel(new BorderLayout());

        JLabel judul = new JLabel(
                "LAPORAN INVENTORY",
                SwingConstants.CENTER);

        judul.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        String[] kolom = {
            "ID Barang",
            "Nama Barang",
            "Stok"
        };

        javax.swing.table.DefaultTableModel model =
                new javax.swing.table.DefaultTableModel(
                        null,
                        kolom);

        JTable tabel = new JTable(model);

        JScrollPane scroll =
                new JScrollPane(tabel);
        
        JButton btnRefresh =
        new JButton("Refresh");

        btnRefresh.setPreferredSize(
                new Dimension(100,30));

        btnRefresh.setBackground(
                new Color(144,202,249));

        btnRefresh.setForeground(
                Color.WHITE);
        
        btnRefresh.addActionListener(e -> {

            model.setRowCount(0);

            try {

                Connection conn =
                        Koneksi.getConnection();

                String sql =
                        "SELECT * FROM inventory";

                PreparedStatement pst =
                        conn.prepareStatement(sql);

                ResultSet rs =
                        pst.executeQuery();

                while(rs.next()){

                    model.addRow(new Object[]{

                        rs.getString("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("stok_saat_ini")

                    });

                }

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage());

            }

        });
        
        JPanel atas = new JPanel(new BorderLayout());

        atas.add(judul, BorderLayout.CENTER);
        atas.add(btnRefresh, BorderLayout.EAST);

        panel.add(atas, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Penjualan().setVisible(true);
        });

    }
}

