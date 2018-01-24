package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardInteractor;
import com.studiant.com.domain.interactors.interfaces.GetPaiementInteractor;
import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetPaiementInteractorImpl extends AbstractInteractor implements GetPaiementInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private String mPostulantId;
    private String mJobId;

    public GetPaiementInteractorImpl(Executor threadExecutor,
                                     MainThread mainThread,
                                     Callback callback, UserRepository userRepository,
                                     String postulantId, String jobId) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
        mJobId = jobId;
        mPostulantId = postulantId;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onPaiementFailed("Card Retreval Failed");
            }
        });
    }

    private void postMessage(final Job job) {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
            mCallback.onPaiementSuccess(job);
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        try {
            Job job = mUserRepository.getPaiement(mPostulantId, mJobId);

            if (job.getId() == null)
                notifyError();
            else
                postMessage(job);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // we have retrieved our message, notify the UI on the main thread
        //postMessage(jobArrayList);
    }

}
