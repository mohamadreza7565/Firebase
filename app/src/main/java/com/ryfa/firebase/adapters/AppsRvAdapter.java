package com.ryfa.firebase.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ryfa.firebase.AddAppActivity;
import com.ryfa.firebase.R;
import com.ryfa.firebase.SendNotificationActivity;
import com.ryfa.firebase.databinding.LayoutRvAppItemBinding;
import com.ryfa.firebase.general.TimeSinceAgo;
import com.ryfa.firebase.model.App;

import java.util.ArrayList;
import java.util.List;

public class AppsRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<App> list;
    OnItemClickListener onItemClickListener;

    public AppsRvAdapter(Context context, List<App> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutRvAppItemBinding binding = LayoutRvAppItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {

        App app = list.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.itemBinding.tvCreateAt.setText(TimeSinceAgo.getTimeAgo(context,app.getDate()/1000));
        viewHolder.itemBinding.tvPackageName.setText(app.getPackageName());
        viewHolder.itemBinding.tvAppName.setText(app.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LayoutRvAppItemBinding itemBinding;

        public ViewHolder(LayoutRvAppItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;

            itemBinding.btnSendNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SendNotificationActivity.class);
                    intent.putExtra("APP",new Gson().toJson(list.get(getAdapterPosition())));
                    context.startActivity(intent);
                }
            });

            itemBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddAppActivity.class);
                    intent.putExtra("APP",new Gson().toJson(list.get(getAdapterPosition())));
                    context.startActivity(intent);
                }
            });

            itemBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onDelete(getAdapterPosition(),list.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onDelete(int position , App app);
    }

    public AppsRvAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }
}
