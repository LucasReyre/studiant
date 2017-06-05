package com.studiant.com.domain.interactors.impl;


import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.base.AbstractInteractor;
import com.studiant.com.domain.interactors.interfaces.ConnexionFacebookInteractor;
import com.studiant.com.domain.interactors.interfaces.GetProfileInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;

import java.util.Calendar;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class GetProfileInteractorImpl extends AbstractInteractor implements GetProfileInteractor {

    private GetProfileInteractor.Callback mCallback;
    private UserRepository mUserRepository;

    public GetProfileInteractorImpl(Executor threadExecutor,
                                    MainThread mainThread,
                                    Callback callback, UserRepository userRepository) {
        super(threadExecutor, mainThread);

        mCallback = callback;
        mUserRepository = userRepository;
    }

    private void notifyError() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onRetrievalFailed("Nothing to welcome you with :(");
            }
        });
    }

    private void postMessage(final User user) {
        String[] birth = user.getBirthday().split("/");
        user.setAge(getAge(Integer.parseInt(birth[2]), Integer.parseInt(birth[0]), Integer.parseInt(birth[1])));

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onProfileRetrieve(PresentationModelConverter.convertToUserPresenterModel(user));
            }
        });
    }

    @Override
    public void run() {
        // retrieve the message
        final User user = mUserRepository.getConnectedProfile();
        // we have retrieved our message, notify the UI on the main thread
        postMessage(user);
    }

    /**
     * Method to extract the user's age from the entered Date of Birth.
     *
     *
     * @return ageS String The user's age in years based on the supplied DoB.
     */
    private int getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        return Integer.parseInt(ageS);
    }
}
