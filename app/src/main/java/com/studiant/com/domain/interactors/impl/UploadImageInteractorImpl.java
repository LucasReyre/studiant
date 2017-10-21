package com.studiant.com.domain.interactors.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertUserInteractor;
import com.studiant.com.domain.interactors.interfaces.UploadImageInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class UploadImageInteractorImpl extends AbstractInteractor implements UploadImageInteractor {

    private Callback mCallback;
    private UserRepository mUserRepository;
    private String mImage;

    public UploadImageInteractorImpl(Executor threadExecutor,
                                     MainThread mainThread,
                                     Callback callback, UserRepository userRepository, String image) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
        mImage = image;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
            }
        });
    }

    private void postMessage(final String urlPicture) {
        System.out.println("post");
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onImageUpload(urlPicture);
            }
        });
    }

    @Override
    public void run() {
        String urlPicture = null;
        try {
            urlPicture= mUserRepository.uploadImage(mImage);
            if (urlPicture != null){
                postMessage(urlPicture);
            }else {
                notifyError();
            }
        } catch (Exception e) {
            notifyError();
        }
    }

}
