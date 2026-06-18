<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tambah Customer | RentCar</title>
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
            <a href="../mobil/data.jsp" class="nav-item"><i class="fas fa-car"></i><span>Data Mobil</span></a>
            <a href="data.jsp" class="nav-item active"><i class="fas fa-users"></i><span>Data Customer</span></a>
            <a href="../transaksi/sewa.jsp" class="nav-item"><i class="fas fa-file-signature"></i><span>Transaksi Sewa</span></a>
            <a href="../transaksi/kembali.jsp" class="nav-item"><i class="fas fa-undo-alt"></i><span>Pengembalian</span></a>
            <a href="../LaporanServlet" class="nav-item"><i class="fas fa-print"></i><span>Cetak Laporan</span></a>
        </nav>
        <div class="sidebar-footer"><p>&copy; 2026 RentCar System</p></div>
    </aside>
    
    <main class="main-content">
        <div class="top-bar">
            <div class="page-title">
                <h1><i class="fas fa-user-plus"></i> Tambah Customer</h1>
                <p>Isi form berikut untuk menambahkan customer baru</p>
            </div>
        </div>
        
        <div class="form-container">
            <form action="../CustomerServlet" method="post">
                <div class="form-group">
                    <label><i class="fas fa-user"></i> Nama Lengkap</label>
                    <input type="text" name="nama" placeholder="Masukkan nama lengkap" required>
                </div>
                <div class="form-group">
                    <label><i class="fas fa-map-marker-alt"></i> Alamat</label>
                    <input type="text" name="alamat" placeholder="Masukkan alamat" required>
                </div>
                <div class="form-group">
                    <label><i class="fas fa-phone"></i> Nomor HP</label>
                    <input type="tel" name="hp" placeholder="08xxxxxxxxxx" required>
                </div>
                <div class="form-group">
                    <label><i class="fas fa-id-card"></i> Nomor SIM</label>
                    <input type="text" name="sim" placeholder="Nomor SIM" required>
                </div>
                <div class="button-group">
                    <button type="submit" class="btn-primary"><i class="fas fa-save"></i> Simpan Customer</button>
                    <a href="data.jsp" class="btn-outline"><i class="fas fa-arrow-left"></i> Batal</a>
                </div>
            </form>
        </div>
        
        <footer class="footer"><p>&copy; 2026 RentCar System | All rights reserved</p></footer>
    </main>
</div>

</body>
</html>