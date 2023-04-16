package com.example.myapp.models;

import com.google.gson.annotations.SerializedName;

public class PokemonInfo {
    @SerializedName("base_experience")
    private int exp;
    @SerializedName("base_experience")
    private int index;
    @SerializedName("base_experience")
    private int level;
    @SerializedName("base_experience")
    private int  height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public PokemonInfo(){  }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        exp = exp;
    }
}
