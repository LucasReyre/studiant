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

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class ChoosePostulantInteractorImpl extends AbstractInteractor implements ChoosePostulantInteractor {

    private Callback mCallback;
    private PostulantRepository mPostulantRepository;
    private Job mJob;
    private User mPostulant;

    public ChoosePostulantInteractorImpl(Executor threadExecutor,
                                         MainThread mainThread,
                                         Callback callback, PostulantRepository postulantRepository, Job job, User postulant) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mPostulantRepository = postulantRepository;
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
        // retrieve the message
        mPostulantRepository.choosePostulant(mPostulant, mJob);
        // we have retrieved our message, notify the UI on the main thread
        postMessage();
    }

}
