package com.example.roomdb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.roomdb.model.Produk;

public class UpdateActivity extends AppCompatActivity {

    EditText etNama, etProduk;
    Button btnUpdate, btnDelete;
    AppDatabase app;
    int productId; // Menggunakan int jika id di Produk adalah int

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Inisialisasi EditText dan Button
        etNama = findViewById(R.id.et_nama);
        etProduk = findViewById(R.id.et_produk);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        // Inisialisasi database Room
        app = Room.databaseBuilder(this, AppDatabase.class, "inventory.db")
                .allowMainThreadQueries()
                .build();

        // Ambil data dari Intent
        Intent intent = getIntent();
        productId = intent.getIntExtra("PRODUCT_ID", -1); // Menggunakan getIntExtra jika id adalah int
        String productName = intent.getStringExtra("PRODUCT_NAME");
        String productDesc = intent.getStringExtra("PRODUCT_DESC");

        // Isi EditText dengan data yang ada
        etNama.setText(productName);
        etProduk.setText(productDesc);

        // Event klik tombol Update
        btnUpdate.setOnClickListener(v -> {
            String namaProduk = etNama.getText().toString();
            String deskripsiProduk = etProduk.getText().toString();

            if (namaProduk.isEmpty() || deskripsiProduk.isEmpty()) {
                Toast.makeText(this, "Nama dan Deskripsi harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            // Buat objek Produk dengan data yang diperbarui
            Produk produk = new Produk();
            produk.id = productId; // ID yang sesuai
            produk.nama = namaProduk;
            produk.deskripsi = deskripsiProduk;

            // Perbarui data di database
            app.produkDao().update(produk);

            Toast.makeText(this, "Data Berhasil Diperbarui", Toast.LENGTH_SHORT).show();

            // Kembali ke MainActivity
            Intent intentToMain = new Intent(this, MainActivity.class);
            startActivity(intentToMain);
            finish(); // Menutup UpdateActivity
        });

        // Event klik tombol Delete
        btnDelete.setOnClickListener(v -> {
            if (productId != -1) {
                // Ambil objek Produk dari database berdasarkan ID
                Produk produkToDelete = app.produkDao().getAll().stream()
                        .filter(p -> p.id == productId)
                        .findFirst()
                        .orElse(null);

                if (produkToDelete != null) {
                    // Hapus objek Produk dari database
                    app.produkDao().delete(produkToDelete);

                    Toast.makeText(this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();

                    // Kembali ke MainActivity
                    Intent intentToMain = new Intent(this, MainActivity.class);
                    startActivity(intentToMain);
                    finish(); // Menutup UpdateActivity
                } else {
                    Toast.makeText(this, "Produk tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "ID Produk tidak valid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
