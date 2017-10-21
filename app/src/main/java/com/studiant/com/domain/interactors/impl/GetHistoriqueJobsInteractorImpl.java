package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetHistoriqueJobsInteractor;
import com.studiant.com.domain.interactors.interfaces.GetJobsInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;

import java.util.ArrayList;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetHistoriqueJobsInteractorImpl extends AbstractInteractor implements GetHistoriqueJobsInteractor {

    private Callback mCallback;
    private JobRepository mJobRepository;
    private User mUser;

    public GetHistoriqueJobsInteractorImpl(Executor threadExecutor,
                                           MainThread mainThread,
                                           Callback callback, JobRepository jobRepository, User user) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mJobRepository = jobRepository;
        mUser = user;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Job Retreval Failed");
            }
        });
    }

    private void postMessage(final ArrayList<Job> jobArrayList) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {

                if (jobArrayList.size()>0)
                    mCallback.onJobsRetrieve(jobArrayList);
                else
                    mCallback.onRetrievalFailed("No Job Found");
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        ArrayList<Job> jobArrayList = new ArrayList<Job>();
        jobArrayList = mJobRepository.getHistoriqueJobsByUser(mUser);

        // we have retrieved our message, notify the UI on the main thread
        postMessage(jobArrayList);
    }

}
