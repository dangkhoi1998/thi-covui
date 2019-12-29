package com.hung.bthocvien;

public class Hocvien {
    private int mID;
    private String hoten;
    private String email;
    private String shp;

    public Hocvien(){
    }
    public Hocvien(int mID, String hoten, String email, String shp) {
        this.mID = mID;
        this.hoten = hoten;
        this.email = email;
        this.shp = shp;
    }
    public Hocvien(String hoten, String email, String shp) {
        this.hoten = hoten;
        this.email = email;
        this.shp = shp;
    }
    public int getmID() {
        return mID;
    }
    public void setmID(int mID) {
        this.mID = mID;
    }
    public String getHoten() {
        return hoten;
    }
    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getShp() {
        return shp;
    }
    public void setShp(String shp) {
        this.shp = shp;
    }

    public String toString(){
        return "ID:" +getmID()+"\n"+"Họ và tên:"+getHoten()+"\n"+"Email:"+getEmail()+"\n"+"Số học phần:"+getShp()+"\n";
    }
}
