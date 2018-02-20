package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.UpdateUserFirebaseTokenInteractor;
import com.studiant.com.domain.interactors.interfaces.UpdateUserInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class UpdateUserFirebaseTokenInteractorImpl extends AbstractInteractor implements UpdateUserFirebaseTokenInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private User mUser;

    public UpdateUserFirebaseTokenInteractorImpl(Executor threadExecutor,
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
                mCallback.onUpdateFailed("Nothing to welcome you with :(");
            }
        });
    }

    private void postMessage(final User user) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onUserFirebaseTokenUpdate(user);
            }
        });
    }

    @Override
    public void run() {
        User user = null;
        try {
            user = mUserRepository.updateUserFirebaseToken(mUser);
            System.out.println("userFirebaseToken update" + user.getId());
            postMessage(user);
        } catch (Exception e) {
            notifyError();
        }
    }

}
