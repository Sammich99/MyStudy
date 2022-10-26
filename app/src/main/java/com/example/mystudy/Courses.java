package com.example.mystudy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;




public class Courses extends AppCompatActivity implements MyAdapter.OnClickListener {
    RecyclerView recyclerView;
    ArrayList<String> coursetitle, coursecode;
    DBHelper DB;
    MyAdapter adapter;
    BottomNavigationView bottomNavigationView;
    Button  addacoursebutton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courselist);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.courses);
        DB = new DBHelper(this);
        coursetitle = new ArrayList<>();
        coursecode = new ArrayList<>();
        recyclerView =  findViewById(R.id.courselistrecyclerview);
        adapter = new MyAdapter(this, coursetitle, coursecode, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), CourseActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.courses:
                        return true;
                }
                return true;
            }
        });

        addacoursebutton = (Button) findViewById(R.id.addacoursebutton);
        addacoursebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_a_course();
            }
        });





    }



    private void displaydata()
    {
        Cursor cursor = DB.getdata();
        if(cursor.getCount()==0)
        {

        }
        else
        {
            while(cursor.moveToNext())
            {
                coursetitle.add(cursor.getString(0));
                coursecode.add(cursor.getString(1));
            }
        }




    }

    public void add_a_course(){
        Intent intent = new Intent(this, AddCourse.class);
        startActivity(intent);
    }



    @Override
    public void onClick(int position) {
        coursetitle.get(position);
        coursecode.get(position);
        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);
    }
}
