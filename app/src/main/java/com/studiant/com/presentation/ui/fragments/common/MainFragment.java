package com.studiant.com.presentation.ui.fragments.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.studiant.com.R;
import com.studiant.com.presentation.ui.activities.common.MainActivity;
import com.studiant.com.presentation.ui.fragments.etudiant.ProfilEtudiant2Fragment;
import com.studiant.com.presentation.ui.fragments.particulier.CategoriesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;

public class MainFragment extends Fragment {

    @BindView(R.id.buttonParticular) Button btn_particulier;
    @BindView(R.id.buttonStudiant) Button btn_etudiant;

    private MainActivity mainActivity;

    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @OnClick(R.id.buttonParticular)
    void navigateToParticulierForm() {
        mainActivity.transitionFragment(CategoriesFragment.newInstance(), R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @OnClick(R.id.buttonStudiant)
    void navigateToConnexionActivity() {
        //mainActivity.transitionFragment(ConnexionFragment.newInstance(STATUS_ETUDIANT), R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
        mainActivity.transitionFragment(ProfilEtudiant2Fragment.newInstance(false), R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @OnClick(R.id.loginTextView)
    void onLoginClick(){
        mainActivity.transitionFragment(LoginFragment.newInstance(), R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
    }


    public interface OnFragmentInteractionListener {
    }
}
