/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTS;

/**
 *
 * @author asus
 */
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.EmptyStackException;

public class TokoATK {

    Stack<Produk> stackProduk = new Stack<>();

    // Tambah produk
    public void tambahProduk(Produk p) {
        stackProduk.push(p);
        System.out.println("Produk ditambahkan");
    }

    // Hapus produk
    public Produk hapusProduk() {
        if(stackProduk.isEmpty()) {
            throw new EmptyStackException();
        }
        return stackProduk.pop();
    }

    // Tampilkan produk
    public String tampilProduk() {
        String hasil = "";

        for(Produk p : stackProduk) {
            hasil += p.toString() + "\n";
        }

        return hasil;
    }

    // Searching
    public String cariProduk(String nama) {

        for(Produk p : stackProduk) {
            if(p.getNama().equalsIgnoreCase(nama)) {
                return p.toString();
            }
        }

        return "Produk tidak ditemukan";
    }

    // Sorting harga
    public void sortingHarga() {

        Collections.sort(stackProduk, new Comparator<Produk>() {
            @Override
            public int compare(Produk o1, Produk o2) {
                return o1.getHarga() - o2.getHarga();
            }
        });
    }
}
