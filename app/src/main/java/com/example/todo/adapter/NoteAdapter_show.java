package com.example.todo.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.model.NoteModel;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class NoteAdapter_show extends RecyclerView.Adapter<NoteAdapter_show.NoteViewHolder> {
    private ArrayList<NoteModel> noteModelsList;
    Context context;
    NoteModel noteModel;

    public NoteAdapter_show(ArrayList<NoteModel> noteModels, Context context) {
        this.noteModelsList = noteModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_item_todo, parent, false);
        return new NoteViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        noteModel = noteModelsList.get(position);
        holder.tvTitle.setText(noteModel.getName());
        holder.tvCategory.setText(noteModel.getCategory());
        //set am or pm
        final int hour = LocalDateTime.now().getHour();
        if(hour > 12)
            holder.tvAMorPM.setText("PM");
        else
            holder.tvAMorPM.setText("AM");
    }

    @Override
    public int getItemCount() {
        return noteModelsList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvCategory, tvDateTime;
        TextView tvAMorPM;
        ImageView ivColor;
        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle_todo);
            tvCategory = itemView.findViewById(R.id.tvCategory_todo);
            tvAMorPM = itemView.findViewById(R.id.tvPMorAM);
            tvDateTime = itemView.findViewById(R.id.tvTime_todo);

//            ivColor = itemView.findViewById(R.id.ivColor_atodo);
        }
    }
}
