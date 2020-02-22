package com.rathana.room_database.data.dao;

import java.util.List;

public interface BaseDao<T> {

    void save(T t);

    void update(T t);

    void delete(T t);

    List<T> getAll();
}
