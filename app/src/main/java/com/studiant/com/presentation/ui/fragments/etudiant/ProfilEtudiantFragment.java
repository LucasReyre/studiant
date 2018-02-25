package com.studiant.com.presentation.ui.fragments.etudiant;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.squareup.picasso.Picasso;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.AddRibPresenterImpl;
import com.studiant.com.presentation.presenters.impl.ProfilEtudiantPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ProfilEtudiantPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

import static com.studiant.com.storage.Constants.INTENT_USER;

public class ProfilEtudiantFragment extends Fragment implements ProfilEtudiantPresenter.View {

    @BindView(R.id.profilEtudiantscrollView)
    NestedScrollView profilEtudiantscrollView;

    @BindView(R.id.imageViewProfilPicture)
    ImageView profilPictureImageView;

    @BindView(R.id.firstNameTextView)
    TextView firstNameEditText;

    @BindView(R.id.mailTextView)
    TextView mailTextView;

    @BindView(R.id.addRibButton)
    Button addRibButton;

    @BindView(R.id.diplomeEditText)
    TextView diplomeEditText;

    @BindView(R.id.phoneEditText)
    TextView phoneEditText;


    @BindView(R.id.editTextDescription)
    TextView descriptionEditText;

    private ProgressDialog progressDialog;
    User user;

    private ProfilEtudiantPresenter mPresenter;

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
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = new ProfilEtudiantPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl());

        return inflater.inflate(R.layout.fragment_profil_etudiant, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        bindView();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //setup materialviewpager
        MaterialViewPagerHelper.registerScrollView(getActivity(), profilEtudiantscrollView);

        if(user.getIdIban() != null){
            addRibButton.setBackgroundColor(ContextCompat.getColor(this.getContext(),R.color.warm_grey));
        }

    }


    @OnClick(R.id.addRibButton)
    public void onAddRibClick(){
        if(user.getIdIban() != null){
            Toast.makeText(getActivity(), "Un RIB à déja été renseigné",
                    Toast.LENGTH_LONG).show();
        }else{
            AddRibFragment addRibFragment = AddRibFragment.newInstance(user);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.add(android.R.id.content, addRibFragment, addRibFragment.getClass().getName()).commit();
        }
    }


    @OnClick(R.id.getMoneybutton)
    public void onGetMoneyButtonClick(){
        if(user.getIdIban() == null){
            Toast.makeText(getActivity(), "Merci de renseigner un RIB",
            Toast.LENGTH_LONG).show();
        }else{
            mPresenter.getMoney(user);
        }
    }

    private void bindView() {
        Log.d("bindView", " "+user.getFirstName() + " "+user.getDescription() + " " + user.isPermis());
        Picasso.with(getContext()).load(user.getProfilePicture()).into(profilPictureImageView);
        firstNameEditText.setText(user.getFirstName() + " " + user.getLastName());
        mailTextView.setText(user.getEmail());
        phoneEditText.setText(user.getTelephone());
        descriptionEditText.setText(user.getDescription());
        diplomeEditText.setText(user.getDiplome());
    }

    @Override
    public void onMoneyRetrieve() {
        Toast.makeText(getActivity(), "Votre argent à bien été transféré",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMoneyRetrievedFail() {
        Toast.makeText(getActivity(), "Vous n'avez pas d'argent à transféré",
                Toast.LENGTH_LONG).show();
    }

    public void showProgress() {
        progressDialog.setMessage(getResources().getString(R.string.get_message_wait));
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @OnClick(R.id.saveButton)
    public void onSaveClick(){
        user.setDiplome(diplomeEditText.getText().toString());
        user.setTelephone(phoneEditText.getText().toString());
        user.setDescription(descriptionEditText.getText().toString());
        System.out.println("onSaveClick : diplome "+user.getDiplome());
        System.out.println("onSaveClick : telephone "+user.getTelephone());
        mPresenter.saveUser(user);
    }

    @Override
    public void onUserUpdate() {
        Toast.makeText(getActivity(), "Profil sauvegardé !",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserUpdateFailed() {
        Toast.makeText(getActivity(), "Une erreur est survenue !",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(WSException e) {

    }
}
