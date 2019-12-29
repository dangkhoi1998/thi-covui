package com.hung.trankhanhhung;

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
    private List<Sach> listsv;
    private CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database =new Database(this);
        initwidget();
        listsv=database.getAllS();
        setAdapter();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach s=CreateSV();
                if(s!=null){
                    database.addS(s);
                }
                listsv.clear();
                listsv.addAll(database.getAllS());
                setAdapter();
            }
        });
        lvsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Sach sv=listsv.get(position);
                edtID.setText(String.valueOf(sv.getMa()));
                edtName.setText(sv.getTen());
                edtSDT.setText(sv.getLoai());
                edtDiaChi.setText(sv.getNxb());
                edtEmail.setText(sv.getNam());
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sach s=new Sach();
                s.setMa(Integer.parseInt(String.valueOf(edtID.getText())));
                s.setTen(edtName.getText()+"");
                s.setLoai(edtSDT.getText()+"");
                s.setNxb(edtDiaChi.getText()+"");
                s.setNam(edtEmail.getText()+"");
                int kq= database.UpdateS(s);
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
                Sach sv=listsv.get(position);
                int kq=database.DeleteS(sv.getMa());
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
        edtID=(EditText)findViewById(R.id.txt_ma);
        edtName = (EditText) findViewById(R.id.txt_ten);
        edtDiaChi = (EditText) findViewById(R.id.txt_loai);
        edtEmail = (EditText) findViewById(R.id.txt_nxb);
        edtSDT = (EditText) findViewById(R.id.txt_nam);
        btnSave = (Button) findViewById(R.id.btn_Save);
        lvsv=(ListView)findViewById(R.id.lv_danhsach);
        btnUpdate=(Button)findViewById(R.id.btn_Update);
    }
    private Sach CreateSV() {
        String name = edtName.getText().toString();
        String sdt = edtSDT.getText().toString();
        String diachi = edtDiaChi.getText().toString();
        String email = edtEmail.getText().toString();
        Sach s = new Sach(name,sdt,diachi, email);
        return s;
    }
    private void setAdapter(){
        if(customAdapter==null){
            customAdapter=new CustomAdapter(this,R.layout.item_lists,listsv);
            lvsv.setAdapter(customAdapter);
        }else {
            customAdapter.notifyDataSetChanged();
        }
    }
    public void updateListSV() {
        listsv.clear();
        listsv.addAll(database.getAllS());
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
    }
}