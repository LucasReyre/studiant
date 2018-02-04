package com.studiant.com.presentation.ui.fragments.particulier;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Text;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.ProfilEtudiantPresenterImpl;
import com.studiant.com.presentation.presenters.impl.ProfilParticulierDashboardPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.ProfilParticulierDashboardPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.studiant.com.storage.Constants.INTENT_USER;

public class ProfilParticulierFragment extends Fragment implements ProfilParticulierDashboardPresenter.View {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.firstLastNameTextView) TextView firstLastNameTextView;
    @BindView(R.id.mailEditText)
    EditText mailTextView;
    @BindView(R.id.telephoneEditText) EditText numberTextView;

    private ProfilParticulierDashboardPresenter mPresenter;

    private User user;

    public ProfilParticulierFragment() {
        // Required empty public constructor
    }

    public static ProfilParticulierFragment newInstance(User user) {
        ProfilParticulierFragment fragment = new ProfilParticulierFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mPresenter = new ProfilParticulierDashboardPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new UserRepositoryImpl());

        return inflater.inflate(R.layout.fragment_profil_particulier, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        firstLastNameTextView.setText(user.getFirstName()+" "+user.getLastName());
        mailTextView.setText(user.getEmail());
        numberTextView.setText(user.getTelephone());
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

    @OnClick(R.id.saveButton)
    public void onSaveButtonClick(){
        user.setTelephone(numberTextView.getText().toString());
        user.setEmail(mailTextView.getText().toString());
        mPresenter.saveUser(user);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onUserUpdate() {
        Toast.makeText(getActivity(), "Modifications effectuées!",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserUpdateFailed() {

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
