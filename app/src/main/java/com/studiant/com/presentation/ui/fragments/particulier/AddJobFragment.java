package com.studiant.com.presentation.ui.fragments.particulier;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.domain.model.Job;
import com.studiant.com.presentation.presenters.impl.AddJobPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.AddJobPresenter;
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.ui.activities.particulier.DashboardParticulierActivity;
import com.studiant.com.presentation.ui.components.MDatePicker;
import com.studiant.com.presentation.ui.components.MTimePicker;
import com.studiant.com.storage.ChooseCategoryRepository;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.studiant.com.storage.Constants.CATEGORIE_ID_JOB;
import static com.studiant.com.storage.Constants.INTENT_USER;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddJobFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddJobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddJobFragment extends Fragment implements AddJobPresenter.View, PlaceSelectionListener {

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.spinner_categorie)
    MaterialSpinner spinner;

    @BindView(R.id.editTextDescription)
    TextView descriptionTextView;

    @BindView(R.id.editTextPrice)
    TextView priceTextView;

    @BindView(R.id.textViewDate)
    TextView dateTextView;

    @BindView(R.id.textViewTime)
    TextView timeTextView;

    @BindView(R.id.buttonAddJob)
    Button addJobButton;

    PlaceAutocompleteFragment autocompleteFragment;

    private AddJobPresenter mPresenter;

    private User user;

    private int categorie;

    private Job job;


    public AddJobFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddJobFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddJobFragment newInstance(User user, int categorie) {
        AddJobFragment fragment = new AddJobFragment();
        Bundle args = new Bundle();
        args.putSerializable(INTENT_USER, user);
        args.putSerializable(CATEGORIE_ID_JOB, categorie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(INTENT_USER);
            categorie = getArguments().getInt(CATEGORIE_ID_JOB);
        }

        job = new Job();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_job, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mPresenter = new AddJobPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ChooseCategoryRepository(),
                new JobRepositoryImpl(),
                new UserRepositoryImpl()
        );
        mPresenter.resume();

        autocompleteFragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(this);
        autocompleteFragment.setBoundsBias(new LatLngBounds(
                new LatLng(42.244785, -2.208252),
                new LatLng(51.138001, 7.943115)));

    }

    @OnClick(R.id.buttonAddJob)
    void onClickAddJob() {

        job.setDescription(descriptionTextView.getText().toString());
        job.setPrix(priceTextView.getText().toString());
        job.setDate(dateTextView.getText().toString());
        job.setHeure(timeTextView.getText().toString());
        job.setUtilisateurId(user.getId());

        mPresenter.insertJob(job);
    }

    @OnTouch(R.id.textViewDate)
    boolean onDatePickerRequired(){
        new MDatePicker(getContext(), dateTextView.getId());
        return false;
    }

    @OnTouch(R.id.textViewTime)
    boolean onTimePickerRequired(){
        new MTimePicker(getContext(), timeTextView.getId());
        return false;
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
    public void onPlaceSelected(Place place) {
        job.setAdresse(place.getAddress().toString());
    }

    @Override
    public void onError(Status status) {

    }

    @Override
    public void displayListCategorie(String[] listItem) {
        spinner.setItems(listItem);
        spinner.setSelectedIndex(categorie);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onJobInsert() {
        Intent intent = new Intent(getActivity(), DashboardParticulierActivity.class);
        intent.putExtra(INTENT_USER, user);
        this.startActivity(intent);
    }



    @OnClick(R.id.buttonCancelJob)
    public void onCancelAddJobClick(){
        Intent intent = new Intent(getActivity(), DashboardParticulierActivity.class);
        intent.putExtra(INTENT_USER, user);
        this.startActivity(intent);
    }

    @Override
    public void displayAddCard() {

    }

    @Override
    public void displayPayment() {

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
