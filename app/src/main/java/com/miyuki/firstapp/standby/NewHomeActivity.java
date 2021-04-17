package com.miyuki.firstapp.standby;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.miyuki.firstapp.standby.ui.home.HomeFragment;

public class NewHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_home);
        Button delete = (Button) findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "It has been deleted", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(NewHomeActivity.this, HomeFragment.class);
                startActivity(intent);
            }
        });
    }
}