package com.example.recycler;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ContactViewHolder> {
    ImageView imageView;

    public DataAdapter(OnContactClicked onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false);
        return new ContactViewHolder(view);
    }

    private OnContactClicked onItemClickedListener;
    public ArrayList<Data> datas = new ArrayList<>();

    interface OnContactClicked {
        void edit(Data data, int position);
        void delet(Data data );
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
        holder.onBind(datas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickedListener.edit(datas.get(position), position);
            }
        });
        imageView=holder.itemView.findViewById(R. id.img_delete);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickedListener.delet(datas.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textNumber;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.tv_name);
            textNumber = itemView.findViewById(R.id.tv_number);
        }

        void onBind(Data contact) {
            textName.setText(contact.getName());
            textNumber.setText(contact.getNumber());
        }
    }
}
