package com.studiant.com.presentation.presenters.interfaces;

import com.studiant.com.domain.model.Postulant;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.presenters.base.BasePresenter;
import com.studiant.com.presentation.ui.BaseView;

import java.util.ArrayList;
import java.util.Map;


public interface DashboardEtudiantPresenter extends BasePresenter {

    void getJobs();
    void getPostulant(String utilisateurId, String jobId);
    void getJobsWithFilter(Map<String,String> myMap );
    void insertPostulant(Job job, User user);

    interface View extends BaseView {
        void showProgressPostulant();
        void onJobsRetrieve(ArrayList<Job> jobArrayList);
        void onJobRetrieveFail();
        void onPostulantRetrieve();
        void onPostulantRetrieveFail();
    }
}
