package com.example.projetoatividade01.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoatividade01.DetalheTodoActivity;
import com.example.projetoatividade01.R;
import com.example.projetoatividade01.model.Photo;
import com.example.projetoatividade01.model.Todo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>{

    private List<Photo> photoList;
    private  int layout;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

    public PhotoAdapter(List<Photo> photos, int layout){
        this.photoList = photos;
        this.layout = layout;
    }


    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, int position) {

        Photo obj = (Photo)this.photoList.get(position);

        TextView tv = holder.view.findViewById(R.id.photoCard_Title);
        tv.setText("Titulo: " + obj.getTitle()+"");
        DetalheTodoActivity dta = new DetalheTodoActivity();
        //deveria ser só trocar o link por obj.getThumbnailUrl(), mas não funciona ;-;
        dta.loadImg("https://apucarana.cidadecancao.com/media/catalog/product/cache/1/small_image/200x200/9df78eab33525d08d6e5fb8d27136e95/a/b/abacaxi-perola-unidade-0000000032674.jpg",
                holder.view.findViewById(R.id.photoCard_ImageView));

        CardView cv = holder.view.findViewById(R.id.CardPhoto);
        cv.setTag(obj);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView btn = (CardView) v;
                Photo photo = (Photo) btn.getTag();
                Intent intent = new Intent(holder.view.getContext(), DetalheTodoActivity.class);

                intent.putExtra("objTodo", obj);

                holder.view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.photoList.size();
    }


}
