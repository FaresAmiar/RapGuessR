package com.rapguessr;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class FinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin);

        Intent i = getIntent();

        TextView txtFinal = findViewById(R.id.txtFinal);

        String finale = i.getStringExtra("pseudo") + " ," + " voici votre score : " +i.getStringExtra("score");
        finale += ". Félicitations !";
        txtFinal.setText(finale);

    }

}
