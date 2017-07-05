package com.studiant.com.presentation.ui.activities.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.interfaces.MainPresenter;
import com.studiant.com.presentation.presenters.interfaces.MainPresenter.View;
import com.studiant.com.presentation.presenters.impl.MainPresenterImpl;
import com.studiant.com.presentation.ui.activities.particulier.ChooseActivity;
import com.studiant.com.storage.WelcomeMessageRepository;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;
import static com.studiant.com.storage.Constants.STATUS_PARTICULIER;
import static com.studiant.com.storage.Constants.STATUS_USER;

public class MainActivity extends Activity implements View {

    @BindView(R.id.welcome_textview) TextView mWelcomeTextView;

    @BindView(R.id.btn_particulier) Button btn_particulier;
    @BindView(R.id.btn_etudiant) Button btn_etudiant;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        //mPresenter.resume();
    }


    @OnClick(R.id.btn_particulier)
    void navigateToParticulierForm() {
        Intent intentToLaunch = new Intent(this, ChooseActivity.class);
        intentToLaunch.putExtra(STATUS_USER, STATUS_PARTICULIER);
        this.startActivity(intentToLaunch);
    }

    @OnClick(R.id.btn_etudiant)
    void navigateToConnexionActivity() {
        Intent intentToLaunch = new Intent(this, ConnexionActivity.class);
        intentToLaunch.putExtra(STATUS_USER, STATUS_ETUDIANT);
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
