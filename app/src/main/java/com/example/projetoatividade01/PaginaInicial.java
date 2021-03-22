package com.example.projetoatividade01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PaginaInicial extends AppCompatActivity {

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

    }

    public void retornarLogin(View view){
        Intent intent = new Intent(this, TelaLogin.class);
        //Intent it = getIntent();
        //String txt = it.getStringExtra("valorTexto");
        //String pass = it.getStringExtra("valorSenha");
        //intent.putExtra("valorTexto", txt);
        //intent.putExtra("valorSenha", pass);
        //tentando preencher novamente nome/senha com os dados do ultimo usuário ao deslogar, não consegui ;-;
        startActivity(intent);
        finish();
    }
    public void retornarSplash(View view){
        Intent intent = new Intent(this, SpashScreen.class);
        startActivity(intent);
        finish();
    }
}