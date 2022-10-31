package com.example.mystudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nguyencse.URLEmbeddedData;
import com.nguyencse.URLEmbeddedView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    List<SearchData> searchData;
    Context context;

    public SearchAdapter(Context context , List<SearchData> searches){
        this.context = context;
        searchData = searches;
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item_container, parent , false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchData post = searchData.get(position);
        holder.embView.setURL(post.getLink(), new URLEmbeddedView.OnLoadURLListener() {
            @Override
            public void onLoadURLCompleted(URLEmbeddedData data) {
                holder.embView.title(data.getTitle());
                holder.embView.description(data.getDescription());
                holder.embView.host(data.getHost());
                if (!(data.getThumbnailURL() == null ||data.getThumbnailURL().isEmpty())){
                    holder.embView.thumbnail(data.getThumbnailURL());
                }
                holder.embView.favor(data.getFavorURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchData.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        com.nguyencse.URLEmbeddedView embView;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            embView = itemView.findViewById(R.id.uev);

        }
    }
}