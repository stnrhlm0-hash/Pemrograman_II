package com.unpam.view;

/**
 *
 * @author Anisa
 */

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "MainForm", urlPatterns = {"/MainForm"})

public class MainForm extends HttpServlet {

    public void tampilkan(
    HttpServletRequest request,
    HttpServletResponse response,
    String konten)

    throws ServletException, IOException {

        response.setContentType(
        "text/html;charset=UTF-8");

        HttpSession session =
        request.getSession(true);

        String menu =

        "<br><b>Master Data</b><br>"

        + "<a href=MahasiswaController>"
        + "Mahasiswa</a><br>"

        + "<a href=MataKuliahController>"
        + "Mata Kuliah</a><br><br>"

        + "<b>Transaksi</b><br>"

        + "<a href=NilaiController>"
        + "Nilai</a><br><br>"

        + "<b>Laporan</b><br>"

        + "<a href=LaporanNilaiController>"
        + "Nilai</a><br><br>"

        + "<a href=LogoutController>"
        + "Logout</a><br><br>";

        String topMenu =

        "<nav>"

        + "<ul class='topmenu'>"

        + "<li><a href=.>"
        + "Home</a></li>"

        + "<li><a href=#>"
        + "Master Data</a>"

        + "<ul>"

        + "<li><a href=MahasiswaController>"
        + "Mahasiswa</a></li>"

        + "<li><a href=MataKuliahController>"
        + "Mata Kuliah</a></li>"

        + "</ul>"

        + "</li>"

        + "<li><a href=NilaiController>"
        + "Transaksi</a></li>"

        + "<li><a href=#>"
        + "Laporan</a>"

        + "<ul>"

        + "<li><a href=LaporanNilaiController>"
        + "Nilai</a></li>"

        + "</ul>"

        + "</li>"

        + "<li><a href=LogoutController>"
        + "Logout</a></li>"

        + "</ul>"

        + "</nav>";

        String userName = "";

        if (!session.isNew()) {

            try {

                userName =
                session.getAttribute(
                "userName").toString();

            } catch (Exception ex) {}

            if (!((userName == null)
            || userName.equals(""))) {

                if (konten.equals("")) {

                    konten =

                    "<br><h1>"
                    + "Selamat Datang"
                    + "</h1><h2>"

                    + userName

                    + "</h2>";
                }

                try {

                    menu =
                    session.getAttribute(
                    "menu").toString();

                } catch (Exception ex) {}

                try {

                    topMenu =
                    session.getAttribute(
                    "topMenu").toString();

                } catch (Exception ex) {}
            }
        }

        try (PrintWriter out =
        response.getWriter()) {

            out.println("<!DOCTYPE html>");

            out.println("<html>");

            out.println("<head>");

            out.println(
            "<link href='style.css' "
            + "rel='stylesheet' "
            + "type='text/css' />");

            out.println(
            "<title>"
            + "Informasi Nilai Mahasiswa"
            + "</title>");

            out.println("</head>");

            out.println(
            "<body bgcolor=\"#7f7f7f\">");

            out.println("<center>");

            out.println(
            "<table width=\"80%\" "
            + "bgcolor=\"#f0f0f0\">");

            out.println("<tr>");

            out.println(
            "<td colspan=\"2\" "
            + "align=\"center\">");

            out.println("<br>");

            out.println(
            "<h2 style="
            + "\"margin-bottom:0px;"
            + "margin-top:0px;\">");

            out.println(
            "Informasi Nilai Mahasiswa");

            out.println("</h2>");

            out.println(
            "<h1 style="
            + "\"margin-bottom:0px;"
            + "margin-top:0px;\">");

            out.println(
            "UNIVERSITAS PAMULANG");

            out.println("</h1>");

            out.println(
            "<h4 style="
            + "\"margin-bottom:0px;"
            + "margin-top:0px;\">");

            out.println(
            "Jl. Surya Kencana "
            + "No. 1 Pamulang, "
            + "Tangerang Selatan, "
            + "Banten");

            out.println("</h4>");

            out.println("<br>");

            out.println("</td>");

            out.println("</tr>");

            out.println(
            "<tr height=\"180\">");

            out.println(
            "<td width=\"260\" "
            + "align=\"center\" "
            + "valign=\"top\" "
            + "bgcolor=\"#d9e6d5\">");

            out.println("<div id='menu'>");

            out.println(menu);

            out.println("</div>");

            out.println("</td>");

            out.println(
            "<td align=\"center\" "
            + "valign=\"top\" "
            + "bgcolor=\"#ffffff\">");

            out.println(topMenu);

            out.println("<br>");

            out.println(konten);

            out.println("</td>");

            out.println("</tr>");

            out.println("<tr>");

            out.println(
            "<td colspan=\"2\" "
            + "align=\"center\" "
            + "bgcolor=\"#f0f0f0\">");

            out.println("<small>");

            out.println(
            "Copyright &copy; "
            + "2026 Universitas "
            + "Pamulang<br>");

            out.println(
            "Jl. Surya Kencana "
            + "No. 1 Pamulang, "
            + "Tangerang Selatan, "
            + "Banten<br>");

            out.println("</small>");

            out.println("</td>");

            out.println("</tr>");

            out.println("</table>");

            out.println("</center>");

            out.println("</body>");

            out.println("</html>");
        }
    }
}