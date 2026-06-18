package dao;


import config.Koneksi;
import model.Mobil;

import java.sql.*;
import java.util.ArrayList;


public class MobilDAO {



public void tambah(Mobil m){


try{


Connection c =
Koneksi.getConnection();



PreparedStatement ps =
c.prepareStatement(

"INSERT INTO mobil "+
"(no_plat,merk,tipe,tahun,harga_sewa,status)"
+
"VALUES(?,?,?,?,?,?)"

);



ps.setString(1,m.getNo_plat());

ps.setString(2,m.getMerk());

ps.setString(3,m.getTipe());

ps.setInt(4,m.getTahun());

ps.setInt(5,m.getHarga_sewa());

ps.setString(6,m.getStatus());


ps.executeUpdate();



}

catch(Exception e){

System.out.println(e);

}


}




public ArrayList<Mobil> tampil(){


ArrayList<Mobil> list =
new ArrayList<>();



try{


Connection c =
Koneksi.getConnection();


Statement s =
c.createStatement();



ResultSet rs =
s.executeQuery(

"SELECT * FROM mobil"

);



while(rs.next()){


Mobil m =
new Mobil();



m.setId_mobil(
rs.getInt("id_mobil")
);


m.setNo_plat(
rs.getString("no_plat")
);



m.setMerk(
rs.getString("merk")
);



m.setTipe(
rs.getString("tipe")
);



m.setTahun(
rs.getInt("tahun")
);



m.setHarga_sewa(
rs.getInt("harga_sewa")
);



m.setStatus(
rs.getString("status")
);



list.add(m);



}



}

catch(Exception e){

System.out.println(e);

}


return list;


}



public void hapus(int id){


try{


Connection c =
Koneksi.getConnection();


PreparedStatement ps =

c.prepareStatement(

"DELETE FROM mobil WHERE id_mobil=?"

);


ps.setInt(1,id);


ps.executeUpdate();


}

catch(Exception e){

System.out.println(e);

}



}


}
