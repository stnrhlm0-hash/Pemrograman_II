package src.java.servlet;

import config.Koneksi;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class LaporanServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        try {

            String file = getServletContext()
                    .getRealPath("/laporan/LaporanTransaksi.jasper");

            Connection conn = Koneksi.getConnection();

            if (conn == null) {
                response.setContentType("text/plain");
                response.getWriter().println("Koneksi database gagal");
                return;
            }

            JasperPrint jp = JasperFillManager.fillReport(
                    file,
                    null,
                    conn
            );

            response.setContentType("application/pdf");

            JasperExportManager.exportReportToPdfStream(
                    jp,
                    response.getOutputStream()
            );

        } catch (Exception e) {

            response.setContentType("text/plain");
            e.printStackTrace(response.getWriter());

        }
    }
}