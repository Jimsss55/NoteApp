package com.example.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<Notes> noteArrayList;
    Context context;
    private OnItemCLickListener listener;

    public interface OnItemCLickListener{
        void onEditClick(int position);
        void onDeleteClick(int position);
    }

    public NoteAdapter(Context context, ArrayList<Notes> noteArrayList, OnItemCLickListener listener){
        this.context=context;
        this.noteArrayList=noteArrayList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recycler,parent, false);
        return new NoteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, final int position) {

        holder.editButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                listener.onEditClick(position);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                listener.onDeleteClick(position);
            }
        });
//        Assigning the values to the ro
        holder.rectitle.setText(noteArrayList.get(position).getTitle());
        holder.recdescription.setText(noteArrayList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {

        return noteArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rectitle, recdescription;
        Button editButton, deleteButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rectitle = itemView.findViewById(R.id.rectitle);
            recdescription = itemView.findViewById(R.id.recbody);
            editButton = itemView.findViewById(R.id.edit);
            deleteButton = itemView.findViewById(R.id.delete);
        }
    }
}
