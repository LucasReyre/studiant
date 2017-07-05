package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.interactors.interfaces.SendNotificationInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.repository.GCMMessageRepository;
import com.studiant.com.domain.repository.JobRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class SendNotificationInteractorImpl extends AbstractInteractor implements SendNotificationInteractor {

    private Callback mCallback;
    private GCMMessageRepository mGcmMessageRepository;
    private Job mJob;

    public SendNotificationInteractorImpl(Executor threadExecutor,
                                          MainThread mainThread,
                                          Callback callback, GCMMessageRepository gcmRepository, Job job) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mGcmMessageRepository = gcmRepository;
        mJob = job;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                //mCallback.onJobInsertFailed("Job Insert Failed");
            }
        });
    }

    private void postMessage() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                //mCallback.onJobInsert();
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        mGcmMessageRepository.sendNotification(mJob);
        // we have retrieved our message, notify the UI on the main thread
        //postMessage();
    }

}
