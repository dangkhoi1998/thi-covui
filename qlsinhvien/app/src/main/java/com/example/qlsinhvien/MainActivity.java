package com.example.qlsinhvien;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtID;
    private EditText edtSDT;
    private EditText edtDiaChi;
    private EditText edtEmail;
    private Button btnSave;
    private Button btnUpdate;
    private Button btnDelete;
    private ListView lvsv;
    private Database database;
    private List<SinhVien> listsv;
    private CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database =new Database(this);
        initwidget();
        listsv=database.getAllSV();
        setAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinhVien sv=CreateSV();
                if(sv!=null){
                    database.addSV(sv);
                }
                listsv.clear();
                listsv.addAll(database.getAllSV());
                setAdapter();
            }
        });
        lvsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                SinhVien sv=listsv.get(position);
                edtID.setText(String.valueOf(sv.getmID()));
                edtName.setText(sv.getHoten());
                edtSDT.setText(sv.getSdt());
                edtDiaChi.setText(sv.getDiachi());
                edtEmail.setText(sv.getEmail());
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinhVien sv=new SinhVien();
                sv.setmID(Integer.parseInt(String.valueOf(edtID.getText())));
                sv.setHoten(edtName.getText()+"");
                sv.setSdt(edtSDT.getText()+"");
                sv.setDiachi(edtDiaChi.getText()+"");
                sv.setEmail(edtEmail.getText()+"");
                int kq= database.UpdateSV(sv);
                if(kq>0){
                    updateListSV();
                }btnSave.setEnabled(true);
                btnUpdate.setEnabled(false);
            }
        });
        lvsv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int
                    position, long id) {
                SinhVien sv=listsv.get(position);
                int kq=database.DeleteSV(sv.getmID());
                if(kq>0) {
                    Toast.makeText(MainActivity.this,"Delete succesfuly",
                            Toast.LENGTH_SHORT).show();
                    updateListSV();
                } else {
                    Toast.makeText(MainActivity.this,"Delete false",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    private void initwidget(){
        edtID=(EditText)findViewById(R.id.edt_id);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtDiaChi = (EditText) findViewById(R.id.edt_diachi);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtSDT = (EditText) findViewById(R.id.edt_sdt);
        btnSave = (Button) findViewById(R.id.btn_Save);
        lvsv=(ListView)findViewById(R.id.lv_thongtin);
        btnUpdate=(Button)findViewById(R.id.btn_Update);
    }
    private SinhVien CreateSV() {
        String name = edtName.getText().toString();
        String sdt = edtSDT.getText().toString();
        String diachi = edtDiaChi.getText().toString();
        String email = edtEmail.getText().toString();
        SinhVien sv = new SinhVien(name,sdt,diachi, email);
        return sv;
    }
    private void setAdapter(){
        if(customAdapter==null){
            customAdapter=new CustomAdapter(this,R.layout.item_listsv,listsv);
            lvsv.setAdapter(customAdapter);
        }else {
            customAdapter.notifyDataSetChanged();
        }
    }
    public void updateListSV() {
        listsv.clear();
        listsv.addAll(database.getAllSV());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }
}
