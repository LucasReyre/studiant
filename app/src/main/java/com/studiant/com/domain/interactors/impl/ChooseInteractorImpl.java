package com.studiant.com.domain.interactors.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.WelcomingInteractor;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.domain.repository.MessageRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class ChooseInteractorImpl extends AbstractInteractor implements ChooseInteractor{

    private Callback mCallback;
    private CategoryRepository mCategoryRepository;

    public ChooseInteractorImpl(Executor threadExecutor,
                                MainThread mainThread,
                                Callback callback, CategoryRepository categoryRepository) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mCategoryRepository = categoryRepository;
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
                mCallback.onListCategoryRetrieved(listItem);
            }
        });
    }

    @Override
    public void run() {

        // retrieve the message
        final String[] listItem = mCategoryRepository.getListCategoryMessage();

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
