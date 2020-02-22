package com.rathana.room_database;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rathana.room_database.data.BookStoreDatabase;
import com.rathana.room_database.data.dao.BookDao;
import com.rathana.room_database.data.dao.CategoryDao;
import com.rathana.room_database.data.enitty.Book;
import com.rathana.room_database.data.enitty.Category;

import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {

    private EditText edtTitle, edtAuthor, edtPublicationDate;
    private Button btnSave;
    private BookDao bookDao;
    private Spinner spinnerCategory;
    private CategoryDao categoryDao;

    private List<Category> categories;
    private List<String> categoriesString = new ArrayList<>();
    private int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        edtAuthor = findViewById(R.id.author);
        edtTitle = findViewById(R.id.edit);
        edtPublicationDate = findViewById(R.id.dateCreate);
        spinnerCategory = findViewById(R.id.spinner);

        btnSave = findViewById(R.id.btnSave);
        //create book Dao object
        bookDao = BookStoreDatabase.getInstance(this).bookDao();
        categoryDao = BookStoreDatabase.getInstance(this).categoryDao();

        setupSpinner();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Book book = new Book();
                book.categoryId = categoryId;
                book.title = edtTitle.getText().toString();
                book.author = edtAuthor.getText().toString();
                book.dateCreated = edtPublicationDate.getText().toString();
                bookDao.save(book);

                Toast.makeText(AddBookActivity.this, "saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSpinner() {
        categories = categoryDao.getAll();
        for (Category cat : categories) {
            categoriesString.add(cat.name);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                categoriesString
        );
        spinnerCategory.setAdapter(arrayAdapter);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoryId = categories.get(i).id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();

    }
}
