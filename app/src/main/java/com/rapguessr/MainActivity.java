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

        GLA gla = new GLA("x1lo0H_7JTDLVitYtAz2YmsMlbU_jJhGgNpgSk_ru0d5-uuw18P27o4iUIyyX3fZ","r75U8Fje6k0DJkRw0giN4VAODU-cnRuLGJaJYlL8uSk226TnqznqK_xlaTDtAn5x");
        String lyrics = gla.search("Ninho");
        TextView tv = findViewById(R.id.textView);

        tv.setText(lyrics);
    }
}
