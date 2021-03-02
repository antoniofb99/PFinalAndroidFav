package com.example.pfinalfav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRegistro extends AppCompatActivity implements View.OnClickListener {

    EditText editCorreo, editContraseña;
    Button btnRegisto, login;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registro);
        instancias();
        acciones();
    }




    private void acciones() {
        btnRegisto.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    private void instancias() {
        firebaseAuth = FirebaseAuth.getInstance();
        editContraseña = findViewById(R.id.pass);
        editCorreo = findViewById(R.id.correo);
        login = findViewById(R.id.btn_login);
        btnRegisto = findViewById(R.id.btn_registro);
        firebaseUser = firebaseAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        Log.v("usuarios", "shklhfash");
        switch (v.getId()) {
            case R.id.btn_registro:
                firebaseAuth.createUserWithEmailAndPassword(editCorreo.getText().toString(), editContraseña.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginRegistro.this, "Creado", Toast.LENGTH_SHORT).show();
                            Log.v("usuarios", "el usuario se ha creado correctamente ");
                            FirebaseUser usuario = firebaseAuth.getCurrentUser();
                            Log.v("usuarios", usuario.getUid());
                        } else {
                            Log.v("usuarios", "el usuario no  se ha creado correctamente ");
                        }
                    }
                });
                break;

            case R.id.btn_login:
                firebaseAuth.signInWithEmailAndPassword(editCorreo.getText().toString(), editContraseña.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser usuario = firebaseAuth.getCurrentUser();
                            Intent intent = new Intent(LoginRegistro.this, MainActivity.class);
                            intent.putExtra("id", usuario.getUid());
                            startActivity(intent);
                            Log.v("usuario", "login correcto");


                        } else {
                            Log.v("usuario", "Login Incorrecto");
                        }

                    }
                });



                break;
        }

    }
}
