package com.rapguessr;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rapguessr.models.User;

import java.util.ArrayList;

public class ChoixRapperActivity extends AppCompatActivity {

    private ArrayList<String> rappeurs;
    private EditText etRappeur;
    private Spinner rappeurSpinner;
    private RadioGroup radioGroup;
    private int cptRappeurs;
    private RadioButton radFacile, radDifficile;
    private String pseudo;

    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_rapper);

        databaseUser = FirebaseDatabase.getInstance().getReference("users");

        Bundle extras = getIntent().getExtras();
        pseudo =extras.getString("pseudo");

        if(databaseUser.child(pseudo) == null)


        rappeurs = new ArrayList<>();
        etRappeur = findViewById(R.id.txtRappeur);
        rappeurSpinner = findViewById(R.id.choixRappeur);
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
            int score = 0;
            int nbActivite = 1;
            int idRad = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
            RadioButton radSelected = findViewById(idRad);
            if(radFacile.isChecked()) {
                etRappeur.setText("facile");
                Intent i = new Intent(ChoixRapperActivity.this, JeuFacileActivity.class);
                i.putExtra("score",score);
                i.putStringArrayListExtra("Rappeurs", (ArrayList<String>) rappeurs);
                startActivity(i);
            }
            else if(radDifficile.isChecked()){
                etRappeur.setText("difficile");
                Intent i = new Intent(ChoixRapperActivity.this, JeuDifficileActivity.class);
                i.putExtra("nbActivite",nbActivite);
                i.putExtra("score",score);
                i.putStringArrayListExtra("Rappeurs", (ArrayList<String>) rappeurs);
                startActivity(i);
            }
        }
        else
            Toast.makeText(getApplicationContext(),"Il faut sélectionner 1 rappeur minimum",Toast.LENGTH_SHORT);
    }

    private void btnRappeurListener() {
            //if (cptRappeurs < 5)
                rappeurs.add(etRappeur.getText().toString());
                etRappeur.setText("");
                cptRappeurs++;
            //else
             //   Toast.makeText(getApplicationContext(),"Vous avez déja ajouté 5 rappeurs",Toast.LENGTH_SHORT);


            // String rappeurSaisie = etRappeur.getText().toString();
            // String rappeurChoix = rappeurSpinner.getSelectedItem().toString();
            // if (cptRappeurs < 5) {
            //     cptRappeurs++;
            //     if (TextUtils.isEmpty(rappeurSaisie) && !TextUtils.isEmpty(rappeurChoix))
            //         rappeurs.add(rappeurChoix);
            //     if (!TextUtils.isEmpty(rappeurSaisie) && TextUtils.isEmpty(rappeurChoix))
            //         rappeurs.add(rappeurSaisie);
            //     else
            //         Toast.makeText(getApplicationContext(),"Veuillez selectionner un rappeur",Toast.LENGTH_SHORT);
            // }
            // else
            //     Toast.makeText(getApplicationContext(),"Vous avez déja ajouté 5 rappeurs",Toast.LENGTH_SHORT);
    }

    private void addUser(){
        User user = new User(pseudo,0,rappeurs);
        databaseUser.child(pseudo).setValue(user);
    }

}
