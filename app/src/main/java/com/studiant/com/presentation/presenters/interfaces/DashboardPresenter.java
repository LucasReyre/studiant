package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.domain.model.Job;
import com.studiant.com.domain.model.User;
import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.ui.BaseView;

import java.util.ArrayList;


public interface DashboardPresenter extends BasePresenter {

    void getJobsByUser(User user);
    void getJobs();

    interface View extends BaseView {
        void onJobsRetrieve(ArrayList<Job> jobArrayList);
    }
}
