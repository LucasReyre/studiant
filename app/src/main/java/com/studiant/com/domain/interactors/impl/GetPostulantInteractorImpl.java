package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetJobsInteractor;
import com.studiant.com.domain.interactors.interfaces.GetPostulantInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.Postulant;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.PostulantRepository;

import java.util.ArrayList;
import java.util.Map;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetPostulantInteractorImpl extends AbstractInteractor implements GetPostulantInteractor {

    private Callback mCallback;
    private PostulantRepository mPostulantRepository;
    private String mUtilisateurId;
    private String mJobId;

    public GetPostulantInteractorImpl(Executor threadExecutor,
                                      MainThread mainThread,
                                      Callback callback, PostulantRepository postulantRepository,
                                      String utilisateurId, String jobId) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mPostulantRepository = postulantRepository;
        mUtilisateurId = utilisateurId;
        mJobId = jobId;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onPostulantRequestError("Job Retreval Failed");
            }
        });
    }

    private void postMessage(final Postulant postulant) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {

                if (postulant != null)
                    mCallback.onPostulantRetrieve();
                else
                    mCallback.onPostulantRetrievalFailed("No postulant Found");
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        Postulant postulant = null;

        postulant = mPostulantRepository.findPostulant(mJobId, mUtilisateurId);

        // we have retrieved our message, notify the UI on the main thread
        postMessage(postulant);
    }

}
