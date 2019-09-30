package com.example.grandpovar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private String email;
    private String password;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.menu_events:
                    loadFragment(MessagesFragment.newInstance());
                    return true;
                case R.id.menu_search:
                    loadFragment(MessagesFragment.newInstance());
                    return true;
                case R.id.menu_messages:
                    loadFragment(MessagesFragment.newInstance());
                    return true;
                case R.id.menu_profile:
                    loadFragment(MessagesFragment.newInstance());
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //проверка вошел ли пользователь


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            //нет пользователя открытие LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);

        loadFragment(MessagesFragment.newInstance());
    }
    private void loadFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentView, fragment);
        ft.commit();

    }
}
