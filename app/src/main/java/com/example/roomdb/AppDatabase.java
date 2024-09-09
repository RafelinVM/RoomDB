package com.example.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomdb.model.Produk;
import com.example.roomdb.model.ProdukDao;

@Database(entities = {Produk.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProdukDao produkDao();
}
