package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertRibInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Rib;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.UserRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class InsertRibInteractorImpl extends AbstractInteractor implements InsertRibInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private Rib mRib;

    public InsertRibInteractorImpl(Executor threadExecutor,
                                   MainThread mainThread,
                                   Callback callback, UserRepository userRepository, Rib rib) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
        mRib = rib;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRibInsertFailed("Job Insert Failed");
            }
        });
    }

    private void postMessage(final Rib rib) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRibInsert(rib);
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        try {
            Rib rib = mUserRepository.insertRib(mRib);
            postMessage(rib);
        } catch (Exception e) {
            notifyError();
        }
    }

}
