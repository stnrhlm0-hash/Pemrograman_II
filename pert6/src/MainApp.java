import javax.swing.*;

public class MainApp {
public static void main(String[] args) {
String[] pilihan = {"Cari Data", "Update Data", "Hapus Data"};

    int pilih = JOptionPane.showOptionDialog(null,
            "Pilih Menu",
            "Menu Utama",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            pilihan,
            pilihan[0]);

    switch (pilih) {
        case 0 -> new CariData();
        case 1 -> new UpdateData();
        case 2 -> new HapusData();
    }
}

}
