package com.studiant.com.presentation.ui.fragments.particulier;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.studiant.com.R;
import com.studiant.com.presentation.presenters.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.studiant.com.storage.Constants.INTENT_USER;

public class ProfilParticulierFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.firstLastNameTextView) TextView firstLastNameTextView;
    @BindView(R.id.mailTextView) TextView mailTextView;
    @BindView(R.id.numberTextView) TextView numberTextView;

    private User user;

    public ProfilParticulierFragment() {
        // Required empty public constructor
    }

    public static ProfilParticulierFragment newInstance(User user) {
        ProfilParticulierFragment fragment = new ProfilParticulierFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_particulier, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        firstLastNameTextView.setText(user.getFirstName()+" "+user.getLastName());
        mailTextView.setText(user.getEmail());
        numberTextView.setText(user.getTelephone());
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
    }
}
