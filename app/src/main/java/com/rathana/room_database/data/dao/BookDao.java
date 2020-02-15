package com.rathana.room_database.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rathana.room_database.data.enitty.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void save(Book book);

    @Insert
    void save(Book... books);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(List<Book> books);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);

    @Query("SELECT * FROM book ORDER BY id ASC")
    List<Book> getBooks();

}
