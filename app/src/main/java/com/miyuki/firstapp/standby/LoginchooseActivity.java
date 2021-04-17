package com.miyuki.firstapp.standby;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginchooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginchoose);
        Button registration = (Button) findViewById(R.id.registration);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginchooseActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}