package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetProfileInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertUserInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;

import java.util.Calendar;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class InsertUserInteractorImpl extends AbstractInteractor implements InsertUserInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private User mUser;

    public InsertUserInteractorImpl(Executor threadExecutor,
                                    MainThread mainThread,
                                    Callback callback, UserRepository userRepository, User user) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
        mUser = user;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
            }
        });
    }

    private void postMessage(final User user) {
        System.out.println("post");
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onUserInsert(user);
            }
        });
    }

    @Override
    public void run() {
        User user = null;
        try {
            user = mUserRepository.insertUser(mUser);
            System.out.println("userInserted" + user.getId());
            postMessage(user);
        } catch (Exception e) {
            notifyError();
        }
    }

}
