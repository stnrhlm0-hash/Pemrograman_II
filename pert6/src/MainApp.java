import javax.swing.*;

public class MainApp extends JFrame {

    public MainApp() {
        setTitle("Menu Utama");
        setSize(300,200);
        setLayout(new java.awt.FlowLayout());

        JButton cariBtn = new JButton("Cari Data");
        JButton updateBtn = new JButton("Update Data");
        JButton hapusBtn = new JButton("Hapus Data");

        add(cariBtn);
        add(updateBtn);
        add(hapusBtn);

        cariBtn.addActionListener(e -> new CariData());
        updateBtn.addActionListener(e -> new UpdateData());
        hapusBtn.addActionListener(e -> new HapusData());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainApp();
    }
}