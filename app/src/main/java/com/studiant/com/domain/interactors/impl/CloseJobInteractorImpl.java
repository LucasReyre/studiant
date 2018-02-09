package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.ChoosePostulantInteractor;
import com.studiant.com.domain.interactors.interfaces.CloseJobInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;

import static com.studiant.com.storage.Constants.STATUS_JOB_CLOSE;
import static com.studiant.com.storage.Constants.STATUS_JOB_POSTULANT_CHOOSE;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class CloseJobInteractorImpl extends AbstractInteractor implements CloseJobInteractor {

    private Callback mCallback;
    private JobRepository mJobRepository;
    private Job mJob;

    public CloseJobInteractorImpl(Executor threadExecutor,
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
                mCallback.onCloseFailed("close Failed");
            }
        });
    }

    private void postMessage() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onJobClose();
            }
        });
    }

    @Override
    public void run() {
        mJob.setStatutJob(STATUS_JOB_CLOSE);
        // retrieve the message
        mJobRepository.updateJob(mJob);
        // we have retrieved our message, notify the UI on the main thread
        postMessage();
    }

}
