package com.rapguessr;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class JeuDifficileActivity extends AppCompatActivity {

    private EditText txtReponse;
    private Intent i;
    private String reponse, bonneReponse, rappeur, joker, lyrics;
    private ArrayList<String> rappeurs;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_difficile);

        i = getIntent();
        EditText txtReponse = findViewById(R.id.txtReponse);

        rappeurs = i.getStringArrayListExtra("Rappeurs");
        //rappeurs random dans la liste
        Collections.shuffle(rappeurs);
        rappeur = rappeurs.get(0);

        new RecupererJSON().execute(rappeur);

        Button btnReponse = findViewById(R.id.btnReponse);
        btnReponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnReponseListener();
            }
        });
    }

    private void btnReponseListener() {
        int nbActivite = i.getIntExtra("nbActivite",0);
        nbActivite++;
        Intent next = null;
        if(nbActivite < 10 && !rappeurs.isEmpty()) {
            next = new Intent(JeuDifficileActivity.this, JeuDifficileActivity.class);
        }
        else {
            next = new Intent(JeuDifficileActivity.this,FinActivity.class);
        }
        int score = i.getIntExtra("score",0);
        score += verifReponse() ? 10 : 0 ;
        next.putStringArrayListExtra("Rappeurs",rappeurs);
        startActivity(next);
    }

    private void reponses() {
        //Ligne des paroles
        String paroles [] = lyrics.split("\n");

        //Phrase de la réponse
        for(String phrase : paroles) {
            if(phrase.charAt(0) == '[')
                continue;
                String[] motsPhrase = phrase.split(" ");

                for (String mot : motsPhrase) {
                    //mot réponse si il a plus de 4 lettres
                    //sinon on retente un autre mot dans la phrase
                    if (reponse.length() > 4) {
                        reponse = mot;
                        lyrics = phrase;
                    }
                }
        }
    }


    private boolean verifReponse() {
        return reponse.equals(bonneReponse);
    }

    private void lancerJoker() {
        txtReponse.setText(joker);
    }


    private class RecupererJSON extends AsyncTask<String,Void,String[]>{
        ProgressBar progressBar;
        @Override
        protected String[] doInBackground(String... strings) {
            String url = "http://localhost:8000/getRapper/" + strings[0];

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        joker = response.getString("joker");
                        lyrics = response.getString("lyrics");
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

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setDialog(true);
        }

        @Override
        protected void onPostExecute(String [] result) {


            joker = ""; lyrics = "";
            rappeurs.remove(rappeur);

            joker = result[0];
            lyrics = result[1];

            reponses();

            txtReponse.setText(lyrics);
            setDialog(false);
        }
    }

    private void setDialog(boolean show){
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        //View view = getLayoutInflater().inflate(R.layout.progress);
        builder.setView(R.layout.progress);
        Dialog dialog = builder.create();
        if (show)dialog.show();
        else dialog.dismiss();
    }
}
