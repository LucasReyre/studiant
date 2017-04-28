package com.studiant.com.domain.interactors.impl;

import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.ConnexionFacebookInteractor;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.storage.ConnexionRepository;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class ConnexionFacebookInteractorImpl extends AbstractInteractor implements ConnexionFacebookInteractor {

    private Callback mCallback;
    private ConnexionRepository mConnexionRepository;

    public ConnexionFacebookInteractorImpl(Executor threadExecutor,
                                   MainThread mainThread,
                                   Callback callback, ConnexionRepository connexionRepository) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mConnexionRepository = connexionRepository;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
            }
        });
    }

    private void postMessage(final String[] listItem) {

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onConnexionFacebookSuccess();
            }
        });
    }

    @Override
    public void run() {

        RequestData();
        // retrieve the message
        final String[] listItem = mConnexionRepository.getListCategoryMessage();

        // we have retrieved our message, notify the UI on the main thread
        postMessage(listItem);
    }

    private void RequestData() {

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                final JSONObject json = response.getJSONObject();
                Profile profile = Profile.getCurrentProfile();
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
}
