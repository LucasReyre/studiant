package com.studiant.com.presentation.presenters.impl;

import com.studiant.com.domain.executor.Executor;
import com.studiant.com.domain.executor.MainThread;
import com.studiant.com.domain.interactors.impl.GetProfileInteractorImpl;
import com.studiant.com.domain.interactors.impl.InsertUserInteractorImpl;
import com.studiant.com.domain.interactors.interfaces.ChooseInteractor;
import com.studiant.com.domain.interactors.interfaces.ChoosePostulantInteractor;
import com.studiant.com.domain.interactors.interfaces.GetProfileInteractor;
import com.studiant.com.domain.interactors.interfaces.InsertUserInteractor;
import com.studiant.com.domain.model.User;
import com.studiant.com.domain.repository.PostulantRepository;
import com.studiant.com.domain.repository.UserRepository;
import com.studiant.com.presentation.presenters.base.AbstractPresenter;
import com.studiant.com.presentation.presenters.interfaces.ChoosePostulantPresenter;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;

/**
 * Created by dmilicic on 12/13/15.
 */
public class ChoosePostulantPresenterImpl extends AbstractPresenter implements ChoosePostulantPresenter, ChoosePostulantInteractor.Callback{

    private View mView;
    private PostulantRepository mPostulantRepository;

    public ChoosePostulantPresenterImpl(Executor executor, MainThread mainThread,
                                        View view, PostulantRepository postulantRepository) {
        super(executor, mainThread);
        mView = view;
        mPostulantRepository = postulantRepository;
    }

    @Override
    public void resume() {

        mView.showProgress();
        // initialize the interactor
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
    public void onPostulantInsert() {

    }

    @Override
    public void onInsertFailed(String error) {

    }

    @Override
    public void choosePostulant(User user) {

    }
}
