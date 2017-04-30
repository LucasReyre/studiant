package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.impl.MainPresenterImpl;
import com.studiant.com.presentation.presenters.impl.ProfilParticulierPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;
import com.studiant.com.domain.model.User;
import com.studiant.com.storage.WelcomeMessageRepository;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfilParticulierActivity extends Activity implements ProfilParticulierPresenter.View {

    @Bind(R.id.editTextFirstNameParticulier)
    EditText firstNameEditText;

    @Bind(R.id.editTextLastNameParticulier)
    EditText lastNameEditText;

    @Bind(R.id.editTextEmailParticulier)
    AutoCompleteTextView emailEditText;

    @Bind(R.id.buttonValidateParticulier)
    Button validateButton;

    private ProfilParticulierPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_particulier);
        ButterKnife.bind(this);

        mPresenter = new ProfilParticulierPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl() {
                }
        );
    }

    @OnClick(R.id.buttonValidateParticulier)
    void navigateToAddJob() {
        Intent intentToLaunch = new Intent(this, AddJobActivity.class);
        this.startActivity(intentToLaunch);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
    }
    @Override
    public void onProfileRetrieve(User user) {
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        emailEditText.setText(user.getEmail());
        Log.d("onProfileRetrieve", "my profil  :" + user.getLastName());

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
