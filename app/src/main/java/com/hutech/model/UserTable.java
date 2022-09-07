package com.hutech.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usertable")
public class UserTable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userName;
    private String password;

   public UserTable(int id,String userName,String password){
    this.id = id;
    this.userName = userName;
    this.password = password;
   }


    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
