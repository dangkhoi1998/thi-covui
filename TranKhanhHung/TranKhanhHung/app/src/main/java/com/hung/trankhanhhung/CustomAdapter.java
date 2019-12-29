package com.hung.trankhanhhung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
public class CustomAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private int resource;
    private List<Sach> ListS;
    public CustomAdapter(Context context, int resource, List<Sach> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.ListS= objects;}
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=
                    LayoutInflater.from(context).inflate(R.layout.item_lists,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tvId=(TextView)convertView.findViewById(R.id.tv_ma);
            viewHolder.tvTen=(TextView)convertView.findViewById(R.id.tv_ten);
            viewHolder.tvSdt=(TextView)convertView.findViewById(R.id.tv_loai);
            viewHolder.tvDiachi=(TextView)convertView.findViewById(R.id.tv_nxb);
            viewHolder.tvEmail=(TextView)convertView.findViewById(R.id.tv_nam);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Sach sv=ListS.get(position);
        viewHolder.tvId.setText(String.valueOf(sv.getMa()));
        viewHolder.tvTen.setText(sv.getTen());
        viewHolder.tvSdt.setText(sv.getLoai());
        viewHolder.tvDiachi.setText(sv.getNxb());
        viewHolder.tvEmail.setText(sv.getNam());
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
