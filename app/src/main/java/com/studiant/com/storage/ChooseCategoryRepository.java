package com.studiant.com.storage;

import android.content.res.Resources;
import android.util.Log;

import com.studiant.com.R;
import com.studiant.com.domain.model.Categorie;
import com.studiant.com.domain.repository.CategoryRepository;


/**
 * Created by dmilicic on 1/29/16.
 */
public class ChooseCategoryRepository implements CategoryRepository {

    String[] ListItem = {"Cours particuliers",
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

    @Override
    public String[] getListCategoryMessage() {
        Categorie categorie = new Categorie();

        return categorie.getCategories();
    }
}
