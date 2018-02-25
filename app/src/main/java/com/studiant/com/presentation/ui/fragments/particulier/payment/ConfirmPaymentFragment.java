package com.studiant.com.presentation.ui.fragments.particulier.payment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.presentation.presenters.impl.ConfirmPaymentPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ConfirmPaymentPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.INTENT_JOB;
import static com.studiant.com.storage.Constants.INTENT_USER;

public class ConfirmPaymentFragment extends Fragment implements ConfirmPaymentPresenter.View{


    private OnFragmentInteractionListener mListener;
    @BindView(R.id.confirmPaymentButton) Button confirmPaymentButton;
    @BindView(R.id.amountTextView) TextView amountTextView;

    private ProgressDialog progressDialog;
    private User user;
    private Job job;
    private ConfirmPaymentPresenter mPresenter;

    public ConfirmPaymentFragment() {
        // Required empty public constructor
    }


    public static ConfirmPaymentFragment newInstance(User user, Job job) {
        ConfirmPaymentFragment fragment = new ConfirmPaymentFragment();
        Bundle args = new Bundle();
        args.putSerializable(INTENT_USER, user);
        args.putSerializable(INTENT_JOB, job);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(INTENT_USER);
            job = (Job) getArguments().getSerializable(INTENT_JOB);
        }

        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setCancelable(false);

        mPresenter = new ConfirmPaymentPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl()
        );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_payment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        amountTextView.setText(job.getPrix() +" €");

    }

    @OnClick(R.id.confirmPaymentButton)
    public void confirmPayment(){
        switch (job.getMoyenPayment()){
            case "CB":
                mPresenter.directePayment(user.getIdMangoPay(), job.getPrix());
                break;
            case "CESU":
                mListener.onPaymentConfirm();
                break;
            case "ESPECES":
                mListener.onPaymentConfirm();
                break;
        }

    }

    @Override
    public void showProgress() {
        progressDialog.setMessage(getResources().getString(R.string.get_message_wait));
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }


    @Override
    public void onPaymentAccepted() {
        mListener.onPaymentConfirm();
    }

    @Override
    public void onPaymentRefused() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(this).commit();
        Toast.makeText(this.getContext(), "Une erreure est survenue lors du paiement !",
                Toast.LENGTH_LONG).show();
        mListener.needCbRegister();
        System.out.println("payment refused");
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
        void onPaymentConfirm();
        void needCbRegister();
    }
}
