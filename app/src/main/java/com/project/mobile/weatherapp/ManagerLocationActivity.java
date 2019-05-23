package com.project.mobile.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ManagerLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_location);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_add_location: {
                Toast.makeText(this, "Search button selected", Toast.LENGTH_SHORT).show();
                Intent iAddLocation = new Intent(this, AddLocationActivity.class);
                startActivity(iAddLocation);
                break;
            }
            case R.id.text_cur_location: {
                Intent currentLocation = new Intent(this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("usingLocation", true);
                currentLocation.putExtra("CurrentLocation", bundle);
                startActivity(currentLocation);
            }
        }
    }
}
