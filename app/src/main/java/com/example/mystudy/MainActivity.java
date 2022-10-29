package com.example.mystudy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    EditText searchField;
    ProgressBar progressBar;
    ApiInterface apiInterface;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        searchField = (EditText) findViewById(R.id.search);
        progressBar = findViewById(R.id.progress_loader);

        recyclerView = findViewById(R.id.searchesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://serpapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(),CourseActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(), Courses.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return true;
            }
        });

        searchField.addTextChangedListener(
                new TextWatcher() {
                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    private Timer timer = new Timer();
                    private final long DELAY = 500; // milliseconds

                    @Override
                    public void afterTextChanged(final Editable s) {
                        timer.cancel();
                        timer = new Timer();
                        timer.schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        setLoader(View.VISIBLE);
                                        getSearches(s.toString());
                                    }
                                },
                                DELAY
                        );
                    }
                }
        );

    }

    private void setLoader(final int value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(value);
            }
        });
    }

    public void getSearches(String txt){
        Call<Search> call = apiInterface.fetchSearches(txt);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                setLoader(View.GONE);
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<SearchData> searchList = response.body().organic_results;
                SearchAdapter searchAdapter = new SearchAdapter(MainActivity.this , searchList);
                recyclerView.setAdapter(searchAdapter);
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                setLoader(View.GONE);

                Toast.makeText(MainActivity.this, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}