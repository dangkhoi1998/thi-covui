package com.example.qlsinhvien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
public class Database extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";
    // khai bao tên CSDL
    public static final String DATABASE_NAME = "DS_Sinhvien.db";
    // khai bao tên bảng
    private static final String TABLE_NAME = "sinhvien";
    //khai báo tên các trường
    private static final String ID = "id";
    private static final String NAME = "hoten";
    private static final String EMAIL = "email";
    private static final String SDT = "sdt";
    private static final String ADDRESS = "diachi";
    // khai báo phiên bản
    private static int VERSION = 1;
    private Context context;
    //Tạo bảng với các trường tương ứng
    String sqlQuery = " CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            NAME + " TEXT, " +
            EMAIL + " TEXT, " +
            SDT + " TEXT, " +
            ADDRESS + " TEXT) ";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d(TAG,"Database:");
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);
        Toast.makeText(context,"Tao bang thanh cong",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        Toast.makeText(context,"Drop thanh cong", Toast.LENGTH_SHORT).show();
    }
    //Them moi 1 SV
    public void addS(SinhVien sv){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues gt=new ContentValues();
        gt.put(NAME,sv.getHoten());
        gt.put(SDT, sv.getSdt());
        gt.put(EMAIL, sv.getEmail());
        gt.put(ADDRESS, sv.getDiachi());
        db.insert(TABLE_NAME, null, gt);
        db.close();
    }
    //Đọc thông tin SV
    public SinhVien getSVById(int id){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        Cursor cursor = (Cursor) db.query(TABLE_NAME,new String[]{
                ID,NAME,EMAIL,SDT,ADDRESS},"ID=?",new
                String[]{String.valueOf(id)},null,null,null,null);
        if (cursor !=null)
            cursor.moveToFirst();
        SinhVien sv=new
                SinhVien(cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4));
        cursor.close();
        db.close();
        return sv;
    }
    // Lấy toàn bộ danh sách SV
    public List<SinhVien> getAllSV(){
        List<SinhVien> listSV=new ArrayList<SinhVien>();
// lựa chọn tất cả
        String selectQuery="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                SinhVien sv=new SinhVien();
                sv.setmID(cursor.getInt(0));
                sv.setHoten(cursor.getString(1));
                sv.setSdt(cursor.getString(2));
                sv.setDiachi(cursor.getString(3));
                sv.setEmail(cursor.getString(4));
                listSV.add(sv);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listSV;
    }
    //Chọn SV thông qua ID
    public SinhVien getSVByID(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.query(TABLE_NAME, new String[]
                { ID, NAME, EMAIL,SDT,ADDRESS }, ID + "=?",new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        SinhVien sv = new
                SinhVien(cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4));
        cursor.close();
        db.close();
        return sv;
    }
    // Update thong tin sv
    public int UpdateSV(SinhVien sv){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,sv.getHoten());
        values.put(SDT,sv.getSdt());
        values.put(ADDRESS,sv.getDiachi());
        values.put(EMAIL,sv.getEmail());
        int a= db.update(TABLE_NAME,values,ID+"=?",new
                String[]{String.valueOf(sv.getmID())});
        return a;
    }
    // Xoa 1 sv thông qua ID
    public int DeleteSV(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int kq= db.delete(TABLE_NAME, ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
        return kq;
    }
}
