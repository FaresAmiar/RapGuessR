package com.rapguessr;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JeuDifficileActivity extends AppCompatActivity {

    private EditText txtReponse;
    private Intent i;
    private String reponse, bonneReponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_difficile);

        i = getIntent();

        List<String> rappeurs = i.getStringArrayListExtra("Rappeurs");

        //rappeurs random dans la liste
        Collections.shuffle(rappeurs);
        String rappeur = rappeurs.get(0), image = "", titre = "", lyrics = "";

        //appeler nodejs

        Button btnConfirmer = findViewById(R.id.btnReponse);
        btnConfirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nbActivite = i.getIntExtra("nbActivite",0);
                nbActivite++;
                Intent next = null;
                if(nbActivite < 10) {
                    next = new Intent(JeuDifficileActivity.this, JeuDifficileActivity.class);
                }
                else {
                    next = new Intent(JeuDifficileActivity.this,FinActivity.class);
                }
                int score = i.getIntExtra("score",0);
                score += verifReponse() ? 10 : 0 ;
                startActivity(next);
            }
        });
    }

    private void reponses(String lyrics) {


        //Ligne des paroles
        String paroles [] = lyrics.split("\n");

        //Phrase de la réponse
        for(String phrase : paroles) {
            String[] motsPhrase = phrase.split(" ");

            for(String mot : motsPhrase) {
                //mot réponse si il a plus de 4 lettres
                //sinon on retente un autre mot dans la phrase
                if (reponse.length() > 4) {
                    reponse = mot;
                }
            }
        }
    }


    private boolean verifReponse() {
        return reponse.equals(bonneReponse);
    }

    private void lancerJoker() {

    }
}
