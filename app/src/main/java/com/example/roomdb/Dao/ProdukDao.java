package com.example.roomdb.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdb.model.Produk;

import java.util.List;
@Dao
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
