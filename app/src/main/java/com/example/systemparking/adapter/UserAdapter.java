package com.example.systemparking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.systemparking.R;
import com.example.systemparking.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private Context context;
    private List<User> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public UserAdapter(Context context, List<User> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvlist,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.snipper.setText(list.get(position).getSnipper());
        holder.nama.setText(list.get(position).getNama());
        holder.nomor.setText(list.get(position).getNomor());
        holder.merek.setText(list.get(position).getMerek());
        holder.tipe.setText(list.get(position).getTipe());
        holder.tahun.setText(list.get(position).getTahun());
        holder.tanggal.setText(list.get(position).getTanggal());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView snipper, nama, nomor, merek, tipe, tahun, tanggal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            snipper = itemView.findViewById(R.id.tvParkir);
            nama = itemView.findViewById(R.id.tvNamapemilik);
            nomor = itemView.findViewById(R.id.tvNomorTNBK);
            merek = itemView.findViewById(R.id.tvMerekkendaraan);
            tipe = itemView.findViewById(R.id.tvTipekendaraan);
            tahun = itemView.findViewById(R.id.tvTahunkendaraan);
            tanggal = itemView.findViewById(R.id.tvTanggal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });

        }
    }
}
