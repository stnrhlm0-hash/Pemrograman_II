<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tambah Mobil | RentCar</title>
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
                <h1><i class="fas fa-car-side"></i> Tambah Mobil</h1>
                <p>Isi form berikut untuk menambahkan mobil baru</p>
            </div>
        </div>
        
        <%
            String pesan = (String) session.getAttribute("pesan");
            if(pesan != null){
        %>
            <div class="alert alert-success"><i class="fas fa-check-circle"></i> <%= pesan %></div>
        <%
                session.removeAttribute("pesan");
            }
        %>
        
        <div class="form-container">
            <form action="../MobilServlet" method="post">
                <div class="form-group">
                    <label><i class="fas fa-plate"></i> Nomor Plat</label>
                    <input type="text" name="plat" placeholder="Contoh: B 1234 XYZ" required>
                </div>
                <div class="form-group">
                    <label><i class="fas fa-car"></i> Merk</label>
                    <input type="text" name="merk" placeholder="Contoh: Toyota, Honda" required>
                </div>
                <div class="form-group">
                    <label><i class="fas fa-car-side"></i> Tipe</label>
                    <input type="text" name="tipe" placeholder="Contoh: Avanza, Brio" required>
                </div>
                <div class="form-group">
                    <label><i class="fas fa-calendar-alt"></i> Tahun</label>
                    <input type="number" name="tahun" placeholder="Tahun pembuatan" required>
                </div>
                <div class="form-group">
                    <label><i class="fas fa-tag"></i> Harga Sewa (per hari)</label>
                    <input type="number" name="harga" placeholder="Harga dalam Rupiah" required>
                </div>
                <div class="button-group">
                    <button type="submit" class="btn-primary"><i class="fas fa-save"></i> Simpan Mobil</button>
                    <a href="data.jsp" class="btn-outline"><i class="fas fa-arrow-left"></i> Batal</a>
                </div>
            </form>
        </div>
        
        <footer class="footer"><p>&copy; 2026 RentCar System | All rights reserved</p></footer>
    </main>
</div>

</body>
</html>