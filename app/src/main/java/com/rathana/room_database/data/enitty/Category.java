package com.rathana.room_database.data.enitty;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "category"
        //, primaryKeys = {"name", "language"}
        , indices={@Index(value = {"name"}, unique = true)}
)
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String language;

}
