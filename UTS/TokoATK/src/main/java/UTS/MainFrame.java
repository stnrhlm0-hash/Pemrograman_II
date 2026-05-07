
package UTS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class MainFrame extends JFrame {

    TokoATK toko = new TokoATK();

    JTextField txtNama = new JTextField();
    JTextField txtHarga = new JTextField();

    String[] kategori = {"Pulpen", "Buku", "Pensil", "Penghapus"};
    JComboBox<String> cbKategori = new JComboBox<>(kategori);

    JButton btnTambah = new JButton("Tambah");
    JButton btnHapus = new JButton("Hapus");
    JButton btnCari = new JButton("Cari");
    JButton btnSort = new JButton("Sort Harga");

    // Tabel
    String[] kolom = {"Nama Produk", "Harga", "Kategori"};
    DefaultTableModel model = new DefaultTableModel(kolom, 0);
    JTable tabel = new JTable(model);

    public MainFrame() {

        setTitle("Aplikasi Toko Alat Tulis");
        setSize(600, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Label Nama
        JLabel l1 = new JLabel("Nama Produk");
        l1.setBounds(20, 20, 100, 20);
        add(l1);

        // Input Nama
        txtNama.setBounds(130, 20, 180, 25);
        add(txtNama);

        // Label Harga
        JLabel l2 = new JLabel("Harga");
        l2.setBounds(20, 60, 100, 20);
        add(l2);

        // Input Harga
        txtHarga.setBounds(130, 60, 180, 25);
        add(txtHarga);

        // Label Kategori
        JLabel l3 = new JLabel("Kategori");
        l3.setBounds(20, 100, 100, 20);
        add(l3);

        // ComboBox
        cbKategori.setBounds(130, 100, 180, 25);
        add(cbKategori);

        // Tombol
        btnTambah.setBounds(20, 150, 100, 30);
        add(btnTambah);

        btnHapus.setBounds(130, 150, 100, 30);
        add(btnHapus);

        btnCari.setBounds(240, 150, 100, 30);
        add(btnCari);

        btnSort.setBounds(350, 150, 120, 30);
        add(btnSort);

        // ScrollPane Tabel
        JScrollPane sp = new JScrollPane(tabel);
        sp.setBounds(20, 210, 540, 220);
        add(sp);

        // ================= TAMBAH =================
        btnTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    String nama = txtNama.getText();

                    if (nama.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Nama produk tidak boleh kosong!");
                        return;
                    }

                    int harga = Integer.parseInt(txtHarga.getText());

                    String pilihKategori =
                            cbKategori.getSelectedItem().toString();

                    Produk p = new Produk(nama, harga, pilihKategori);

                    toko.tambahProduk(p);

                    tampilKeTabel();

                    txtNama.setText("");
                    txtHarga.setText("");

                    JOptionPane.showMessageDialog(null,
                            "Produk berhasil ditambahkan");

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(null,
                            "Harga harus berupa angka!");

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null,
                            "Terjadi kesalahan!");
                }
            }
        });

        // ================= HAPUS =================
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    Produk p = toko.hapusProduk();

                    tampilKeTabel();

                    JOptionPane.showMessageDialog(null,
                            "Produk " + p.getNama() + " berhasil dihapus");

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null,
                            "Stok kosong!");
                }
            }
        });

        // ================= CARI =================
        btnCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nama = txtNama.getText();

                if (nama.isEmpty()) {

                    JOptionPane.showMessageDialog(null,
                            "Masukkan nama produk!");
                    return;
                }

                String hasil = toko.cariProduk(nama);

                JOptionPane.showMessageDialog(null, hasil);
            }
        });

        // ================= SORT =================
        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                toko.sortingHarga();

                tampilKeTabel();

                JOptionPane.showMessageDialog(null,
                        "Produk berhasil diurutkan");
            }
        });
    }

    // Method tampil ke tabel
    public void tampilKeTabel() {

        model.setRowCount(0);

        for (Produk p : toko.stackProduk) {

            Object[] data = {
                p.getNama(),
                p.getHarga(),
                p.getKategori()
            };

            model.addRow(data);
        }
    }
}
