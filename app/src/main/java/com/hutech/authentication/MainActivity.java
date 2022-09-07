package com.hutech.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.hutech.authentication.databinding.ActivityMainBinding;
import com.hutech.controller.MyDatabase;
import com.hutech.controller.UserDao;
import com.hutech.model.UserTable;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyDatabase myDb;
    UserDao userDao;
    public static boolean isAllowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDb = Room.databaseBuilder(this,MyDatabase.class,"usertable")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();

        userDao = myDb.getDao();

        binding.userEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
              String username = editable.toString();
                if (userDao.is_taken(username)){
                    isAllowed = false;
                    Toast.makeText(MainActivity.this, "User Name Already Taken", Toast.LENGTH_SHORT).show();
                } else {
                    isAllowed = true;
                }
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (!validmatchemaill()){
                    binding.userEmail.setError("Enter valid email");
                } else if (binding.userPassword.getText().toString().length() ==0){
                    binding.userPassword.setError("Enter password");
                }else if (isAllowed){
                    UserTable userTable = new UserTable(0,binding.userEmail.getText().toString(),binding.userPassword.getText().toString());
                    userDao.insertUser(userTable);
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                } else {
                    Toast.makeText(MainActivity.this, "User Name Already Taken", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.existLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

    }

    public boolean validmatchemaill(){
        String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String input = binding.userEmail.getText().toString().trim();
        if(input.length()==0){
            binding.userEmail.setError("Enter Email Address");
            return false;
        }
        if (!input.matches(pattern)){
            binding.userEmail.setError("Please Enter Valid Mail");
            return false;
        }
        return true;
    }

}