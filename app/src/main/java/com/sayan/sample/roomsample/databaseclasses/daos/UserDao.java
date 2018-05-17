package com.sayan.sample.roomsample.databaseclasses.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sayan.sample.roomsample.databaseclasses.tables.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user where first_name LIKE  :firstName AND last_name LIKE :lastName")
    User findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from user")
    int countUsers();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);

    @Query("UPDATE user SET first_name = :firstName WHERE first_name = :oldFirstName")
    void updateFirstName(String firstName, String oldFirstName);
}
