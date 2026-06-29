package com.unpam.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.unpam.view.PesanDialog;
import java.sql.PreparedStatement;

public class MataKuliah {

    private String kodeMataKuliah, namaMataKuliah;

    private int jumlahSks;

    private String pesan;

    private Object[][] list;

    private final Koneksi koneksi = new Koneksi();

    private final PesanDialog pesanDialog =
    new PesanDialog();

    public String getKodeMataKuliah() {
        return kodeMataKuliah;
    }

    public void setKodeMataKuliah(String kodeMataKuliah) {
        this.kodeMataKuliah = kodeMataKuliah;
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setNamaMataKuliah(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }

    public int getJumlahSks() {
        return jumlahSks;
    }

    public void setJumlahSks(int jumlahSks) {
        this.jumlahSks = jumlahSks;
    }

    public String getPesan() {
        return pesan;
    }

    public Object[][] getList() {
        return list;
    }

    public void setList(Object[][] list) {
        this.list = list;
    }

    public boolean simpan() {

        boolean adaKesalahan = false;

        Connection connection;

        if ((connection = koneksi.getConnection()) != null) {

            int jumlahSimpan = 0;

            boolean simpan = false;

            Statement sta;

            ResultSet rset;

            String SQLStatemen = "";

            try {

                simpan = true;

                SQLStatemen =
                "insert into tbmatakuliah values ('"
                + kodeMataKuliah + "','"
                + namaMataKuliah + "','"
                + jumlahSks + "')";

                sta = connection.createStatement();

                jumlahSimpan =
                sta.executeUpdate(SQLStatemen);

                if (simpan) {

                    if (jumlahSimpan < 1) {

                        adaKesalahan = true;

                        pesan =
                        "Gagal menyimpan data mata kuliah";
                    }
                }

            } catch (SQLException ex) {

                adaKesalahan = true;

                pesan =
                "Tidak dapat membuka tabel tbmatakuliah\n"
                + ex + "\n" + SQLStatemen;
            }

        } else {

            adaKesalahan = true;

            pesan =
            "Tidak dapat melakukan koneksi ke server\n"
            + koneksi.getPesanKesalahan();
        }

        return !adaKesalahan;
    }
    
        public ResultSet getData() {

        ResultSet resultSet = null;

        Connection connection;

        if ((connection = koneksi.getConnection()) != null) {

            try {

                String SQLStatemen =
                "SELECT * FROM tbmatakuliah ORDER BY kodeMataKuliah";

                PreparedStatement preparedStatement =
                connection.prepareStatement(SQLStatemen);

                resultSet =
                preparedStatement.executeQuery();

            } catch (SQLException ex) {

                pesan =
                "Tidak dapat membaca data mata kuliah\n"
                + ex.getMessage();
            }
        }

        return resultSet;
    }
}