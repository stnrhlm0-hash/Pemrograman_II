package src.java.servlet;



import dao.CustomerDAO;
import model.Customer;



import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;



public class CustomerServlet extends HttpServlet {



CustomerDAO dao =
new CustomerDAO();



protected void doPost(
HttpServletRequest request,
HttpServletResponse response)

throws IOException,ServletException{


Customer c =
new Customer();



c.setNama(
request.getParameter("nama")
);



c.setAlamat(
request.getParameter("alamat")
);



c.setNo_hp(
request.getParameter("hp")
);



c.setSim(
request.getParameter("sim")
);



dao.tambah(c);



response.sendRedirect(
"customer/data.jsp"
);



}



}
