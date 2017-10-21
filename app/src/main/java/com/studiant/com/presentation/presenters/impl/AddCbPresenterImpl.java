package com.studiant.com.presentation.presenters.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.ChooseInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetCardInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetCardRegInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertJobInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardRegInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.CardReg;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.AddCbPresenter;
import com.studiant.com.presentation.presenters.interfaces.AddJobPresenter;
import com.studiant.com.presentation.presenters.model.User;

/**
 * Created by dmilicic on 12/13/15.
 */
public class AddCbPresenterImpl extends AbstractPresenter implements AddCbPresenter, GetCardRegInteractor.Callback {

    private View mView;
    private UserRepository mUserRepository;

    public AddCbPresenterImpl(Executor executor, MainThread mainThread,
                              View view, UserRepository userRepository) {
        super(executor, mainThread);
        mView = view;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {
            }


    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {
        //mView.showError(message);
    }

    @Override
    public void insertCard(Card card) {

    }

    @Override
    public void getCardReg(User user) {
        GetCardRegInteractor interactor = new GetCardRegInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mUserRepository,
                user
        );

        // run the interactor
        interactor.execute();
    }

    @Override
    public void onCardRegRetrieve(CardReg cardReg) {
        mView.onGetCardReg(cardReg);
    }

    @Override
    public void onCardRegRetrievalFailed(String error) {

    }
}
