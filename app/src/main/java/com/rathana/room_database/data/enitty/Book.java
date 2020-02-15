package com.rathana.room_database.data.enitty;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "book")
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String author;
    @ColumnInfo(name = "date_created")
    public String dateCreated;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
