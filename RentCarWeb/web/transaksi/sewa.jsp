<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Penyewaan Mobil | RentCar</title>
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
            <a href="sewa.jsp" class="nav-item active"><i class="fas fa-file-signature"></i><span>Transaksi Sewa</span></a>
            <a href="kembali.jsp" class="nav-item"><i class="fas fa-undo-alt"></i><span>Pengembalian</span></a>
            <a href="../LaporanServlet" class="nav-item"><i class="fas fa-print"></i><span>Cetak Laporan</span></a>
        </nav>
        <div class="sidebar-footer"><p>&copy; 2026 RentCar System</p></div>
    </aside>
    
    <main class="main-content">
        <div class="top-bar">
            <div class="page-title">
                <h1><i class="fas fa-file-signature"></i> Transaksi Penyewaan</h1>
                <p>Isi form untuk menyewa mobil</p>
            </div>
        </div>
        
        <div class="form-container">
            <div class="alert alert-info" style="margin-bottom: 24px;">
                <i class="fas fa-info-circle"></i> Pastikan ID Customer dan ID Mobil yang dimasukkan sudah terdaftar
            </div>
            
            <form action="../TransaksiServlet" method="post">
                <input type="hidden" name="aksi" value="sewa">
                
                <div class="form-group">
                    <label><i class="fas fa-user"></i> ID Customer</label>
                    <input type="number" name="customer" placeholder="Masukkan ID Customer" required>
                </div>
                
                <div class="form-group">
                    <label><i class="fas fa-car"></i> ID Mobil</label>
                    <input type="number" name="mobil" placeholder="Masukkan ID Mobil" required>
                </div>
                
                <div class="form-group">
                    <label><i class="fas fa-calendar-alt"></i> Tanggal Sewa</label>
                    <input type="date" name="tanggal" required>
                </div>
                
                <div class="form-group">
                    <label><i class="fas fa-calendar-check"></i> Tanggal Kembali </label>
                    <input type="date" name="tgl_kembali">
                </div>
                
                <div class="form-group">
                    <label><i class="fas fa-clock"></i> Lama Sewa (Hari)</label>
                    <input type="number" name="lama" min="1" placeholder="Berapa hari?" required>
                </div>
                
                <div class="form-group">
                    <label><i class="fas fa-money-bill-wave"></i> Total Biaya (Rp)</label>
                    <input type="number" name="total" min="0" placeholder="Total pembayaran" required>
                </div>
                
                <div class="button-group">
                    <button type="submit" class="btn-primary"><i class="fas fa-check-circle"></i> Sewa Mobil</button>
                    <a href="../index.jsp" class="btn-outline"><i class="fas fa-home"></i> Kembali</a>
                </div>
            </form>
        </div>
        
        <footer class="footer"><p>&copy; 2026 RentCar System | All rights reserved</p></footer>
    </main>
</div>

</body>
</html>