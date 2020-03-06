package com.rapguessr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import core.GLA;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLA gla = new GLA();
        String lyrics = gla.search("Ninho");
        TextView tv = findViewById(R.id.textView);

        tv.setText(lyrics);
    }
}
