package com.studiant.com.presentation.ui.fragments.etudiant;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.model.Rib;
import com.studiant.com.presentation.presenters.impl.AddRibPresenterImpl;
import com.studiant.com.presentation.presenters.impl.HistoriqueJobEtudiantPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.AddRibPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.INTENT_USER;


public class AddRibFragment extends Fragment implements AddRibPresenter.View{
    private static final String ARG_PARAM1 = "param1";

    @BindView(R.id.editTextIban) EditText editTextIban;
    @BindView(R.id.editTextBic) EditText editTextBic;
    @BindView(R.id.editTextNomPrenom) EditText editTextNomPrenom;
    @BindView(R.id.editTextAdresse) EditText editTextAdresse;
    @BindView(R.id.editTextCp) EditText editTextCp;
    @BindView(R.id.editTextVille) EditText editTextVille;

    private User user;
    private AddRibPresenter mPresenter;

    public AddRibFragment() {
        // Required empty public constructor
    }

    public static AddRibFragment newInstance(User user) {
        AddRibFragment fragment = new AddRibFragment();
        Bundle args = new Bundle();
        args.putSerializable(INTENT_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.user = (User) getArguments().getSerializable(INTENT_USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mPresenter = new AddRibPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl()
        );

        return inflater.inflate(R.layout.fragment_add_rib, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @OnClick(R.id.buttonValidateRib)
    public void onButtonValidateRib(){
        Rib rib = new Rib(user.getIdMangoPay(),
                            editTextIban.getText().toString(),
                            editTextBic.getText().toString(),
                            editTextNomPrenom.getText().toString(),
                            editTextAdresse.getText().toString(),
                            editTextVille.getText().toString(),
                            editTextCp.getText().toString());

        mPresenter.insertRib(rib);
    }

    @Override
    public void onRibInsert(Rib rib) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(this).commit();

    }

    @Override
    public void onRibError() {

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
}
