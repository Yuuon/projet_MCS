package com.himenomiya.www.projetmcs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class MainActivity extends AppCompatActivity {
    public String message;
    public static String EXTRA_MESSAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);

    // Example of a call to a native method

    }
    public void recAvance(View view) {
        Intent intent = new Intent(this, recording.class);
        String message = "avance";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void recRecule(View view) {
        Intent intent = new Intent(this, recording.class);
        String message = "recule";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void recDroite(View view) {
        Intent intent = new Intent(this, recording.class);
        String message = "droite";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void recGauche(View view) {
        Intent intent = new Intent(this, recording.class);
        String message = "gauche";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void recTrouneDroite(View view) {
        Intent intent = new Intent(this, recording.class);
        String message = "tourne_droite";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void recTrouneGauche(View view) {
        Intent intent = new Intent(this, recording.class);
        String message = "troune_gauche";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void recFaisFlip(View view) {
        Intent intent = new Intent(this, recording.class);
        String message = "fais_flip";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void recArreteToi(View view) {
        Intent intent = new Intent(this, recording.class);
        String message = "arrete_toi";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void recEtatUrgence(View view) {
        Intent intent = new Intent(this, recording.class);
        String message = "etat_urgence";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goTest(View view) {
        Intent intent = new Intent(this, TestCommand.class);
        startActivity(intent);
    }
}
