package com.studiant.com.presentation.ui.fragments.particulier;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.ProfilParticulierPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.activities.common.MainActivity;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.CATEGORIE_ID_JOB;
import static com.studiant.com.storage.Constants.STATUS_CONNEXION_FACEBOOK;
import static com.studiant.com.storage.Constants.STATUS_CONNEXION_NORMAL;
import static com.studiant.com.storage.Constants.STATUS_PARTICULIER;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InscriptionParticulierFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InscriptionParticulierFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InscriptionParticulierFragment extends Fragment implements ProfilParticulierPresenter.View{

    private OnFragmentInteractionListener mListener;
    private int categorie;
    User user = null;

    @BindView(R.id.editTextFirstNameParticulier)
    EditText firstNameEditText;

    @BindView(R.id.editTextLastNameParticulier)
    EditText lastNameEditText;

    @BindView(R.id.editTextEmailParticulier)
    AutoCompleteTextView emailEditText;

    @BindView(R.id.editTextTelParticulier)
    AutoCompleteTextView telEditText;

    @BindView(R.id.buttonValidateParticulier)
    Button validateButton;

    private MainActivity mainActivity;

    private ProfilParticulierPresenter mPresenter;

    public InscriptionParticulierFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InscriptionParticulierFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InscriptionParticulierFragment newInstance(int categorie) {
        InscriptionParticulierFragment fragment = new InscriptionParticulierFragment();
        Bundle args = new Bundle();
        args.putInt(CATEGORIE_ID_JOB, categorie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categorie = getArguments().getInt(CATEGORIE_ID_JOB);
        }
        mainActivity = (MainActivity)getActivity();

        mPresenter = new ProfilParticulierPresenterImpl(
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
        return inflater.inflate(R.layout.fragment_inscription_particulier, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPresenter.resume();
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
    public void onProfileRetrieve(User mUser) {
        user = mUser;
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        emailEditText.setText(user.getEmail());
    }

    @Override
    public void onImageUpload(String urlImage) {
        
    }

    @OnClick(R.id.buttonValidateParticulier)
    void navigateToAddJob() {
        System.out.println("navigateToAddJob");

        if (user != null){
            user.setFirstName(firstNameEditText.getText().toString());
            user.setLastName(lastNameEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setTypeUser(STATUS_PARTICULIER);
            user.setTypeConnexion(STATUS_CONNEXION_FACEBOOK);
            user.setTelephone(telEditText.getText().toString());
            if (FirebaseInstanceId.getInstance().getToken() != null)
                user.setFirebaseToken(FirebaseInstanceId.getInstance().getToken());

            mPresenter.insertProfile(user);
        }else{
            User user = new User();
            user.setFirstName(firstNameEditText.getText().toString());
            user.setLastName(lastNameEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setTypeUser(STATUS_PARTICULIER);
            user.setTelephone(telEditText.getText().toString());
            user.setTypeConnexion(STATUS_CONNEXION_NORMAL);
            if (FirebaseInstanceId.getInstance().getToken() != null)
                user.setFirebaseToken(FirebaseInstanceId.getInstance().getToken());

            mPresenter.insertProfile(user);
        }

    }

    @Override
    public void onUserInsert(User user) {
        mPresenter.saveUser(user);
        mainActivity.transitionFragment(AddJobFragment.newInstance(user, categorie), R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

    }
}
