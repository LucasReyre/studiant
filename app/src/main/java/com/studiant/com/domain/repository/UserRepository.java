package com.studiant.com.domain.repository;

import com.studiant.com.domain.model.Card;
import com.studiant.com.domain.model.CardReg;
import com.studiant.com.domain.model.PayIn;
import com.studiant.com.domain.model.Rib;
import com.studiant.com.domain.model.User;

/**
 * A repository that is responsible for getting our welcome message.
 */
public interface UserRepository {

    User insertUser(User user) throws Exception;
    User loginUser(String mail, String password) throws Exception;
    Card getCardUser(User user) throws Exception;
    Rib insertRib(Rib rib) throws Exception;
    CardReg getCardReg(User user) throws Exception;
    User getConnectedFacebookProfile();
    User getConnectedNormalProfile();
    User getProfileFromConnectedUser();
    String uploadImage(String image) throws Exception;
    PayIn directPayment(String mangoPayId, String amount) throws Exception;

}
