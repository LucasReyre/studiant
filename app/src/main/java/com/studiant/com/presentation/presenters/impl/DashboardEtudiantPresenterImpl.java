package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.GetJobsInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertPostulantInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.GetJobsInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertPostulantInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Postulant;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.PostulantRepository;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.DashboardEtudiantPresenter;
import com.studiant.com.presentation.presenters.interfaces.DashboardPresenter;

import java.util.ArrayList;

/**
 * Created by dmilicic on 12/13/15.
 */
public class DashboardEtudiantPresenterImpl extends AbstractPresenter implements DashboardEtudiantPresenter,
        GetJobsInteractor.Callback, InsertPostulantInteractor.Callback {

    private View mView;
    private JobRepository mJobRepository;
    private PostulantRepository mPostulantRepository;

    public DashboardEtudiantPresenterImpl(Executor executor, MainThread mainThread,
                                          View view, JobRepository jobRepository, PostulantRepository postulantRepository) {
        super(executor, mainThread);
        mView = view;
        mJobRepository = jobRepository;
        mPostulantRepository = postulantRepository;
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
        mView.showError(message);
    }


    @Override
    public void getJobs() {
        User user = null;
        GetJobsInteractor interactor = new GetJobsInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mJobRepository,
                user
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
                job,
                user

        );

        interactor.execute();
    }

    @Override
    public void onJobsRetrieve(ArrayList<Job> jobArrayList) {
        mView.onJobsRetrieve(jobArrayList);
    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.showError(error);

    }

    @Override
    public void onPostulantInsert() {

    }

    @Override
    public void onPostulantInsertFailed(String error) {

    }
}
