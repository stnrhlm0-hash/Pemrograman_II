package src.java.servlet;

import dao.TransaksiDAO;
import model.Transaksi;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class TransaksiServlet extends HttpServlet {

    TransaksiDAO dao = new TransaksiDAO();

    @Override
    protected void doPost(HttpServletRequest req,
            HttpServletResponse res)
            throws IOException, ServletException {

        String aksi = req.getParameter("aksi");

        // =======================
        // PROSES SEWA MOBIL
        // =======================
        if ("sewa".equals(aksi)) {

            Transaksi t = new Transaksi();

            t.setId_customer(
                    Integer.parseInt(
                            req.getParameter("customer")
                    )
            );

            t.setId_mobil(
                    Integer.parseInt(
                            req.getParameter("mobil")
                    )
            );

            t.setTgl_sewa(
                    req.getParameter("tanggal")
            );

            // lama -> lama_sewa
            t.setLama_sewa(
                    Integer.parseInt(
                            req.getParameter("lama")
                    )
            );

            // jika form memiliki input tanggal kembali
            String tglKembali = req.getParameter("tgl_kembali");

            if (tglKembali != null && !tglKembali.isEmpty()) {
                t.setTgl_kembali(tglKembali);
            }

            t.setTotal(
                    Integer.parseInt(
                            req.getParameter("total")
                    )
            );

            t.setStatus("Disewa");

            dao.sewa(t);

            res.sendRedirect(
                    "transaksi/sewa.jsp"
            );
        }

        // =======================
        // PROSES PENGEMBALIAN
        // =======================
        else if ("kembali".equals(aksi)) {

            int idTransaksi =
                    Integer.parseInt(
                            req.getParameter("transaksi")
                    );

            int idMobil =
                    Integer.parseInt(
                            req.getParameter("mobil")
                    );

            dao.kembali(
                    idTransaksi,
                    idMobil
            );

            res.sendRedirect(
                    "transaksi/kembali.jsp"
            );
        }
    }
}