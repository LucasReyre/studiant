package com.studiant.com.storage;

import com.studiant.com.domain.repository.CategoryRepository;

/**
 * Created by dmilicic on 1/29/16.
 */
public class ConnexionRepository implements CategoryRepository {

    String[] ListItem = {"Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};

    @Override
    public String[] getListCategoryMessage() {
        String[] ListItem = {"Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};
        return ListItem;
    }
}
