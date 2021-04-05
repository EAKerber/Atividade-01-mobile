package com.example.projetoatividade01.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoatividade01.DetalheTodoActivity;
import com.example.projetoatividade01.R;
import com.example.projetoatividade01.model.Album;
import com.example.projetoatividade01.model.Todo;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{

    private List<Album> albumList;
    private  int layout;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

    public AlbumAdapter(List<Album> albums, int layout){
        this.albumList = albums;
        this.layout = layout;
    }


    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {

        Album obj = (Album)this.albumList.get(position);

        TextView tv = holder.view.findViewById(R.id.albumCard_Title);
        tv.setText("Titulo: " + obj.getTitle()+"");
        tv = holder.view.findViewById(R.id.albumCard_Id);
        tv.setText("ID: "+obj.getId());
        tv = holder.view.findViewById(R.id.albumCard_UserId);
        tv.setText("UserID: "+obj.getId());

        CardView cv = holder.view.findViewById(R.id.CardAlbum);
        cv.setTag(obj);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView btn = (CardView) v;
                Album album = (Album) btn.getTag();
                Intent intent = new Intent(holder.view.getContext(), DetalheTodoActivity.class);

                intent.putExtra("objTodo", obj);

                holder.view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.albumList.size();
    }


}
