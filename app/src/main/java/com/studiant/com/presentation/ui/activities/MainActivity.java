package com.studiant.com.presentation.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.studiant.com.R;
import com.studiant.com.domain.executor.impl.ThreadExecutor;
import com.studiant.com.presentation.presenters.MainPresenter;
import com.studiant.com.presentation.presenters.MainPresenter.View;
import com.studiant.com.presentation.presenters.impl.MainPresenterImpl;
import com.studiant.com.storage.WelcomeMessageRepository;
import com.studiant.com.threading.MainThreadImpl;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View {

    @Bind(R.id.welcome_textview)
    TextView mWelcomeTextView;

    @Bind(R.id.carouselView)
    CarouselView carouselView;

    private MainPresenter mPresenter;

    int[] sampleImages = {R.drawable.test1, R.drawable.test2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

        // create a presenter for this view
        mPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new WelcomeMessageRepository()
        );
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    @Override
    protected void onResume() {
        super.onResume();

        // let's start welcome message retrieval when the app resumes
        mPresenter.resume();
    }

    @Override
    public void showProgress() {
        mWelcomeTextView.setText("Retrieving...");
    }

    @Override
    public void hideProgress() {
        Toast.makeText(this, "Retrieved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        mWelcomeTextView.setText(message);
    }

    @Override
    public void displayWelcomeMessage(String msg) {
        mWelcomeTextView.setText(msg);
    }
}
