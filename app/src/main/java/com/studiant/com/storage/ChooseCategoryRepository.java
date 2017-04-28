package com.studiant.com.storage;

import com.studiant.com.domain.repository.CategoryRepository;

/**
 * Created by dmilicic on 1/29/16.
 */
public class ChooseCategoryRepository implements CategoryRepository {

    String[] ListItem = {"Service à la personne", "Baby sitting", "Ménage", "Bricolage", "Autres"};

    @Override
    public String[] getListCategoryMessage() {
        return ListItem;
    }
}
