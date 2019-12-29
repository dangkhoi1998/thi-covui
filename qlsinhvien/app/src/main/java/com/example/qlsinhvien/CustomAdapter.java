package com.example.qlsinhvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
public class CustomAdapter extends ArrayAdapter<SinhVien> {
    private Context context;
    private int resource;
    private List<SinhVien> ListSV;
    public CustomAdapter(Context context, int resource, List<SinhVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.ListSV= objects;}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=
                    LayoutInflater.from(context).inflate(R.layout.item_listsv,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tvId=(TextView)convertView.findViewById(R.id.tv_id);
            viewHolder.tvTen=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tvSdt=(TextView)convertView.findViewById(R.id.tv_sdt);
            viewHolder.tvDiachi=(TextView)convertView.findViewById(R.id.tv_diachi);
            viewHolder.tvEmail=(TextView)convertView.findViewById(R.id.tv_email);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        SinhVien sv=ListSV.get(position);
        viewHolder.tvId.setText(String.valueOf(sv.getmID()));
        viewHolder.tvTen.setText(sv.getHoten());
        viewHolder.tvSdt.setText(sv.getSdt());
        viewHolder.tvDiachi.setText(sv.getDiachi());
        viewHolder.tvEmail.setText(sv.getEmail());
        return convertView;
    }
    public class ViewHolder{
        //khai báo các TextView trong Item su dung
        private TextView tvId;
        private TextView tvTen;
        private TextView tvDiachi;
        private TextView tvSdt;
        private TextView tvEmail;
    }
}
