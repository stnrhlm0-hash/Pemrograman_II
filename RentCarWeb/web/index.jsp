<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="dao.MobilDAO"%>
<%@page import="model.Mobil"%>
<%@page import="dao.CustomerDAO"%>
<%@page import="model.Customer"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard | RentCar System</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>

<div class="dashboard">
    <!-- Sidebar -->
    <aside class="sidebar">
        <div class="sidebar-header">
            <div class="logo-icon">
                <i class="fas fa-car-side"></i>
            </div>
            <h2>RentCar</h2>
            <p>RENTAL SYSTEM</p>
        </div>
        
        <nav class="sidebar-nav">
            <a href="index.jsp" class="nav-item active">
                <i class="fas fa-tachometer-alt"></i>
                <span>Dashboard</span>
            </a>
            <a href="mobil/data.jsp" class="nav-item">
                <i class="fas fa-car"></i>
                <span>Data Mobil</span>
            </a>
            <a href="customer/data.jsp" class="nav-item">
                <i class="fas fa-users"></i>
                <span>Data Customer</span>
            </a>
            <a href="transaksi/sewa.jsp" class="nav-item">
                <i class="fas fa-file-signature"></i>
                <span>Transaksi Sewa</span>
            </a>
            <a href="transaksi/kembali.jsp" class="nav-item">
                <i class="fas fa-undo-alt"></i>
                <span>Pengembalian</span>
            </a>
            <a href="LaporanServlet" class="nav-item">
                <i class="fas fa-print"></i>
                <span>Cetak Laporan</span>
            </a>
        </nav>
        
        <div class="sidebar-footer">
            <p>&copy; 2026 RentCar System</p>
        </div>
    </aside>
    
    <!-- Main Content -->
    <main class="main-content">
        <!-- Top Bar -->
        <div class="top-bar">
            <div class="page-title">
                <h1>Dashboard</h1>
                <p>Selamat datang di sistem rental mobil</p>
            </div>
            <div class="header-actions">
                <button class="header-btn" onclick="window.location.href='transaksi/sewa.jsp'">
                    <i class="fas fa-plus"></i> Sewa Baru
                </button>
            </div>
        </div>
        
        <%
            // Fetch data for stats
            MobilDAO mobilDAO = new MobilDAO();
            CustomerDAO customerDAO = new CustomerDAO();
            ArrayList<Mobil> mobilList = mobilDAO.tampil();
            ArrayList<Customer> customerList = customerDAO.tampil();
            
            int totalMobil = mobilList.size();
            int totalCustomer = customerList.size();
            int mobilTersedia = 0;
            int mobilDisewa = 0;
            
            for(Mobil m : mobilList) {
                if("Tersedia".equalsIgnoreCase(m.getStatus())) {
                    mobilTersedia++;
                } else {
                    mobilDisewa++;
                }
            }
        %>
        
        <!-- Stats Cards -->
        <div class="stats-grid">
            <div class="stat-card">
                <div class="stat-info">
                    <h3><i class="fas fa-car"></i> Total Mobil</h3>
                    <div class="stat-number"><%= totalMobil %></div>
                </div>
                <div class="stat-icon">
                    <i class="fas fa-car"></i>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-info">
                    <h3><i class="fas fa-check-circle"></i> Tersedia</h3>
                    <div class="stat-number"><%= mobilTersedia %></div>
                </div>
                <div class="stat-icon" style="border-left-color: var(--soft-mint);">
                    <i class="fas fa-check-circle" style="color: var(--soft-mint);"></i>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-info">
                    <h3><i class="fas fa-clock"></i> Disewa</h3>
                    <div class="stat-number"><%= mobilDisewa %></div>
                </div>
                <div class="stat-icon">
                    <i class="fas fa-clock" style="color: var(--teal);"></i>
                </div>
            </div>
            
            <div class="stat-card">
                <div class="stat-info">
                    <h3><i class="fas fa-users"></i> Customer</h3>
                    <div class="stat-number"><%= totalCustomer %></div>
                </div>
                <div class="stat-icon">
                    <i class="fas fa-users"></i>
                </div>
            </div>
        </div>
        
        <!-- Recent Data Section -->
        <div class="card">
            <div class="card-header">
                <h2><i class="fas fa-car"></i> Daftar Mobil Terbaru</h2>
            </div>
            <div class="card-body">
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Plat Nomor</th>
                                <th>Merk</th>
                                <th>Tipe</th>
                                <th>Harga/Hari</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int limit = Math.min(5, mobilList.size());
                                for(int i = 0; i < limit; i++) {
                                    Mobil m = mobilList.get(i);
                                    String statusClass = "";
                                    if("Tersedia".equalsIgnoreCase(m.getStatus())) {
                                        statusClass = "status-tersedia";
                                    } else {
                                        statusClass = "status-disewa";
                                    }
                            %>
                            <tr>
                                <td><%= m.getId_mobil() %></td>
                                <td><strong><%= m.getNo_plat() %></strong></td>
                                <td><%= m.getMerk() %></td>
                                <td><%= m.getTipe() %></td>
                                <td>Rp <%= String.format("%,d", m.getHarga_sewa()) %></td>
                                <td><span class="status-badge <%= statusClass %>"><%= m.getStatus() %></span></td>
                            </tr>
                            <%
                                }
                                if(mobilList.isEmpty()) {
                            %>
                            <tr>
                                <td colspan="6" style="text-align: center; padding: 40px;">
                                    <i class="fas fa-inbox"></i> Belum ada data mobil
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
                <div class="button-group">
                    <a href="mobil/data.jsp" class="btn-outline">
                        <i class="fas fa-arrow-right"></i> Lihat Semua Mobil
                    </a>
                </div>
            </div>
        </div>
        
        <!-- Footer -->
        <footer class="footer">
            <p>&copy; 2026 RentCar System | All rights reserved</p>
        </footer>
    </main>
</div>

</body>
</html>