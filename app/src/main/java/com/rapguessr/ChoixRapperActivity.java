package com.rapguessr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ChoixRapperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_rapper);

        EditText etRappeur = findViewById(R.id.txtRappeur);
        RadioGroup radioGroup = findViewById(R.id.rdGroup);

        Button btnRappeur = findViewById(R.id.btnRappeur);
        Button btnConfirmer = findViewById(R.id.btnDemarrer);

        int cptRappeur = 0;

        btnRappeur.setOnClickListener( new View.OnClickListener() {
            @Override public void onClick(View view) {
                btnRappeurListener();
            }
        }
        );

        btnConfirmer.setOnClickListener( new View.OnClickListener() {
            @Override public void onClick(View view) {

                Intent i = new Intent();
                startActivity(i);
            }
        }
        );

    }

    private void demarrerJeu() {

    }

    private void btnRappeurListener() {

    }

}
