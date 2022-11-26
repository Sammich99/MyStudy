package com.example.mystudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageView newtask;
    TaskAdapter taskAdapter;
    RecyclerView recyclerView;
    List<TaskModel> taskList = new ArrayList<>();

    TaskDatabase taskDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_activity);
        newtask = findViewById(R.id.createTask);
        recyclerView = (RecyclerView) findViewById(R.id.taskRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        taskDatabase = new TaskDatabase(this);

        fetchdata();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.task);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.task:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(), CourseList.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        newtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskActivity.this, CreateTask.class);
                startActivity(intent);
            }
        });

        taskAdapter = new TaskAdapter(this, TaskActivity.this, taskList);
        recyclerView.setAdapter(taskAdapter);


    }


    private void fetchdata() {
        Cursor cursor = taskDatabase.readAllData();

        if(cursor == null || cursor.getCount() == 0){
            Toast.makeText(this, "No data. ", Toast.LENGTH_SHORT).show();
        } else
        {
            while (cursor.moveToNext()) {
                taskList.add(new TaskModel(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));

            }
        }
    }
}




