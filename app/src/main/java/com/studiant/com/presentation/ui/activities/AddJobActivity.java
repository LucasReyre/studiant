package com.studiant.com.presentation.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.impl.AddJobPresenterImpl;
import com.studiant.com.presentation.presenters.interfaces.AddJobPresenter;
import com.studiant.com.presentation.ui.components.MDatePicker;
import com.studiant.com.presentation.ui.components.MTimePicker;
import com.studiant.com.storage.ChooseCategoryRepository;
import com.studiant.com.threading.MainThreadImpl;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static com.studiant.com.storage.Constants.CATEGORIE_ID_JOB;

public class AddJobActivity extends Activity implements AddJobPresenter.View {

    @Bind(R.id.spinner_categorie)
    MaterialSpinner spinner;

    @Bind(R.id.textViewDate)
    TextView dateTextView;

    @Bind(R.id.textViewTime)
    TextView timeTextView;

    private AddJobPresenter mPresenter;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        ButterKnife.bind(this);
        context = this;

        mPresenter = new AddJobPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new ChooseCategoryRepository()
        );


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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
