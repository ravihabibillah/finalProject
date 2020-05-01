package com.example.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.Model.LoginModel;
import com.example.finalproject.Presenter.LoginPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private TextView tvRegister;
    private FloatingActionButton fabLogin;
    private TextInputEditText tietPhone, tietPassword;
    private ProgressDialog loadingbar;
    private String parentDB = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadingbar = new ProgressDialog(this);

        tietPhone = findViewById(R.id.tiet_phone);
        tietPassword = findViewById(R.id.tiet_password);

        fabLogin = findViewById(R.id.fab_login);
        fabLogin.setOnClickListener(this);

        tvRegister = findViewById(R.id.tv_btn_register);
        tvRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_login: {
                loginUser();
            }
            break;

            case R.id.tv_btn_register: {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
            break;
        }
    }

    private void loginUser() {
        String phone = tietPhone.getText().toString().trim();
        String password = tietPassword.getText().toString().trim();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        } else {
            loadingbar.setTitle("Login");
            loadingbar.setMessage("Please wait...");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            allowAccount(phone, password);
        }

    }

    private void allowAccount(final String phone, final String password) {
        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDB).child(phone).exists()) {
                    LoginModel userData = dataSnapshot.child(parentDB).child(phone).getValue(LoginModel.class);

                    if (userData.getPhone().equals(phone) && userData.getPassword().equals(password)) {
                        Toast.makeText(Login.this, "Logged is successfully...", Toast.LENGTH_SHORT).show();
                        loadingbar.dismiss();

                        Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                        LoginPresenter.currentOnlineUser = userData;
                        startActivity(intent);
                    } else {
                        loadingbar.dismiss();
                        Toast.makeText(Login.this, "Login failed: password incorrect...", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    loadingbar.dismiss();
                    Toast.makeText(Login.this, "Account with " + phone + " number do not exist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
