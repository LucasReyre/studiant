package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.CloseJobInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetHistoriqueJobsInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetJobsInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertPostulantInteractorImpl;
import com.studiant.com.domain.interactors.impl.SendNotificationInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.CloseJobInteractor;
import com.studiant.com.domain.interactors.interfaces.GetHistoriqueJobsInteractor;
import com.studiant.com.domain.interactors.interfaces.GetJobsInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertPostulantInteractor;
import com.studiant.com.domain.interactors.interfaces.SendNotificationInteractor;
import com.studiant.com.domain.repository.GCMMessageRepository;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.PostulantRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.interfaces.DashboardEtudiantPresenter;
import com.studiant.com.presentation.presenters.interfaces.HistoriqueJobEtudiantPresenter;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.storage.network.WSException;

import java.util.ArrayList;

/**
 * Created by dmilicic on 12/13/15.
 */
public class HistoriqueJobEtudiantPresenterImpl extends AbstractPresenter implements HistoriqueJobEtudiantPresenter,
        GetHistoriqueJobsInteractor.Callback, CloseJobInteractor.Callback {

    private View mView;
    private JobRepository mJobRepository;

    public HistoriqueJobEtudiantPresenterImpl(Executor executor, MainThread mainThread,
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
    public void onJobsRetrieve(ArrayList<com.studiant.com.domain.model.Job> jobArrayList) {
        mView.onJobsRetrieve(PresentationModelConverter.convertToArrayListPresenterJobModel(jobArrayList));

    }

    @Override
    public void onRetrievalFailed(String error) {
    }


    @Override
    public void getHistoriqueJobs(User user) {

        GetHistoriqueJobsInteractor interactor = new GetHistoriqueJobsInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mJobRepository,
                PresentationModelConverter.convertToUserDomainModel(user)
        );

        interactor.execute();

    }

    @Override
    public void closeJob(Job job) {
        mView.showProgress();
        CloseJobInteractor interactor = new CloseJobInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mJobRepository,
                PresentationModelConverter.convertToJobDomainModel(job)
        );

        interactor.execute();
    }

    @Override
    public void onJobClose() {
        mView.hideProgress();
        mView.onJobClose();
    }

    @Override
    public void onCloseFailed(String error) {
        mView.hideProgress();
    }
}
