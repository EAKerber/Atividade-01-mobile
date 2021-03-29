package com.example.projetoatividade01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projetoatividade01.model.Album;
import com.example.projetoatividade01.model.Comment;
import com.example.projetoatividade01.model.Photo;
import com.example.projetoatividade01.model.Post;
import com.example.projetoatividade01.model.Todo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetalheTodoActivity extends AppCompatActivity {
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_todo);
        Intent intent = getIntent();
        Parcelable parcelable = intent.getParcelableExtra("objTodo");

        if(parcelable instanceof Todo){
            layoutTodo();
        }else if(parcelable instanceof Post){
            layoutPost();
        }else  if(parcelable instanceof Album){
            layoutAlbum();
        }else if(parcelable instanceof Comment){
            layoutComment();
        }else if(parcelable instanceof Photo){
            layoutPhoto();
        }
    }

    private void layoutPhoto() {
        setContentView(R.layout.layout_photo);
        Intent intent = getIntent();
        Photo photo = intent.getParcelableExtra("objTodo");
        bindPhoto(photo);
        loadImg();
    }

    private void bindPhoto(Photo obj) {
        getSupportActionBar().setTitle("Photos - "+"ID: " + obj.getId()+"     "+"UserID: "+obj.getAlbumId());
        TextView tv = findViewById(R.id.photoLayout_Id);
        tv.setText("ID: " + obj.getId()+"");
        tv = findViewById(R.id.photoLayout_AlbumId);
        tv.setText("AlbumID: "+obj.getAlbumId());
        tv = findViewById(R.id.photoLayout_Title);
        tv.setText("Título: "+obj.getTitle());
        tv = findViewById(R.id.photoLayout_ThumbnailUrl);
        tv.setText("Thumbnail Url: "+obj.getThumbnailUrl());
    }

    private void layoutComment() {
        setContentView(R.layout.layout_comment);
        Intent intent = getIntent();
        Comment comment = intent.getParcelableExtra("objTodo");
        bindComment(comment);
    }

    private void bindComment(Comment obj) {
        getSupportActionBar().setTitle("Comments - "+"ID: " + obj.getId()+"     "+"UserID: "+obj.getPostId());
        TextView tv = findViewById(R.id.commentLayout_Id);
        tv.setText("ID: " + obj.getId()+"");
        tv = findViewById(R.id.commentLayout_PostId);
        tv.setText("PostID: "+obj.getPostId());
        tv = findViewById(R.id.commentLayout_Name);
        tv.setText("Nome: "+obj.getName());
        tv = findViewById(R.id.commentLayout_Email);
        tv.setText("Email: "+obj.getEmail());
        tv = findViewById(R.id.commentLayout_Body);
        tv.setText("''"+obj.getBody()+"''");
    }

    private void layoutAlbum() {
        setContentView(R.layout.layout_album);
        Intent intent = getIntent();
        Album album = intent.getParcelableExtra("objTodo");
        bindAlbum(album);
    }

    private void bindAlbum(Album obj) {
        getSupportActionBar().setTitle("Albums - "+"ID: " + obj.getId()+"     "+"UserID: "+obj.getUserId());
        TextView tv = findViewById(R.id.albumLayout_Id);
        tv.setText("ID: " + obj.getId()+"");
        tv = findViewById(R.id.albumLayout_UserId);
        tv.setText("UserID: "+obj.getUserId());
        tv = findViewById(R.id.albumLayout_Title);
        tv.setText("''"+obj.getTitle()+"''");
    }


    private void layoutPost() {
        setContentView(R.layout.layout_post);
        Intent intent = getIntent();
        Post post = intent.getParcelableExtra("objTodo");
        bindPost(post);
    }

    private void bindPost(Post obj) {
        getSupportActionBar().setTitle("Posts - "+"ID: " + obj.getId()+"     "+"UserID: "+obj.getUserId());
        TextView tv = findViewById(R.id.postLayout_Id);
        tv.setText("ID: " + obj.getId()+"");
        tv = findViewById(R.id.postLayout_UserId);
        tv.setText("UserID: "+obj.getUserId());
        tv = findViewById(R.id.postLayout_Title);
        tv.setText("''"+obj.getTitle()+"''");
        tv = findViewById(R.id.postLayout_Body);
        tv.setText(obj.getBody()+"");
    }

    public  void layoutTodo(){
        setContentView(R.layout.layout_todo);
        Intent intent = getIntent();
        Todo todo = intent.getParcelableExtra("objTodo");
        bindTodo(todo);
    }

    private void bindTodo(Todo obj){
        getSupportActionBar().setTitle("Todos - "+"ID: " + obj.getId()+"     "+"UserID: "+obj.getUserId());
        TextView tv = findViewById(R.id.todoLayout_Id);
        tv.setText("ID: " + obj.getId()+"");
        tv = findViewById(R.id.todoLayout_UserId);
        tv.setText("UserID: "+obj.getUserId());
        tv = findViewById(R.id.todoLayout_Title);
        tv.setText("''"+obj.getTitle()+"''");
        CheckBox cb = findViewById(R.id.cbTodoLayout_Completed);
        cb.setChecked(obj.isCompleted());
    }

    public void loadImg(){
        new Thread(){
            public void run(){
                Bitmap img = null;
                try {
                    URL url = new URL("https://www.bambui.ifmg.edu.br/portal_padrao_joomla/joomla/images/phocagallery/galeria2/thumbs/phoca_thumb_l_image03_grd.png");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    img = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final Bitmap imgAux = img;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ImageView iv = findViewById(R.id.imPhotoLayout_ThumbnailUrl);
                        iv.setImageBitmap(imgAux);
                        //LinearLayout ll =(LinearLayout) findViewById(R.id.llPhoto);
                        //ll.addView(iv);
                    }
                });
            }
    }.start();
    }
}