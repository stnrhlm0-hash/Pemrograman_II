package com.unpam.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Nilai {

    private String nim;

    private String kodeMataKuliah;

    private double nilaiTugas;

    private double nilaiUts;

    private double nilaiUas;

    private String pesan;

    private final Koneksi koneksi =
    new Koneksi();

    public String getNim() {

        return nim;
    }

    public void setNim(String nim) {

        this.nim = nim;
    }

    public String getKodeMataKuliah() {

        return kodeMataKuliah;
    }

    public void setKodeMataKuliah(
    String kodeMataKuliah) {

        this.kodeMataKuliah =
        kodeMataKuliah;
    }

    public double getNilaiTugas() {

        return nilaiTugas;
    }

    public void setNilaiTugas(
    double nilaiTugas) {

        this.nilaiTugas =
        nilaiTugas;
    }

    public double getNilaiUts() {

        return nilaiUts;
    }

    public void setNilaiUts(
    double nilaiUts) {

        this.nilaiUts =
        nilaiUts;
    }

    public double getNilaiUas() {

        return nilaiUas;
    }

    public void setNilaiUas(
    double nilaiUas) {

        this.nilaiUas =
        nilaiUas;
    }

    public void simpan() {

        try {

            Koneksi koneksi =
            new Koneksi();

            Connection connection =
            koneksi.getConnection();

            String sql =

            "insert into tbnilai "
            + "(nim, kodeMataKuliah, "
            + "nilaiTugas, nilaiUts, nilaiUas) "
            + "values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement =
            connection.prepareStatement(sql);

            preparedStatement.setString(
            1, nim);

            preparedStatement.setString(
            2, kodeMataKuliah);

            preparedStatement.setDouble(
            3, nilaiTugas);

            preparedStatement.setDouble(
            4, nilaiUts);

            preparedStatement.setDouble(
            5, nilaiUas);

            preparedStatement.executeUpdate();

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
    
        public ResultSet getData() {

        ResultSet resultSet = null;

        Connection connection;

        if ((connection = koneksi.getConnection()) != null) {

            try {

                String SQLStatemen =
                "SELECT * FROM tbnilai ORDER BY nim";

                PreparedStatement preparedStatement =
                connection.prepareStatement(SQLStatemen);

                resultSet =
                preparedStatement.executeQuery();

            } catch (SQLException ex) {

                pesan =
                "Tidak dapat membaca data nilai\n"
                + ex.getMessage();
            }
        }

        return resultSet;
    }
}