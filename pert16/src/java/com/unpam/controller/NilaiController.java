package com.unpam.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.unpam.view.MainForm;
import com.unpam.model.Nilai;
import java.sql.ResultSet;

@WebServlet(name = "NilaiController", urlPatterns = {"/NilaiController"})

public class NilaiController extends HttpServlet {

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

        String nim =
        request.getParameter("nim");

        if ((nim != null)
        && (!nim.equals(""))) {

            Nilai nilai =
            new Nilai();

            nilai.setNim(
            request.getParameter("nim"));

            nilai.setKodeMataKuliah(
            request.getParameter(
            "kodeMataKuliah"));

            nilai.setNilaiTugas(
            Double.parseDouble(
            request.getParameter(
            "nilaiTugas")));

            nilai.setNilaiUts(
            Double.parseDouble(
            request.getParameter(
            "nilaiUts")));

            nilai.setNilaiUas(
            Double.parseDouble(
            request.getParameter(
            "nilaiUas")));

            nilai.simpan();
        }

        MainForm mainForm =
        new MainForm();

        String konten =

        "<center>"

        + "<h2>Input Nilai Mahasiswa</h2>"

        + "<form method='post'>"

        + "<table>"

        + "<tr>"
        + "<td>NIM</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='nim' size='20'>"
        + "</td>"

        + "<td>"
        + "<input type='submit' "
        + "value='Cari'>"
        + "</td>"

        + "<td>"
        + "<input type='submit' "
        + "value='Lihat'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Nama</td>"
        + "<td>:</td>"
        + "<td colspan='3'>"
        + "<input type='text' "
        + "name='nama' size='40'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Semester</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='semester' "
        + "size='5'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Kelas</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='kelas' "
        + "size='5'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Kode Mata Kuliah</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='kodeMataKuliah' "
        + "size='20'>"
        + "</td>"

        + "<td>"
        + "<input type='submit' "
        + "value='Cari'>"
        + "</td>"

        + "<td>"
        + "<input type='submit' "
        + "value='Lihat'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Nama Mata Kuliah</td>"
        + "<td>:</td>"
        + "<td colspan='3'>"
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
        + "name='jumlahSks' "
        + "size='5'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Nilai Tugas</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='nilaiTugas' "
        + "size='5'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Nilai UTS</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='nilaiUts' "
        + "size='5'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Nilai UAS</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='nilaiUas' "
        + "size='5'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td colspan='5' "
        + "align='center'>"

        + "<br>"

        + "<input type='submit' "
        + "value='Simpan'>"

        + "&nbsp;"

        + "<input type='reset' "
        + "value='Hapus'>"

        + "&nbsp;"

        + "<input type='button' "
        + "value='Cetak PDF' "
        + "onclick=\"window.location='LaporanNilaiController'\">"

        + "</td>"
        + "</tr>"

        + "</table>"

        + "</form>"

        + "</center>";
        
        Nilai nilai =
        new Nilai();

        ResultSet resultSet =
        nilai.getData();

        String tabel =

        "<br><h2>Daftar Nilai Mahasiswa</h2>"

        + "<table border='1' cellpadding='5'>"

        + "<tr>"

        + "<th>NIM</th>"

        + "<th>Kode MK</th>"

        + "<th>Tugas</th>"

        + "<th>UTS</th>"

        + "<th>UAS</th>"

        + "</tr>";

        try {

            while (resultSet.next()) {

                tabel +=

                "<tr>"

                + "<td>"
                + resultSet.getString("nim")
                + "</td>"

                + "<td>"
                + resultSet.getString("kodeMataKuliah")
                + "</td>"

                + "<td>"
                + resultSet.getDouble("nilaiTugas")
                + "</td>"

                + "<td>"
                + resultSet.getDouble("nilaiUts")
                + "</td>"

                + "<td>"
                + resultSet.getDouble("nilaiUas")
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