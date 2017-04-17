package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.interfaces.MainPresenter;
import com.studiant.com.presentation.presenters.interfaces.MainPresenter.View;
import com.studiant.com.presentation.presenters.impl.MainPresenterImpl;
import com.studiant.com.presentation.ui.components.MCarouselView;
import com.studiant.com.storage.WelcomeMessageRepository;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements View {

    @Bind(R.id.welcome_textview) TextView mWelcomeTextView;

    @Bind(R.id.carouselView) MCarouselView carouselView;

    @Bind(R.id.btn_particulier) Button btn_particulier;
    @Bind(R.id.btn_etudiant) Button btn_etudiant;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        this.carouselView.setImage();
        // create a presenter for this view
        mPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new WelcomeMessageRepository()
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
    }


    /**
     * Goes to the user list screen.
     */
    @OnClick(R.id.btn_particulier)
    void navigateToParticulierForm() {
        Intent intentToLaunch = new Intent(this, ChooseActivity.class);
        this.startActivity(intentToLaunch);

    }

    @Override
    public void showProgress() {
        mWelcomeTextView.setText("Retrieving...");
    }

    @Override
    public void hideProgress() {
        Toast.makeText(this, "Retrieved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        mWelcomeTextView.setText(message);
    }

    @Override
    public void displayWelcomeMessage(String msg) {
        mWelcomeTextView.setText(msg);
    }
}
