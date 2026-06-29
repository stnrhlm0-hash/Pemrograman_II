package com.unpam.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.unpam.view.MainForm;
import com.unpam.model.MataKuliah;
import java.sql.ResultSet;

@WebServlet(name = "MataKuliahController", urlPatterns = {"/MataKuliahController"})

public class MataKuliahController extends HttpServlet {

    protected void processRequest(
    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        HttpSession session =
        request.getSession(false);

        if (session == null) {

            response.sendRedirect(
            "LoginController");

            return;
        }

        String kodeMataKuliah =
        request.getParameter(
        "kodeMataKuliah");

        if (kodeMataKuliah != null) {

            MataKuliah mataKuliah =
            new MataKuliah();

            mataKuliah.setKodeMataKuliah(
            request.getParameter(
            "kodeMataKuliah"));

            mataKuliah.setNamaMataKuliah(
            request.getParameter(
            "namaMataKuliah"));

            mataKuliah.setJumlahSks(
            Integer.parseInt(
            request.getParameter(
            "jumlahSks")));

            mataKuliah.simpan();
        }

        MainForm mainForm =
        new MainForm();

        String konten =

        "<h2>Input Mata Kuliah</h2>"

        + "<form method='post'>"

        + "<table>"

        + "<tr>"
        + "<td>Kode Mata Kuliah</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='kodeMataKuliah'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Nama Mata Kuliah</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='namaMataKuliah' "
        + "size='40'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Jumlah SKS</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='jumlahSks' size='5'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td colspan='3' align='center'>"
        + "<br>"

        + "<input type='submit' "
        + "value='Simpan'>"

        + "<input type='reset' "
        + "value='Hapus'>"

        + "</td>"
        + "</tr>"

        + "</table>"

        + "</form>";

        MataKuliah mataKuliah =
        new MataKuliah();

        ResultSet resultSet =
        mataKuliah.getData();

        String tabel =

        "<br><h2>Daftar Mata Kuliah</h2>"

        + "<table border='1' cellpadding='5'>"

        + "<tr>"

        + "<th>Kode Mata Kuliah</th>"

        + "<th>Nama Mata Kuliah</th>"

        + "<th>Jumlah SKS</th>"

        + "</tr>";

        try {

            while (resultSet.next()) {

                tabel +=

                "<tr>"

                + "<td>"
                + resultSet.getString("kodeMataKuliah")
                + "</td>"

                + "<td>"
                + resultSet.getString("namaMataKuliah")
                + "</td>"

                + "<td>"
                + resultSet.getInt("jumlahSks")
                + "</td>"

                + "</tr>";
            }

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        tabel += "</table>";

        konten += tabel;
        
        mainForm.tampilkan(
        request,
        response,
        konten);
    }

    @Override
    protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        processRequest(
        request,
        response);
    }

    @Override
    protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        processRequest(
        request,
        response);
    }
}