package com.studiant.com.presentation.ui.fragments.etudiant;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.AddRibPresenterImpl;
import com.studiant.com.presentation.presenters.impl.StudiantCodePresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.AddRibPresenter;
import com.studiant.com.presentation.presenters.interfaces.StudiantCodePresenter;
import com.studiant.com.presentation.presenters.model.Job;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.ButterKnife;

public class StudiantCodeFragment extends Fragment implements StudiantCodePresenter.View {

    private OnFragmentInteractionListener mListener;
    private StudiantCodePresenter mPresenter;

    public StudiantCodeFragment() {
        // Required empty public constructor
    }

    public static StudiantCodeFragment newInstance(String param1, String param2) {
        StudiantCodeFragment fragment = new StudiantCodeFragment();
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
        mPresenter = new StudiantCodePresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl()
        );

        return inflater.inflate(R.layout.fragment_studiant_code, container, false);
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

    @Override
    public void onPaiementSuccess(Job job) {

    }

    @Override
    public void onPaiementError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(WSException e) {

    }


    public interface OnFragmentInteractionListener {
    }
}
