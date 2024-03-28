package com.azhar.peko.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.peko.R;
import com.azhar.peko.model.repo.RepositoryDataCap;

import java.util.ArrayList;


public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private ArrayList<RepositoryDataCap> modelRepoArrayList = new ArrayList<>();
    private Context context;

    public RepositoryAdapter(Context context) {
        this.context = context;
    }

    public void setRepoList(ArrayList<RepositoryDataCap> items) {
        modelRepoArrayList.clear();
        modelRepoArrayList.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        RepositoryDataCap item = modelRepoArrayList.get(position);


        holder.repository_name.setText(item.getName() != null ? item.getName() : "Not Found");
        holder.description.setText(item.getDescription() != null ? item.getDescription() : "Description not found");
        holder.language.setText("Language - " + (item.getLanguage() != null ? item.getLanguage() : ""));
        holder.forks.setText("Forks - " + (String.valueOf(item.getForksCount()) != null ? String.valueOf(item.getForksCount()) : "0"));
        holder.stars.setText("Stars - " + (String.valueOf(item.getStarsCount()) != null ? String.valueOf(item.getStarsCount()) : "0"));


        holder.repository_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(item.getProj_url()));
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return modelRepoArrayList.size();
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {

        CardView cvListUser;
        TextView repository_name, description, language, stars, forks;
        ImageView imageUser, imageArrow;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            repository_name = itemView.findViewById(R.id.repository_name);
            description = itemView.findViewById(R.id.description);
            language = itemView.findViewById(R.id.language);
            stars = itemView.findViewById(R.id.stars);
            forks = itemView.findViewById(R.id.forks);

        }
    }

}
