
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.Job;

import java.util.ArrayList;


public interface GetJobsInteractor extends Interactor {

    interface Callback {
        void onJobsRetrieve(ArrayList<Job> job);
        void onRetrievalFailed(String error);
    }
}
