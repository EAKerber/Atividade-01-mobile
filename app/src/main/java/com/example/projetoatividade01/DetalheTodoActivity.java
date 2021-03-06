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

import com.example.projetoatividade01.model.users.Address;
import com.example.projetoatividade01.model.Album;
import com.example.projetoatividade01.model.Comment;
import com.example.projetoatividade01.model.users.Geo;
import com.example.projetoatividade01.model.Photo;
import com.example.projetoatividade01.model.Post;
import com.example.projetoatividade01.model.Todo;
import com.example.projetoatividade01.model.users.User;

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
        }else if(parcelable instanceof User){
            layoutUser();
        }


    }
    private void layoutUser() {
        setContentView(R.layout.layout_user);
        Intent intent = getIntent();
        User user = intent.getParcelableExtra("objTodo");
        bindUser(user);
    }

    private void bindUser(User obj) {
        getSupportActionBar().setTitle("Users - "+"ID: " + obj.getId());
        TextView tv = findViewById(R.id.userLayout_Id);
        tv.setText("ID: " + obj.getId()+"");
        tv = findViewById(R.id.userLayout_Name);
        tv.setText("Nome: "+obj.getName());
        tv = findViewById(R.id.userLayout_UserName);
        tv.setText("UserName: "+obj.getUserName());
        tv = findViewById(R.id.userLayout_Email);
        tv.setText("Email: "+obj.getEmail());
        tv = findViewById(R.id.userLayout_Phone);
        tv.setText("Telefone: "+obj.getPhone());
        tv = findViewById(R.id.userLayout_WebSite);
        tv.setText("WebSite: "+obj.getWebSite());
        tv = findViewById(R.id.userLayout_Lat);
        tv.setText("Latitude: "+obj.getAddress().getGeo().getLat());
        tv = findViewById(R.id.userLayout_Lng);
        tv.setText("Longitude: "+obj.getAddress().getGeo().getLng());
        tv = findViewById(R.id.userLayout_Street);
        tv.setText("Rua: "+obj.getAddress().getStreet());
        tv = findViewById(R.id.userLayout_Suite);
        tv.setText("Suite: "+obj.getAddress().getSuite());
        tv = findViewById(R.id.userLayout_City);
        tv.setText("Cidade: "+obj.getAddress().getCity());
        tv = findViewById(R.id.userLayout_ZipCode);
        tv.setText("ZipCode: "+obj.getAddress().getZipcode());
        tv = findViewById(R.id.userLayout_CpnName);
        tv.setText("Nome: "+obj.getCompany().getName());
        tv = findViewById(R.id.userLayout_CatchPhrase);
        tv.setText("''"+obj.getCompany().getCatchPhrase()+"''");
        tv = findViewById(R.id.userLayout_Bs);
        tv.setText("Bs: "+obj.getCompany().getBs());
    }

    private void layoutPhoto() {
        setContentView(R.layout.layout_photo);
        Intent intent = getIntent();
        Photo photo = intent.getParcelableExtra("objTodo");
        bindPhoto(photo);
        ImageView im = findViewById(R.id.imPhotoLayout_ThumbnailUrl);
        loadImg(photo.getThumbnailUrl(), im);
    }

    private void bindPhoto(Photo obj) {
        getSupportActionBar().setTitle("Photos - "+"ID: " + obj.getId()+"     "+"UserID: "+obj.getAlbumId());
        TextView tv = findViewById(R.id.photoLayout_Id);
        tv.setText("ID: " + obj.getId()+"");
        tv = findViewById(R.id.photoLayout_AlbumId);
        tv.setText("AlbumID: "+obj.getAlbumId());
        tv = findViewById(R.id.photoLayout_Title);
        tv.setText("T??tulo: "+obj.getTitle());
        tv = findViewById(R.id.photoLayout_ThumbnailUrl);
        tv.setText("Thumbnail Url: "+obj.getThumbnailUrl());
        tv = findViewById(R.id.photoLayout_Url);
        tv.setText("Url: "+obj.getUrl());
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

    public void loadImg(String urlPhoto, ImageView imageView){
        new Thread(){
            public void run(){
                Bitmap img = null;
                try {
                    URL url = new URL(urlPhoto);
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
                        ImageView iv = imageView;
                        iv.setImageBitmap(imgAux);
                        //LinearLayout ll =(LinearLayout) findViewById(R.id.llPhoto);
                        //ll.addView(iv);
                    }
                });
            }
    }.start();
    }
}