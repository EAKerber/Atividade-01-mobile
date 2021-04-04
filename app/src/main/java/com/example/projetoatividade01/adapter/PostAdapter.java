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
import com.example.projetoatividade01.model.Post;
import com.example.projetoatividade01.model.Todo;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private List<Post> postList;
    private  int layout;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

    public PostAdapter(List<Post> posts, int layout){
        this.postList = posts;
        this.layout = layout;
    }


    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        Post obj = (Post)this.postList.get(position);

        TextView tv = holder.view.findViewById(R.id.postCard_Title);
        tv.setText("Titulo: " + obj.getTitle()+"");
        tv = holder.view.findViewById(R.id.postCard_Body);
        tv.setText("''"+obj.getBody()+"''");

        TextView cv = holder.view.findViewById(R.id.postCard_Title);
        cv.setTag(obj);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView btn = (TextView) v;
                Post post = (Post) btn.getTag();
                Intent intent = new Intent(holder.view.getContext(), DetalheTodoActivity.class);

                intent.putExtra("objTodo", obj);

                holder.view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.postList.size();
    }


}