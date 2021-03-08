package com.example.antssignments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    public static final String TAG = "NoteAdapter";

    ArrayList<String> items;
    Context context;

   onClickListener clickListener;
    OnLongClickListener longClickListener;


    public NoteAdapter(Context context, ArrayList<String> items,
                       OnLongClickListener longClickListener, onClickListener clickListener) {
        this.items = items;
        this.context = context;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    public interface onClickListener {
        void onItemClicked(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View todoView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;
        Button btnAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            tvItem.setText(item);

            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                    Log.i(TAG, String.valueOf(getAdapterPosition()));
                }
            });

            tvItem.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }

}
