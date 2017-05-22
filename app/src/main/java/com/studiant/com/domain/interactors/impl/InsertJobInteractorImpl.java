package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertUserInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.UserRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class InsertJobInteractorImpl extends AbstractInteractor implements InsertJobInteractor {

    private Callback mCallback;
    private JobRepository mJobRepository;
    private Job mJob;

    public InsertJobInteractorImpl(Executor threadExecutor,
                                   MainThread mainThread,
                                   Callback callback, JobRepository jobRepository, Job job) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mJobRepository = jobRepository;
        mJob = job;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onJobInsertFailed("Job Insert Failed");
            }
        });
    }

    private void postMessage() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onJobInsert();
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        mJobRepository.insertJob(mJob);
        // we have retrieved our message, notify the UI on the main thread
        postMessage();
    }

}
