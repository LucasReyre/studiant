package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.squareup.picasso.Picasso;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.model.User;
import com.studiant.com.presentation.presenters.impl.ProfilParticulierPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;
import com.studiant.com.presentation.ui.components.AdjustKeyboard;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.INTENT_USER;
import static com.studiant.com.storage.Constants.STATUS_CONNEXION_FACEBOOK;
import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;

public class ProfilEtudiantActivity extends Activity implements ProfilParticulierPresenter.View{

    @BindView(R.id.editTextFirstNameParticulier)
    EditText firstNameEditText;

    @BindView(R.id.editTextLastNameParticulier)
    EditText lastNameEditText;

    @BindView(R.id.editTextEmailParticulier)
    AutoCompleteTextView emailEditText;

    @BindView(R.id.editTextDescription)
    EditText descriptionEditText;

    @BindView(R.id.editTextDiplome)
    EditText diplomeEditText;

    @BindView(R.id.switchPermis)
    Switch permisSwitch;

    @BindView(R.id.buttonValidateEtudiant)
    Button validateButton;

    @BindView(R.id.imageViewProfilPicture)
    ImageView profilPictureImageView;

    User user = null;

    private ProfilParticulierPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_etudiant);
        ButterKnife.bind(this);
        AdjustKeyboard.assistActivity(this);

        mPresenter = new ProfilParticulierPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl() {
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @OnClick(R.id.buttonValidateEtudiant)
    void onClickValidate() {

        if (user != null){
            user.setDescription(descriptionEditText.getText().toString());
            user.setDiplome(diplomeEditText.getText().toString());
            user.setPermis(permisSwitch.isChecked());
            user.setTypeUser(STATUS_ETUDIANT);
            user.setTypeConnexion(STATUS_CONNEXION_FACEBOOK);
            mPresenter.insertProfile(user);
        }

    }

    @Override
    public void onProfileRetrieve(User mUser) {
        user = mUser;
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        emailEditText.setText(user.getEmail());
        Picasso.with(this).load(user.getProfilePicture()).into(profilPictureImageView);
    }

    @Override
    public void onUserInsert(User user) {
        Intent intent = new Intent(this, DashboardEtudiantActivity.class);
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
