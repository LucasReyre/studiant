package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.ChoosePostulantInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.PostulantRepository;

import static com.studiant.com.storage.Constants.STATUS_JOB_CLOSE;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class ChoosePostulantInteractorImpl extends AbstractInteractor implements ChoosePostulantInteractor {

    private Callback mCallback;
    private JobRepository mJobRepository;
    private Job mJob;
    private User mPostulant;

    public ChoosePostulantInteractorImpl(Executor threadExecutor,
                                         MainThread mainThread,
                                         Callback callback, JobRepository jobRepository, Job job, User postulant) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mJobRepository = jobRepository;
        mJob = job;
        mPostulant = postulant;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onInsertFailed("Postulant Insert Failed");
            }
        });
    }

    private void postMessage() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onPostulantInsert();
            }
        });
    }

    @Override
    public void run() {
        mJob.setStatutJob(STATUS_JOB_CLOSE);
        mJob.setPostulantId(mPostulant.getId());
        // retrieve the message
        mJobRepository.updateJob(mJob);
        // we have retrieved our message, notify the UI on the main thread
        postMessage();
    }

}
