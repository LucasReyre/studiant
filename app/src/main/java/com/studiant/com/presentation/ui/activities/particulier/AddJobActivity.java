package com.studiant.com.presentation.ui.activities.particulier;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.studiant.com.presentation.ui.fragments.particulier.payment.AddCbFragment;
import com.studiant.com.presentation.ui.fragments.particulier.payment.ConfirmPaymentFragment;
import com.studiant.com.presentation.ui.fragments.particulier.payment.PaymentChoiceFragment;
import com.studiant.com.storage.ChooseCategoryRepository;
import com.studiant.com.storage.impl.JobRepositoryImpl;
import com.studiant.com.storage.impl.UserRepositoryImpl;
import com.studiant.com.storage.network.WSException;
import com.studiant.com.threading.MainThreadImpl;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.facebook.login.widget.ProfilePictureView.TAG;
import static com.studiant.com.storage.Constants.CATEGORIE_ID_JOB;
import static com.studiant.com.storage.Constants.INTENT_USER;

public class AddJobActivity extends AppCompatActivity implements AddJobPresenter.View,
                                                                PlaceSelectionListener,
                                                                PaymentChoiceFragment.OnFragmentInteractionListener,
                                                                AddCbFragment.OnFragmentInteractionListener,
                                                                ConfirmPaymentFragment.OnFragmentInteractionListener{

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

    @BindView(R.id.fragmentFramelayout)
    FrameLayout fragmentFrameLayout;

    PlaceAutocompleteFragment autocompleteFragment;

    private AddJobPresenter mPresenter;
    private ProgressDialog progressDialog;
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
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        System.out.println("addjob : " + user.getFirstName() + " " + user.getIdMangoPay());
        mPresenter = new AddJobPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ChooseCategoryRepository(),
                new JobRepositoryImpl(),
                new UserRepositoryImpl()
        );

        progressDialog = new ProgressDialog(this);
        //progressDialog.setCancelable(false);

        autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        autocompleteFragment.setOnPlaceSelectedListener(this);
        autocompleteFragment.setHint("Choisir un lieu");
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
        job.setCategorie(listItem[0]);
        Log.d("displayListCategorie", "- "+ getIntent().getIntExtra(CATEGORIE_ID_JOB,0));
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                job.setCategorie(item);
                //Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onJobInsert() {
        Intent intent = new Intent(this, DashboardParticulierActivity.class);
        intent.putExtra(INTENT_USER, user);
        this.startActivity(intent);
    }


    @OnClick(R.id.buttonCancelJob)
    public void onCancelAddJobClick(){
        Intent intent = new Intent(this, DashboardParticulierActivity.class);
        intent.putExtra(INTENT_USER, user);
        this.startActivity(intent);
    }

    @OnClick(R.id.buttonAddJob)
    void onClickAddJob() {

        FragmentManager fm = getSupportFragmentManager();
        PaymentChoiceFragment paymentChoiceFragment= PaymentChoiceFragment.newInstance();
        paymentChoiceFragment.show(fm, paymentChoiceFragment.getTag());

        job.setDescription(descriptionTextView.getText().toString());
        job.setPrix(priceTextView.getText().toString());
        job.setDate(dateTextView.getText().toString());
        job.setHeure(timeTextView.getText().toString());
        job.setUtilisateurId(user.getId());

        //mPresenter.insertJob(job);
    }

    @Override
    public void onClickPayment(String choice) {
        job.setMoyenPayment(choice);

        switch (choice){
            case "CB":
                mPresenter.getCard(user);
            break;
            case "CESU":
                displayPayment();
                break;
            case "ESPECES":
                displayPayment();
                break;
        }
    }

    @Override
    public void displayAddCard() {

        fragmentFrameLayout.setVisibility(View.VISIBLE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AddCbFragment addCbFragment= AddCbFragment.newInstance(user);
        ft.add(R.id.fragmentFramelayout, addCbFragment, addCbFragment.getClass().getName()).commit();

        /*addCbFragment.show(fm, addCbFragment.getTag());
        addCbFragment*/

    }

    @Override
    public void onCardRegister() {
        displayPayment();
    }

    @Override
    public void displayPayment() {
        fragmentFrameLayout.setVisibility(View.VISIBLE);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ConfirmPaymentFragment confirmPaymentFragment= ConfirmPaymentFragment.newInstance(user, job);
        ft.replace(R.id.fragmentFramelayout, confirmPaymentFragment, confirmPaymentFragment.getClass().getName()).commit();
    }

    @Override
    public void onPaymentConfirm() {
        fragmentFrameLayout.setVisibility(View.GONE);
        mPresenter.insertJob(job);

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



    @Override
    public void onPlaceSelected(Place place) {
        Geocoder mGeocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses = null;
            addresses = mGeocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                job.setCity(addresses.get(0).getLocality());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        job.setLat(String.valueOf(place.getLatLng().latitude));
        job.setLng(String.valueOf(place.getLatLng().longitude));
        job.setAdresse(place.getAddress().toString());

    }

    @Override
    public void onError(Status status) {
        Log.i(TAG, "An error occurred: " + status);

    }
}
