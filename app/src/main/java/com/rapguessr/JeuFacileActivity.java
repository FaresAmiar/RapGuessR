package com.rapguessr;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class JeuFacileActivity extends AppCompatActivity {

    private RadioButton radRep1, radRep2, radRep3, radRep4;
    private RadioGroup radGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_facile);

        String [] reponses = new String[4];
        Integer bonneReponse = 0;

        radRep1 = findViewById(R.id.radRep1);
        radRep2 = findViewById(R.id.radRep2);
        radRep3 = findViewById(R.id.radRep3);
        radRep4 = findViewById(R.id.radRep4);
        radGroup = findViewById(R.id.radRepGroup);

        Intent i = getIntent();

        List<String> rappeurs = i.getStringArrayListExtra("Rappeurs");

        //rappeurs random dans la liste
        Collections.shuffle(rappeurs);
        String rappeur = rappeurs.get(0), image = "", titre = "", lyrics = "";

        //appeler nodejs

        reponses(lyrics,reponses,bonneReponse);



    }

    private void reponses(String lyrics, String [] reponses, Integer bonneReponse) {

        Random rd = new Random();

        List<String> paroles = new ArrayList<>();

        

    }

    private boolean verifReponse(Integer bonneReponse) {
        int idRep = radGroup.getCheckedRadioButtonId();
        return idRep == bonneReponse;
    }

}
