package com.rathana.room_database.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rathana.room_database.data.enitty.Category;

import java.util.List;

@Dao
public interface CategoryDao extends BaseDao<Category> {

    @Update
    @Override
    void update(Category category);

    @Delete
    @Override
    void delete(Category category);

    @Insert
    @Override
    void save(Category category);

    @Insert
    void save(Category... category);

    @Query("SELECT * FROM category ORDER BY id ASC")
    @Override
    List<Category> getAll();
}
