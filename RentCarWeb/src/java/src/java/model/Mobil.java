package model;


public class Mobil {


private int id_mobil;
private String no_plat;
private String merk;
private String tipe;
private int tahun;
private int harga_sewa;
private String status;



public int getId_mobil(){
return id_mobil;
}


public void setId_mobil(int id){
this.id_mobil=id;
}



public String getNo_plat(){
return no_plat;
}


public void setNo_plat(String n){
no_plat=n;
}



public String getMerk(){
return merk;
}


public void setMerk(String m){
merk=m;
}


public String getTipe(){
return tipe;
}


public void setTipe(String t){
tipe=t;
}



public int getTahun(){
return tahun;
}


public void setTahun(int t){
tahun=t;
}



public int getHarga_sewa(){
return harga_sewa;
}


public void setHarga_sewa(int h){
harga_sewa=h;
}



public String getStatus(){
return status;
}


public void setStatus(String s){
status=s;
}



}
