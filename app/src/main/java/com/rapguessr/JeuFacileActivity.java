package com.rapguessr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class JeuFacileActivity extends AppCompatActivity {

    private RadioButton radRep1, radRep2, radRep3, radRep4;
    private RadioGroup radGroup;
    private Intent i;
    private String [] reponses;
    private Integer bonneReponse;
    private ArrayList<String> rappeurs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_facile);

        reponses = new String[4];
        bonneReponse = 0;

        radRep1 = findViewById(R.id.radRep1);
        radRep2 = findViewById(R.id.radRep2);
        radRep3 = findViewById(R.id.radRep3);
        radRep4 = findViewById(R.id.radRep4);
        radGroup = findViewById(R.id.radRepGroup);


        i = getIntent();

        rappeurs = i.getStringArrayListExtra("Rappeurs");

        //rappeurs random dans la liste
        Collections.shuffle(rappeurs);
        String rappeur = rappeurs.get(0), image = "", titre = "", lyrics = "";
        rappeurs.remove(rappeur);


        //appeler nodejs
        String result [] = null;
        try {
            result = new RecupererJSON().execute(rappeur).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String joker = result[0];
        lyrics = result[1];

        reponses(lyrics,reponses,bonneReponse);

        radRep1.setText(reponses[0]);
        radRep2.setText(reponses[1]);
        radRep3.setText(reponses[2]);
        radRep4.setText(reponses[3]);



        Button btnReponse = findViewById(R.id.btnReponse);

        btnReponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean gagne = verifReponse(bonneReponse);
                int score = i.getIntExtra("score",0);
                score += gagne ? 5 : 0;
                int nbActivite = i.getIntExtra("nbActivite",0);
                ++nbActivite;
                Intent next = null;
                if(nbActivite < 10 && !rappeurs.isEmpty()) {
                    next = new Intent(JeuFacileActivity.this, JeuFacileActivity.class);
                }
                else {
                    next = new Intent(JeuFacileActivity.this,FinActivity.class);
                }
                next.putStringArrayListExtra("Rappeurs",rappeurs);
                startActivity(next);
            }
        });
    }

    private void reponses(String lyrics, String [] reponses, Integer bonneReponse) {

        //Random pour générer
        Random rd = new Random();

        //Ligne des paroles
        String paroles [] = lyrics.split("\n");

        //numero de la phrase ou il y a la reponse
        int phraseRep = rd.nextInt(paroles.length);

        //liste des index des numeros de reponses
        List<Integer> numReponses = Arrays.asList(0,1,2,3);

        Collections.shuffle(numReponses);

        Integer idRep = numReponses.get(0);

        bonneReponse = idRep;

        //Phrase de la réponse
        String phraseReponse = paroles[phraseRep];
        paroles[phraseRep] = "";
        String [] motsPhrase = phraseReponse.split(" ");

        //mot réponse si il a plus de 4 lettres
        String reponse = motsPhrase[rd.nextInt(motsPhrase.length)];

        //sinon on retente un autre mot dans la phrase
        while (reponse.length() < 4)
            reponse = motsPhrase[rd.nextInt(motsPhrase.length)];

        //ajout de la réponse dans le tableau des réponses
        reponses[bonneReponse] = reponse;

        //on enleve l'index réponse de la liste
        numReponses.remove(bonneReponse);

        //boucle sur le reste des lignes
        for(String ligne: paroles) {
            if(ligne.charAt(0) == '[')
                continue;
            while(!numReponses.isEmpty()) {
                //mots de la phrase
                String[] mots = ligne.split(" ");
                //boucle sur les mots
                for (String mot : mots) {
                    if (mot.length() > 4) {
                        Collections.shuffle(numReponses);
                        idRep = numReponses.get(0);
                        reponses[idRep] = mot;
                        numReponses.remove(idRep);
                        break;
                    }
                }
            }
        }
    }

    private boolean verifReponse(Integer bonneReponse) {
        int idRep = radGroup.indexOfChild(findViewById(radGroup.getCheckedRadioButtonId()));
        return idRep == bonneReponse;
    }

    private class RecupererJSON extends AsyncTask<String,Void,String[]> {

        @Override
        protected String[] doInBackground(String... strings) {
            String url = "http://localhost:8000/getRapper/" + strings[0];
            String joker = "", lyrics = "";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String joker = response.getString("joker");
                        String lyrics = response.getString("lyrics");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Veuillez relancer l'app",Toast.LENGTH_SHORT);
                }
            });
            return new String[]{joker, lyrics};
        }
    }

}
