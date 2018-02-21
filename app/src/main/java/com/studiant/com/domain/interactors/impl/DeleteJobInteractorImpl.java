package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.DeleteJobInteractor;
import com.studiant.com.domain.interactors.interfaces.UpdateUserInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.UserRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class DeleteJobInteractorImpl extends AbstractInteractor implements DeleteJobInteractor {

    private Callback mCallback;
    private JobRepository mJobRepository;
    private String mIdJob;

    public DeleteJobInteractorImpl(Executor threadExecutor,
                                   MainThread mainThread,
                                   Callback callback, JobRepository jobRepository, String idJob) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mJobRepository = jobRepository;
        mIdJob = idJob;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onDeleteFailed("Une erreure est survenue");
            }
        });
    }

    private void postMessage() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onJobDelete();
            }
        });
    }

    @Override
    public void run() {
        User user = null;
        try {
            mJobRepository.deleteJob(mIdJob);
            postMessage();
        } catch (Exception e) {
            notifyError();
        }
    }

}
