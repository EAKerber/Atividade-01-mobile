package com.example.projetoatividade01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetoatividade01.model.Comment;
import com.example.projetoatividade01.model.Todo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaginaInicial extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener{

    List<Todo> todos = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_inicial);

        Intent it = getIntent();
        String txt = it.getStringExtra("valorTexto");
        String pass = it.getStringExtra("valorSenha");
        getSupportActionBar().setTitle("Seja Bem-Vindo, "+txt+".");

        Button btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(this::retornarLogin);
        Button btn2 = (Button)findViewById(R.id.button4);
        btn2.setOnClickListener(this::retornarSplash);
        Button btn3 = (Button)findViewById(R.id.buttonTodo);
        btn3.setOnClickListener(this::todoOnClick);
        Button btn4 = (Button)findViewById(R.id.buttonComment);
        btn4.setOnClickListener(this::commentOnClick);

    }

    public void retornarLogin(View view){
        Intent intent = new Intent(this, TelaLogin.class);
        /*
        Intent it = getIntent();
        String txt = it.getStringExtra("valorTexto");
        String pass = it.getStringExtra("valorSenha");
        intent.putExtra("valorTexto", txt);
        intent.putExtra("valorSenha", pass);
        tentando preencher novamente nome/senha com os dados do ultimo usuário ao deslogar, não consegui ;-;
        */
        startActivity(intent);
        finish();
    }
    public void retornarSplash(View view){
        Intent intent = new Intent(this, SpashScreen.class);
        startActivity(intent);
        finish();
    }

    public  void todoOnClick(View view){
        //Intent intent = getIntent();
        //Intent intent =
        LinearLayout ll = findViewById(R.id.principalVerticalSV);
        ll.removeAllViews();
        todos.clear();
        exibeTodo();
        //startActivity(intent);
    }
    public  void commentOnClick(View view){
        LinearLayout ll = findViewById(R.id.principalVerticalSV);
        ll.removeAllViews();
        comments.clear();
        exibeComment();
    }

    private void exibeComment() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://jsonplaceholder.typicode.com/comments";
        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, url,
                null,
                this,
                this);
        queue.add(jsonArrayRequest2);
    }


    public void exibeTodo(){
        //Intent intent = new Intent(this, PaginaInicial.class);
        //startActivity(intent);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/todos";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null,
                this,
                this);
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String msg = error.getMessage();
        Toast.makeText(this.getApplicationContext(),"Deu erro "+msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {
        if(response.length() == 200){
            try {
                for (int i = 0; i < response.length(); i++){
                    JSONObject json = response.getJSONObject(i);
                    Todo obj = new Todo(json.getInt("userId"),
                        json.getInt("id"),
                        json.getString("title"),
                        json.getBoolean("completed"));
                    todos.add(obj);
                }
                LinearLayout ll = findViewById(R.id.principalVerticalSV);
                for (Todo obj1 : todos){
                    Button bt = new Button(this);
                    bt.setText(obj1.getTitle());
                    bt.setTag(obj1);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Button btn = (Button) v;
                            Todo todo = (Todo)btn.getTag();
                            Intent intent = new Intent(getApplicationContext(), DetalheTodoActivity.class);
                            intent.putExtra("objTodo", todo);
                            Toast.makeText(v.getContext(), todo.getId()+" - "+todo.getTitle(), Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }
                    });
                    ll.addView(bt);
                }
            } catch (JSONException e) {
                Log.e("erro", e.getMessage());
                e.printStackTrace();
            }
        }else {
            try {
                for (int i = 0; i < response.length(); i++){
                    JSONObject json = response.getJSONObject(i);
                    Comment obj = new Comment(json.getInt("postId"),
                            json.getInt("id"),
                            json.getString("name"),
                            json.getString("email"),
                            json.getString("body"));
                    comments.add(obj);
                }
                LinearLayout ll = findViewById(R.id.principalVerticalSV);
                for (Comment obj1 : comments){
                    Button bt = new Button(this);
                    bt.setText(obj1.getBody());
                    bt.setTag(obj1);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Button btn = (Button) v;
                            Comment comment = (Comment)btn.getTag();
                            Intent intent = new Intent(getApplicationContext(), DetalheTodoActivity.class);
                            intent.putExtra("objTodo", comment);
                            Toast.makeText(v.getContext(), comment.getId()+" - "+comment.getBody(), Toast.LENGTH_LONG).show();
                            startActivity(intent);
                        }
                    });
                    ll.addView(bt);
                }
            } catch (JSONException e) {
                Log.e("erro", e.getMessage());
                e.printStackTrace();
            }
        }
    }
}