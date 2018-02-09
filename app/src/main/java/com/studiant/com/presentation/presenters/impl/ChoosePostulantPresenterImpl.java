package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.ChoosePostulantInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertPostulantInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.ChoosePostulantInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertPostulantInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.PostulantRepository;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.interfaces.ChoosePostulantPresenter;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class ChoosePostulantPresenterImpl extends AbstractPresenter implements ChoosePostulantPresenter, ChoosePostulantInteractor.Callback{

    private View mView;
    private JobRepository mJobRepository;

    public ChoosePostulantPresenterImpl(Executor executor, MainThread mainThread,
                                        View view, JobRepository jobRepository) {
        super(executor, mainThread);
        mView = view;
        mJobRepository = jobRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();
        // initialize the interactor
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
    public void onPostulantInsert() {
        mView.hideProgress();
        mView.onPostulantChoosed();
    }

    @Override
    public void onInsertFailed(String error) {
        mView.hideProgress();
    }

    @Override
    public void choosePostulant(User user, Job job) {
        mView.showProgress();
        ChoosePostulantInteractor interactor = new ChoosePostulantInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mJobRepository,
                job,
                user
        );

        interactor.execute();

    }
}
