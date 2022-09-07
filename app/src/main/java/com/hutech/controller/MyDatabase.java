package com.hutech.controller;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.hutech.model.UserTable;


@Database(entities = {UserTable.class},version = 2)
public abstract class MyDatabase extends RoomDatabase{
   public abstract UserDao getDao();

}
