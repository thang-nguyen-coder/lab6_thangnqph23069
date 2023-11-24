package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.lab6.bai1.Bai1Activity;
import com.example.lab6.bai2.Bai2Activity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = findViewById(R.id.btnbai1);
        Button btn2 = findViewById(R.id.btnbai2);
        btn1.setOnClickListener(v -> {
            startActivity(new Intent(this, Bai1Activity.class));
        });
        btn2.setOnClickListener(v -> {
            startActivity(new Intent(this, Bai2Activity.class));
        });
    }
}