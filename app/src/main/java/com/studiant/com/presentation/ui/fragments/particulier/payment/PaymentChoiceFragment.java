package com.studiant.com.presentation.ui.fragments.particulier.payment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studiant.com.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentChoiceFragment extends DialogFragment {

    private OnFragmentInteractionListener mListener;

    public PaymentChoiceFragment() {
        // Required empty public constructor
    }


    public static PaymentChoiceFragment newInstance() {
        PaymentChoiceFragment fragment = new PaymentChoiceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_choice, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
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

    @OnClick(R.id.CbButton)
    public void onCbButtonClick(){
        mListener.onClickPayment("CB");
        this.dismiss();
    }

    @OnClick(R.id.CESUbutton)
    public void onCESUButtonClick(){
        mListener.onClickPayment("CESU");
        this.dismiss();
    }

    @OnClick(R.id.EspeceButton)
    public void onESPECEButtonClick(){
        mListener.onClickPayment("ESPECES");
        this.dismiss();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onClickPayment(String choice);
    }
}
