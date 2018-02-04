package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.GetPasswordInteractorImpl;
import com.studiant.com.domain.interactors.impl.LoginInteractorImpl;
import com.studiant.com.domain.interactors.impl.SaveUserInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.GetPasswordInteractor;
import com.studiant.com.domain.interactors.interfaces.LoginInteractor;
import com.studiant.com.domain.interactors.interfaces.SaveUserInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.interfaces.LoginPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenter, LoginInteractor.Callback, GetPasswordInteractor.Callback {

    private View mView;
    private UserRepository mUserRepository;

    public LoginPresenterImpl(Executor executor, MainThread mainThread,
                              View view, UserRepository userRepository) {
        super(executor, mainThread);
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        //mView.showError(message);
    }

    @Override
    public void login(String mail, String password) {

        mView.showProgress();

        LoginInteractor interactor = new LoginInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                mail,
                password
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void getMdp(String mail) {
        mView.showProgress();
        GetPasswordInteractor interactor = new GetPasswordInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                mail
        );

        // run the interactor
        interactor.execute();
    }

    public void saveUser(User user){
        System.out.println("save : "+user.getTelephone());
        SaveUserInteractor interactor = new SaveUserInteractorImpl(
                mExecutor,
                mMainThread,
                PresentationModelConverter.convertToUserPresenterModel(user)
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void onPasswordRetrieve() {
        mView.hideProgress();
    }

    @Override
    public void onLoginSuccess(User user) {
        saveUser(user);
        mView.onLoginSuccess(user);
    }

    @Override
    public void onLoginFailed(String error) {
        mView.hideProgress();
    }

    @Override
    public void onPasswordRetrievalFailed(String error) {
        mView.hideProgress();
    }
}
