package dao;

import config.Koneksi;
import model.Transaksi;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransaksiDAO {

    public void sewa(Transaksi t) {

        try {

            Connection c = Koneksi.getConnection();

            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO transaksi "
                    + "(id_customer,id_mobil,tgl_sewa,lama_sewa,total,status) "
                    + "VALUES(?,?,?,?,?,'Sewa')"
            );

            ps.setInt(1, t.getId_customer());
            ps.setInt(2, t.getId_mobil());
            ps.setString(3, t.getTgl_sewa());
            ps.setInt(4, t.getLama_sewa());
            ps.setInt(5, t.getTotal());

            ps.executeUpdate();

            // Update status mobil menjadi Disewa
            PreparedStatement update = c.prepareStatement(
                    "UPDATE mobil "
                    + "SET status='Disewa' "
                    + "WHERE id_mobil=?"
            );

            update.setInt(1, t.getId_mobil());

            update.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public void kembali(int idTransaksi, int idMobil) {

        try {

            Connection c = Koneksi.getConnection();

            PreparedStatement ps = c.prepareStatement(
                    "UPDATE transaksi "
                    + "SET status='Kembali', "
                    + "tgl_kembali=CURDATE() "
                    + "WHERE id_transaksi=?"
            );

            ps.setInt(1, idTransaksi);

            ps.executeUpdate();

            // Update status mobil menjadi Tersedia
            PreparedStatement mobil = c.prepareStatement(
                    "UPDATE mobil "
                    + "SET status='Tersedia' "
                    + "WHERE id_mobil=?"
            );

            mobil.setInt(1, idMobil);

            mobil.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}