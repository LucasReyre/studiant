package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.DeleteDataUserInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetPasswordInteractorImpl;
import com.studiant.com.domain.interactors.impl.LoginInteractorImpl;
import com.studiant.com.domain.interactors.impl.SaveUserInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.DeleteDataUserInteractor;
import com.studiant.com.domain.interactors.interfaces.GetPasswordInteractor;
import com.studiant.com.domain.interactors.interfaces.LoginInteractor;
import com.studiant.com.domain.interactors.interfaces.SaveUserInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.interfaces.LoginPresenter;
import com.studiant.com.presentation.presenters.interfaces.SettingPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class SettingPresenterImpl extends AbstractPresenter implements SettingPresenter {

    private View mView;

    public SettingPresenterImpl(Executor executor, MainThread mainThread,
                                View view) {
        super(executor, mainThread);
        mView = view;
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
    public void logout() {

        DeleteDataUserInteractor interactor = new DeleteDataUserInteractorImpl(
                mExecutor,
                mMainThread
        );

        // run the interactor
        interactor.execute();

        mView.onLogOut();
    }

}
