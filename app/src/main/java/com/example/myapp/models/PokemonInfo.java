package com.example.myapp.models;

import com.google.gson.annotations.SerializedName;

public class PokemonInfo {
    @SerializedName("base_experience")
    private int exp;

    public PokemonInfo(){  }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        exp = exp;
    }
}
