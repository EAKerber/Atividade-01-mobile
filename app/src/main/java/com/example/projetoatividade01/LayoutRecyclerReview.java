package com.example.projetoatividade01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetoatividade01.adapter.AlbumAdapter;
import com.example.projetoatividade01.adapter.CommentAdapter;
import com.example.projetoatividade01.adapter.GenericAdapter;
import com.example.projetoatividade01.adapter.PostAdapter;
import com.example.projetoatividade01.model.Album;
import com.example.projetoatividade01.model.Comment;
import com.example.projetoatividade01.model.Post;
import com.example.projetoatividade01.model.Todo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LayoutRecyclerReview extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener{

    int num = 0;
    String btnTag;
    List<String> lista = new ArrayList<>();
    List<Todo> todos2 = new ArrayList<>();
    List<Post> posts2 = new ArrayList<>();
    List<Comment> comments2 = new ArrayList<>();
    List<Album> albums2 = new ArrayList<>();

    TextView tv_RR;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_recycler_review);
        getSupportActionBar().setTitle("RecyclerView");
        clearLists();
        encheLista();
        fazRequest();
    }

    public void fazRequest(){

        int i = 0;
        for (String s : lista){
            i++;
        }

        if(i > num){
            btnTag = lista.get(num);
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://jsonplaceholder.typicode.com/" + btnTag;

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                this, this);

            queue.add(jsonArrayRequest);
            num++;
        }
    }

    public void clearLists(){
        lista.clear();
        todos2.clear();
        posts2.clear();
        comments2.clear();
        albums2.clear();
    }
    public void encheLista(){
        lista.add(0,"todos");
        lista.add(1,"posts");
        lista.add(2,"comments");
        lista.add(3,"albums");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String msg = error.getMessage();
        Toast.makeText(this.getApplicationContext(),"deu erro: "+msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONArray response) {
        switch (btnTag){
            case "todos":

                tv_RR = findViewById(R.id.textView_RR_Todo);
                tv_RR.setText(btnTag);

            try {
                for (int i = 0; i < response.length(); i++){
                    JSONObject json = response.getJSONObject(i);
                    Todo obj = new Todo(json.getInt("userId"),
                            json.getInt("id"),
                            json.getString("title"),
                            json.getBoolean("completed"));
                    todos2.add(obj);
                }

                RecyclerView rvTodo = findViewById(R.id.todo_RecyclerReview);

                LinearLayoutManager llhm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                rvTodo.setLayoutManager(llhm);
                GenericAdapter adapter = new GenericAdapter(todos2, R.layout.layout_todo_card) {
                };
                rvTodo.setAdapter(adapter);

                fazRequest();

            } catch (JSONException e) {
                Log.e("erro", e.getMessage());
                e.printStackTrace();
            }
                break;

            case "posts":

                tv_RR = findViewById(R.id.textView_RR_Post);
                tv_RR.setText(btnTag);

                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject json = response.getJSONObject(i);
                        Post obj = new Post(json.getInt("userId"),
                                json.getInt("id"),
                                json.getString("title"),
                                json.getString("body"));
                        posts2.add(obj);
                    }

                    RecyclerView rvPost = findViewById(R.id.post_RecyclerReview);

                    LinearLayoutManager llhm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                    rvPost.setLayoutManager(llhm);
                    PostAdapter adapter = new PostAdapter(posts2, R.layout.layout_post_card){
                    };
                    rvPost.setAdapter(adapter);

                    fazRequest();

                } catch (JSONException e) {
                    Log.e("erro", e.getMessage());
                    e.printStackTrace();
                }
                    break;

            case "comments":

                tv_RR = findViewById(R.id.textView_RR_Comment);
                tv_RR.setText(btnTag);

                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject json = response.getJSONObject(i);
                        Comment obj = new Comment(json.getInt("postId"),
                                json.getInt("id"),
                                json.getString("name"),
                                json.getString("email"),
                                json.getString("body"));
                        comments2.add(obj);
                    }

                    RecyclerView rvComment = findViewById(R.id.comment_RecyclerReview);

                    LinearLayoutManager llhm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                    rvComment.setLayoutManager(llhm);
                    CommentAdapter adapter = new CommentAdapter(comments2, R.layout.layout_comment_card){
                    };
                    rvComment.setAdapter(adapter);

                    fazRequest();

                } catch (JSONException e) {
                    Log.e("erro", e.getMessage());
                    e.printStackTrace();
                }
                    break;

            case "albums":

                tv_RR = findViewById(R.id.textView_RR_Album);
                tv_RR.setText(btnTag);

                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject json = response.getJSONObject(i);
                        Album obj = new Album(json.getInt("userId"),
                                json.getInt("id"),
                                json.getString("title"));
                        albums2.add(obj);
                    }

                    RecyclerView rvAlbum = findViewById(R.id.album_RecyclerReview);

                    LinearLayoutManager llhm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
                    rvAlbum.setLayoutManager(llhm);
                    AlbumAdapter adapter = new AlbumAdapter(albums2, R.layout.layout_album_card){
                    };
                    rvAlbum.setAdapter(adapter);

                    fazRequest();

                } catch (JSONException e) {
                    Log.e("erro", e.getMessage());
                    e.printStackTrace();
                }
                    break;

        }
    }
}