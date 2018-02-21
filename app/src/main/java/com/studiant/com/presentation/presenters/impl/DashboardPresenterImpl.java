package com.studiant.com.presentation.presenters.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.ChooseInteractorImpl;
import com.studiant.com.domain.interactors.impl.DeleteJobInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetJobsInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertJobInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.DeleteJobInteractor;
import com.studiant.com.domain.interactors.interfaces.GetJobsInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.interfaces.AddJobPresenter;
import com.studiant.com.presentation.presenters.interfaces.DashboardPresenter;

import java.util.ArrayList;

/**
 * Created by dmilicic on 12/13/15.
 */
public class DashboardPresenterImpl extends AbstractPresenter implements DashboardPresenter,
        GetJobsInteractor.Callback, DeleteJobInteractor.Callback {

    private View mView;
    private JobRepository mJobRepository;

    public DashboardPresenterImpl(Executor executor, MainThread mainThread,
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
    public void getJobsByUser(User user) {
        GetJobsInteractor interactor = new GetJobsInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mJobRepository,
                PresentationModelConverter.convertToUserDomainModel(user),
                null
        );

        interactor.execute();
    }

    @Override
    public void deleteJob(String idJob) {
        mView.showProgress();
        DeleteJobInteractor interactor = new DeleteJobInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mJobRepository,
                idJob
        );

        interactor.execute();
    }

    @Override
    public void onJobsRetrieve(ArrayList<Job> jobArrayList) {
        mView.onJobsRetrieve(PresentationModelConverter.convertToArrayListPresenterJobModel(jobArrayList));
    }

    @Override
    public void onRetrievalFailed(String error) {
       // mView.showError(error);

    }

    @Override
    public void onJobDelete() {
        mView.hideProgress();
        mView.onJobDelete();
    }

    @Override
    public void onDeleteFailed(String error) {
        mView.hideProgress();
    }
}
