package com.studiant.com.presentation.presenters.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.ChooseInteractorImpl;
import com.studiant.com.domain.interactors.impl.GetCardInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertJobInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.GetCardInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertJobInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertUserInteractor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.domain.repository.JobRepository;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.AddJobPresenter;
import com.studiant.com.presentation.presenters.interfaces.ChoosePresenter;
import com.studiant.com.presentation.presenters.model.User;

import java.util.ArrayList;

/**
 * Created by dmilicic on 12/13/15.
 */
public class AddJobPresenterImpl extends AbstractPresenter implements AddJobPresenter,
        ChooseInteractor.Callback, InsertJobInteractor.Callback, GetCardInteractor.Callback {

    private View mView;
    private CategoryRepository mCategoryRepository;
    private JobRepository mJobRepository;
    private UserRepository mUserRepository;

    public AddJobPresenterImpl(Executor executor, MainThread mainThread,
                               View view, CategoryRepository categoryRepository, JobRepository jobRepository, UserRepository userRepository) {
        super(executor, mainThread);
        mView = view;
        mCategoryRepository = categoryRepository;
        mJobRepository = jobRepository;
        mUserRepository = userRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();
        // initialize the interactor
        ChooseInteractor interactor = new ChooseInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mCategoryRepository
        );

        // run the interactor
        interactor.execute();
        Log.d("AddJobPresenters", "resume");
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
    public void getCard(User user) {
        GetCardInteractor interactor = new GetCardInteractorImpl(
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
    public void onCardRetrieve() {
        System.out.println("onCardRetrieve");
        mView.displayPayment();

    }

    @Override
    public void onCardRetrievalFailed(String error) {
        System.out.println("onCardRetrievalFailed");
        mView.displayAddCard();
    }



    @Override
    public void onListCategoryRetrieved(String[] listItem) {
        Log.d("onListCategoryRetrieved", "ok");
        mView.hideProgress();
        mView.displayListCategorie(listItem);

    }


    @Override
    public void insertJob(Job job) {
        InsertJobInteractor interactor = new InsertJobInteractorImpl(
                mExecutor,
                mMainThread,
                this,
                mJobRepository,
                job
        );

        // run the interactor
        interactor.execute();

    }



    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        onError(error);
    }

    @Override
    public void onJobInsert() {
        mView.onJobInsert();
    }

    @Override
    public void onJobInsertFailed(String error) {

    }
}
