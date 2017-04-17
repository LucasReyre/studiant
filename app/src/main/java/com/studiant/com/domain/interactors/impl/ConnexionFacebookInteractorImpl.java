package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.ConnexionFacebookInteractor;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.storage.ConnexionRepository;

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

        // retrieve the message
        final String[] listItem = mConnexionRepository.getListCategoryMessage();

        // check if we have failed to retrieve our message
        if (listItem == null || listItem.length == 0) {

            // notify the failure on the main thread
            notifyError();

            return;
        }

        // we have retrieved our message, notify the UI on the main thread
        postMessage(listItem);
    }
}
