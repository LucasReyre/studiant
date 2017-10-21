package com.studiant.com.presentation.ui.fragments.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.ConnexionPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ConnexionPresenter;
import com.studiant.com.presentation.ui.activities.common.MainActivity;
import com.studiant.com.presentation.ui.activities.etudiant.ProfilEtudiantActivity;
import com.studiant.com.presentation.ui.activities.particulier.ProfilParticulierActivity;
import com.studiant.com.presentation.ui.fragments.etudiant.ProfilEtudiant2Fragment;
import com.studiant.com.presentation.ui.fragments.particulier.CategoriesFragment;
import com.studiant.com.presentation.ui.fragments.particulier.ProfilParticulierFragment;
import com.studiant.com.storage.ConnexionRepository;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.CATEGORIE_ID_JOB;
import static com.studiant.com.storage.Constants.STATUS_PARTICULIER;
import static com.studiant.com.storage.Constants.STATUS_USER;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConnexionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConnexionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnexionFragment extends Fragment implements FacebookCallback {

    // TODO: Rename and change types of parameters
    private int statusUser;
    private int categorie ;

    private OnFragmentInteractionListener mListener;
    private MainActivity mainActivity;

    @BindView(R.id.login_button_facebook)
    LoginButton loginButton;

    private ConnexionPresenter mPresenter;
    CallbackManager callbackManager;

    public ConnexionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param statusUser Parameter 1.
     * @return A new instance of fragment ConnexionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConnexionFragment newInstance(int statusUser, int categorie) {
        ConnexionFragment fragment = new ConnexionFragment();
        Bundle args = new Bundle();
        args.putInt(STATUS_USER, statusUser);
        args.putInt(CATEGORIE_ID_JOB, categorie);
        fragment.setArguments(args);
        return fragment;
    }

    public static ConnexionFragment newInstance(int statusUser) {
        ConnexionFragment fragment = new ConnexionFragment();
        Bundle args = new Bundle();
        args.putInt(STATUS_USER, statusUser);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            statusUser = getArguments().getInt(STATUS_USER);
            if (statusUser == STATUS_PARTICULIER)
                categorie = getArguments().getInt(CATEGORIE_ID_JOB);
        }
        mainActivity = (MainActivity)getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connexion, container, false);
        ButterKnife.bind(this, view);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email", "user_birthday"));
        LoginManager.getInstance().registerCallback(callbackManager, this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

    void goToProfil(boolean fromFacebook) {
       Intent intentToLaunch = null;

        if (statusUser == STATUS_PARTICULIER){
            mainActivity.transitionFragment(ProfilParticulierFragment.newInstance(categorie), R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
        }else {
            mainActivity.transitionFragment(ProfilEtudiant2Fragment.newInstance(fromFacebook), R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
        }
//        this.startActivity(intentToLaunch);
    }

    @Override
    public void onSuccess(Object o) {
        if (AccessToken.getCurrentAccessToken() != null) {
            //mPresenter.setFacebookData();
            //RequestData();
            goToProfil(true);
        }
    }

    @OnClick(R.id.inscriptionParticulierButton)
    void onInscriptionParticulierClick(){
        goToProfil(false);
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onError(FacebookException error) {

    }

    public interface OnFragmentInteractionListener {
    }
}
