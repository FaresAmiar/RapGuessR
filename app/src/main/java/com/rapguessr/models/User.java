package com.rapguessr.models;

import java.util.ArrayList;

public class User {

    String pseudo;
    int score;
    ArrayList<String> rappeurs;

    public User(){}


    public User(String pseudo, int score, ArrayList<String> rappeurs) {
        this.pseudo = pseudo;
        this.score = score;
        this.rappeurs = rappeurs;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<String> getRappeurs() {
        return rappeurs;
    }
}
