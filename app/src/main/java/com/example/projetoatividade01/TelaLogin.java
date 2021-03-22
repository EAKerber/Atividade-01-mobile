package com.example.projetoatividade01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        getSupportActionBar().setTitle("Login");
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(this::efetuarLogin);


    }
    public void efetuarLogin(View view){
        Intent intent = new Intent(this, PaginaInicial.class);
        EditText editNome = (EditText)findViewById(R.id.editTextTextPersonName2);
        EditText editPass = (EditText)findViewById(R.id.editTextTextPassword);
        if(editNome.getText().toString().equals(editPass.getText().toString())) {
            if(editNome.getText().toString().length() > 0){
                intent.putExtra("valorTexto", editNome.getText().toString());
                intent.putExtra("valorSenha", editPass.getText().toString());
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Nome e Senha devem ser preenchidos", Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this, "Nome e Senha devem ser iguais", Toast.LENGTH_LONG).show();
        }
    }
}

