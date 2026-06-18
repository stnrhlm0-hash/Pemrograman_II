package dao;


import config.Koneksi;
import model.Customer;


import java.sql.*;
import java.util.ArrayList;



public class CustomerDAO {



public void tambah(Customer c){


try{


Connection conn =
Koneksi.getConnection();



PreparedStatement ps =
conn.prepareStatement(

"INSERT INTO customer"+
"(nama,alamat,no_hp,sim)"
+
" VALUES(?,?,?,?)"

);



ps.setString(1,c.getNama());

ps.setString(2,c.getAlamat());

ps.setString(3,c.getNo_hp());

ps.setString(4,c.getSim());


ps.executeUpdate();



}

catch(Exception e){

System.out.println(e);

}


}





public ArrayList<Customer> tampil(){


ArrayList<Customer> data =
new ArrayList<>();


try{


Connection conn =
Koneksi.getConnection();



Statement st =
conn.createStatement();



ResultSet rs =
st.executeQuery(

"SELECT * FROM customer"

);



while(rs.next()){


Customer c =
new Customer();



c.setId_customer(
rs.getInt("id_customer")
);


c.setNama(
rs.getString("nama")
);


c.setAlamat(
rs.getString("alamat")
);


c.setNo_hp(
rs.getString("no_hp")
);


c.setSim(
rs.getString("sim")
);



data.add(c);


}



}


catch(Exception e){

System.out.println(e);

}



return data;


}


}
