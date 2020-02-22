package com.rathana.room_database.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rathana.room_database.data.dao.BookDao;
import com.rathana.room_database.data.dao.CategoryDao;
import com.rathana.room_database.data.enitty.Book;
import com.rathana.room_database.data.enitty.Category;

@Database(version = 4, entities = {Book.class, Category.class})
abstract public class BookStoreDatabase extends RoomDatabase {

    private static final String DB_NAME = "bookstore_db";

    //create abstract method for dao
    public abstract BookDao bookDao();
    public abstract CategoryDao categoryDao();

    public static BookStoreDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, BookStoreDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

}
