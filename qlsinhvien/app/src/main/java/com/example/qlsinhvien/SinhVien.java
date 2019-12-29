package com.example.qlsinhvien;

public class SinhVien {
    private int mID;
    private String hoten;
    private String diachi;
    private String sdt;
    private String email;
    public SinhVien(){
    }
    public SinhVien(int mID, String hoten, String diachi, String sdt, String
            email) {
        this.mID = mID;
        this.hoten = hoten;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
    }
    public SinhVien(String hoten, String diachi, String sdt, String email) {
        this.hoten = hoten;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
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
    public String getDiachi() {
        return diachi;
    }
    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getEmail() {
        return email;
    }public void setEmail(String email) {
        this.email = email;
    }
    public String toString(){
        return "ID:" +getmID()+"\n"+"Họ tên:"+getHoten()+"\n"+"Địa chỉ:"+getDiachi()+"\n"+"Số điện thoại:"+getSdt()+"\n"+"Email:"+getEmail()+"\n";
    }
}