<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

    <title>
        Informasi Nilai Mahasiswa
    </title>

    <link rel="stylesheet"
          href="style.css">

</head>

<body>

<div id="wrapper">

    <!-- =========================
         HEADER
    ========================== -->

    <div id="header">

        <h2>
            Informasi Nilai Mahasiswa
        </h2>

        <h1>
            UNIVERSITAS PAMULANG
        </h1>

        <h3>
            Jl. Surya Kencana No. 1
            Pamulang, Tangerang Selatan, Banten
        </h3>

    </div>

    <!-- =========================
         MENU
    ========================== -->

    <div id="menu">

        <ul>

            <li>
                <a href="index.jsp">
                    Home
                </a>
            </li>

            <li>
                <a href="#">
                    Master Data
                </a>
            </li>

            <li>
                <a href="#">
                    Transaksi
                </a>
            </li>

            <li>
                <a href=
                "NilaiController?aksi=laporan"
                   target="_blank">

                    Laporan

                </a>
            </li>

            <li>
                <a href="#">
                    Logout
                </a>
            </li>

        </ul>

    </div>

    <!-- =========================
         SIDEBAR
    ========================== -->

    <div id="sidebar">

        <h3>Master Data</h3>

        <a href="#">
            Mahasiswa
        </a>

        <a href="#">
            Mata Kuliah
        </a>

        <h3>Transaksi</h3>

        <a href="#">
            Nilai
        </a>

        <h3>Laporan</h3>

        <a href=
        "NilaiController?aksi=laporan"
           target="_blank">

            Nilai

        </a>

        <a href="#">
            Logout
        </a>

    </div>

    <!-- =========================
         CONTENT
    ========================== -->

    <div id="content">

        <h2>
            Input Nilai Mahasiswa
        </h2>

        <!-- =========================
             FORM NILAI
        ========================== -->

        <form method="post"
              action="NilaiController">

            <input type="hidden"
                   name="aksi"
                   value="simpan">

            <table>

                <!-- NIM -->

                <tr>

                    <td>NIM</td>

                    <td>

                        <input type="text"
                               name="nim"
                               required>

                        <button type="submit"
                                formaction=
                                "MahasiswaController"
                                formmethod="get"
                                name="aksi"
                                value="cari">

                            Cari

                        </button>

                        <button type="button">

                            Lihat

                        </button>

                    </td>

                </tr>

                <!-- NAMA -->

                <tr>

                    <td>Nama</td>

                    <td>

                        <input type="text"
                               name="nama">

                    </td>

                </tr>

                <!-- SEMESTER -->

                <tr>

                    <td>Semester</td>

                    <td>

                        <input type="text"
                               name="semester">

                    </td>

                </tr>

                <!-- KELAS -->

                <tr>

                    <td>Kelas</td>

                    <td>

                        <input type="text"
                               name="kelas">

                    </td>

                </tr>

                <!-- KODE MK -->

                <tr>

                    <td>
                        Kode Mata Kuliah
                    </td>

                    <td>

                        <input type="text"
                               name="kode_mk"
                               required>

                        <button type="submit"
                                formaction=
                                "MataKuliahController"
                                formmethod="get"
                                name="aksi"
                                value="cari">

                            Cari

                        </button>

                        <button type="button">

                            Lihat

                        </button>

                    </td>

                </tr>

                <!-- NAMA MK -->

                <tr>

                    <td>
                        Nama Mata Kuliah
                    </td>

                    <td>

                        <input type="text"
                               name="nama_mk">

                    </td>

                </tr>

                <!-- SKS -->

                <tr>

                    <td>
                        Jumlah SKS
                    </td>

                    <td>

                        <input type="text"
                               name="sks">

                    </td>

                </tr>

                <!-- NILAI TUGAS -->

                <tr>

                    <td>
                        Nilai Tugas
                    </td>

                    <td>

                        <input type="number"
                               name="nilai_tugas"
                               required>

                    </td>

                </tr>

                <!-- NILAI UTS -->

                <tr>

                    <td>
                        Nilai UTS
                    </td>

                    <td>

                        <input type="number"
                               name="nilai_uts"
                               required>

                    </td>

                </tr>

                <!-- NILAI UAS -->

                <tr>

                    <td>
                        Nilai UAS
                    </td>

                    <td>

                        <input type="number"
                               name="nilai_uas"
                               required>

                    </td>

                </tr>

                <!-- BUTTON -->

                <tr>

                    <td></td>

                    <td>

                        <input type="submit"
                               value="Simpan">

                        <input type="reset"
                               value="Hapus">

                    </td>

                </tr>

            </table>

        </form>

    </div>

    <!-- =========================
         FOOTER
    ========================== -->

    <div id="footer">

        Copyright © 2014
        Universitas Pamulang

        <br>

        Jl. Surya Kencana No.1
        Pamulang, Tangerang Selatan,
        Banten

    </div>

</div>

</body>

</html>