package src.java.servlet;


import dao.MobilDAO;
import model.Mobil;


import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;



public class MobilServlet extends HttpServlet {



    MobilDAO dao = new MobilDAO();



    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)

            throws IOException, ServletException {



        try {



            Mobil m = new Mobil();



            m.setNo_plat(
                    request.getParameter("plat")
            );



            m.setMerk(
                    request.getParameter("merk")
            );



            m.setTipe(
                    request.getParameter("tipe")
            );



            m.setTahun(
                    Integer.parseInt(
                            request.getParameter("tahun")
                    )
            );



            m.setHarga_sewa(
                    Integer.parseInt(
                            request.getParameter("harga")
                    )
            );



            // status awal mobil
            m.setStatus("Tersedia");



            // simpan database
            dao.tambah(m);



            // kirim pesan ke JSP
            HttpSession session =
                    request.getSession();



            session.setAttribute(

                    "pesan",

                    "Data mobil berhasil disimpan ke database!"

            );



            // kembali ke form tambah
            response.sendRedirect(

                    "mobil/tambah.jsp"

            );



        }

        catch(Exception e){


            e.printStackTrace();



            HttpSession session =
                    request.getSession();



            session.setAttribute(

                    "pesan",

                    "Data mobil gagal disimpan!"

            );



            response.sendRedirect(

                    "mobil/tambah.jsp"

            );

        }



    }


}