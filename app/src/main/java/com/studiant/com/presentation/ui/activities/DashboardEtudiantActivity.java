package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ramotion.foldingcell.FoldingCell;
import com.studiant.com.R;


public class DashboardEtudiantActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_etudiant);

        final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);
        fc.initialize(30, 1000, Color.DKGRAY, 2);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
            }
        });

    }
}
