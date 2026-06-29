package com.unpam.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import com.unpam.model.Koneksi;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@WebServlet(name = "LaporanNilaiController",
urlPatterns = {"/LaporanNilaiController"})

public class LaporanNilaiController
extends HttpServlet {

    protected void processRequest(
    HttpServletRequest request,
    HttpServletResponse response)

    throws ServletException, IOException {

        response.setContentType(
        "text/html;charset=UTF-8");

        try {

            // koneksi database
            Koneksi koneksi =
            new Koneksi();

            Connection connection =
            koneksi.getConnection();

            // lokasi file jrxml
            String jrxmlFile =
            getServletContext().getRealPath(
            "/reports/laporanNilai.jrxml");

            // compile report
            JasperReport jasperReport =
            JasperCompileManager.compileReport(
            jrxmlFile);

            // isi laporan
            JasperPrint jasperPrint =
            JasperFillManager.fillReport(
            jasperReport,
            null,
            connection);

            // folder project web
            String folderWeb =
            getServletContext().getRealPath("/");

            String htmlFile =
            folderWeb + File.separator
            + "laporanNilai.html";

            JasperExportManager.exportReportToHtmlFile(
            jasperPrint,
            htmlFile);

            response.sendRedirect(
            request.getContextPath()
            + "/laporanNilai.html");

        } catch (Exception ex) {

            response.getWriter().println(

            "<h2>Terjadi Kesalahan :</h2>"

            + ex);
        }
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