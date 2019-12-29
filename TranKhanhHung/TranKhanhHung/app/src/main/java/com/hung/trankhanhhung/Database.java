package com.hung.trankhanhhung;

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
    public static final String DATABASE_NAME = "DS_Sach.db";
    // khai bao tên bảng
    private static final String TABLE_NAME = "sach";
    //khai báo tên các trường
    private static final String MA = "ma";
    private static final String TEN = "ten";
    private static final String LOAI = "loai";
    private static final String NXB = "nxb";
    private static final String NAM = "nam";
    // khai báo phiên bản
    private static int VERSION = 1;
    private Context context;
    //Tạo bảng với các trường tương ứng
    String sqlQuery = " CREATE TABLE " + TABLE_NAME + " (" +
            MA + " integer primary key, " +
            TEN + " TEXT, " +
            LOAI + " TEXT, " +
            NXB + " TEXT, " +
            NAM + " TEXT) ";
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
    public void addS(Sach s){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues gt=new ContentValues();
        gt.put(TEN,s.getTen());
        gt.put(LOAI,s.getLoai());
        gt.put(NXB, s.getNxb());
        gt.put(NAM, s.getNam());
        db.insert(TABLE_NAME, null, gt);
        db.close();
    }
    //Đọc thông tin SV
    public Sach getSachByma(int ma){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        Cursor cursor = (Cursor) db.query(TABLE_NAME,new String[]{
                MA,TEN,LOAI,NXB,NAM},"ID=?",new
                String[]{String.valueOf(ma)},null,null,null,null);
        if (cursor !=null)
            cursor.moveToFirst();
        Sach s=new
                Sach(cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4));
        cursor.close();
        db.close();
        return s;
    }
    // Lấy toàn bộ danh sách Sách
    public List<Sach> getAllS(){
        List<Sach> listS=new ArrayList<Sach>();
// lựa chọn tất cả
        String selectQuery="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Sach s=new Sach();
                s.setMa(cursor.getInt(0));
                s.setTen(cursor.getString(1));
                s.setLoai(cursor.getString(2));
                s.setNxb(cursor.getString(3));
                s.setNam(cursor.getString(4));
                listS.add(s);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listS;
    }
    //Chọn SV thông qua ID
    public Sach getSVByma(int ma){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.query(TABLE_NAME, new String[]
                { MA, TEN, LOAI,NXB,NAM }, MA + "=?",new String[] { String.valueOf(ma) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Sach s = new
                Sach(cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4));
        cursor.close();
        db.close();
        return s;
    }
    // Update thong tin sach
    public int UpdateS(Sach s){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TEN,s.getTen());
        values.put(LOAI,s.getLoai());
        values.put(NXB,s.getNxb());
        values.put(NAM,s.getNam());
        int a= db.update(TABLE_NAME,values,MA+"=?",new
                String[]{String.valueOf(s.getMa())});
        return a;
    }
    // Xoa 1 sv thông qua ID
    public int DeleteS(int ma) {
        SQLiteDatabase db = this.getWritableDatabase();
        int kq= db.delete(TABLE_NAME, ma + " = ?",
                new String[] { String.valueOf(ma) });
        db.close();
        return kq;
    }
}