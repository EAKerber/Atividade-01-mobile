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
        dta.loadImg("https://images-ext-1.discordapp.net/external/PMeU-z2SZ9k3R3DCJrIBVSb9QVdsW9W-reautC5C3p8/%3F_nc_cat%3D107%26ccb%3D1-3%26_nc_sid%3D174925%26_nc_eui2%3DAeFr-dCVeF6qNFn6Ejsax8QIwrdx1XULoPHCt3HVdQug8cCH_nSzDzVlOoSLHAjBeh4%26_nc_ohc%3D1pmgyrC2FVMAX9cjQ_m%26_nc_ht%3Dscontent.fbfh1-2.fna%26oh%3Dad0f78bf69805656f692b51a7bdf31e2%26oe%3D60ABF7F9/https/scontent.fbfh1-2.fna.fbcdn.net/v/t1.18169-9/10982036_617952641671138_5149806917836956363_n.jpg?width=473&height=473",
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
