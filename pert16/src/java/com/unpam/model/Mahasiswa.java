package com.unpam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.unpam.view.PesanDialog;

public class Mahasiswa {

    private String nim, nama, kelas;
    private int semester;

    private String pesan;

    private Object[][] list;

    private final Koneksi koneksi = new Koneksi();

    private final PesanDialog pesanDialog =
    new PesanDialog();

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
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

            String SQLStatemen = "";

            PreparedStatement preparedStatement;

            try {

                SQLStatemen =
                "INSERT INTO tbmahasiswa(nim,nama,semester,kelas) VALUES (?,?,?,?)";

                preparedStatement =
                connection.prepareStatement(SQLStatemen);

                preparedStatement.setString(1, nim);
                preparedStatement.setString(2, nama);
                preparedStatement.setInt(3, semester);
                preparedStatement.setString(4, kelas);

                jumlahSimpan =
                preparedStatement.executeUpdate();

                if (jumlahSimpan < 1) {

                    adaKesalahan = true;

                    pesan =
                    "Gagal menyimpan data mahasiswa";
                }

                preparedStatement.close();

                connection.close();

            } catch (SQLException ex) {

                adaKesalahan = true;

                pesan =
                "Tidak dapat membuka tabel tbmahasiswa\n"
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
                "SELECT * FROM tbmahasiswa ORDER BY nim";

                PreparedStatement preparedStatement =
                connection.prepareStatement(SQLStatemen);

                resultSet =
                preparedStatement.executeQuery();

            } catch (SQLException ex) {

                pesan =
                "Tidak dapat membaca data mahasiswa\n"
                + ex.getMessage();
            }
        }

        return resultSet;
    }
}