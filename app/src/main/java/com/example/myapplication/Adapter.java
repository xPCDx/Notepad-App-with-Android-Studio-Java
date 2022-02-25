package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Node;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    LayoutInflater inflater;
    List<Note> notes;

    Adapter(Context context, List<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.custom_list_view, viewGroup, false);
       // GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
       // lp.width = viewGroup.getMeasuredWidth();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String title = notes.get(i).getTitle();
        //String content = notes.get(i).getContent();
        long id = notes.get(i).getId();

        viewHolder.nTitle.setText(title);
        //viewHolder.nContent.setText(content);
        //viewHolder.nId.setText(String.valueOf(notes.get(i).getId()));



    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nTitle, nId;

        Button cardViewBtn = (Button) itemView.findViewById(R.id.cardViewButton);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.noteTitle);
            //nContent = itemView.findViewById(R.id.detailsDescription);
            nId = itemView.findViewById(R.id.listId);


            cardViewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(),Details.class);
                    i.putExtra("id",notes.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(i);
                    Log.d("asd", "asd"+ notes.get(getAdapterPosition()).getId());
                }
            });



        }
    }
}
