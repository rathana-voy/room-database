package com.rathana.room_database.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rathana.room_database.R;
import com.rathana.room_database.data.enitty.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {


    private List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(books.get(position));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_book, parent, false
        );
        return new ViewHolder(view);
    }


    public void addMoreItem(List<Book> books) {
        int previousSize = this.books.size();
        this.books.addAll(books);
        notifyItemRangeInserted(previousSize, books.size());
    }

    public void reloadItems(List<Book> books) {
        this.books.clear();
        this.books.addAll(books);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, author, publicationDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            publicationDate = itemView.findViewById(R.id.dateCreate);
        }

        void bind(Book book) {
            title.setText(book.title);
            author.setText(book.author);
            publicationDate.setText(book.dateCreated);
        }
    }


}
