<%@page import="dao.CustomerDAO"%>
<%@page import="model.Customer"%>
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Customer | RentCar</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<div class="dashboard">
    <!-- Sidebar -->
    <aside class="sidebar">
        <div class="sidebar-header">
            <div class="logo-icon"><i class="fas fa-car-side"></i></div>
            <h2>RentCar</h2>
            <p>RENTAL SYSTEM</p>
        </div>
        <nav class="sidebar-nav">
            <a href="../index.jsp" class="nav-item"><i class="fas fa-tachometer-alt"></i><span>Dashboard</span></a>
            <a href="../mobil/data.jsp" class="nav-item"><i class="fas fa-car"></i><span>Data Mobil</span></a>
            <a href="data.jsp" class="nav-item active"><i class="fas fa-users"></i><span>Data Customer</span></a>
            <a href="../transaksi/sewa.jsp" class="nav-item"><i class="fas fa-file-signature"></i><span>Transaksi Sewa</span></a>
            <a href="../transaksi/kembali.jsp" class="nav-item"><i class="fas fa-undo-alt"></i><span>Pengembalian</span></a>
            <a href="../LaporanServlet" class="nav-item"><i class="fas fa-print"></i><span>Cetak Laporan</span></a>
        </nav>
        <div class="sidebar-footer"><p>&copy; 2026 RentCar System</p></div>
    </aside>
    
    <!-- Main Content -->
    <main class="main-content">
        <div class="top-bar">
            <div class="page-title">
                <h1><i class="fas fa-users"></i> Data Customer</h1>
                <p>Kelola data customer rental mobil</p>
            </div>
        </div>
        
        <%
            CustomerDAO dao = new CustomerDAO();
            ArrayList<Customer> data = dao.tampil();
        %>
        
        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-info">
                    <h3>Total Customer</h3>
                    <div class="stat-number"><%= data.size() %></div>
                </div>
                <div class="stat-icon"><i class="fas fa-user-friends"></i></div>
            </div>
        </div>
        
        <div class="card">
            <div class="card-header">
                <h2><i class="fas fa-list"></i> Daftar Customer</h2>
            </div>
            <div class="card-body">
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>ID Customer</th><th>Nama</th><th>Alamat</th><th>No HP</th><th>SIM</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Customer c : data){ %>
                            <tr>
                                <td><%=c.getId_customer()%></td>
                                <td><strong><%=c.getNama()%></strong></td>
                                <td><%=c.getAlamat()%></td>
                                <td><%=c.getNo_hp()%></td>
                                <td><%=c.getSim()%></td>
                            </tr>
                            <% } if(data.isEmpty()){ %>
                            <tr><td colspan="5" style="text-align:center; padding:40px;"><i class="fas fa-inbox"></i> Belum ada data customer</td></tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="button-group">
                    <a href="tambah.jsp" class="btn-primary"><i class="fas fa-plus"></i> Tambah Customer</a>
                    <a href="../index.jsp" class="btn-outline"><i class="fas fa-arrow-left"></i> Kembali</a>
                </div>
            </div>
        </div>
        
        <footer class="footer"><p>&copy; 2026 RentCar System | All rights reserved</p></footer>
    </main>
</div>

</body>
</html>