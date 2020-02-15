package com.rathana.room_database.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rathana.room_database.data.dao.BookDao;
import com.rathana.room_database.data.enitty.Book;

@Database(version = 2, entities = {Book.class})
abstract public class BookStoreDatabase extends RoomDatabase {

    private static final String DB_NAME = "bookstore_db";

    //create abstract method for dao
    public abstract BookDao bookDao();


    public static BookStoreDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, BookStoreDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

}
