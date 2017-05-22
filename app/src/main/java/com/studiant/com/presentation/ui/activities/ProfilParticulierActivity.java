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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.CATEGORIE_ID_JOB;
import static com.studiant.com.storage.Constants.INTENT_USER;
import static com.studiant.com.storage.Constants.STATUS_CONNEXION_FACEBOOK;
import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;
import static com.studiant.com.storage.Constants.STATUS_PARTICULIER;

public class ProfilParticulierActivity extends Activity implements ProfilParticulierPresenter.View {

    @BindView(R.id.editTextFirstNameParticulier)
    EditText firstNameEditText;

    @BindView(R.id.editTextLastNameParticulier)
    EditText lastNameEditText;

    @BindView(R.id.editTextEmailParticulier)
    AutoCompleteTextView emailEditText;

    @BindView(R.id.buttonValidateParticulier)
    Button validateButton;

    private ProfilParticulierPresenter mPresenter;

    User user = null;

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
        if (user != null){
            user.setFirstName(firstNameEditText.getText().toString());
            user.setLastName(lastNameEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setTypeUser(STATUS_PARTICULIER);
            user.setTypeConnexion(STATUS_CONNEXION_FACEBOOK);
            mPresenter.insertProfile(user);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
    }
    @Override
    public void onProfileRetrieve(User mUser) {
        user = mUser;
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        emailEditText.setText(user.getEmail());
        //Log.d("onProfileRetrieve", "my profil  :" + user.getLastName() + " - "+user.getBirthday() + " - "+user.getAge());

    }


    @Override
    public void onUserInsert(User user) {
        Intent intent = new Intent(this, AddJobActivity.class);
        intent.putExtra(CATEGORIE_ID_JOB, getIntent().getIntExtra(CATEGORIE_ID_JOB, 0));
        intent.putExtra(INTENT_USER, user);
        this.startActivity(intent);
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
