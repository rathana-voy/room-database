package com.rathana.room_database;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rathana.room_database.data.BookStoreDatabase;
import com.rathana.room_database.data.dao.BookDao;
import com.rathana.room_database.data.enitty.Book;

public class EditActivity extends AppCompatActivity {


    EditText edtTitle, edtAuthor, edtDateCreate;
    Button btnUpdate;

    private BookDao bookDao;
    private Book book;
    private static final String KEY_BOOK = "book";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtAuthor = findViewById(R.id.author);
        edtTitle = findViewById(R.id.title);
        edtDateCreate = findViewById(R.id.dateCreate);
        btnUpdate = findViewById(R.id.btnUpdate);
        bookDao = BookStoreDatabase.getInstance(this).bookDao();
        bind();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update
                updateBook();
            }
        });
    }

    private void bind() {
        Intent intent = getIntent();
        if (intent != null) {
            book = intent.getParcelableExtra(KEY_BOOK);
            edtTitle.setText(book.title);
            edtAuthor.setText(book.author);
            edtDateCreate.setText(book.dateCreated);
        }
    }

    private void updateBook() {
        //todo
        if (book != null) {
            book.title = edtTitle.getText().toString();
            book.author = edtAuthor.getText().toString();
            book.dateCreated = edtDateCreate.getText().toString();
            bookDao.update(book);

            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}