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
        View view = LayoutInflater.from(context).inflate(R.layout.item,null);
        TextView tvNama = view.findViewById(R.id.tv_nama);
        TextView tvDeskripsi = view.findViewById(R.id.tv_deskripsi);

        tvNama.setText(produkList.get(position).nama);
        tvDeskripsi.setText(produkList.get(position).deskripsi);

        return view;
    }
}
