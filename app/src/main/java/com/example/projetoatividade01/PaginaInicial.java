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
import com.example.projetoatividade01.model.Album;
import com.example.projetoatividade01.model.Comment;
import com.example.projetoatividade01.model.Post;
import com.example.projetoatividade01.model.Todo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaginaInicial extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener{

    String btnTag = "";
    List<Todo> todos = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();
    List<Post> posts = new ArrayList<>();
    List<Album> albums = new ArrayList<>();
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
        btn3.setOnClickListener(this::OnClick);
        Button btn4 = (Button)findViewById(R.id.buttonComment);
        btn4.setOnClickListener(this::OnClick);
        Button btn5 = (Button)findViewById(R.id.buttonPost);
        btn5.setOnClickListener(this::OnClick);
        Button btn6 = (Button)findViewById(R.id.buttonAlbum);
        btn6.setOnClickListener(this::OnClick);

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
    public  void OnClick(View view){
        LinearLayout ll = findViewById(R.id.principalVerticalSV);
        ll.removeAllViews();
        comments.clear();
        todos.clear();
        posts.clear();
        albums.clear();

        RequestQueue queue = Volley.newRequestQueue(this);

        Button btn = (Button)view;
        btnTag = btn.getTag()+"";

        String url = "https://jsonplaceholder.typicode.com/"+btn.getTag();
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
            switch (btnTag){
                case "todos":

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
                    break;

                case "comments":

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
                    break;

                case "posts":

                    try {
                        for (int i = 0; i < response.length(); i++){
                            JSONObject json = response.getJSONObject(i);
                            Post obj = new Post(json.getInt("userId"),
                                    json.getInt("id"),
                                    json.getString("title"),
                                    json.getString("body"));
                            posts.add(obj);
                        }
                        LinearLayout ll = findViewById(R.id.principalVerticalSV);
                        for (Post obj1 : posts){
                            Button bt = new Button(this);
                            bt.setText(obj1.getTitle());
                            bt.setTag(obj1);
                            bt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Button btn = (Button) v;
                                    Post post = (Post) btn.getTag();
                                    Intent intent = new Intent(getApplicationContext(), DetalheTodoActivity.class);
                                    intent.putExtra("objTodo", post);
                                    Toast.makeText(v.getContext(), post.getId()+" - "+post.getTitle(), Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                }
                            });
                            ll.addView(bt);
                        }
                    } catch (JSONException e) {
                        Log.e("erro", e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case "albums":

                    try {
                        for (int i = 0; i < response.length(); i++){
                            JSONObject json = response.getJSONObject(i);
                            Album obj = new Album(json.getInt("userId"),
                                    json.getInt("id"),
                                    json.getString("title"));
                            albums.add(obj);
                        }
                        LinearLayout ll = findViewById(R.id.principalVerticalSV);
                        for (Album obj1 : albums){
                            Button bt = new Button(this);
                            bt.setText(obj1.getTitle());
                            bt.setTag(obj1);
                            bt.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Button btn = (Button) v;
                                    Album album = (Album) btn.getTag();
                                    Intent intent = new Intent(getApplicationContext(), DetalheTodoActivity.class);
                                    intent.putExtra("objTodo", album);
                                    Toast.makeText(v.getContext(), album.getId()+" - "+album.getTitle(), Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                }
                            });
                            ll.addView(bt);
                        }
                    } catch (JSONException e) {
                        Log.e("erro", e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    break;

                case "3":
                    break;
            }
        }
    }
