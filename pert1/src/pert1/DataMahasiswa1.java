/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pert1;

import java.util.Scanner;


public class DataMahasiswa1 {
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Mahasiswa[] data = new Mahasiswa[0];
        int jml, x, pil;

        do {
            System.out.println("\nPILIH MENU");
            System.out.println("======================");
            System.out.println("1. Input Data");
            System.out.println("2. Tampil Data");
            System.out.println("3. Keluar Program");
            System.out.println("======================");

            System.out.print("Pilih Menu? ");
            pil = input.nextInt();

            switch (pil){
                case 1 -> {
                    System.out.print("Jumlah Data: ");
                    jml = input.nextInt();

                    data = new Mahasiswa[jml];

                    for (x = 0; x < jml; x++){
                        data[x] = new Mahasiswa();

                        System.out.println("\nData ke-" + (x+1));

                        System.out.print("NIM : ");
                        data[x].setNim(input.next());

                        input.nextLine();
                        System.out.print("Nama : ");
                        data[x].setNama(input.nextLine());

                        System.out.print("Nilai UTS : ");
                        data[x].setUts(input.nextFloat());

                        System.out.print("Nilai UAS : ");
                        data[x].setUas(input.nextFloat());
                    }
                }

                case 2 -> {
                    System.out.println("\n=== DATA MAHASISWA ===");

                    for (x = 0; x < data.length; x++){
                        System.out.println("\nData ke-" + (x+1));
                        System.out.println("NIM : " + data[x].getNim());
                        System.out.println("Nama : " + data[x].getNama());
                        System.out.println("UTS : " + data[x].getUts());
                        System.out.println("UAS : " + data[x].getUas());
                        System.out.println("Nilai Akhir : " + data[x].getNilaiAkhir());
                        System.out.println("Grade : " + data[x].getGrade());
                    }
                }

                case 3 -> System.out.println("Program selesai");
            }

        } while (pil != 3);
    }

}
