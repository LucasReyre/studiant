package com.studiant.com.presentation.ui.fragments.particulier.payment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.mangopay.android.sdk.Callback;
import com.mangopay.android.sdk.MangoPay;
import com.mangopay.android.sdk.model.CardRegistration;
import com.mangopay.android.sdk.model.MangoCard;
import com.mangopay.android.sdk.model.MangoSettings;
import com.mangopay.android.sdk.model.exception.MangoException;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.model.CardReg;
import com.studiant.com.presentation.presenters.impl.AddCbPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.AddCbPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.INTENT_USER;

public class AddCbFragment extends DialogFragment implements AddCbPresenter.View{

    private User user;

    @BindView(R.id.numeroCbEditText) EditText numeroCbEditText;
    @BindView(R.id.mmEditText) EditText mmEditText;
    @BindView(R.id.yyEditText) EditText yyEditText;
    @BindView(R.id.ccvEditText) EditText ccvEditText;

    private OnFragmentInteractionListener mListener;
    private AddCbPresenter mPresenter;

    private MangoPay mangopay;

    public AddCbFragment() {
        // Required empty public constructor
    }

    public static AddCbFragment newInstance(User user) {
        AddCbFragment fragment = new AddCbFragment();
        Bundle args = new Bundle();
        args.putSerializable(INTENT_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(INTENT_USER);
            System.out.println("user : "+user.getIdMangoPay());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_cb, container, false);

        mPresenter = new AddCbPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl()
        );

        mPresenter.getCardReg(user);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onGetCardReg(CardReg cardReg) {
        System.out.println("onGetCardReg " +cardReg.getAccessKey());

        // holds the card registration data
        MangoSettings mSettings = new MangoSettings(cardReg.getBaseUrl(),cardReg.getClientId(),cardReg.getCardPreregistrationId(),cardReg.getCardRegistrationUrl(), cardReg.getPreregistrationData(),cardReg.getAccessKey());

        // using the default constructor where you should pass the android context and  the settings object
        mangopay = new MangoPay(getContext(), mSettings);

    }

    @OnClick(R.id.addCbButton)
    public void insertCbButton(){
        // holds the card information
        String dateExpiration = mmEditText.getText().toString() +""+yyEditText.getText().toString();

        MangoCard mCard = new MangoCard(numeroCbEditText.getText().toString(), dateExpiration, ccvEditText.getText().toString());

// register card method with callback
        mangopay.registerCard(mCard, new Callback() {
            @Override public void success(CardRegistration cardRegistration) {
                Log.d("log", cardRegistration.toString());
                mListener.onCardRegister();
            }

            @Override
            public void failure(MangoException error) {
                System.out.println("fail "+error.getMessage());
            }
        });
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(WSException e) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onCardRegister();
    }
}
