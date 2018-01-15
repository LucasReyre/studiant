package com.studiant.com.presentation.ui.fragments.etudiant;

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
import android.widget.TextView;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.squareup.picasso.Picasso;
import com.studiant.com.R;
import com.studiant.com.presentation.presenters.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.studiant.com.storage.Constants.INTENT_USER;

public class ProfilEtudiantFragment extends Fragment {

    @BindView(R.id.profilEtudiantscrollView)
    NestedScrollView profilEtudiantscrollView;

    @BindView(R.id.imageViewProfilPicture)
    ImageView profilPictureImageView;

    @BindView(R.id.firstNameTextView)
    TextView firstNameEditText;

    @BindView(R.id.mailTextView)
    TextView mailTextView;


    @BindView(R.id.phoneEditText)
    TextView phoneEditText;


    @BindView(R.id.editTextDescription)
    TextView descriptionEditText;

    User user;

    public static ProfilEtudiantFragment newInstance(User user) {

        ProfilEtudiantFragment fragment = new ProfilEtudiantFragment();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profil_etudiant, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        bindView();

        //setup materialviewpager
        MaterialViewPagerHelper.registerScrollView(getActivity(), profilEtudiantscrollView);

    }

    private void bindView() {
        Log.d("bindView", " "+user.getFirstName() + " "+user.getDescription() + " " + user.isPermis());
        Log.d("bindView", " "+user.getTelephone() + " " + user.getDiplome());
        Picasso.with(getContext()).load(user.getProfilePicture()).into(profilPictureImageView);
        firstNameEditText.setText(user.getFirstName() + " " + user.getLastName());
        mailTextView.setText(user.getEmail());
        phoneEditText.setText(user.getTelephone());
        descriptionEditText.setText(user.getDescription());
    }
}
