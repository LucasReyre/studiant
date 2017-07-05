package com.studiant.com.presentation.ui.activities.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.ConnexionPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ConnexionPresenter;
import com.studiant.com.presentation.ui.activities.etudiant.ProfilEtudiantActivity;
import com.studiant.com.presentation.ui.activities.particulier.ProfilParticulierActivity;
import com.studiant.com.storage.ConnexionRepository;
import com.studiant.com.threading.MainThreadImpl;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.CATEGORIE_ID_JOB;
import static com.studiant.com.storage.Constants.STATUS_PARTICULIER;
import static com.studiant.com.storage.Constants.STATUS_USER;

public class ConnexionActivity extends Activity implements ConnexionPresenter.View, FacebookCallback {


    @BindView(R.id.login_button_facebook)
    LoginButton loginButton;

    @BindView(R.id.buttonGoToProfil)
    Button goToProfil;

    CallbackManager callbackManager;
    ConnexionPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        ButterKnife.bind(this);

        mPresenter = new ConnexionPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ConnexionRepository()
        );

        callbackManager = CallbackManager.Factory.create();

        /*AccessToken token = AccessToken.getCurrentAccessToken();
        token.isExpired();
        */

        loginButton.setReadPermissions(Arrays.asList("email", "user_birthday"));
        LoginManager.getInstance().registerCallback(callbackManager, this);
    }

    @OnClick(R.id.buttonGoToProfil)
    void goToProfil() {
        Intent intentToLaunch = null;

        if (getIntent().getIntExtra(STATUS_USER, STATUS_PARTICULIER) == STATUS_PARTICULIER){
            intentToLaunch = new Intent(this, ProfilParticulierActivity.class);
            intentToLaunch.putExtra(CATEGORIE_ID_JOB, getIntent().getIntExtra(CATEGORIE_ID_JOB, 0));
        }else {
            intentToLaunch = new Intent(this, ProfilEtudiantActivity.class);
        }
        this.startActivity(intentToLaunch);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConnexionFacebookSuccess() {
        goToProfil();
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


    /*
     *Facebook Callback
     */
    @Override
    public void onSuccess(Object o) {
        Log.e("onSuccess","ok");
        if (AccessToken.getCurrentAccessToken() != null) {
            //mPresenter.setFacebookData();
            //RequestData();
            goToProfil();
        }
    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }
}
