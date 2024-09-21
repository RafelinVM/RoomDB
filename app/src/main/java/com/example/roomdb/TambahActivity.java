package com.example.roomdb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.roomdb.model.Produk;

public class TambahActivity extends AppCompatActivity {

    EditText etNama, etProduk;
    Button btnSimpan;
    AppDatabase app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        // Inisialisasi EditText dan Button
        etNama = findViewById(R.id.et_nama);
        etProduk = findViewById(R.id.et_produk);
        btnSimpan = findViewById(R.id.btn_simpan);

        // Inisialisasi database Room
        app = Room.databaseBuilder(this, AppDatabase.class, "inventory.db")
                .allowMainThreadQueries()
                .build();

        // Aksi ketika tombol simpan diklik
        btnSimpan.setOnClickListener(v -> {
            String namaProduk = etNama.getText().toString();
            String deskripsiProduk = etProduk.getText().toString();

            if (namaProduk.isEmpty() || deskripsiProduk.isEmpty()) {
                Toast.makeText(this, "Nama dan Deskripsi harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            // Memasukkan data ke database
            Produk produk = new Produk();
            produk.nama = namaProduk;
            produk.deskripsi = deskripsiProduk;

            app.produkDao().insert(produk);

            // Menampilkan toast dan mengembalikan ke MainActivity
            Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();

            // Kembali ke MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish(); // Menutup TambahActivity
        });
    }
}
