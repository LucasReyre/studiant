package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;

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
import com.studiant.com.presentation.presenters.model.User;
import com.studiant.com.presentation.presenters.impl.AddJobPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.AddJobPresenter;
import com.studiant.com.presentation.ui.components.MDatePicker;
import com.studiant.com.presentation.ui.components.MTimePicker;
import com.studiant.com.storage.ChooseCategoryRepository;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.facebook.login.widget.ProfilePictureView.TAG;
import static com.studiant.com.storage.Constants.CATEGORIE_ID_JOB;
import static com.studiant.com.storage.Constants.INTENT_USER;
import static com.studiant.com.storage.Constants.STATUS_CONNEXION_FACEBOOK;
import static com.studiant.com.storage.Constants.STATUS_ETUDIANT;
import static com.studiant.com.storage.Constants.STATUS_USER;

public class AddJobActivity extends Activity implements AddJobPresenter.View, PlaceSelectionListener {

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

    private Job job;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        ButterKnife.bind(this);
        context = this;
        job = new Job();
        user = (User) getIntent().getSerializableExtra(INTENT_USER);

        mPresenter = new AddJobPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ChooseCategoryRepository(),
                new JobRepositoryImpl()
        );

        autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(this);
        autocompleteFragment.setBoundsBias(new LatLngBounds(
                new LatLng(42.244785, -2.208252),
                new LatLng(51.138001, 7.943115)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
    }

    @OnTouch(R.id.textViewDate)
        boolean onDatePickerRequired(){
        new MDatePicker(context, dateTextView.getId());
        return false;
    }

    @OnTouch(R.id.textViewTime)
        boolean onTimePickerRequired(){
        new MTimePicker(context, timeTextView.getId());
        return false;
    }

    @Override
    public void displayListCategorie(String[] listItem) {
        spinner.setItems(listItem);
        spinner.setSelectedIndex(getIntent().getIntExtra(CATEGORIE_ID_JOB,0));
        Log.d("displayListCategorie", "- "+ getIntent().getIntExtra(CATEGORIE_ID_JOB,0));
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onJobInsert() {
        Intent intent = new Intent(this, DashboardParticulierActivity.class);
        intent.putExtra(INTENT_USER, user);
        this.startActivity(intent);
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

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onPlaceSelected(Place place) {
        Log.i(TAG, "Place: " + place.getName());
        job.setAdresse(place.getAddress().toString());

    }

    @Override
    public void onError(Status status) {
        Log.i(TAG, "An error occurred: " + status);

    }
}
