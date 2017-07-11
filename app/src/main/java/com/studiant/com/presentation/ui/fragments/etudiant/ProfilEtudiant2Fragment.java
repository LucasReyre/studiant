package com.studiant.com.presentation.ui.fragments.etudiant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.ProfilParticulierPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.activities.etudiant.DashboardEtudiantActivity;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.INTENT_USER;
import static com.studiant.com.storage.Constants.STATUS_CONNEXION_FACEBOOK;
import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilEtudiant2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfilEtudiant2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilEtudiant2Fragment extends Fragment implements ProfilParticulierPresenter.View{
    @BindView(R.id.imageViewProfilPicture)
    ImageView profilPictureImageView;

    @BindView(R.id.editTextFirstName)
    EditText firstNameEditText;

    @BindView(R.id.editTextLastName)
    EditText lastNameEditText;

    @BindView(R.id.editTextEmail)
    EditText emailEditText;

    @BindView(R.id.editTextDiplome)
    EditText diplomeEditText;

    @BindView(R.id.editTextDescription)
    EditText descriptionEditText;

    @BindView(R.id.switchPermis)
    Switch permisSwitch;

    User user;
    private ProfilParticulierPresenter mPresenter;
    private OnFragmentInteractionListener mListener;

    public ProfilEtudiant2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfilEtudiant2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilEtudiant2Fragment newInstance() {
        ProfilEtudiant2Fragment fragment = new ProfilEtudiant2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mPresenter = new ProfilParticulierPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl() {
                }
        );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil_etudiant2, container, false);
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

    @OnClick(R.id.buttonValidateEtudiant)
    void onClickValidate() {

        if (user != null){
            user.setDescription(descriptionEditText.getText().toString());
            user.setDiplome(diplomeEditText.getText().toString());
            user.setPermis(permisSwitch.isChecked());
            user.setTypeUser(STATUS_ETUDIANT);
            user.setTypeConnexion(STATUS_CONNEXION_FACEBOOK);

            if (FirebaseInstanceId.getInstance().getToken() != null)
                user.setFirebaseToken(FirebaseInstanceId.getInstance().getToken());

            Log.d("Token ", " - "+FirebaseInstanceId.getInstance().getToken());

            mPresenter.insertProfile(user);
        }

    }

    @Override
    public void onProfileRetrieve(User mUser) {
        user = mUser;
        firstNameEditText.setText(user.getFirstName());
        lastNameEditText.setText(user.getLastName());
        emailEditText.setText(user.getEmail());
        Picasso.with(getContext()).load(user.getProfilePicture()).into(profilPictureImageView);
    }

    @Override
    public void onUserInsert(User user) {
        Intent intent = new Intent(getActivity(), DashboardEtudiantActivity.class);
        intent.putExtra(INTENT_USER, user);
        this.startActivity(intent);
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
