package com.example.roomdb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.roomdb.R;
import com.example.roomdb.model.Produk;

import java.util.List;

public class ProdukAdapter extends BaseAdapter {
    Context context;
    List<Produk> produkList;

    // Constructor
    public ProdukAdapter(Context context, List<Produk> produkList) {
        this.context = context;
        this.produkList = produkList;
    }

    @Override
    public int getCount() {
        return produkList != null ? produkList.size() : 0; // Handle null case
    }

    @Override
    public Object getItem(int position) {
        return produkList != null ? produkList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            // Inflate the view
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            holder = new ViewHolder();

            // Initialize views
            holder.tvNama = convertView.findViewById(R.id.tv_nama);
            holder.tvDeskripsi = convertView.findViewById(R.id.tv_deskripsi);

            // Set the holder as tag to avoid calling findViewById again
            convertView.setTag(holder);
        } else {
            // Reuse the ViewHolder to avoid re-inflating
            holder = (ViewHolder) convertView.getTag();
        }

        // Set data into the views
        Produk produk = produkList.get(position);
        holder.tvNama.setText(produk.nama);
        holder.tvDeskripsi.setText(produk.deskripsi);

        return convertView;
    }

    // ViewHolder pattern for performance optimization
    static class ViewHolder {
        TextView tvNama;
        TextView tvDeskripsi;
    }
}
