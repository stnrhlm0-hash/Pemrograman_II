package model;

public class Transaksi {

    private int id_transaksi;
    private int id_customer;
    private int id_mobil;
    private String tgl_sewa;
    private String tgl_kembali;
    private int lama_sewa;
    private int total;
    private String status;

    // Getter & Setter id_transaksi
    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    // Getter & Setter id_customer
    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    // Getter & Setter id_mobil
    public int getId_mobil() {
        return id_mobil;
    }

    public void setId_mobil(int id_mobil) {
        this.id_mobil = id_mobil;
    }

    // Getter & Setter tgl_sewa
    public String getTgl_sewa() {
        return tgl_sewa;
    }

    public void setTgl_sewa(String tgl_sewa) {
        this.tgl_sewa = tgl_sewa;
    }

    // Getter & Setter tgl_kembali
    public String getTgl_kembali() {
        return tgl_kembali;
    }

    public void setTgl_kembali(String tgl_kembali) {
        this.tgl_kembali = tgl_kembali;
    }

    // Getter & Setter lama_sewa
    public int getLama_sewa() {
        return lama_sewa;
    }

    public void setLama_sewa(int lama_sewa) {
        this.lama_sewa = lama_sewa;
    }

    // Getter & Setter total
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    // Getter & Setter status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}