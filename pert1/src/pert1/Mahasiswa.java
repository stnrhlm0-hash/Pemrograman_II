/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pert1;

public class Mahasiswa {
    public String nim, nama;
    private float uts, uas;

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setUts(float uts) {
        this.uts = uts;
    }

    public float getUts() {
        return uts;
    }

    public void setUas(float uas) {
        this.uas = uas;
    }

    public float getUas() {
        return uas;
    }

    public double getNilaiAkhir(){
        return (uts + uas) / 2;
    }

    public String getGrade(){
        double nilaiakhir = getNilaiAkhir();

        if(nilaiakhir < 50)
            return "E";
        else if(nilaiakhir < 60)
            return "D";
        else if(nilaiakhir < 70)
            return "C";
        else if(nilaiakhir < 80)
            return "B";
        else
            return "A";
    }

}
