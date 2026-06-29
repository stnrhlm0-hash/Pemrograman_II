package com.unpam.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.unpam.view.MainForm;
import com.unpam.model.Mahasiswa;
import java.sql.ResultSet;

@WebServlet(name = "MahasiswaController", urlPatterns = {"/MahasiswaController"})

public class MahasiswaController extends HttpServlet {

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

        if (nim != null) {

            Mahasiswa mahasiswa =
            new Mahasiswa();

            mahasiswa.setNim(
            request.getParameter("nim"));

            mahasiswa.setNama(
            request.getParameter("nama"));

            mahasiswa.setSemester(
            Integer.parseInt(
            request.getParameter("semester")));

            mahasiswa.setKelas(
            request.getParameter("kelas"));

            mahasiswa.simpan();
        }

        MainForm mainForm =
        new MainForm();

        String konten =

        "<h2>Input Mahasiswa</h2>"

        + "<form method='post'>"

        + "<table>"

        + "<tr>"
        + "<td>NIM</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' name='nim'>"
        + "</td>"
        + "<td>"
        + "<input type='submit' value='Cari'>"
        + "</td>"
        + "<td>"
        + "<input type='submit' value='Lihat'>"
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
        + "name='semester' size='5'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td>Kelas</td>"
        + "<td>:</td>"
        + "<td>"
        + "<input type='text' "
        + "name='kelas' size='5'>"
        + "</td>"
        + "</tr>"

        + "<tr>"
        + "<td colspan='5' align='center'>"
        + "<br>"
        + "<input type='submit' "
        + "value='Simpan'>"

        + "<input type='reset' "
        + "value='Hapus'>"

        + "</td>"
        + "</tr>"

        + "</table>"

        + "</form>";
        
        Mahasiswa mahasiswa =
    new Mahasiswa();

    ResultSet resultSet =
    mahasiswa.getData();

    String tabel =

    "<br><h2>Daftar Mahasiswa</h2>"

    + "<table border='1' cellpadding='5'>"

    + "<tr>"

    + "<th>NIM</th>"

    + "<th>Nama</th>"

    + "<th>Semester</th>"

    + "<th>Kelas</th>"

    + "</tr>";

    try {

        while (resultSet.next()) {

            tabel +=

            "<tr>"

            + "<td>"
            + resultSet.getString("nim")
            + "</td>"

            + "<td>"
            + resultSet.getString("nama")
            + "</td>"

            + "<td>"
            + resultSet.getInt("semester")
            + "</td>"

            + "<td>"
            + resultSet.getString("kelas")
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