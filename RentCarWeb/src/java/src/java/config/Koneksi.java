package config;

import java.sql.Connection;
import java.sql.DriverManager;


public class Koneksi {

private static Connection conn;


public static Connection getConnection(){

try{

Class.forName("com.mysql.cj.jdbc.Driver");


conn =
DriverManager.getConnection(
"jdbc:mysql://localhost:3306/rentcar",
"root",
""
);


}catch(Exception e){

System.out.println(e);

}


return conn;

}

}