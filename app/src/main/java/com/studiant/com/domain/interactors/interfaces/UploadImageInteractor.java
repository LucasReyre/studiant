
package com.studiant.com.domain.interactors.interfaces;


import com.studiant.com.domain.interactors.base.Interactor;
import com.studiant.com.domain.model.User;


public interface UploadImageInteractor extends Interactor {

    interface Callback {
        void onImageUpload(String image);
        void onRetrievalFailed(String error);
    }
}
