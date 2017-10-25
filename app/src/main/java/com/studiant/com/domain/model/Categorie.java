package com.studiant.com.domain.model;

import com.studiant.com.R;

/**
 * Created by lucas on 22/10/2017.
 */

public class Categorie {

    String[] Categories = {"Cours particuliers",
            "Informatique",
            "Jardinage",
            "Baby-sitting",
            "Déménagement",
            "Bricolage",
            "Evènementiel",
            "Nettoyage/Repassage",
            "Animaux",
            "Mécanique/Réparation",
            "Transport/Livraison",
            "Beauté/Bien être",
            "Administration",
            "Autres"};


    int imageRessource;


    public Categorie() {
    }

    public Categorie(String categorie) {

        if (categorie == null){
            this.setImageRessource(R.drawable.autres);
            return;
        }

        System.out.println("categorie : "+categorie);
        switch (categorie){
            case "Cours particuliers":
                this.setImageRessource(R.drawable.cours);
                break;
            case "Informatique":
                this.setImageRessource(R.drawable.informatique);
                break;
            case "Jardinage":
                this.setImageRessource(R.drawable.jardinage);
                break;
            case "Baby-sitting":
                this.setImageRessource(R.drawable.baby);
                break;
            case "Déménagement":
                this.setImageRessource(R.drawable.demenagement);
                break;
            case "Bricolage":
                this.setImageRessource(R.drawable.bricomecarepa);
                break;
            case "Evènementiel":
                this.setImageRessource(R.drawable.evenementiel);
                break;
            case "Nettoyage/Repassage":
                this.setImageRessource(R.drawable.nettoyage);
                break;
            case "Mécanique/Réparation":
                this.setImageRessource(R.drawable.bricomecarepa);
                break;
            case "Animaux":
                this.setImageRessource(R.drawable.animaux);
                break;
            case "Transport/Livraison":
                this.setImageRessource(R.drawable.transport);
                break;
            case "Beauté/Bien être":
                this.setImageRessource(R.drawable.bienetre);
                break;
            case "Administration":
                this.setImageRessource(R.drawable.administration);
                break;
            case "Autres":
                this.setImageRessource(R.drawable.autres);
                break;
            default:
                this.setImageRessource(R.drawable.autres);
                break;
        }

    }

    public String[] getCategories() {
        return Categories;
    }

    public void setCategories(String[] categories) {
        Categories = categories;
    }

    public int getImageRessource() {
        return imageRessource;
    }

    public void setImageRessource(int imageRessource) {
        this.imageRessource = imageRessource;
    }
}
