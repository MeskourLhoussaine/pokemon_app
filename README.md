# Application  Android  (consomation des API Pkémon)
Il s'agit d'une application Android qui permet aux utilisateurs de parcourir une liste de Pokémon récupérés depuis Pokemon API
https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home

## How to used
Premièrement il faut installer Android Studio sur votre  machin\
1/ lancer Android Studio.\
2/ Cliquez sur "Ouvrir un projet Android Studio existant".\
3/ Accédez au dossier du projet importer.\
4/ Attendez que le projet soit généré ou bien les  installe les packages que j'ai utlisé.\
5/ Connectez votre appareil Android à votre machine (assurez-vous que le débogage USB est activé), ou bien instaler un  Emulateur  dans Android studio.\
6/ Cliquez sur le bouton "Exécuter" dans Android Studio et sélectionnez votre appareil connecté comme cible de déploiement.\
7/ Attendez que l'application s'installe et s'exécute sur votre appareil.\
     Au lancement de l'application, une liste de Pokémons s'affichera. L'utilisateur peut faire défiler la liste pour voir plus de Pokémons.\
Si l'utilisateur tape sur un Pokémon, un nouvel écran s'ouvrira affichant plus d'informations sur le Pokémon sélectionné.\

## Les Outile utiliser

-Android Studio - L'IDE utilisé pour créer l'application.\
-Retrofit - Client HTTP utilisé pour effectuer des requêtes API.\
-Gson - Utilisé pour analyser les données JSON.\
-PokeAPI - API utilisée pour récupérer les données Pokémon.\
-Glide - Framework de chargement d'images pour Android (load imag).
-Composants matériels - Activez un flux de travail de développement fiable pour créer des applications Android belles et fonctionnelles.\
-RecyclerView est le ViewGroup qui contient les vues correspondant à vos données.\
### Classe Pokemon.java
```java
package com.example.myapp.models;

public class Pokemon {
    private int number;
    private String name;
    private String url;
//Getters et Seters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlPartes =url.split("/");
        return Integer.parseInt(urlPartes[urlPartes .length-1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
```
###Clase RetrofitConnection.java
```java 
package com.example.myapp.connection;

import com.example.myapp.models.PokemonInfo;
import com.example.myapp.models.PokemonRespuesta;
import com.example.myapp.pokeapi.PokeaService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {
    private static Retrofit retrofit;

    // Constructeur privé de la classe RetrofitConnection
    private RetrofitConnection(){
        // Crée un nouvel objet Retrofit en utilisant le Builder
        // avec l'URL de base de l'API, le convertisseur GsonConverterFactory
        // et l'enregistre dans la variable statique retrofit
        retrofit=new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Méthode privée pour obtenir l'objet Retrofit
    private static Retrofit getRetrofit(){
        // Vérifie si retrofit est null, et si oui, crée une nouvelle instance
        // de RetrofitConnection (en appelant le constructeur privé)
        // et enregistre cette instance dans la variable statique retrofit
        if(Objects.isNull(retrofit)) new RetrofitConnection();
        return retrofit;
    }

    // Méthode privée pour obtenir l'objet PokeaService
    private static PokeaService getPokeaService(){
        // Appelle la méthode getRetrofit() pour obtenir l'objet Retrofit,
        // puis utilise la méthode create() pour créer et retourner un objet PokeaService
        return getRetrofit().create(PokeaService.class);
    }

    // Méthode publique pour obtenir un objet Call<PokemonRespuesta> pour la liste des pokémons
    public static Call<PokemonRespuesta> getPokemonRespuestaCall(int limit, int offset){
        // Appelle la méthode getPokeaService() pour obtenir l'objet PokeaService,
        // puis appelle la méthode obtenerListaPokemon() de cet objet en passant les paramètres limit et offset,
        // et retourne l'objet Call<PokemonRespuesta> résultant
        return getPokeaService().obtenerListaPokemon(20,offset);
    }

    // Méthode publique pour obtenir un objet Call<PokemonInfo> pour les informations d'un pokémon
    public static Call<PokemonInfo> getPokemonInfoCall(int id){
        // Appelle la méthode getPokeaService() pour obtenir l'objet PokeaService,
        // puis appelle la méthode getPokemonInfoCall() de cet objet en passant le paramètre id,
        // et retourne l'objet Call<PokemonInfo> résultant
        return getPokeaService().getPokemonInfoCall(id);
    }

}
```
###classe 
```java
package com.example.myapp.pokeapi;

import com.example.myapp.models.PokemonInfo;
import com.example.myapp.models.PokemonRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeaService {
    // Déclaration de l'annotation GET pour la méthode obtenerListaPokemon()
    // avec le chemin "pokemon" pour l'URL de l'API
    // et deux paramètres de requête "limit" et "offset"
    // qui seront substitués dans l'URL finale
    // et un objet Call<PokemonRespuesta> comme type de retour de la méthode
    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset")  int offset);

    // Déclaration de l'annotation GET pour la méthode getPokemonInfoCall()
    // avec le chemin "pokemon/{id}" pour l'URL de l'API
    // et un chemin de substitution "{id}" pour le paramètre id de la méthode
    // qui sera substitué dans l'URL finale
    // et un objet Call<PokemonInfo> comme type de retour de la méthode
    @GET("pokemon/{id}")
    Call<PokemonInfo> getPokemonInfoCall(@Path("id") int id);
}
```
### Class 
```java
package com.example.myapp;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.connection.RetrofitConnection;
import com.example.myapp.models.PokemonInfo;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonActivity extends AppCompatActivity {
    private Intent intent;
    private TextView textView;
    private ImageView imageView;
    private ProgressBar progressBarExp;
    //****
    private ProgressBar progressBarindex;
    private ProgressBar progressBarlevel;
    //level_learned_at
    private ProgressBar progressBarheight;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        // Création d'une instance de ProgressBar avec l'id R.id.progressExp
        this.progressBarExp = findViewById(R.id.progressExp); 
      // Création d'une instance de ProgressBar avec l'id R.id.progressIndex
        this.progressBarindex=findViewById(R.id.progressIndex); 
        // Création d'une instance de ProgressBar avec l'id R.id.progressLevel
        this.progressBarlevel=findViewById(R.id.progressLevel); 
        // Création d'une instance de ProgressBar avec l'id R.id.progressheih
        this.progressBarheight=findViewById(R.id.progressheih); 
        // Récupération de l'intent qui a démarré cette activité
        this.intent = getIntent(); 
        // Récupération de la valeur de l'extra "id" de l'intent et conversion en entier
        int id = Integer.parseInt(intent.getStringExtra("id")); 
        // Création d'une instance de TextView avec l'id R.id.textName
        this.textView = findViewById(R.id.textName); 
         // Création d'une instance de ImageView avec l'id R.id.imagePok
        this.imageView = findViewById(R.id.imagePok);
        
        // Définition du texte de textView avec la valeur de l'extra "name" de l'intent
        textView.setText(intent.getStringExtra("name")); 
         // Chargement d'une image dans imageView à partir de l'URL spécifiée dans l'extra "imagePok" de l'intent
        Picasso.get().load(intent.getStringExtra("imagePok")).into(imageView);

        RetrofitConnection.getPokemonInfoCall(id).enqueue(new Callback<PokemonInfo>() { 
        // Appel asynchrone à l'API avec Retrofit pour obtenir les informations sur un Pokémon
            @Override
            public void onResponse(Call<PokemonInfo> call, Response<PokemonInfo> response) {
             // Vérification si la réponse de l'API n'est pas réussie
                if(!response.isSuccessful()){
                // Affichage d'un message d'erreur dans les logs avec le code d'erreur de la réponse
                    Log.i("POKEMON", "ERROR" + response.message()); 
                    return;
                }

                progressBarExp.setMax(200); // Définition de la valeur maximale de progressBarExp à 200
                ObjectAnimator.ofInt(progressBarExp, "progress", response.body().getExp()).setDuration(1000).start(); 
                // Animation de la progression de progressBarExp jusqu'à la valeur d'expérience du Pokémon obtenue dans la réponse de l'API

                progressBarindex.setMax(200); // Définition de la valeur maximale de progressBarindex à 200
                ObjectAnimator.ofInt(progressBarindex, "progress", response.body().getIndex()).setDuration(1000).

```

## Aplication interface

<div align="center"> 
  <img src="screenshoots/1.jpeg" width="300"> 
     &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="screenshoots/2.jpeg" width="300">  
</div> 










