package com.hutech.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hutech.authentication.databinding.ActivityLoginBinding;
import com.hutech.controller.MyDatabase;
import com.hutech.controller.UserDao;
import com.hutech.utils.Sharedpreference;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    MyDatabase myDb;
    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDb = Room.databaseBuilder(this, MyDatabase.class,"usertable")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();

        userDao = myDb.getDao();

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();
                if (!validmatchemaill()){
                    binding.loginEmail.setError("Please enter valid mail");
                } else if (binding.loginPassword.getText().toString().length() == 0){
                    binding.loginPassword.setError("Please enter password");
                } else if(userDao.login(userName,password)){
                    startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                    Sharedpreference.storeBooleanValue(LoginActivity.this,"User_Login",true);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid user name or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.newRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

    }

    public boolean validmatchemaill(){
        String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String input = binding.loginEmail.getText().toString().trim();
        if(input.length()==0){
            binding.loginEmail.setError("Enter Email Address");
            return false;
        }
        if (!input.matches(pattern)){
            binding.loginEmail.setError("Please Enter Valid Mail");
            return false;
        }
        return true;
    }
}