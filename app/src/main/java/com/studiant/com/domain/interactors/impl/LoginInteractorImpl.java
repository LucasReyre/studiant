package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.LoginInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private String mail;
    private String password;

    public LoginInteractorImpl(Executor threadExecutor,
                               MainThread mainThread,
                               Callback callback,
                               UserRepository userRepository,
                               String mail,
                               String password) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
        this.mail = mail;
        this.password= password;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                //mCallback.onRetrievalFailed("Nothing to welcome you with :(");
                mCallback.onLoginFailed("Merci de vérifier vos informations");
            }
        });
    }

    private void postMessage(final User user) {

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onLoginSuccess(user);
            }
        });
    }

    @Override
    public void run() {

        try {
           User user = mUserRepository.loginUser(mail, password);
            System.out.println("save login success"+user.getTelephone());
            postMessage(user);
        } catch (Exception e) {
            notifyError();
            e.printStackTrace();
        }
    }


}
