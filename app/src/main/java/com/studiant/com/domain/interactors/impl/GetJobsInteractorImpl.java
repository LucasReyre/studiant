package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetJobsInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;

import java.util.ArrayList;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetJobsInteractorImpl extends AbstractInteractor implements GetJobsInteractor {

    private Callback mCallback;
    private JobRepository mJobRepository;
    private User mUser;

    public GetJobsInteractorImpl(Executor threadExecutor,
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
                mCallback.onJobsRetrieve(jobArrayList);
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        ArrayList<Job> jobArrayList = mJobRepository.getJobsByUser(mUser);
        // we have retrieved our message, notify the UI on the main thread
        postMessage(jobArrayList);
    }

}
