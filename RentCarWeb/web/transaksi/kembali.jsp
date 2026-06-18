<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pengembalian Mobil | RentCar</title>
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
            <a href="../customer/data.jsp" class="nav-item"><i class="fas fa-users"></i><span>Data Customer</span></a>
            <a href="sewa.jsp" class="nav-item"><i class="fas fa-file-signature"></i><span>Transaksi Sewa</span></a>
            <a href="kembali.jsp" class="nav-item active"><i class="fas fa-undo-alt"></i><span>Pengembalian</span></a>
            <a href="../LaporanServlet" class="nav-item"><i class="fas fa-print"></i><span>Cetak Laporan</span></a>
        </nav>
        <div class="sidebar-footer"><p>&copy; 2026 RentCar System</p></div>
    </aside>
    
    <main class="main-content">
        <div class="top-bar">
            <div class="page-title">
                <h1><i class="fas fa-undo-alt"></i> Pengembalian Mobil</h1>
                <p>Isi form untuk mengembalikan mobil yang disewa</p>
            </div>
        </div>
        
        <div class="form-container">
            <form action="../TransaksiServlet" method="post">
                <input type="hidden" name="aksi" value="kembali">
                
                <div class="form-group">
                    <label><i class="fas fa-receipt"></i> ID Transaksi</label>
                    <input type="number" name="transaksi" placeholder="Masukkan ID Transaksi" required>
                </div>
                
                <div class="form-group">
                    <label><i class="fas fa-car"></i> ID Mobil</label>
                    <input type="number" name="mobil" placeholder="Masukkan ID Mobil" required>
                </div>
                
                <div class="button-group">
                    <button type="submit" class="btn-primary"><i class="fas fa-check-double"></i> Kembalikan Mobil</button>
                    <a href="../index.jsp" class="btn-outline"><i class="fas fa-arrow-left"></i> Kembali</a>
                    <a href="../index.jsp" class="btn-secondary"><i class="fas fa-home"></i> Selesai</a>
                </div>
            </form>
        </div>
        
        <footer class="footer"><p>&copy; 2026 RentCar System | All rights reserved</p></footer>
    </main>
</div>

</body>
</html>