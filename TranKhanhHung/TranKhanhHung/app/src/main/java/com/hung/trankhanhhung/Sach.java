package com.hung.trankhanhhung;

public class Sach {
    private int ma;
    private String ten;
    private String loai;
    private String nxb;
    private String nam;
    public Sach(){
    }
    public Sach(int ma, String ten, String loai, String nxb, String
            nam) {
        this.ma = ma;
        this.ten = ten;
        this.loai = loai;
        this.nxb = nxb;
        this.nam = nam;
    }
    public Sach(String ten, String loai, String nxb, String nam) {
        this.ten = ten;
        this.loai = loai;
        this.nxb = nxb;
        this.nam = nam;
    }
    public int getMa() {
        return ma;
    }
    public void setMa(int ma) {
        this.ma = ma;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getLoai() {
        return loai;
    }
    public void setLoai(String loai) {
        this.loai = loai;
    }
    public String getNxb() {
        return nxb;
    }
    public void setNxb(String nxb) {
        this.nxb = nxb;
    }
    public String getNam() {
        return nam;
    }public void setNam(String nam) {
        this.nam = nam;
    }
    public String toString(){
        return "ID:" +getMa()+"\n"+"Tên sách:"+getTen()+"\n"+"Loại sách:"+getLoai()+"\n"+"Nhà xuất bản:"+getNxb()+"\n"+"Năm xuất bản:"+getNam()+"\n";
    }
}
