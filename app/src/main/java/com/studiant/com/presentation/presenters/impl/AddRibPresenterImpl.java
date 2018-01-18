package com.studiant.com.presentation.presenters.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.ChooseInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetCardInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertJobInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertRibInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertRibInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Rib;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.AddJobPresenter;
import com.studiant.com.presentation.presenters.interfaces.AddRibPresenter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * Created by dmilicic on 12/13/15.
 */
public class AddRibPresenterImpl extends AbstractPresenter implements AddRibPresenter, InsertRibInteractor.Callback {

    private View mView;
    private UserRepository mUserRepository;

    public AddRibPresenterImpl(Executor executor, MainThread mainThread,
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
    public void insertRib(Rib rib) {
        mView.showProgress();
        // initialize the interactor
        InsertRibInteractor interactor = new InsertRibInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                rib
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void onRibInsert(Rib rib) {
        mView.onRibInsert(rib);
    }

    @Override
    public void onRibInsertFailed(String error) {
        mView.onRibError();
    }
}
