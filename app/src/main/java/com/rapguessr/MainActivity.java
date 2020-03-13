package com.rapguessr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("message");
    //EditText editTextPseudo;
    //Button btnJouer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJouer = (Button) findViewById(R.id.btnJouer);
        editTextPseudo = (EditText) findViewById(R.id.editTextPseudo);

        final String pseudo = editTextPseudo.getText().toString().trim();

        btnJouer.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!TextUtils.isEmpty(pseudo)){
                            Intent i = new Intent(MainActivity.this,ChoixRapperActivity.class);
                            i.putExtra("pseudo",pseudo);
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(),"Veuillez entrer un pseudo",Toast.LENGTH_LONG).show();
                        }

                    }
        });

    }


}
