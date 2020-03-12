package com.rapguessr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChoixRapperActivity extends AppCompatActivity {

    private List<String> rappeurs;
    private EditText etRappeur;
    private RadioGroup radioGroup;
    private int cptRappeurs;
    private RadioButton radFacile, radDifficile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_rapper);

        rappeurs = new ArrayList<>();
        etRappeur = findViewById(R.id.txtRappeur);
        radioGroup = findViewById(R.id.rdGroup);
        radFacile = findViewById(R.id.radFacile);
        radDifficile = findViewById(R.id.radDifficile);

        Button btnRappeur = findViewById(R.id.btnRappeur);
        Button btnConfirmer = findViewById(R.id.btnDemarrer);

        cptRappeurs = 0;

        btnRappeur.setOnClickListener( new View.OnClickListener() {
            @Override public void onClick(View view) {
                btnRappeurListener();
            }
        }
        );

        btnConfirmer.setOnClickListener( new View.OnClickListener() {
            @Override public void onClick(View view) {
                demarrerJeu();
            }
        }
        );
    }

    private void demarrerJeu() {
        if(cptRappeurs <= 5 && cptRappeurs >=1) {
            if(radFacile.isChecked()) {
                Intent i = new Intent(this, JeuFacileActivity.class);
                i.putStringArrayListExtra("Rappeurs", (ArrayList<String>) rappeurs);
                startActivity(i);
            }
            else {
                Intent i = new Intent(this, JeuDifficileActivity.class);
                i.putStringArrayListExtra("Rappeurs", (ArrayList<String>) rappeurs);
                startActivity(i);
            }
        }
        else
            Toast.makeText(getApplicationContext(),"Il faut sélectionner 1 rappeur minimum",Toast.LENGTH_SHORT);
    }

    private void btnRappeurListener() {
            if (cptRappeurs < 5)
                rappeurs.add(etRappeur.getText().toString());
            else
                Toast.makeText(getApplicationContext(),"Vous avez déja ajouté 5 rappeurs",Toast.LENGTH_SHORT);
    }

}
