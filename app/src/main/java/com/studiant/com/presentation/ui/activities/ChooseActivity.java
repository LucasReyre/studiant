package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Button;
import android.widget.Spinner;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.ChoosePresenterImpl;
import com.studiant.com.presentation.presenters.impl.MainPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ChoosePresenter;
import com.studiant.com.presentation.presenters.interfaces.MainPresenter;
import com.studiant.com.storage.ChooseCategoryRepository;
import com.studiant.com.storage.WelcomeMessageRepository;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;


public class ChooseActivity extends Activity implements ChoosePresenter.View {

    @Bind(R.id.spinner_categorie)
    MaterialSpinner spinner;

    @Bind(R.id.button_validate)
    Button btnValidate;

    private ChoosePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        ButterKnife.bind(this);

        mPresenter = new ChoosePresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ChooseCategoryRepository()
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
    }

    @Override
    public void displayListCategorie(String[] listItem) {


        spinner.setItems(listItem);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.button_validate)
    void onClickValidate() {
        Intent intentToLaunch = new Intent(this, ConnexionActivity.class);
        this.startActivity(intentToLaunch);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
