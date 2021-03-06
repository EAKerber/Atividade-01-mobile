package com.example.projetoatividade01.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoatividade01.DetalheTodoActivity;
import com.example.projetoatividade01.R;
import com.example.projetoatividade01.model.Post;
import com.example.projetoatividade01.model.Todo;

import java.util.List;

public class GenericAdapter extends RecyclerView.Adapter<GenericAdapter.ViewHolder>{

    private List<Todo> todoList;
    private  int layout;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
        }
    }

    public GenericAdapter(List<Todo> todos, int layout){
        this.todoList = todos;
        this.layout = layout;
    }


    @NonNull
    @Override
    public GenericAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(this.layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericAdapter.ViewHolder holder, int position) {

        Todo obj = (Todo)this.todoList.get(position);

        TextView tv = holder.view.findViewById(R.id.todoCard_Title);
        tv.setText("''"+obj.getTitle()+"''");
        CheckBox cb = holder.view.findViewById(R.id.cbTodoCard);
        cb.setChecked(obj.isCompleted());

        CardView cv = holder.view.findViewById(R.id.CardTodo);
        cv.setTag(obj);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView btn = (CardView) v;
                Todo todo = (Todo) btn.getTag();
                Intent intent = new Intent(holder.view.getContext(), DetalheTodoActivity.class);

                intent.putExtra("objTodo", obj);

                holder.view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.todoList.size();
    }


}
