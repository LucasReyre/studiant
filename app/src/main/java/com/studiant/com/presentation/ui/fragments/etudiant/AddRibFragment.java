package com.studiant.com.presentation.ui.fragments.etudiant;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.model.Rib;
import com.studiant.com.presentation.presenters.impl.AddRibPresenterImpl;
import com.studiant.com.presentation.presenters.impl.HistoriqueJobEtudiantPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.AddRibPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.activities.etudiant.DashboardEtudiantActivity;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.INTENT_USER;


public class AddRibFragment extends Fragment implements AddRibPresenter.View{

    @BindView(R.id.editTextIban) EditText editTextIban;
    @BindView(R.id.editTextBic) EditText editTextBic;
    @BindView(R.id.editTextNomPrenom) EditText editTextNomPrenom;
    @BindView(R.id.editTextAdresse) EditText editTextAdresse;
    @BindView(R.id.editTextCp) EditText editTextCp;
    @BindView(R.id.editTextVille) EditText editTextVille;

    private ProgressDialog progressDialog;

    private User user;
    private AddRibPresenter mPresenter;
    private OnFragmentInteractionListener mListener;

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
        progressDialog = new ProgressDialog(getContext());
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
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mListener.onAddRibDisplay();

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

    @OnClick(R.id.buttonValidateRib)
    public void onButtonValidateRib(){
        Rib rib = new Rib(  user.getIdMangoPay(),
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
        user.setIban(editTextIban.getText().toString());
        user.setIdIban(rib.getId());
        System.out.println("onRibInsert : " + rib.getId());
        mPresenter.saveUser(user);

        ((DashboardEtudiantActivity) getActivity()).updateUser(user);

        mListener.onAddRibClose();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(this).commit();

    }

    @Override
    public void onRibError() {
        Toast.makeText(getActivity(), "Merci de vérifier votre saisie",
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void showProgress() {
        progressDialog.setMessage(getResources().getString(R.string.get_message_add_rib));
        progressDialog.show();

    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void showError(WSException e) {

    }

    public interface OnFragmentInteractionListener {
        void onAddRibDisplay();
        void onAddRibClose();
    }
}
