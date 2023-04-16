# Application  Android  (consomation des API Pkémon)
Il s'agit d'une application Android qui permet aux utilisateurs de parcourir une liste de Pokémon récupérés depuis Pokemon API
https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home

## How to used
Premièrement il faut installer Android Studio sur votre  machin
1/ lancer Android Studio. 
2/ Cliquez sur "Ouvrir un projet Android Studio existant"
3/ Accédez au dossier du projet importer.
4/ Attendez que le projet soit généré ou bien les  installe les packages que j'ai utlisé
5/ Connectez votre appareil Android à votre machine (assurez-vous que le débogage USB est activé), ou bien instaler un  Emulateur  dans Android studio.
6/ Cliquez sur le bouton "Exécuter" dans Android Studio et sélectionnez votre appareil connecté comme cible de déploiement.
7/ Attendez que l'application s'installe et s'exécute sur votre appareil.
     Au lancement de l'application, une liste de Pokémons s'affichera. L'utilisateur peut faire défiler la liste pour voir plus de Pokémons.
Si l'utilisateur tape sur un Pokémon, un nouvel écran s'ouvrira affichant plus d'informations sur le Pokémon sélectionné.

## Les Outile utiliser

-Android Studio - L'IDE utilisé pour créer l'application
-Retrofit - Client HTTP utilisé pour effectuer des requêtes API
-Gson - Utilisé pour analyser les données JSON
-PokeAPI - API utilisée pour récupérer les données Pokémon
-Glide - Framework de chargement d'images pour Android (load imag).
-Composants matériels - Activez un flux de travail de développement fiable pour créer des applications Android belles et fonctionnelles.
-RecyclerView est le ViewGroup qui contient les vues correspondant à vos données.

## Aplication interface

<div align="center"> 
  <img src="screenshoots/1.jpeg" width="300"> 
     &nbsp;&nbsp;&nbsp;&nbsp;
  <img src="screenshoots/2.jpeg" width="300">  
</div> 
### Classe Pokemon.java
```java
package com.example.myapp.models;

public class Pokemon {
    private int number;
    private String name;
    private String url;

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











