package com.studiant.com.presentation.ui.fragments.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.studiant.com.R;
import com.studiant.com.Utils.SecurityUtils;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.model.User;
import com.studiant.com.presentation.presenters.converters.PresentationModelConverter;
import com.studiant.com.presentation.presenters.impl.HistoriqueJobEtudiantPresenterImpl;
import com.studiant.com.presentation.presenters.impl.LoginPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.LoginPresenter;
import com.studiant.com.presentation.ui.activities.common.MainActivity;
import com.studiant.com.presentation.ui.activities.etudiant.DashboardEtudiantActivity;
import com.studiant.com.presentation.ui.activities.particulier.DashboardParticulierActivity;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.INTENT_USER;
import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;
import static com.studiant.com.storage.Constants.STATUS_PARTICULIER;

public class LoginFragment extends Fragment implements LoginPresenter.View {

    private OnFragmentInteractionListener mListener;
    @BindView(R.id.editTextEmail) EditText editTextEmail;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    private LoginPresenter mPresenter;
    private MainActivity mainActivity;

    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mainActivity = (MainActivity) this.getActivity();
        mPresenter = new LoginPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl()
        );

    }


    @OnClick(R.id.buttonLogin)
    void onLoginClick(){
        System.out.println();
        mPresenter.login(editTextEmail.getText().toString(), SecurityUtils.hashToSha512(editTextPassword.toString()));
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
    public void onLoginSuccess(User user) {
        Intent intent = new Intent();

        if (user.getTypeUser() == STATUS_ETUDIANT)
            intent = new Intent(this.getContext(), DashboardEtudiantActivity.class);
        else if(user.getTypeUser() == STATUS_PARTICULIER)
            intent = new Intent(this.getContext(), DashboardParticulierActivity.class);

        intent.putExtra(INTENT_USER, PresentationModelConverter.convertToUserPresenterModel(user));
        startActivity(intent);
    }


    @Override
    public void onLoginFailed() {

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
