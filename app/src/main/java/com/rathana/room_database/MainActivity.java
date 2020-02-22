package com.rathana.room_database;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rathana.room_database.adapter.BookAdapter;
import com.rathana.room_database.data.BookStoreDatabase;
import com.rathana.room_database.data.dao.BookDao;
import com.rathana.room_database.data.dao.CategoryDao;
import com.rathana.room_database.data.enitty.Book;
import com.rathana.room_database.data.enitty.Category;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
//    private Button btnGetBook;

    private BookDao bookDao;

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private static int position;
    private static final int REQUEST_ADD_BOOK_CODE = 10;
    private static final int REQUEST_EDIT_BOOK_CODE = 11;
    private static final String KEY_BOOK = "book";

    private CategoryDao categoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.floatingActionButton);
        BookStoreDatabase database = BookStoreDatabase.getInstance(this);
        bookDao = database.bookDao();
        categoryDao = database.categoryDao();
        recyclerView = findViewById(R.id.rvBook);
        setupRecyclerView();
        loadBookList();

//        createDefaultCategory();

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
        adapter = new BookAdapter(new ArrayList<Book>(),
                new BookAdapter.AdapterListener() {
                    @Override
                    public void onButtonMoreClicked(View view, Book book, int position) {
                        openPopupMenu(view, book, position);
                    }
                });
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
        if (resultCode == RESULT_OK &&
                (requestCode == REQUEST_ADD_BOOK_CODE || requestCode == REQUEST_EDIT_BOOK_CODE)) {
            reload();
        }
    }

    private void editBook(Book book) {
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(KEY_BOOK, book);
        startActivityForResult(intent, REQUEST_EDIT_BOOK_CODE);
    }

    private void deleteBook(Book book) {
        if (position >= 0) {
            bookDao.delete(book);
            adapter.remove(position);
        }
    }

    private void openPopupMenu(View view, final Book book, final int pos) {
        position = pos;
        PopupMenu menu = new PopupMenu(this, view);
        menu.inflate(R.menu.popup_menu);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.edit:
                        editBook(book);
                        return true;
                    case R.id.delete:
                        deleteBook(book);
                        return true;
                    default:
                        return false;
                }
            }
        });
        menu.show();
    }

    private void createDefaultCategory() {
        Category category = new Category();
        category.name = "History";
        category.language = "en";

        Category category2 = new Category();
        category2.name = "Biology";
        category2.language = "en";

        Category category3 = new Category();
        category3.name = "Chemistry";
        category3.language = "en";

        categoryDao.save(category,category2,category3);

    }
}
