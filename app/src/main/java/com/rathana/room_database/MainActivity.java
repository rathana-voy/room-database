package com.rathana.room_database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rathana.room_database.adapter.BookAdapter;
import com.rathana.room_database.data.BookStoreDatabase;
import com.rathana.room_database.data.dao.BookDao;
import com.rathana.room_database.data.enitty.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
//    private Button btnGetBook;

    private BookDao bookDao;

    private RecyclerView recyclerView;
    private BookAdapter adapter;

    private static final int REQUEST_ADD_BOOK_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.floatingActionButton);
        bookDao = BookStoreDatabase.getInstance(this).bookDao();
        recyclerView = findViewById(R.id.rvBook);
        setupRecyclerView();
        loadBookList();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this,
                                AddBookActivity.class), REQUEST_ADD_BOOK_CODE
                );
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new BookAdapter(new ArrayList<Book>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadBookList() {
        adapter.addMoreItem(bookDao.getBooks());
    }

    private void reload() {
        adapter.reloadItems(bookDao.getBooks());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD_BOOK_CODE && resultCode == RESULT_OK) {
            reload();
        }
    }
}
