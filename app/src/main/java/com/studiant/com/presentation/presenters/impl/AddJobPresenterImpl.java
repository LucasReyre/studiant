package com.studiant.com.presentation.presenters.impl;

import android.util.Log;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.ChooseInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.repository.CategoryRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.AddJobPresenter;
import com.studiant.com.presentation.presenters.interfaces.ChoosePresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class AddJobPresenterImpl extends AbstractPresenter implements AddJobPresenter,
        ChooseInteractor.Callback {

    private View mView;
    private CategoryRepository mCategoryRepository;

    public AddJobPresenterImpl(Executor executor, MainThread mainThread,
                               View view, CategoryRepository categoryRepository) {
        super(executor, mainThread);
        mView = view;
        mCategoryRepository = categoryRepository;
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
        mView.showError(message);
    }


    @Override
    public void onListCategoryRetrieved(String[] listItem) {
        Log.d("onListCategoryRetrieved", "ok");
        mView.hideProgress();
        mView.displayListCategorie(listItem);

    }

    @Override
    public void onRetrievalFailed(String error) {
        mView.hideProgress();
        onError(error);
    }
}
