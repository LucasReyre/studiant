package com.studiant.com.domain.interactors.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertPostulantInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Postulant;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.PostulantRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.storage.network.WSException;

import static com.studiant.com.storage.Constants.STATUS_POSTULANT_NOT_CHOOSE;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class InsertPostulantInteractorImpl extends AbstractInteractor implements InsertPostulantInteractor {

    private Callback mCallback;
    private PostulantRepository mPostulantRepository;
    private Job mJob;
    private User mUser;

    public InsertPostulantInteractorImpl(Executor threadExecutor,
                                         MainThread mainThread,
                                         Callback callback, PostulantRepository postulantRepository, Job job, User user) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mPostulantRepository = postulantRepository;
        mJob = job;
        mUser = user;
    }

    private void notifyError(final WSException e) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onPostulantInsertFailed(e);
            }
        });
    }

    private void postMessage() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onPostulantInsert(PresentationModelConverter.convertToJobPresenterModel(mJob));
            }
        });
    }

    @Override
    public void run() {

        Postulant postulant = new Postulant(null, getCurrentTimestamp(), STATUS_POSTULANT_NOT_CHOOSE, mUser, mJob);
        // retrieve the message
        try {
            mPostulantRepository.insertPostulant(postulant);
            postMessage();
        } catch (WSException e) {
            notifyError(e);
        }
        // we have retrieved our message, notify the UI on the main thread
    }

    public String getCurrentTimestamp(){
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

}
