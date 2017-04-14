package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.studiant.com.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChooseActivity extends Activity  {

    @Bind(R.id.spinner_categorie)
    MaterialSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ButterKnife.bind(this);

        spinner.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // let's start welcome message retrieval when the app resumes
    }

}
