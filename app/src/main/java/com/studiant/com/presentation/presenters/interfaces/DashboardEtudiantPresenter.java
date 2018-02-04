package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.ui.BaseView;

import java.util.ArrayList;
import java.util.Map;


public interface DashboardEtudiantPresenter extends BasePresenter {

    void getJobs();
    void getJobsWithFilter(Map<String,String> myMap );
    void insertPostulant(Job job, User user);

    interface View extends BaseView {
        void onJobsRetrieve(ArrayList<Job> jobArrayList);
        void onJobRetrieveFail();
    }
}
