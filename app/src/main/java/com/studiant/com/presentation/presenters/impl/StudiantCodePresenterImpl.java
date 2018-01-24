package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.GetPaiementInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertRibInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.GetPaiementInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertRibInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Rib;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.interfaces.AddRibPresenter;
import com.studiant.com.presentation.presenters.interfaces.StudiantCodePresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class StudiantCodePresenterImpl extends AbstractPresenter implements StudiantCodePresenter, GetPaiementInteractor.Callback {

    private View mView;
    private UserRepository mUserRepository;

    public StudiantCodePresenterImpl(Executor executor, MainThread mainThread,
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
    public void getPaiement(String postulantId, String jobId) {
        mView.showProgress();
        // initialize the interactor
        GetPaiementInteractor interactor = new GetPaiementInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                postulantId,
                jobId
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void onPaiementSuccess(Job job) {
        mView.onPaiementSuccess(PresentationModelConverter.convertToJobPresenterModel(job));
    }

    @Override
    public void onPaiementFailed(String error) {

    }
}
