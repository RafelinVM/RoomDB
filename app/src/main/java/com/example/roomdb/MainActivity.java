package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.roomdb.adapter.ProdukAdapter;
import com.example.roomdb.model.Produk;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvProduk;
    FloatingActionButton fabTambah;
    AppDatabase app;
    List<Produk> produkList;
    ProdukAdapter produkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProduk = findViewById(R.id.lv_produk);
        fabTambah = findViewById(R.id.fab_tambah);

        // Initialize database
        app = Room.databaseBuilder(this, AppDatabase.class, "inventory.db")
                .allowMainThreadQueries()
                .build();

        // Display data
        tampilkanData();

        // Event listener for adding new product
        fabTambah.setOnClickListener(view -> {
            Intent intent = new Intent(this, TambahActivity.class);
            startActivity(intent);
        });

        // Event listener for item click in ListView
        lvProduk.setOnItemClickListener((parent, view, position, id) -> {
            Produk selectedProduk = produkList.get(position);
            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
            intent.putExtra("PRODUCT_ID", selectedProduk.id); // Send product ID
            intent.putExtra("PRODUCT_NAME", selectedProduk.nama); // Send product name
            intent.putExtra("PRODUCT_DESC", selectedProduk.deskripsi); // Send product description
            startActivity(intent);
        });
    }

    private void tampilkanData() {
        produkList = app.produkDao().getAll(); // Fetch data
        produkAdapter = new ProdukAdapter(this, produkList); // Convert data
        lvProduk.setAdapter(produkAdapter); // Display data
    }
}
