package com.example.roomdb.model;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface ProdukDao {
    @Insert
    void insert(Produk produk);
    @Update
    void update(Produk produk);
    @Delete
    void delete(Produk produk);
    @Query("SELECT * FROM Produk")
    List<Produk> getAll();
}
