package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.interactors.interfaces.ConnexionInteractor;
import com.studiant.com.presentation.presenters.impl.ChoosePresenterImpl;
import com.studiant.com.presentation.presenters.impl.ConnexionPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ChoosePresenter;
import com.studiant.com.presentation.presenters.interfaces.ConnexionPresenter;
import com.studiant.com.storage.ChooseCategoryRepository;
import com.studiant.com.storage.ConnexionRepository;
import com.studiant.com.threading.MainThreadImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConnexionActivity extends Activity implements ConnexionPresenter.View {


    @Bind(R.id.login_button_facebook)
    LoginButton loginButton;

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
        //LoginButton loginButton = (LoginButton) this.findViewById(R.id.login_button_facebook);

        callbackManager = CallbackManager.Factory.create();

        /*AccessToken token = AccessToken.getCurrentAccessToken();
        token.isExpired();
        Profile profile = Profile.getCurrentProfile();

        Log.e("Info ", token.isExpired() + " " + profile.getFirstName() + " " + profile.getLastName());*/
        loginButton.setReadPermissions(Arrays.asList("email"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Log.e("onSuccess","ok");
                        if (AccessToken.getCurrentAccessToken() != null) {
                            mPresenter.setFacebookData();
                            RequestData();
                        }

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void RequestData() {

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object,GraphResponse response) {

                final JSONObject json = response.getJSONObject();



                try {
                    if(json != null){

                        Log.e("lucas", json.getString("name"));
                        Log.e("lucas", json.getString("email"));
                        Log.e("lucas", json.getString("id"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onConnexionFacebookSuccess() {

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
