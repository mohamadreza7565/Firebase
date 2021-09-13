package com.ryfa.firebase.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryfa.firebase.R;
import com.ryfa.firebase.databinding.LayoutRvNotifItemBinding;
import com.ryfa.firebase.model.NotificationItem;

import java.util.ArrayList;

public class NotificationItemRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<NotificationItem> list;

    public NotificationItemRvAdapter(Context context, ArrayList<NotificationItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutRvNotifItemBinding binding = LayoutRvNotifItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        NotificationItem item = list.get(position);

        ViewHolder viewHolder = (ViewHolder) holder;

        if (!item.getKey().isEmpty())
            viewHolder.itemBinding.etKey.setText(item.getKey());
        if (!item.getValue().isEmpty())
            viewHolder.itemBinding.etValue.setText(item.getValue());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LayoutRvNotifItemBinding itemBinding;

        public ViewHolder(LayoutRvNotifItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;

            binding.etValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    NotificationItem item = new NotificationItem();
                    item.setValue(binding.etValue.getText().toString());
                    item.setKey(binding.etKey.getText().toString());
                    list.set(getAdapterPosition(), item);
                }
            });

            binding.etKey.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    NotificationItem item = new NotificationItem();
                    item.setValue(binding.etValue.getText().toString());
                    item.setKey(binding.etKey.getText().toString());
                    list.set(getAdapterPosition(), item);
                }
            });

        }
    }

    public ArrayList<NotificationItem> getList() {
        return list;
    }

}
