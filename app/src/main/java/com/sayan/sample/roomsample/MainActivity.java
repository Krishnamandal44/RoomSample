package com.sayan.sample.roomsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sayan.sample.roomsample.databaseclasses.AppDatabase;
import com.sayan.sample.roomsample.databaseclasses.tables.User;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private static void populateWithTestData(final AppDatabase db) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                User user =new User();
                user.setFirstName("Titir");
                user.setLastName("Mukherjee");
                user.setAge(25);
                db.userDao().insertAll(user);
            }
        }) .start();
    }

    public void add(View view) {
        AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
        populateWithTestData(appDatabase);
    }

    public void get(View view) {
        final AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
        getAllUsers(appDatabase);
    }

    void getAllUsers(final AppDatabase appDatabase){
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.userDao().getAll();
            }
        }) .start();
    }

    void getUserByName(final AppDatabase appDatabase, final String firstName, final String lastName){
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.userDao().findByName(firstName, lastName).getAge();
            }
        }) .start();
    }

    void updateFirstName(final AppDatabase appDatabase, final String firstName, final String oldFirstName){
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.userDao().updateFirstName(firstName, oldFirstName);
            }
        }) .start();
    }

    public void update(View view) {
        final AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
        updateFirstName(appDatabase, "Ajay", "Sayan");
    }
}
