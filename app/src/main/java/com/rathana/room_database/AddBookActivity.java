package com.rathana.room_database;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rathana.room_database.data.BookStoreDatabase;
import com.rathana.room_database.data.dao.BookDao;
import com.rathana.room_database.data.enitty.Book;

public class AddBookActivity extends AppCompatActivity {

    private EditText edtTitle, edtAuthor, edtPublicationDate;
    private Button btnSave;

    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        edtAuthor = findViewById(R.id.author);
        edtTitle = findViewById(R.id.title);
        edtPublicationDate = findViewById(R.id.dateCreate);
        btnSave = findViewById(R.id.btnSave);
        //create book Dao object
        bookDao = BookStoreDatabase.getInstance(this).bookDao();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Book book = new Book();
                book.title = edtTitle.getText().toString();
                book.author = edtAuthor.getText().toString();
                book.dateCreated = edtPublicationDate.getText().toString();
                bookDao.save(book);

                Toast.makeText(AddBookActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();

    }
}
