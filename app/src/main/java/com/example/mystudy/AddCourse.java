package com.example.mystudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCourse extends AppCompatActivity {
    EditText coursetitle, coursecode;
    ImageButton imageButton;
    Button buttonaddcourse;
    CoursesDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);

        coursetitle = findViewById(R.id.coursetitle);
        coursecode = findViewById(R.id.coursecode);
        imageButton = findViewById(R.id.imageView);
        buttonaddcourse = (Button) findViewById(R.id.buttondone);

        DB = new CoursesDatabase(this);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(AddCourse.this, CourseList.class));
            }
        });

        buttonaddcourse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddCourse.this, CourseList.class));

                String coursetitleTxt = coursetitle.getText().toString();
                String coursecodeTxt = coursecode.getText().toString();

                Boolean checkinsertdata = DB.insertcoursedata(coursetitleTxt, coursecodeTxt);
                if(checkinsertdata==true)
                {
                    Toast.makeText(AddCourse.this, "Course Created", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AddCourse.this, "You Haven't Created Any Course", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
