package com.hutech.authentication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hutech.authentication.databinding.ActivityDashboardBinding;
import com.hutech.utils.Sharedpreference;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



      binding.userLogout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(DashboardActivity.this);
              alertDialogBuilder.setMessage("Are you sure want to logout?");
              alertDialogBuilder.setCancelable(false);
              alertDialogBuilder.setPositiveButton("yes",
                      new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface arg0, int arg1) {
                              Sharedpreference.ClearPreference(getApplicationContext());
                              Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                              Sharedpreference.storeBooleanValue(DashboardActivity.this,"User_Login",false);
                              intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                              startActivity(intent);
                          }
                      });
              alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.dismiss();
                  }
              });
              AlertDialog alertDialog = alertDialogBuilder.create();
              alertDialog.show();
          }
      });

    }

    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(this);
        alertDialog.setTitle("Confirm Exit..!!!");
        alertDialog.setMessage("Are you sure want to exit");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        android.app.AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();
    }

}