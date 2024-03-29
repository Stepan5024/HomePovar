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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*
 * Активити выполняет регистрацию пользоввателя с помощью платформы firebase
 *
 *
 * */
public class SignUpActivity extends AppCompatActivity {

    private String mLogin;
    private FirebaseUser user;
    private String mPassword;
    private String mPasswordRepeat;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        user = FirebaseAuth.getInstance().getCurrentUser();

        EditText nick = (EditText) findViewById(R.id.nikname);
        EditText mlogin = ((EditText) findViewById(R.id.Login));
        EditText mpassword = ((EditText) findViewById(R.id.Password));
        EditText mpasswordRepeat = ((EditText) findViewById(R.id.RepeatPassword));

        mpasswordRepeat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        nick.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        mpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        mlogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        Button reg = (Button) findViewById(R.id.button4); // кнопка регистрации
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogin = ((EditText) findViewById(R.id.Login)).getText().toString();
                mPassword = ((EditText) findViewById(R.id.Password)).getText().toString();
                mPasswordRepeat = ((EditText) findViewById(R.id.RepeatPassword)).getText().toString();
                if ("".equals(mLogin) || "".equals(mPassword) || "".equals(mPasswordRepeat)) {
                    Toast.makeText(getApplicationContext(), "Одно из полей не заполненно. Пожалуйста, заполните все поля и повторите отправку", Toast.LENGTH_LONG).show();
                } else {
                    if (mPasswordRepeat.equals(mPassword)) {
                        addUser();
                    } else {
                        Toast.makeText(getApplicationContext(), "Пароли не совпадают, повторите попытку", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }

    private void addUser() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mLogin + "", mPassword + "").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            myRef.child("users").child("uyi").child("infoUser").child("name").setValue(mLogin);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Регистрация провалена. Убедитесь, что почта корректна и уникальна", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}



