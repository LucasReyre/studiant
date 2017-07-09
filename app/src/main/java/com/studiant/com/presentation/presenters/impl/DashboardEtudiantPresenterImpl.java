package com.studiant.com.presentation.presenters.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.GetJobsInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertPostulantInteractorImpl;
import com.studiant.com.domain.interactors.impl.SendNotificationInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.GetJobsInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertPostulantInteractor;
import com.studiant.com.domain.interactors.interfaces.SendNotificationInteractor;
import com.studiant.com.domain.repository.GCMMessageRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.PostulantRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.DashboardEtudiantPresenter;
import com.studiant.com.presentation.presenters.interfaces.DashboardPresenter;
import com.studiant.com.storage.network.WSException;

import java.util.ArrayList;

/**
 * Created by dmilicic on 12/13/15.
 */
public class DashboardEtudiantPresenterImpl extends AbstractPresenter implements DashboardEtudiantPresenter,
        GetJobsInteractor.Callback, InsertPostulantInteractor.Callback,  SendNotificationInteractor.Callback{

    private View mView;
    private JobRepository mJobRepository;
    private PostulantRepository mPostulantRepository;
    private GCMMessageRepository mGcmRepository;

    public DashboardEtudiantPresenterImpl(Executor executor, MainThread mainThread,
                                          View view, JobRepository jobRepository, PostulantRepository postulantRepository, GCMMessageRepository gcmMessageRepository) {
        super(executor, mainThread);
        mView = view;
        mJobRepository = jobRepository;
        mPostulantRepository = postulantRepository;
        mGcmRepository = gcmMessageRepository;
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
    public void getJobs() {
        User user = null;
        GetJobsInteractor interactor = new GetJobsInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mJobRepository,
                PresentationModelConverter.convertToUserDomainModel(user)
        );

        interactor.execute();
    }

    @Override
    public void insertPostulant(Job job, User user) {
        InsertPostulantInteractor interactor = new InsertPostulantInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mPostulantRepository,
                PresentationModelConverter.convertToJobDomainModel(job),
                PresentationModelConverter.convertToUserDomainModel(user)
        );

        interactor.execute();
    }


    @Override
    public void onJobsRetrieve(ArrayList<com.studiant.com.domain.model.Job> jobArrayList) {
        mView.onJobsRetrieve(PresentationModelConverter.convertToArrayListPresenterJobModel(jobArrayList));

    }

    @Override
    public void onRetrievalFailed(String error) {
    }

    @Override
    public void onPostulantInsert(Job job) {
        mView.showProgress();
        SendNotificationInteractor interactor = new SendNotificationInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mGcmRepository,
                PresentationModelConverter.convertToJobDomainModel(job)
        );

        interactor.execute();
    }

    @Override
    public void onPostulantInsertFailed(WSException error) {
        mView.hideProgress();
        mView.showError(error);

    }
}
