package com.example.projetoatividade01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetoatividade01.adapter.GenericAdapter;
import com.example.projetoatividade01.model.Todo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Teste extends AppCompatActivity
        implements Response.Listener<JSONArray>,
        Response.ErrorListener{

    String btnTag = "";
    List<String> lista = new ArrayList<>();
    List<Todo> todos2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        clearLists();
        encheLista();


        for (String s : lista){
            btnTag = s;
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://jsonplaceholder.typicode.com/" + btnTag;

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    this, this);

            queue.add(jsonArrayRequest);
        }

    }

    public void clearLists(){
        lista.clear();
        todos2.clear();
    }
    public void encheLista(){
        lista.add("todos");
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
                GenericAdapter adapter = new GenericAdapter(todos2, R.layout.layout_todo) {
                };
                rvTodo.setAdapter(adapter);

            } catch (JSONException e) {
                Log.e("erro", e.getMessage());
                e.printStackTrace();
            }
                break;
        }
    }
}