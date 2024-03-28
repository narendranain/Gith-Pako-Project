package com.azhar.peko.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.peko.R;
import com.azhar.peko.model.search.ModelSearchData;
import com.azhar.peko.ui.activities.DetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private ArrayList<ModelSearchData> modelSearchDataList = new ArrayList<>();
    private Context context;


    public SearchAdapter(Context context) {
        this.context = context;
    }

    public void setSearchUserList(ArrayList<ModelSearchData> items) {
        modelSearchDataList.clear();
        modelSearchDataList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        ModelSearchData item = modelSearchDataList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(item.getAvatarUrl())
                .into(holder.imageUser);

        // Call the interface method with the data you want to pass
        holder.tvUsername.setText(item.getLogin());
        holder.tvUrl.setText(item.getHtmlUrl());
        holder.cvListUser.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("Nanu", modelSearchDataList.get(position).getAvatarUrl());
            intent.putExtra(DetailActivity.DETAIL_USER, modelSearchDataList.get(position));
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return modelSearchDataList.size();
    }


    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        CardView cvListUser;
        TextView tvUsername, tvUrl;
        ImageView imageUser;

        public SearchViewHolder(View itemView) {
            super(itemView);
            cvListUser = itemView.findViewById(R.id.cvListUser);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvUrl = itemView.findViewById(R.id.tvUrl);
            imageUser = itemView.findViewById(R.id.imageUser);
        }
    }

}
