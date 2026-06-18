<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="dao.MobilDAO"%>
<%@page import="model.Mobil"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Data Mobil | RentCar</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<div class="dashboard">
    <aside class="sidebar">
        <div class="sidebar-header">
            <div class="logo-icon"><i class="fas fa-car-side"></i></div>
            <h2>RentCar</h2><p>RENTAL SYSTEM</p>
        </div>
        <nav class="sidebar-nav">
            <a href="../index.jsp" class="nav-item"><i class="fas fa-tachometer-alt"></i><span>Dashboard</span></a>
            <a href="data.jsp" class="nav-item active"><i class="fas fa-car"></i><span>Data Mobil</span></a>
            <a href="../customer/data.jsp" class="nav-item"><i class="fas fa-users"></i><span>Data Customer</span></a>
            <a href="../transaksi/sewa.jsp" class="nav-item"><i class="fas fa-file-signature"></i><span>Transaksi Sewa</span></a>
            <a href="../transaksi/kembali.jsp" class="nav-item"><i class="fas fa-undo-alt"></i><span>Pengembalian</span></a>
            <a href="../LaporanServlet" class="nav-item"><i class="fas fa-print"></i><span>Cetak Laporan</span></a>
        </nav>
        <div class="sidebar-footer"><p>&copy; 2026 RentCar System</p></div>
    </aside>
    
    <main class="main-content">
        <div class="top-bar">
            <div class="page-title">
                <h1><i class="fas fa-car"></i> Data Mobil</h1>
                <p>Kelola data mobil rental</p>
            </div>
        </div>
        
        <%
            MobilDAO dao = new MobilDAO();
            ArrayList<Mobil> data = dao.tampil();
            int tersedia = 0, disewa = 0;
            for(Mobil m : data) {
                if("Tersedia".equalsIgnoreCase(m.getStatus())) tersedia++;
                else disewa++;
            }
        %>
        
        <div class="stats-grid">
            <div class="stat-card"><div class="stat-info"><h3>Total Mobil</h3><div class="stat-number"><%= data.size() %></div></div><div class="stat-icon"><i class="fas fa-car"></i></div></div>
            <div class="stat-card"><div class="stat-info"><h3>Tersedia</h3><div class="stat-number"><%= tersedia %></div></div><div class="stat-icon"><i class="fas fa-check-circle" style="color: var(--soft-mint);"></i></div></div>
            <div class="stat-card"><div class="stat-info"><h3>Sedang Disewa</h3><div class="stat-number"><%= disewa %></div></div><div class="stat-icon"><i class="fas fa-clock" style="color: var(--teal);"></i></div></div>
        </div>
        
        <div class="card">
            <div class="card-header"><h2><i class="fas fa-list"></i> Daftar Mobil</h2></div>
            <div class="card-body">
                <div class="table-container">
                    <table>
                        <thead><tr><th>ID Mobil</th><th>Plat Nomor</th><th>Merk</th><th>Tipe</th><th>Harga Sewa</th><th>Status</th></tr></thead>
                        <tbody>
                            <% for(Mobil m : data){ String statusClass = "Tersedia".equalsIgnoreCase(m.getStatus()) ? "status-tersedia" : "status-disewa"; %>
                            <tr>
                                <td><%=m.getId_mobil()%></td>
                                <td><strong><%=m.getNo_plat()%></strong></td>
                                <td><%=m.getMerk()%></td>
                                <td><%=m.getTipe()%></td>
                                <td style="color: var(--teal); font-weight:600;">Rp <%= String.format("%,d", m.getHarga_sewa()) %></td>
                                <td><span class="status-badge <%= statusClass %>"><%=m.getStatus()%></span></td>
                            </tr>
                            <% } if(data.isEmpty()){ %>
                            <tr><td colspan="6" style="text-align:center; padding:40px;"><i class="fas fa-inbox"></i> Belum ada data mobil</td></tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="button-group">
                    <a href="tambah.jsp" class="btn-primary"><i class="fas fa-plus"></i> Tambah Mobil</a>
                    <a href="../index.jsp" class="btn-outline"><i class="fas fa-arrow-left"></i> Kembali</a>
                </div>
            </div>
        </div>
        
        <footer class="footer"><p>&copy; 2026 RentCar System | All rights reserved</p></footer>
    </main>
</div>

</body>
</html>