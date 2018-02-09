package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.BaseView;

import java.util.ArrayList;


public interface HistoriqueJobEtudiantPresenter extends BasePresenter {

    void getHistoriqueJobs(User user);
    void closeJob(Job job);

    interface View extends BaseView {
        void onJobsRetrieve(ArrayList<Job> jobArrayList);
        void onJobClose();
    }
}
