package com.example.grandpovar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/*
 * Активити позволят выолнить вход или регистрацию
 * тестовый логин example@gmail.com
 * тестовый пароль example@gmail.com
 *
 * */
public class LoginActivity extends AppCompatActivity {


    private String mLogin;
    private String mPassword;
    private FirebaseAuth mAuth;
    FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else {
            setContentView(R.layout.activity_login);
            Button singIn = (Button) findViewById(R.id.singIn); // кнопка авторизации
            EditText login = (EditText) findViewById(R.id.login), password = (EditText) findViewById(R.id.password);
            login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onFocusChange(View view, boolean b) {

                    EditText editText = (EditText) view;

                    if (b) {
                        editText.setBackground(getResources().getDrawable(R.drawable.input_selected));
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else {
                        editText.setBackground(getResources().getDrawable(R.drawable.input_default));
                        editText.setTextColor(Color.parseColor("#FFFFFF"));
                    }

                }
            });
            password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onFocusChange(View view, boolean b) {

                    EditText editText = (EditText) view;

                    if (b) {
                        editText.setBackground(getResources().getDrawable(R.drawable.input_selected));
                        editText.setTextColor(Color.parseColor("#000000"));
                    } else {
                        editText.setBackground(getResources().getDrawable(R.drawable.input_default));
                        editText.setTextColor(Color.parseColor("#FFFFFF"));
                    }

                }
            });
            singIn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    mLogin = ((EditText) findViewById(R.id.login)).getText().toString();
                    mPassword = ((EditText) findViewById(R.id.password)).getText().toString();
                    if ("".equals(mLogin) || "".equals(mPassword)) {
                        Toast.makeText(getApplicationContext(), "Одно из полей не заполненно. Пожалуйста, заполните все поля и повторите отправку", Toast.LENGTH_LONG).show();
                    } else {
                        singInUser();
                    }

                }
            });

            Button singUp = (Button) findViewById(R.id.singUp); // кнопка регистрации
            singUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void singInUser() {
        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // sing in

                } else {
                    //sing out
                }
            }
        };

        mAuth.signInWithEmailAndPassword(mLogin, mPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("PARAM", 1);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Авторизация провалена. Убедитесь, что почта корректна и уникальна. Тестовый логин и пароль example@gmail.com", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
